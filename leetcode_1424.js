/**
 * @param {number[][]} nums
 * @return {number[]}
 */
 /*
 URL := https://leetcode.com/problems/diagonal-traverse-ii/
 1424. Diagonal Traverse II

Level Order Traversal Approach
Always at least a single el -> no invalid inputs

20 minutes to solution
Learnt more JS again :-) 


 */
var findDiagonalOrder = function(nums) {
    let numRows = nums.length
    let diagOrder = Array(0)
    let traversal = new Array(0) // 0 empty slots initialization
    traversal.push(Array(0,0))
    visitMap = new Map() // JS more dyn typed :-) 
    while ( traversal.length > 0) { // is prototype a function
        let curEl = traversal.shift() // shift first el, pop last el behavior
        let r = curEl[0]
        let c = curEl[1]
        if(!visitMap.has(r)) { // has over contains key
            visitMap.set(r,new Set()) // no specify length of set here
        }
        let rowSet = visitMap.get(r)
        if(!rowSet.has(c)){ // has not contains
            rowSet.add(c)
            // check nums[r][c] even exists too
            if(r < numRows) {
                let numColsInCurRow = nums[r].length
                if(c < numColsInCurRow) {
                    let myAddEl = nums[r][c]
                    diagOrder.push(myAddEl)
                    let bottomEl = Array(r+1,c)
                    let rightEl = Array(r,c+1)
                    traversal.push(bottomEl)
                    traversal.push(rightEl)
                }
            }
        } 
    }
    return diagOrder
};
