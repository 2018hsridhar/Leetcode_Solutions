/**
 * @param {number} m
 * @param {number} n
 * @param {number[][]} guards
 * @param {number[][]} walls
 * @return {number}
 */
 /*
 2257. Count Unguarded Cells in the Grid
 URL := https://leetcode.com/problems/count-unguarded-cells-in-the-grid/

Stanard DFS-cardinalExplore question with counting and enumeration involved

Complexity
Time := O(MN)
Space := O(MN) ( EXP ) O(1) ( IMP ) 
#-guards and #-walls a known

-1 emptycell
0 seenCell
1 guard
2 wall

1/2 = obstructions

 */
var countUnguarded = function(m, n, guards, walls) {
    var numCells = (m*n) - guards.length - walls.length
    var numNotGuarded = numCells
    var visited = Array(m).fill(-1).map(() => Array(n).fill(-1))
    guards.forEach((guard) => {
        var gR = guard[0]
        var gC = guard[1]
        visited[gR][gC] = 1
    })
    walls.forEach((wall) => {
        var wR = wall[0]
        var wC = wall[1]
        visited[wR][wC] = 2
    })
    guards.forEach((guard) => {
        var numNewlyHit = cardinalExplore(guard,m,n,visited)
        numNotGuarded -= numNewlyHit
    })
    return numNotGuarded
};

// Cardinal only -> not even cardinalExplore/DFS
function cardinalExplore(guard,m,n,visited) {
    var numSeenCells = 0
    var dirs = [
        [-1,0],
        [1,0],
        [0,-1],
        [0,1]
    ]
    let sR = guard[0]
    let sC = guard[1]
    dirs.forEach((dir) => {
        let curCellsSeen = 0
        let cR = guard[0]
        let cC = guard[1]
        // temp set [sR,sC] for guard one to not visited here
        // sub -1 for accounting purposes : guard not a hit cell
        visited[cR][cC] = -1
        while(isInBounds(cR,cC,m,n)) {
            // Another guard or a wall hit
            if(visited[cR][cC] == 1 || visited[cR][cC] == 2) {
                break
            } else if ( visited[cR][cC] == -1) {
                visited[cR][cC] = 0
                curCellsSeen++
            }
            cR = cR + dir[0]
            cC = cC + dir[1]
        }
        curCellsSeen -= 1 // for correction 
        numSeenCells += curCellsSeen
    })
    // set back
    visited[sR][sC] = 1
    return numSeenCells
}

function isInBounds(r,c,m,n) {
    return (0 <= r && r < m) && (0 <= c && c < n)
}
