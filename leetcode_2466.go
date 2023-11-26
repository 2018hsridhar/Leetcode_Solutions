/**
 * @param {number} low
 * @param {number} high
 * @param {number} zero
 * @param {number} one
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/count-ways-to-build-good-strings/
 2466. Count Ways To Build Good Strings

Approach -> Recursive, Bottom-Up DP, Combinatorial

 Complexity
 Let L := low, H := high
 Time := O(H)
 Space := O(H) (EXP) O(1) (IMP)

 15 mins to solution ( BUDP :-) )
 */
var countGoodStrings = function(low, high, zero, one) {
    let modulo = Math.pow(10,9) + 7;
    let memo = Array(high+1).fill(0); // Array fill method
    // zero,one <= low. Init to start the algorithm
    for(let i = 1; i <= high; i++) {
        let zeroPrefixCount = 0;
        let onePrefixCount = 0;
        if(i - zero > 0) {
            zeroPrefixCount = memo[i-zero];
        }
        if(i - one > 0) {
            onePrefixCount = memo[i-one];
        }
        let exactCount = 0;
        if(i - zero == 0) {
            exactCount += 1;
        }
        if(i - one == 0) {
            exactCount += 1;
        }
        let numLocalStrings = (zeroPrefixCount + onePrefixCount + exactCount) % modulo;
        memo[i] = numLocalStrings;
    }
    let numGoodStrings = 0;
    for(let i = low; i <= high; i++) {
        numGoodStrings += memo[i];
        numGoodStrings %= modulo;
    }
    return numGoodStrings;
};
