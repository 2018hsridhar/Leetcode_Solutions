/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/uncrossed-lines/
 1035. Uncrossed Lines

 Either we make the line with the earliest encounter
 ... or, we make no line, and ask if we did better with a different values instead ( passing original indices as is )

 Complexity
 Let N := len(nums1)
 Let M := len(nums2)
 Time := O(MN)
 Space := O(1) ( EXP ) O(M+N) ( IMP ) 

 Can convert to DP later?
Wow felled by a break statement
now for DP mode :-) 

30 minutes
Caught on bugs and JS coding
No good way to init 2d array in Javascript

 */
var maxUncrossedLines = function(nums1, nums2) {
    let m = nums1.length;
    let n = nums2.length;
    let memo = Array.from({length: m}, () => Array(n).fill(-1));
    let maxLines = helper(nums1,nums2,0,0,memo);
    return maxLines;
};

function helper(nums1, nums2, idxOne, idxTwo,memo) {
    let m = nums1.length;
    let n = nums2.length;
    if(idxOne >= m || idxTwo >= n) { // && vs || stack overflow case
        return 0;
    }
    if(memo[idxOne][idxTwo] != -1) {
        return memo[idxOne][idxTwo];
    }
    // blocking scoping for loops JS
    let numCrossFromOne = 0;
    let elOne = nums1[idxOne];
    for(let j = idxTwo; j < n; j++) {
        if(elOne == nums2[j]) {
            numCrossFromOne = 1 + helper(nums1,nums2, idxOne + 1, j + 1,memo);
            break; // felled by a break statement?
        }
    }
    let numNoCrossFromOne = helper(nums1,nums2,idxOne + 1, idxTwo,memo);
    let myMax = Math.max(numNoCrossFromOne, numCrossFromOne);
    memo[idxOne][idxTwo] = myMax;
    return myMax;
}
