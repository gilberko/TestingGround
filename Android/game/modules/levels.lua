-- Level definitions + builder (pure Lua, no Defold API).
--
-- Every building has 3 floors. A ladder {f, c} links floor f with floor f+1 at column c.
-- A wall {f, c} fills the whole room height of floor f (only a ladder gets you around it).
-- A crate {f, c, h} is a stack of h boxes you can jump over (h = 1 or 2) - also stops bullets.
-- A desk {f, c} covers columns c and c+1: hold DOWN behind it to hide.
-- Villains {f, c, rate} shoot every `rate` seconds when you are on their floor and in sight.
-- Bystanders {f, c, turn=true} - turn=true ones are disguised villains; `turncoat_chance`
-- additionally turns random bystanders (decided when the level starts).
local C = require "modules.const"
local G = require "modules.grid"

local M = {}

M.list = {
	{ -- 1
		name = "Level 1 - The Lobby", width = 44, tint = { 0.80, 0.88, 1.00 },
		start = { 1, 2 }, door = { 3, 42 },
		ladders = { { 1, 22 }, { 2, 34 } },
		walls = { { 1, 26 }, { 2, 38 } },
		crates = { { 1, 14, 1 } },
		desks = { { 1, 6 }, { 2, 27 }, { 3, 37 } },
		villains = { { 2, 31, 2.6 } },
		bystanders = { { 1, 10 }, { 1, 18 }, { 3, 39 } },
		bullet_speed = 360,
	},
	{ -- 2
		name = "Level 2 - Open Office", width = 60, tint = { 0.95, 0.90, 0.80 },
		start = { 1, 2 }, door = { 3, 57 },
		ladders = { { 1, 24 }, { 2, 41 } },
		walls = { { 1, 28 }, { 2, 45 }, { 3, 36 } },
		crates = { { 1, 7, 1 }, { 2, 36, 1 }, { 3, 53, 2 } },
		desks = { { 1, 10 }, { 1, 17 }, { 2, 33 }, { 3, 46 } },
		villains = { { 1, 21, 2.3 }, { 3, 50, 2.2 } },
		bystanders = { { 1, 13 }, { 2, 30, turn = true }, { 2, 38 }, { 3, 44 } },
		bullet_speed = 380,
	},
	{ -- 3
		name = "Level 3 - Server Room", width = 72, tint = { 0.80, 1.00, 0.85 },
		start = { 1, 2 }, door = { 3, 69 },
		ladders = { { 1, 21 }, { 2, 32 }, { 2, 44 }, { 2, 60 } },
		walls = { { 1, 23 }, { 2, 34 }, { 3, 47 }, { 2, 64 }, { 3, 28 } },
		crates = { { 1, 9, 1 }, { 2, 50, 2 }, { 3, 65, 1 } },
		desks = { { 1, 6 }, { 1, 15 }, { 2, 26 }, { 3, 37 }, { 2, 47 }, { 2, 56 } },
		villains = { { 1, 12, 2.1 }, { 3, 41, 2.0 }, { 2, 54, 1.9 } },
		bystanders = { { 1, 18, turn = true }, { 2, 29 }, { 2, 52 }, { 3, 63 } },
		bullet_speed = 400,
	},
	{ -- 4  BOSS 1
		name = "Boss - The Director", width = 26, boss = true, tint = { 1.00, 0.82, 0.82 },
		start = { 1, 2 },
		ladders = { { 1, 6 }, { 1, 21 }, { 2, 13 } },
		desks = { { 1, 15 }, { 2, 4 }, { 2, 19 }, { 3, 6 }, { 3, 20 } },
		crates = {},
		walls = {},
		buttons = { { 1, 11 }, { 1, 25 }, { 2, 2 }, { 2, 23 }, { 3, 3 }, { 3, 16 }, { 3, 24 } },
		pads = { { 1, 18 }, { 2, 9 }, { 2, 17 }, { 3, 10 }, { 3, 22 } },
		boss = { stay = 2.6, burst = 2, gap = 0.35, rate = 1.3, spread = false, bullet_speed = 400 },
		villains = {}, bystanders = {},
	},
	{ -- 5
		name = "Level 4 - Executive Suites", width = 80, tint = { 0.90, 0.85, 1.00 },
		start = { 1, 2 }, door = { 3, 77 }, turncoat_chance = 0.25,
		ladders = { { 1, 18 }, { 2, 30 }, { 2, 46 }, { 1, 56 }, { 2, 66 } },
		walls = { { 1, 20 }, { 2, 33 }, { 3, 49 }, { 1, 70 }, { 2, 70 }, { 3, 22 } },
		crates = { { 1, 8, 1 }, { 2, 24, 2 }, { 3, 40, 1 }, { 1, 62, 1 }, { 3, 73, 1 } },
		desks = { { 1, 5 }, { 1, 13 }, { 2, 21 }, { 2, 27 }, { 3, 35 }, { 3, 43 }, { 2, 50 }, { 1, 60 }, { 2, 63 }, { 3, 71 } },
		villains = { { 1, 15, 1.9 }, { 3, 38, 1.8 }, { 2, 53, 1.7 }, { 2, 68, 1.8 } },
		bystanders = { { 1, 10 }, { 2, 25 }, { 3, 41, turn = true }, { 1, 58 }, { 1, 65 }, { 3, 74 } },
		bullet_speed = 420,
	},
	{ -- 6
		name = "Level 5 - Secret Labs", width = 88, tint = { 0.80, 0.95, 1.00 },
		start = { 1, 2 }, door = { 2, 86 }, turncoat_chance = 0.35,
		ladders = { { 1, 14 }, { 2, 26 }, { 2, 40 }, { 1, 52 }, { 2, 64 }, { 2, 78 } },
		walls = { { 1, 17 }, { 2, 29 }, { 3, 43 }, { 1, 66 }, { 2, 66 }, { 3, 81 } },
		crates = { { 1, 6, 1 }, { 2, 20, 1 }, { 3, 34, 2 }, { 2, 46, 1 }, { 1, 58, 2 }, { 3, 70, 1 } },
		desks = { { 1, 9 }, { 2, 18 }, { 2, 23 }, { 3, 31 }, { 3, 37 }, { 2, 48 }, { 1, 55 }, { 1, 61 }, { 2, 60 }, { 3, 68 }, { 3, 75 }, { 2, 83 } },
		villains = { { 1, 12, 1.7 }, { 3, 33, 1.6 }, { 2, 50, 1.6 }, { 1, 63, 1.5 }, { 3, 73, 1.6 } },
		bystanders = { { 1, 4 }, { 2, 21, turn = true }, { 3, 39 }, { 2, 44 }, { 1, 57 }, { 3, 76 }, { 2, 82 } },
		bullet_speed = 440,
	},
	{ -- 7
		name = "Level 6 - The Penthouse", width = 96, tint = { 1.00, 0.95, 0.78 },
		start = { 1, 2 }, door = { 3, 94 }, turncoat_chance = 0.45,
		ladders = { { 1, 12 }, { 2, 22 }, { 2, 34 }, { 1, 44 }, { 2, 54 }, { 2, 66 }, { 1, 76 }, { 2, 86 } },
		walls = { { 1, 15 }, { 2, 25 }, { 3, 37 }, { 1, 58 }, { 2, 58 }, { 3, 69 }, { 1, 89 }, { 2, 89 }, { 3, 12 } },
		crates = { { 1, 6, 1 }, { 2, 17, 2 }, { 3, 29, 1 }, { 2, 40, 1 }, { 1, 50, 1 }, { 3, 62, 2 }, { 2, 72, 1 }, { 1, 82, 1 }, { 3, 91, 1 } },
		desks = { { 1, 8 }, { 2, 14 }, { 2, 19 }, { 3, 27 }, { 3, 32 }, { 2, 38 }, { 1, 47 }, { 2, 50 }, { 3, 60 }, { 3, 64 }, { 2, 70 }, { 1, 79 }, { 2, 84 }, { 3, 88 } },
		villains = { { 1, 10, 1.5 }, { 3, 30, 1.4 }, { 2, 46, 1.4 }, { 3, 57, 1.4 }, { 2, 63, 1.5 }, { 1, 84, 1.4 }, { 3, 92, 1.5 } },
		bystanders = { { 1, 4 }, { 2, 16 }, { 3, 25, turn = true }, { 2, 42 }, { 1, 49 }, { 3, 66 }, { 2, 75, turn = true }, { 1, 80 }, { 3, 86 } },
		bullet_speed = 460,
	},
	{ -- 8  BOSS 2
		name = "Final Boss - The Mastermind", width = 26, boss = true, tint = { 0.85, 0.80, 1.00 },
		start = { 1, 2 },
		ladders = { { 1, 5 }, { 1, 22 }, { 2, 10 }, { 2, 17 } },
		desks = { { 1, 13 }, { 2, 3 }, { 2, 21 }, { 3, 4 }, { 3, 13 }, { 3, 21 } },
		crates = { { 1, 9, 1 } },
		walls = {},
		buttons = { { 1, 17 }, { 1, 25 }, { 2, 7 }, { 2, 14 }, { 2, 24 }, { 3, 2 }, { 3, 10 }, { 3, 25 } },
		pads = { { 1, 15 }, { 1, 19 }, { 2, 12 }, { 2, 19 }, { 3, 7 }, { 3, 17 } },
		boss = { stay = 2.0, burst = 3, gap = 0.28, rate = 1.0, spread = true, bullet_speed = 470 },
		villains = {}, bystanders = {},
	},
}

