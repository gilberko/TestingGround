-- Netta's look: options, current choice, save/load, and how to apply it to sprites.
local M = {}

M.cats = {
	{ key = "body", label = "AGENT", names = { "HE", "SHE" }, values = { "m", "f" } },
	{ key = "skin", label = "SKIN", names = { "1", "2", "3", "4", "5", "6" },
		colors = { { 255, 226, 200 }, { 241, 196, 158 }, { 224, 172, 122 }, { 190, 136, 92 }, { 140, 94, 62 }, { 96, 64, 44 } } },
	{ key = "hair", label = "HAIR", names = { "SHORT", "LONG", "PONYTAIL", "SPIKY", "BUZZ", "NONE" },
		values = { "short", "long", "pony", "spiky", "buzz", false } },
	{ key = "hair_color", label = "HAIR COLOR", names = { "BLACK", "BROWN", "AUBURN", "BLONDE", "RED", "SILVER", "BLUE", "PINK" },
		colors = { { 40, 34, 34 }, { 110, 70, 40 }, { 150, 60, 30 }, { 235, 205, 120 }, { 210, 70, 40 }, { 200, 200, 210 }, { 70, 110, 230 }, { 240, 120, 190 } } },
	{ key = "suit", label = "SUIT", names = { "BLACK", "NAVY", "GREY", "WHITE", "RED", "GREEN", "PURPLE", "TAN" },
		colors = { { 50, 50, 60 }, { 40, 60, 120 }, { 120, 125, 135 }, { 235, 235, 240 }, { 180, 35, 45 }, { 40, 120, 70 }, { 110, 50, 150 }, { 190, 160, 110 } } },
	{ key = "acc", label = "GADGET", names = { "NONE", "SUNGLASSES", "EARPIECE", "BERET" },
		values = { false, "shades", "ear", "beret" } },
}

M.look = { body = 2, skin = 2, hair = 2, hair_color = 1, suit = 1, acc = 2 }
M.saved = false

local function path() return sys.get_save_file("NettaSuperAgent", "look") end

function M.load()
	local t = sys.load(path())
	if t and t.body then
		for k, v in pairs(t) do if M.look[k] ~= nil then M.look[k] = v end end
		M.saved = true
	end
end

function M.save()
	sys.save(path(), M.look)
	M.saved = true
end

function M.cycle(key, dir)
	for _, c in ipairs(M.cats) do
		if c.key == key then
			local n = #c.names
			M.look[key] = (M.look[key] - 1 + dir) % n + 1
			return
		end
	end
end

function M.cat(key)
	for _, c in ipairs(M.cats) do if c.key == key then return c end end
end

function M.value_name(key)
	local c = M.cat(key)
	return c.names[M.look[key]]
end

local function col(key)
	local c = M.cat(key).colors[M.look[key]]
	return vmath.vector4(c[1] / 255, c[2] / 255, c[3] / 255, 1)
end

-- The five player layers: list of { id = component id, prefix = image prefix or nil (hidden), tint }
function M.layers()
	local body = M.cat("body").values[M.look.body]
	local hair = M.cat("hair").values[M.look.hair]
	local acc = M.cat("acc").values[M.look.acc]
	local white = vmath.vector4(1, 1, 1, 1)
	return {
		{ id = "skin", prefix = "skin_" .. body, tint = col("skin") },
		{ id = "suit", prefix = "suit_" .. body, tint = col("suit") },
		{ id = "det", prefix = "det_" .. body, tint = white },
		{ id = "hair", prefix = hair and ("hair_" .. hair) or nil, tint = col("hair_color") },
		{ id = "acc", prefix = acc and ("acc_" .. acc) or nil, tint = white },
	}
end

return M
