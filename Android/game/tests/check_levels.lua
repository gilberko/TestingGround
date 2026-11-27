-- Offline checks, run with the LuaJIT bundled in Defold (see tests/run_tests.ps1):
--   luajit tests/check_levels.lua <project root>
-- 1) every level is solvable (door / all boss buttons reachable) and nothing sits inside a wall
-- 2) the player physics behave (land, walk, wall stop, climb up/down, jump crate, hide at desk)
local root = arg[1] or "."
package.path = root .. "/?.lua;" .. package.path

local C = require "modules.const"
local G = require "modules.grid"
local Lv = require "modules.levels"
local S = require "modules.sim"

local fails = 0
local function check(cond, msg)
	if not cond then fails = fails + 1; print("FAIL: " .. msg) end
end

-- ---------------------------------------------------------------- levels
for i = 1, Lv.COUNT do
	local L = Lv.build(i)
	local g = L.grid
	local name = ("L%d %s"):format(i, L.spec.name)
	local function room0(f) return C.slab_row(f) + 1 end
	local function k(c, r) return L.kind[(r - 1) * L.cols + c] end
	local function blocked(f, c)          -- full-height wall (or level edge)
		if c < 1 or c > L.cols then return true end
		return k(c, room0(f)) == "wall"
	end
	local function free(f, c)             -- nothing solid at floor level
		return c >= 1 and c <= L.cols and not g:solid(c, room0(f))
	end
	-- BFS over (floor, column)
	local seen, q = {}, { { L.start.f, L.start.c } }
	seen[L.start.f .. ":" .. L.start.c] = true
	local lad = {}
	for _, l in ipairs(L.ladders) do lad[l.f .. ":" .. l.c] = l.f + 1; lad[(l.f + 1) .. ":" .. l.c .. "d"] = l.f end
	while #q > 0 do
		local f, c = q[1][1], q[1][2]
		table.remove(q, 1)
		local nexts = { { f, c - 1 }, { f, c + 1 } }
		if lad[f .. ":" .. c] then nexts[#nexts + 1] = { lad[f .. ":" .. c], c } end
		if lad[f .. ":" .. c .. "d"] then nexts[#nexts + 1] = { lad[f .. ":" .. c .. "d"], c } end
		for _, n in ipairs(nexts) do
			local key = n[1] .. ":" .. n[2]
			if not seen[key] and not blocked(n[1], n[2]) then
				-- crates of height <= 2 can be jumped
				local kk = k(n[2], room0(n[1]))
				local ok = kk ~= "crate" or not g:solid(n[2], room0(n[1]) + 2)
				if ok then
					seen[key] = true
					q[#q + 1] = n
				end
			end
		end
	end
	local function reach(e) return seen[e.f .. ":" .. e.c] end
	check(free(L.start.f, L.start.c), name .. ": start inside something")
	if L.door then
		check(free(L.door.f, L.door.c), name .. ": door inside something")
		check(reach(L.door), name .. ": door NOT reachable")
	end
	if L.boss then
		check(#L.buttons >= Lv.PRESSES, name .. ": not enough buttons")
		for bi, b in ipairs(L.buttons) do
			check(free(b.f, b.c), name .. ": button " .. bi .. " inside something")
			check(reach(b), name .. ": button " .. bi .. " NOT reachable")
		end
		for pi, p in ipairs(L.pads) do check(free(p.f, p.c), name .. ": pad " .. pi .. " inside something") end
	end
	for _, d in ipairs(L.desks) do
		local c = d.x / C.T
		check(free(d.f, c) and free(d.f, c + 1), name .. ": desk at " .. d.f .. "," .. c .. " overlaps a solid")
		check(not g:ladder(c, room0(d.f)) and not g:ladder(c + 1, room0(d.f)), name .. ": desk on a ladder " .. c)
	end
	for _, v in ipairs(L.villains) do check(free(v.f, v.c), name .. ": villain inside something at " .. v.c) end
	for _, b in ipairs(L.bystanders) do check(free(b.f, b.c), name .. ": bystander inside something at " .. b.c) end
	for _, l in ipairs(L.ladders) do
		check(free(l.f, l.c) and free(l.f + 1, l.c), name .. ": ladder blocked at " .. l.f .. "," .. l.c)
	end
	print(("%-40s ok-check  reachable cells=%d"):format(name, (function() local n = 0 for _ in pairs(seen) do n = n + 1 end return n end)()))
end

-- ---------------------------------------------------------------- physics
local function run(p, inp, secs, g, desks)
	local dt = 1 / 60
	for _ = 1, math.floor(secs * 60 + 0.5) do
		S.update(p, inp, dt, g, desks)
		inp.jump = false
	end
end
local L = Lv.build(1)
local g = L.grid
local p = S.new_player(L.start.x, L.start.y + 30)
run(p, {}, 0.5, g)
check(p.grounded and math.abs(p.y - C.floor_y(1)) < 0.01, "player lands on floor 1 (y=" .. p.y .. ")")
-- walk right into the wall at column 26 on floor 1
p = S.new_player(C.col_x(24), C.floor_y(1))
run(p, { right = true }, 2, g)
check(p.x < 25 * C.T and p.x > 24 * C.T, "wall stops player (x=" .. p.x .. ")")
-- jump over the crate at column 14
p = S.new_player(C.col_x(12), C.floor_y(1))
local inp = { right = true, jump = false }
for t = 1, 120 do
	if t == 10 then inp.jump = true end
	S.update(p, inp, 1 / 60, g, nil); inp.jump = false
end
check(p.x > C.col_x(15), "jumped over crate (x=" .. p.x .. ")")
-- without jumping the crate blocks
p = S.new_player(C.col_x(12), C.floor_y(1))
run(p, { right = true }, 1.5, g)
check(p.x < 13 * C.T, "crate blocks walking (x=" .. p.x .. ")")
-- climb ladder at column 22 from floor 1 to floor 2
p = S.new_player(C.col_x(22) + 6, C.floor_y(1))
run(p, { up = true }, 2.5, g)
check(not p.climbing and math.abs(p.y - C.floor_y(2)) < 0.01, "climbed to floor 2 (y=" .. p.y .. ")")
-- stand on the ladder top, then walk away on floor 2
run(p, {}, 0.5, g)
check(math.abs(p.y - C.floor_y(2)) < 0.01, "stands on ladder top")
run(p, { right = true }, 0.5, g)
check(math.abs(p.y - C.floor_y(2)) < 0.01 and p.x > C.col_x(23), "walks off ladder top on floor 2")
-- climb back down
p = S.new_player(C.col_x(22), C.floor_y(2))
run(p, { down = true }, 2.5, g)
check(math.abs(p.y - C.floor_y(1)) < 0.01 and not p.climbing, "climbed down to floor 1 (y=" .. p.y .. ")")
-- walking across a ladder hole on floor 2 does not fall through
p = S.new_player(C.col_x(20), C.floor_y(2))
run(p, { right = true }, 0.8, g)
check(math.abs(p.y - C.floor_y(2)) < 0.01, "walk across ladder hole (y=" .. p.y .. ")")
-- hide behind the desk on floor 1 (columns 6-7)
p = S.new_player(6 * C.T, C.floor_y(1))
run(p, { down = true }, 0.2, g, L.desks)
check(p.hidden and p.ducking, "hidden behind desk")
p = S.new_player(C.col_x(10), C.floor_y(1))
run(p, { down = true }, 0.2, g, L.desks)
check(p.ducking and not p.hidden, "ducking in the open is not hidden")
-- jump height clears a bullet at BULLET_Y
p = S.new_player(C.col_x(3), C.floor_y(1))
local apex = 0
inp = { jump = true }
for _ = 1, 60 do S.update(p, inp, 1 / 60, g); inp.jump = false; apex = math.max(apex, p.y - C.floor_y(1)) end
check(apex > C.BULLET_Y + C.BULLET_H, "jump apex " .. apex .. " clears bullets")

print(fails == 0 and "ALL CHECKS PASSED" or (fails .. " CHECK(S) FAILED"))
os.exit(fails == 0 and 0 or 1)
