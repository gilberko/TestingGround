-- Player movement + collision against the tile grid (pure Lua, no Defold API).
local C = require "modules.const"
local G = require "modules.grid"

local M = {}
local T = C.T
local HALF = C.PW / 2

local function row(y) return math.floor(y / T) + 1 end
local function col(x) return math.floor(x / T) + 1 end

function M.new_player(x, y)
	return { x = x, y = y, vx = 0, vy = 0, grounded = true, climbing = false, moving = false,
		facing = 1, ducking = false, hidden = false, climb_dir = 0,
		shielding = false, kick = 0, kick_cd = 0, kicked = false }
end

-- feet resting on a solid cell or a ladder top at column lc?
local function on_support(g, p, lc)
	local rb = row(p.y - 1)
	if math.abs(p.y - rb * T) > 1 then return false end
	return g:solid(lc, rb) or g:ladder_top(lc, rb)
end

local function near_ladder(g, p, dy)
	local lc = col(p.x)
	if math.abs(p.x - C.col_x(lc)) > 18 then return nil end
	if g:ladder(lc, row(p.y + dy)) then return lc end
	return nil
end

local function start_climb(p, lc)
	p.climbing = true
	p.x = C.col_x(lc)
	p.vx, p.vy = 0, 0
	p.grounded = false
end

local function update_climb(p, inp, dt, g)
	local lc = col(p.x)
	local want = (inp.right and 1 or 0) - (inp.left and 1 or 0)
	local dir = (inp.up and 1 or 0) - (inp.down and 1 or 0)
	p.climb_dir = dir
	p.moving = dir ~= 0
	if inp.jump then
		p.climbing = false
		p.vy = C.JUMP_V * 0.55
		p.vx = want * C.RUN
		return
	end
	if want ~= 0 and dir == 0 and on_support(g, p, lc) then
		p.climbing = false
		p.grounded = true
		return
	end
	if dir > 0 then
		local top = g:ladder_run_top(lc, row(p.y + 1))
		if not g:ladder(lc, row(p.y + 1)) then top = p.y end
		p.y = p.y + C.CLIMB * dt
		if p.y >= top then
			p.y = top
			p.climbing = false
			p.grounded = true
		end
	elseif dir < 0 then
		local ny = p.y - C.CLIMB * dt
		local rb = row(ny)
		if g:solid(lc, rb) then
			p.y = rb * T
			p.climbing = false
			p.grounded = true
		else
			p.y = ny
		end
	end
	if p.climbing and not g:ladder(lc, row(p.y + 1)) and not g:ladder(lc, row(p.y - 1)) then
		p.climbing = false      -- slipped off the ladder somehow: fall
	end
end

-- inp: { left, right, up, down, shield (held), jump / kick (pressed this frame) }
-- desks: list of { f, x }
-- After the call p.kicked is true on the frame a kick lands (game.script resolves what it hit).
function M.update(p, inp, dt, g, desks)
	if dt > 1 / 30 then dt = 1 / 30 end
	p.ducking = false
	p.hidden = false
	p.moving = false
	p.shielding = false
	p.kicked = false
	if p.kick > 0 then p.kick = p.kick - dt end
	if p.kick_cd > 0 then p.kick_cd = p.kick_cd - dt end

	if not p.climbing then
		if inp.up then
			local lc = near_ladder(g, p, 8)
			if lc and p.grounded then start_climb(p, lc) end
		elseif inp.down and p.grounded then
			local lc = near_ladder(g, p, -8)
			if lc then start_climb(p, lc) end
		end
	end
	if p.climbing then
		p.kick = 0
		update_climb(p, inp, dt, g)
		return
	end

	local want = (inp.right and 1 or 0) - (inp.left and 1 or 0)
	if want ~= 0 and p.kick <= 0 then p.facing = want end
	local speed = C.RUN
	if p.grounded and inp.down then
		p.ducking = true
		want = 0
	elseif inp.kick and p.kick_cd <= 0 then
		p.kick = C.KICK_TIME
		p.kick_cd = C.KICK_COOLDOWN
		p.kicked = true
	end
	if p.kick > 0 then
		if p.grounded then want = 0 end        -- plant the feet for a kick (keep momentum in the air)
	elseif inp.shield and not p.ducking then
		p.shielding = true
		speed = C.RUN * C.SHIELD_SPEED
	end
	if p.kick > 0 and not p.grounded then
		want = p.vx > 0 and 1 or (p.vx < 0 and -1 or 0)
	end
	p.vx = want * speed
	p.moving = want ~= 0
	if inp.jump and p.grounded then
		p.vy = C.JUMP_V
		p.grounded = false
	end
	p.vy = math.max(p.vy - C.GRAVITY * dt, -C.MAX_FALL)

	-- horizontal
	local nx = p.x + p.vx * dt
	if p.vx ~= 0 then
		local hit = g:box_solid(nx - HALF, p.y + 1, nx + HALF, p.y + C.PH - 1)
		if hit then
			if p.vx > 0 then
				nx = (col(nx + HALF) - 1) * T - HALF - 0.01
			else
				nx = col(nx - HALF) * T + HALF + 0.01
			end
			p.moving = false
		end
	end
	p.x = nx

	-- vertical
	local ny = p.y + p.vy * dt
	local c0, c1 = col(p.x - HALF), col(p.x + HALF)
	local landed = false
	if p.vy <= 0 then
		local r = row(ny)
		for c = c0, c1 do
			if g:solid(c, r) or (g:ladder_top(c, r) and p.y >= r * T - 0.01) then
				ny = r * T
				p.vy = 0
				landed = true
				break
			end
		end
	else
		local r = row(ny + C.PH)
		for c = c0, c1 do
			if g:solid(c, r) then
				ny = (r - 1) * T - C.PH - 0.01
				p.vy = 0
				break
			end
		end
	end
	p.y = ny
	p.grounded = landed

	if p.ducking then
		local f = C.floor_of(p.y)
		for _, d in ipairs(desks or {}) do
			if d.f == f and math.abs(p.x - d.x) < 40 then
				p.hidden = true
				break
			end
		end
	end
end

-- Kick vs crates: the crate stack right in front of the player at body height.
-- Returns the column and the row of the TOP crate of that stack (that is the one that breaks), or nil.
function M.kick_crate(p, g, kind, cols)
	local c = col(p.x + p.facing * (HALF + C.KICK_REACH * 0.5))
	if c < 1 or c > cols then return nil end
	local function crate(r) return g:solid(c, r) and kind[(r - 1) * cols + c] == "crate" end
	for r = row(p.y + 1), row(p.y + C.PH - 1) do
		if crate(r) then
			while crate(r + 1) do r = r + 1 end
			return c, r
		end
	end
	return nil
end

function M.break_crate(g, kind, cols, c, r)
	g:set(c, r, G.EMPTY)
	kind[(r - 1) * cols + c] = nil
end

-- is a person standing at x on the player's floor inside the kick's reach?
function M.in_kick_reach(p, x)
	local d = (x - p.x) * p.facing
	return d > -10 and d < HALF + C.KICK_REACH
end

-- AABB of the player's hit box
function M.hitbox(p)
	return p.x - HALF, p.y, p.x + HALF, p.y + C.PH
end

return M
