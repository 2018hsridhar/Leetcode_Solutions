/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number}
 */

 /*
 URL := https://leetcode.com/problems/find-the-winner-of-an-array-game/
 1535. Find the Winner of an Array Game

Approach : Sliding Window, Linear Tape Reads

(1) Leverage Constraints imposed by the game upon the game states :-) . 
(2) Leverage rules of inequalities

Complexity
Let N := len(arr)
Time := O(N)
Space := O(1) ( EXPLICIT ) O(1) ( IMPLICIT ) 

Ok almost there - 20 test cases failing ; investigate again

30 minutes to the solution WOOOOH

 */
var getWinner = function(arr, k) {
    let myWinner = -1; // always a winner ; such ret val be incorrect
    let ptr = 0;
    let n = arr.length;
    curVal = arr[ptr];
    let haveWinner = false;
    let runWinScore = 0;
    while(ptr < n) {
        let nextPtr = ptr + 1;
        if(nextPtr < n) {
            if(curVal < arr[nextPtr]) {
                curVal = arr[nextPtr];
                runWinScore = 1;
            } else if ( curVal > arr[nextPtr]) {
                runWinScore += 1;
            }
            if(runWinScore == k) {
                haveWinner = true;
                myWinner = curVal; 
                break;
            }
        } else if ( nextPtr >= n) {  // curVal infinite winner
            break;
        }
        ptr = ptr + 1;
    }
    if(!haveWinner) {
        myWinner = curVal; // de-facto this last number
    }
    return myWinner
};
