-- Tile grid + collision queries (pure Lua, no Defold API).
-- Cells: rows counted from the bottom (1 = ground slab), columns from the left (1-based).
local C = require "modules.const"

local M = {}
M.EMPTY, M.SOLID, M.LADDER = 0, 1, 2

local Grid = {}
Grid.__index = Grid

function M.new(cols, rows)
	local g = setmetatable({ cols = cols, rows = rows, cells = {} }, Grid)
	for i = 1, cols * rows do g.cells[i] = M.EMPTY end
	return g
end

function Grid:get(c, r)
	if c < 1 or c > self.cols then return M.SOLID end     -- level sides are walls
	if r < 1 then return M.SOLID end
	if r > self.rows then return M.EMPTY end
	return self.cells[(r - 1) * self.cols + c]
end

function Grid:set(c, r, v)
	if c >= 1 and c <= self.cols and r >= 1 and r <= self.rows then
		self.cells[(r - 1) * self.cols + c] = v
	end
end

function Grid:solid(c, r) return self:get(c, r) == M.SOLID end
function Grid:ladder(c, r) return self:get(c, r) == M.LADDER end
-- topmost cell of a ladder run: stand on it like a platform
function Grid:ladder_top(c, r) return self:ladder(c, r) and not self:ladder(c, r + 1) end

function M.cell_of(x, y)
	return math.floor(x / C.T) + 1, math.floor(y / C.T) + 1
end

-- any solid cell overlapping box [x0,x1] x [y0,y1]?
function Grid:box_solid(x0, y0, x1, y1)
	local c0, r0 = M.cell_of(x0, y0)
	local c1, r1 = M.cell_of(x1, y1)
	for r = r0, r1 do
		for c = c0, c1 do
			if self:solid(c, r) then return true, c, r end
		end
	end
	return false
end

-- top y of the ladder run that contains cell (c, r)
function Grid:ladder_run_top(c, r)
	while self:ladder(c, r + 1) do r = r + 1 end
	return r * C.T
end

return M
