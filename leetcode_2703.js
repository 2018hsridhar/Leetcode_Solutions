/*
URL := https://leetcode.com/problems/return-length-of-arguments-passed/
2703. Return Length of Arguments Passed
*/

/**
 * @return {number}
 */
 // Spread operator : takes a varaidic number of args 
var argumentsLength = function(...args) {
    countArgs = 0
    for ( i = 0; i < args.length; i++ ) {
        countArgs++
    }
    return countArgs
};

/**
 * argumentsLength(1, 2, 3); // 3
 */
