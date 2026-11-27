-- Small helpers for GUI scripts that build their nodes in code.
local ST = require "modules.state"

local M = {}

M.WHITE = vmath.vector4(1, 1, 1, 1)
M.GOLD = vmath.vector4(1, 0.82, 0.25, 1)
M.DARK = vmath.vector4(0.06, 0.07, 0.12, 0.92)
M.PANEL = vmath.vector4(0.13, 0.15, 0.24, 0.97)
M.BTN = vmath.vector4(0.20, 0.45, 0.85, 1)
M.BTN2 = vmath.vector4(0.30, 0.32, 0.42, 1)
M.RED = vmath.vector4(0.85, 0.25, 0.25, 1)

function M.box(list, x, y, w, h, color, anim)
	local n = gui.new_box_node(vmath.vector3(x, y, 0), vmath.vector3(w, h, 0))
	gui.set_color(n, color or M.WHITE)
	if anim then
		gui.set_texture(n, "game")
		gui.play_flipbook(n, anim)
	end
	if list then list[#list + 1] = n end
	return n
end

-- full screen dim background (stretches to any aspect ratio)
function M.backdrop(list, color)
	local n = M.box(list, 640, 360, 1280, 720, color or M.DARK)
	gui.set_adjust_mode(n, gui.ADJUST_STRETCH)
	return n
end

function M.text(list, x, y, str, scale, color)
	local n = gui.new_text_node(vmath.vector3(x, y, 0), str)
	gui.set_font(n, "main")
	scale = scale or 0.6
	gui.set_scale(n, vmath.vector3(scale, scale, 1))
	gui.set_color(n, color or M.WHITE)
	gui.set_outline(n, vmath.vector4(0, 0, 0, 1))
	if list then list[#list + 1] = n end
	return n
end

-- a labelled rectangular button: returns { node, label }
function M.button(list, x, y, w, h, label, color, scale)
	local border = M.box(list, x, y, w + 6, h + 6, vmath.vector4(0, 0, 0, 0.6))
	local n = M.box(list, x, y, w, h, color or M.BTN)
	local t = M.text(list, x, y, label, scale or 0.55)
	return { node = n, label = t, border = border }
end

function M.hit(btn, x, y)
	return btn and gui.is_enabled(btn.node) and gui.pick_node(btn.node, x, y)
end

function M.set_enabled(list, on)
	for _, n in ipairs(list) do gui.set_enabled(n, on) end
end

function M.clear(list)
	for _, n in ipairs(list) do gui.delete_node(n) end
	for i = #list, 1, -1 do list[i] = nil end
end

-- One tap -> one press, whether it arrives as a mouse click, a touch, or both in the same frame.
-- Returns x, y of a new press or nil.
function M.tap(action_id, action)
	local x, y
	if action_id == hash("click") and action.pressed then
		x, y = action.x, action.y
	elseif action_id == hash("multitouch") and action.touch then
		for _, t in ipairs(action.touch) do
			if t.pressed then x, y = t.x, t.y end
		end
	end
	if not x then return nil end
	local f = ST.frame or 0
	if M.last_tap_frame == f then return nil end
	M.last_tap_frame = f
	return x, y
end

-- quick press feedback
function M.flash(btn)
	gui.set_scale(btn.node, vmath.vector3(0.92, 0.92, 1))
	gui.animate(btn.node, "scale", vmath.vector3(1, 1, 1), gui.EASING_OUTBACK, 0.2)
end

return M
