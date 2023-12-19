/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
 /*
 URL := https://leetcode.com/problems/advantage-shuffle/
 870. Advantage Shuffle

Comlexity
Let M := len(nums1) = len(nums2)
Time := O(MlgM) ( sorting ) 
Space := O(M) ( EXP ) O(1) ( IMP ) 

Let nums2 be of a fixed length as well ( do not delta ) 
NO assume either has unique numbers

DEQ idea ( double ended queue ) 
Greedy property idea
Wow test cases pass -> but too longggg
How to do faster

Testcases passed, but took too long. ==> wooow
Pointers faster ( shift and pop slower ) 

 */
var advantageCount = function(nums1, nums2) {
    var m = nums1.length
    var bestPerm = Array(m).fill(-1) // -1 should never occur ( default testing ) 
    var sortOne = []
    var sortTwo = []
    nums1.forEach((element,index) => {
        sortOne.push([element,index])
    });
    nums2.forEach((element,index) => {
        sortTwo.push([element,index])
    });
    sortOne.sort((a,b) => {
        if(a[0] < b[0]){
            return -1
        } else if ( a[0] > b[0]) {
            return 1
        } else {
            return (a[1] < b[1])
        }
    })
    sortTwo.sort((a,b) => {
        if(a[0] < b[0]) {
            return -1
        } else if ( a[0] > b[0]) {
            return 1
        } else {
            return (a[1] < b[1])
        }
    })
    var ptr1 = 0
    var ptr2Left = 0
    var ptr2Right = m - 1
    while(ptr1 < m) {
        var firstEl = sortOne[ptr1]
        var secondEl = sortTwo[ptr2Left]
        var lastSecondEl = sortTwo[ptr2Right]
        if(firstEl[0] > secondEl[0]) {
             bestPerm[secondEl[1]] = firstEl[0]
             ptr2Left++
        } else {
             bestPerm[lastSecondEl[1]] = firstEl[0]
             ptr2Right--
        }
        ptr1++
    }
    return bestPerm
};
