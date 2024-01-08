/**
 * @param {number[]} height
 * @return {number}
 */
 /*
42. Trapping Rain Water
URL := https://leetcode.com/problems/trapping-rain-water/

Approach is linear scan both directions and greedy 
Get max on left, max on right ever observed and peg to that
We can always fill units up to the biggest buildings

Let N := #-buildings
Time := O(N)
Space := O(N) ( EXP ) O(1) ( IMP ) 

Yep still recall solution well :-)

 */
var trap = function(height) {
    let volTrapped = 0
    let n = height.length
    let maxOnLeft = Array(n).fill(-1);
    let maxOnRight = Array(n).fill(-1);
    // [1] Left -> Right
    let leftMax = -1
    for(let i = 0; i < n; i++) {
        let curHeight = height[i]
        leftMax = Math.max(leftMax,curHeight)
        maxOnLeft[i] = leftMax
    }
    // [2] Right -> Left
    let rightMax = -1
    for(let i = n-1; i >= 0; i--) {
        let curHeight = height[i]
        rightMax = Math.max(rightMax,curHeight)
        maxOnRight[i] = rightMax
    }
    for(let i = 0; i < n; i++) {
        let maxSeenHeight = Math.min(maxOnLeft[i], maxOnRight[i])
        let build = height[i]
        if(maxSeenHeight != -1 && maxSeenHeight > build) {
            volTrapped += maxSeenHeight - build
        }
    }
    return volTrapped
};
