-- Shared constants (pure Lua, no Defold API: safe for offline tests)
local M = {}

M.T = 48                 -- tile size in world pixels
M.FLOORS = 3             -- floors per building
M.ROWS = 13              -- grid rows: slab, 3 room rows, slab, ... roof
M.FLOOR_STRIDE = 4       -- rows per floor (1 slab + 3 room)

-- player physics
M.GRAVITY = 2300
M.JUMP_V = 760
M.RUN = 270
M.CLIMB = 190
M.MAX_FALL = 1100
M.PW = 26                -- player box width
M.PH = 64                -- player box height (standing)
M.HEARTS = 3
M.INVULN = 1.6

-- enemies
M.BULLET_Y = 46          -- bullet height above the shooter's feet
M.BULLET_LOW_Y = 14      -- low boss shots (jump over them)
M.BULLET_W = 16
M.BULLET_H = 6
M.SIGHT = 11 * 48        -- villain sight range (same floor)
M.TURN_RANGE = 7 * 48    -- disguised bystander reveal range

-- floor k (1-based) -> grid row of the slab the floor stands on (1-based rows from the bottom)
function M.slab_row(k) return 1 + (k - 1) * M.FLOOR_STRIDE end
-- y of the walking surface of floor k
function M.floor_y(k) return M.slab_row(k) * M.T end
-- floor number from a feet y (rounded to the nearest floor below)
function M.floor_of(y)
	local k = math.floor((y - M.T + 4) / (M.FLOOR_STRIDE * M.T)) + 1
	if k < 1 then k = 1 end
	if k > M.FLOORS then k = M.FLOORS end
	return k
end
-- x of the centre of column c
function M.col_x(c) return (c - 0.5) * M.T end

return M
