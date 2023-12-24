/**
 * @param {number[][]} grid
 * @return {number[][]}
 */
 /*
 URL := https://leetcode.com/problems/difference-of-number-of-distinct-values-on-diagonals/
 2711. Difference of Number of Distinct Values on Diagonals

 Is this really a question about efficiency - or not?
 Can we use a hashset - versus constant recomputation - with space tradeoff
 
 Complexity
 Let M, N := #-rows, #-cols in grid
 Time := O(MN)
 Space := O(1) ( IMP ) O(MN) ( EXP ) 

 Small dims : m,n dim by 50 only.

20 minutes
 */
var differenceOfDistinctValues = function(grid) {
    var m = grid.length
    var n = grid[0].length
    var answer = Array(m).fill(-1).map(() => Array(n).fill(-1))
    for(let c = 0; c < n; c++) {
        diagTraverse(0,c,grid,answer)
    }
    for(let r = 1; r < m; r++) {
        diagTraverse(r,0,grid,answer)
    }
    return answer // no neg 1 abs value here    
};

/*
JS pass-by-reference focus
Dodge diag check -> use bounds check with stepper :-)
Always start on upperLeft -> top default empty, bottom Filled up
*/
function diagTraverse(r,c,grid,answer) {
    topDiag = new Map()
    bottomRight = new Map()
    populateBottomRight(r+1,c+1,grid,bottomRight)   
    // console.log("bottomDiag = \n")
    // bottomRight.forEach((el) => console.log(el))
    while(isInBounds(r,c,grid)){
        // console.log("At r,c = %d,%d\n", r, c)
        // console.log("topDiag = \n")
        // topDiag.forEach((el) => console.log(el))
        // console.log("bottomDiag = \n")
        // bottomRight.forEach((el) => console.log(el))
        var curEl = grid[r][c]
        var cellAnswer = Math.abs(topDiag.size - bottomRight.size)
        answer[r][c] = cellAnswer
        var nextR = r + 1
        var nextC = c + 1
        // add current element to topDiag
        if(!topDiag.has(curEl)) {
            topDiag.set(curEl,1)
        } else {
            topDiag.set(curEl, topDiag.get(curEl) + 1)
        }
        if(isInBounds(nextR,nextC,grid)) {
            var nextEl = grid[nextR][nextC]
            bottomRight.set(nextEl, bottomRight.get(nextEl) - 1) // guaranteed to exist
            if(bottomRight.get(nextEl) == 0) {
                bottomRight.delete(nextEl)
            }
        }
        r = nextR
        c = nextC
    }
}

function populateBottomRight(r,c,grid,bottomRight) {
    while(isInBounds(r,c,grid)){
        var el = grid[r][c]
        if(!bottomRight.has(el)) {
            bottomRight.set(el,1)
        } else {
            bottomRight.set(el, bottomRight.get(el) + 1)
        }
        r += 1
        c += 1
    }
}
    

function isInBounds(r,c,grid) {
    var m = grid.length
    var n = grid[0].length
    return (0 <= r && r < m) && (0 <= c && c < n)
}
