/**
 * @param {number[]} cardPoints
 * @param {number} k
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 1423. Maximum Points You Can Obtain from Cards

Approach -> Queue - CircularBuffer

Complexity
Let N := len(input)
Time := O(K) ( twice only ) 
Space := O(1) ( EXP ) O(1) ( IMP ) 

DP approach as cubic time gaah
Time taken = 22 minutes :-)

Queue even needed -> keep a sum -> no space !!!

 */
var maxScore = function(cardPoints, k) {
    let maxS = 0;
    let curS = 0; // let statement avoid noDef issue
    let n = cardPoints.length;
    let leftPtr = n - 1;
    let rightPtr = k-1;
    for(let i = 0; i < k; i++) {
        curS += cardPoints[i]; // k bound here as well :-)
    }
    maxS = curS;
    while(k > 0) {
        // elToRemove = queue.pop(); // remove last El of array
        let elToRemove = cardPoints[rightPtr];
        curS -= elToRemove;
        let elToAdd = cardPoints[leftPtr];
        curS += elToAdd;
        maxS = Math.max(maxS, curS);
        k--;
        rightPtr = (rightPtr - 1 + n) % n;
        leftPtr = (leftPtr - 1 + n) % n;
    }
    return maxS;
};
