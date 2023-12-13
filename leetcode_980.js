/**
 * @param {number[][]} grid
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/unique-paths-iii/
 980. Unique Paths III

Question itself is a variation of a standard DFS/BFS question.


Complexity
M, N tiny -> can really explore a raft number of paths.
Let M, N := dims(grid)
Time := _____ ( it's high ) ( combinatorial try all paths possible )
    4^*(MN) maybe?
    Seems enumeration is good -> how to enumerate effectively
    Test against small inputs first
Space := O(MN) ( IMP ) 

Do not assume starting and ending cell are always in a given position!
Invariant #1 : the count updates only for visitable children -> never updates any other time.
Invariant #2 : any attempt at hitting a visited -> do NOT go again

4 dir walks only
Woooh another LC hard actually solved :-) 

 */
var uniquePathsIII = function(grid) {
    let startCount = 0
    let startPos = getStartPos(grid)
    let endPos = getEndPos(grid)
    // startPos.forEach((x) => console.log(x))
    // endPos.forEach((x) => console.log(x))
    let numNOS = getNumberNonObstacleSquares(grid)
    // it'd be cool for one function set global const ( vs set across many functions ) gaaah
    let m = grid.length
    let n = grid[0].length
    // Check syntax here
    // Array(length).fill(value) style
    let visited = Array(m).fill(false).map(() => Array(n).fill(false))
    // console.log(visited)
    let dirs = [
        [0,1],
        [1,0],
        [0,-1],
        [-1,0]
    ]
    let numFourDirWalks = dfs(dirs, grid, startPos,endPos,1, numNOS, visited)
    return numFourDirWalks
};

function equalPos(startPos,endPos) {
    return (startPos[0] == endPos[0] && startPos[1] == endPos[1])
}

// How to ensure convergence -> aside from avoiding calls
function dfs(dirs, grid, startPos,endPos,stepCount, numNOS, visited){
    // console.log(stepCount)
    // console.log(visited)
    let pathCount = 0
    let pR = startPos[0]
    let pC = startPos[1]
    if(stepCount == numNOS){ // at a terminal solution -> return
        // console.log(visited)
        if(equalPos(startPos,endPos)) {
            pathCount += 1
        }
    }
    if(!visited[pR][pC]){
        visited[pR][pC] = true
        for(dir of dirs) {
            // short vars are good for competitive programming. Not industrial monorepos
            let cR = pR + dir[0]
            let cC = pC + dir[1]
            childPos = [cR, cC]
            if(isInBounds(childPos,grid)){
                if(isNotObstacle(childPos,grid)) {
                    if(!visited[cR][cC]){
                        pathCount += dfs(dirs,grid, childPos,endPos,stepCount+1,numNOS,visited)
                    }
                }
            }
        }
        visited[pR][pC] = false // unset on call stack prop up
    }
    return pathCount
}

// -1 = obstacle that can not be walked over
function isNotObstacle(pos,grid){
    let r = pos[0]
    let c = pos[1]
    return (grid[r][c] != -1)
}

// Please leverage dynamic arrays -> reduce param passing of a bunch of ints
function isInBounds(pos,grid){
    let m = grid.length
    let n = grid[0].length
    let r = pos[0]
    let c = pos[1]
    // console.log("M,n, r, c  = %s\t%s\t%s\t%s\n", m, n,r,c)
    return ((0 <= r && r < m) && (0 <= c && c < n))
}

// `1` = starting square
function getStartPos(grid) {
    let m = grid.length
    let n = grid[0].length
    for(let r = 0; r < m; r++) {
        for(let c = 0; c < n; c++) {
            if(grid[r][c] == 1) {
                // wooh return breaks out of func ( hence all loops no matter nesting level ) 
                return [r,c] 
            }
        }
    }
    return [-1,-1] // wooh array literal return
}

// `2` = ending square
function getEndPos(grid) {
    let m = grid.length
    let n = grid[0].length
    for(let r = 0; r < m; r++) {
        for(let c = 0; c < n; c++) {
            if(grid[r][c] == 2) {
                // wooh return breaks out of func ( hence all loops no matter nesting level ) 
                return [r,c] 
            }
        }
    }
    return [-1,-1] // wooh array literal return
}

// `-1` = obstacle square
function getNumberNonObstacleSquares(grid){
    let m = grid.length
    let n = grid[0].length
    let numNOS = 0
    for(let r = 0; r < m; r++) {
        for(let c = 0; c < n; c++) {
            if(grid[r][c] != -1) {
                numNOS++
            }
        }
    }
    return numNOS
}
