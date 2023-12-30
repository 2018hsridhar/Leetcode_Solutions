/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number} k
 * @return {number}
 */
 /*
URL := https://leetcode.com/problems/maximum-subsequence-score/
2542. Maximum Subsequence Score

Algorithms Type : Greedy, Sorting, PriorityQueue/Heap

 Complexity
 Let N := len(input)
 Time := O(NlgN)+O(NlgK)
 Space := O(K) ( EXP ) O(1) ( IMP ) 

 */
var maxScore = function(nums1, nums2, k) {
    var myMaxScore = 0
    // Zip two arrays via map function ( element, index ) style notation
    toSortArr = nums1.map((element,index) => {
        return [element, nums2[index]];
    });
    // Gaaah sort returns numeric type here
    toSortArr.sort((a,b) => {
        if(a[1] < b[1]) {
            return 1
        } else if ( a[1] > b[1]) {
            return -1
        } else {
            return a[0] > b[0]
        }
    })
    var myRunValSum = 0
    // PQ in Javascript nonTrivial
    // https://www.npmjs.com/package/@datastructures-js/priority-queue#size ( had to use this ) 
    const numbersMaxQueue = new MinPriorityQueue();
    toSortArr.forEach((pair) => {
        var myNewVal = pair[0]
        var myNewMin = pair[1]
        if(numbersMaxQueue.size() < k) {
            myRunValSum += myNewVal 
            numbersMaxQueue.enqueue(myNewVal)
            // console.log(numbersMaxQueue)
        } else {
            myRunValSum += myNewVal 
            numbersMaxQueue.enqueue(myNewVal)
            // Priority Queue object dequeue weirdness going on here GAAH
            var deqVal = numbersMaxQueue.dequeue().element
            // console.log(deqVal)
            myRunValSum -= deqVal
            // Dequeue after enqueu-ing new val ( it may be a worse val in the end )
            // Renders a NOP to solve new min anyways too
        }
        if(numbersMaxQueue.size() === k) {
            var myCurScore = myRunValSum * myNewMin
            myMaxScore = Math.max(myMaxScore,myCurScore)
        }
    })
    return myMaxScore
};
