-- Shared game state between game.script and the GUI scripts (one Lua state per app).
local M = {}

M.mode = "menu"          -- menu | designer | playing | paused | complete | failed | victory
M.level = 1
M.unlocked = 1
M.hearts = 3
M.level_name = ""
M.boss = false
M.presses = 0
M.presses_needed = 5

-- held controls written by the HUD (touch) and keyboard; jump and kick are also edge flags
M.touch = { left = false, right = false, up = false, down = false, jump = false, shield = false, kick = false }
M.keys = { left = false, right = false, up = false, down = false, jump = false, shield = false, kick = false }
M.jump_pressed = false
M.kick_pressed = false

function M.input()
	local t, k = M.touch, M.keys
	return {
		left = t.left or k.left, right = t.right or k.right,
		up = t.up or k.up, down = t.down or k.down,
		shield = t.shield or k.shield,
	}
end

function M.clear_input()
	for k in pairs(M.touch) do M.touch[k] = false end
	for k in pairs(M.keys) do M.keys[k] = false end
	M.jump_pressed = false
	M.kick_pressed = false
end

local function path() return sys.get_save_file("NettaSuperAgent", "progress") end

function M.load()
	local t = sys.load(path())
	if t and t.unlocked then M.unlocked = t.unlocked end
end

function M.save()
	sys.save(path(), { unlocked = M.unlocked })
end

return M
