/**
 * @param {number[][]} grid
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/making-a-large-island/
 827. Making A Large Island

Question is akin to other island-based questions with standard BFS-DFS approaches

Complexity
Let M, N := dims(GRID)
Time := O(MN)
Space := O(MN) ( EXP ) O(1) ( IMP ) 
M, N := reasonable constants here

Only one 0 at a time can be changed -> enumerate over each posible 0 here
This question feels easier ( is this really a hard )? 
Can map each unique r,c ( r-c key mappings too ) 
Utilize UUID Grid -> to much work in one method, but ok.

30 minutes
pass but GAAAAH so much JS errors
JS compiler is ... weak

 */

/*
    Why unexpected identifier error here?
*/
function getIslandSizeDFS(grid,sR,sC,uuidGrid, uuid, visited) {
    let islandSize = 0;
    let dirs = [
        [-1,0],
        [1,0],
        [0,-1],
        [0,1]
    ]
    queue = [[sR,sC]]
    while(queue.length > 0) {
        let pCell = queue.shift();
        let pR = pCell[0];
        let pC = pCell[1];
        if(!visited[pR][pC] && grid[pR][pC] == 1){
            visited[pR][pC] = true;
            islandSize++;
            uuidGrid[pR][pC] = uuid;
            for(dir of dirs) { // of for element, in for key
                let cR = pR + dir[0];
                let cC = pC + dir[1];
                if(isInBounds(grid,cR,cC) && !visited[cR][cC]){
                    queue.push([cR,cC]);
                }
            }
        }
    }
    return islandSize;
}

var largestIsland = function(grid) {
    let myLargest = 0;
    let uuid = 0; // UUID ( for islands ) -> if not encountered, populate
    let m = grid.length;
    let n = grid[0].length;
    let visited = Array(m).fill(false).map(() => Array(n).fill(false));
    let uuidMap = new Map();
    let uuidGrid = Array(m).fill(-1).map(() => Array(n).fill(-1)); // -1 => no init UUID value
    for(let r = 0; r < m; r++) {
        for(let c = 0; c < n; c++) {
            if(grid[r][c] == 1 && visited[r][c] == false) {
                let islandSize = getIslandSizeDFS(grid,r,c,uuidGrid, uuid, visited);
                // console.log("Island size = %d\n", islandSize);
                if(!uuidMap.has(uuid)){
                    uuidMap.set(uuid,islandSize);
                    myLargest = Math.max(myLargest, islandSize); // no set case
                }
                uuid++;
            }
        }
    }
    // console.log(uuidGrid);
    let dirs = [
        [-1,0],
        [1,0],
        [0,-1],
        [0,1]
    ]
    let hitUUID = new Set() // one instantiation of a set with clear operation later on
    // console.log(uuidMap);
    for(let r = 0; r < m; r++) {
        for(let c = 0; c < n; c++) {
            if(grid[r][c] == 0) {
                let curCellSize = 1;
                for(dir of dirs) {
                    let delR = r + dir[0];
                    let delC = c + dir[1];
                    if(isInBounds(grid,delR,delC)) {
                        if(grid[delR][delC] === 1) {
                            let uuidVal = uuidGrid[delR][delC];
                            if(uuidVal != -1 && !hitUUID.has(uuidVal)) {
                                curCellSize += uuidMap.get(uuidVal); 
                                hitUUID.add(uuidVal);
                            }
                        }
                    }
                }
                // JS compiler allows badly named identifiers everywhere WTF
                myLargest = Math.max(myLargest, curCellSize);
                hitUUID.clear();
            }
        }
    }
    return myLargest;
};

// function kewyord -> not func
function isInBounds(grid,r,c) {
    let m = grid.length;
    let n = grid[0].length;
    return (0 <= r && r < m) && (0 <= c && c < n);
} // semicolon function end possible? huh?
