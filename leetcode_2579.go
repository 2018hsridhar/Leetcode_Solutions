/**
 * @param {number} n
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/count-total-number-of-colored-cells/
 2579. Count Total Number of Colored Cells

Complexity
Time := O(1)
Space := O(1) ( EXP ) O(1) (IMP)
 */
var coloredCells = function(n) {
    // Must explicitly mention * operator too
    let numColorCells = (2 * ( (n-1) + (n-1)*(n-2)) ) + (2*n - 1);
    return numColorCells;
};