M.COUNT = #M.list
M.PRESSES = 5   -- boss buttons to press

-- tiny deterministic RNG so the same level always looks the same
local function rng(seed)
	local s = seed * 7919 + 17
	return function(n)
		s = (s * 1103515245 + 12345) % 2147483648
		return (s % n) + 1
	end
end

-- Build runtime data for level index i
function M.build(i)
	local spec = M.list[i]
	local W, H = spec.width, C.ROWS
	local g = G.new(W, H)
	local kind = {}
	local function setk(c, r, v, k)
		g:set(c, r, v)
		kind[(r - 1) * W + c] = k
	end
	-- slabs: ground + between floors + roof
	for k = 1, C.FLOORS + 1 do
		local r = C.slab_row(k)
		for c = 1, W do setk(c, r, G.SOLID, r == H and "roof" or "floor") end
	end
	local L = { index = i, spec = spec, cols = W, rows = H, grid = g, kind = kind,
		width = W * C.T, height = H * C.T, boss = spec.boss,
		desks = {}, villains = {}, bystanders = {}, buttons = {}, pads = {}, decor = {}, ladders = {} }
	local room0 = function(f) return C.slab_row(f) + 1 end
	for _, w in ipairs(spec.walls or {}) do
		for r = room0(w[1]), room0(w[1]) + 2 do setk(w[2], r, G.SOLID, "wall") end
	end
	for _, cr in ipairs(spec.crates or {}) do
		for r = room0(cr[1]), room0(cr[1]) + (cr[3] or 1) - 1 do setk(cr[2], r, G.SOLID, "crate") end
	end
	for _, l in ipairs(spec.ladders) do
		local f, c = l[1], l[2]
		for r = room0(f), C.slab_row(f + 1) do setk(c, r, G.LADDER, "ladder") end
		L.ladders[#L.ladders + 1] = { f = f, c = c }
	end
	local function at(f, c) return { f = f, c = c, x = C.col_x(c), y = C.floor_y(f) } end
	L.start = at(spec.start[1], spec.start[2])
	if spec.door then L.door = at(spec.door[1], spec.door[2]) end
	for _, d in ipairs(spec.desks or {}) do
		local e = at(d[1], d[2]); e.x = d[2] * C.T          -- centred between c and c+1
		L.desks[#L.desks + 1] = e
	end
	for _, v in ipairs(spec.villains or {}) do
		local e = at(v[1], v[2]); e.rate = v[3] or 2.0
		L.villains[#L.villains + 1] = e
	end
	local rand = rng(i)
	for _, b in ipairs(spec.bystanders or {}) do
		local e = at(b[1], b[2]); e.turn = b.turn and true or false
		e.variant = rand(5)
		L.bystanders[#L.bystanders + 1] = e
	end
	for _, b in ipairs(spec.buttons or {}) do L.buttons[#L.buttons + 1] = at(b[1], b[2]) end
	for _, p in ipairs(spec.pads or {}) do L.pads[#L.pads + 1] = at(p[1], p[2]) end

	-- background tiles + decorations
	L.bg = {}
	local busy = {}
	for _, d in ipairs(L.desks) do busy[d.f .. ":" .. (d.x / C.T)] = true; busy[d.f .. ":" .. (d.x / C.T + 1)] = true end
	for _, l in ipairs(L.ladders) do busy[l.f .. ":" .. l.c] = true; busy[(l.f + 1) .. ":" .. l.c] = true end
	if L.door then busy[L.door.f .. ":" .. L.door.c] = true end
	for _, b in ipairs(L.buttons) do busy[b.f .. ":" .. b.c] = true end
	for _, p in ipairs(L.pads) do busy[p.f .. ":" .. p.c] = true end
	for f = 1, C.FLOORS do
		for c = 1, W do
			for r = room0(f), room0(f) + 2 do
				local name = "bg"
				if r == room0(f) + 1 then
					local n = rand(100)
					if n <= 14 then name = "bg_window" elseif n <= 22 then name = "bg_picture" elseif n <= 25 then name = "bg_logo" end
				end
				L.bg[#L.bg + 1] = { name = name, x = C.col_x(c), y = (r - 0.5) * C.T }
			end
			local r0 = room0(f)
			if not busy[f .. ":" .. c] and not g:solid(c, r0) and c > 1 and rand(100) <= 9 then
				L.decor[#L.decor + 1] = { name = rand(2) == 1 and "plant" or "cooler", x = C.col_x(c), y = C.floor_y(f) }
			end
		end
	end
	return L
end

return M
