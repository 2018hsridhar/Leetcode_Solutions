/**
 * @param {number[][]} matrix
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/largest-submatrix-with-rearrangements/
 1727. Largest Submatrix With Rearrangements

Approach : Prefix summation , In-Place sorting

Complexity
Let M, N := dims(matrix)
Time := O(MNlgN)
Space := O(MN) ( EXP ) O(1) ( IMP ) 

Time = 28 minutes 
 */
var largestSubmatrix = function(matrix) {
    var largestSubMat = 0
    var m = matrix.length
    var n = matrix[0].length
    var prefixSums = Array(m).fill(-1).map(() => Array(n).fill(-1))
    var dynArr = []
    for(var c = 0; c < n; c++) {
        prefixSums[0][c] = matrix[0][c]; // base case
        dynArr.push(prefixSums[0][c])
    }
    dynArr.sort((a,b) => Number(a) - Number(b))
    for(let i = 0; i < dynArr.length; i++) {
        let curWidth = dynArr.length - i
        let curArea = dynArr[i] * curWidth;
        largestSubMat = Math.max(largestSubMat,curArea)
    }
    dynArr.length = 0 // 0 out elements
    for(var r = 1; r < m; r++) {
        for(var c = 0; c < n; c++) {
            if(matrix[r][c] == 1) {
                prefixSums[r][c] = prefixSums[r-1][c] + 1
            } else {
                prefixSums[r][c] = 0
            }
            dynArr.push(prefixSums[r][c])
        }
        dynArr.sort((a,b) => Number(a) - Number(b))
        for(let i = 0; i < dynArr.length; i++) {
            let curWidth = dynArr.length - i
            let curArea = dynArr[i] * curWidth;
            largestSubMat = Math.max(largestSubMat,curArea)
        }
        dynArr.length = 0 // 0 out elements
    }
    // console.log(prefixSums);
    return largestSubMat
};
