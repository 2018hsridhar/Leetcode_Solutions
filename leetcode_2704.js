/*
URL = https://leetcode.com/problems/to-be-or-not-to-be/description/?envType=study-plan-v2&envId=30-days-of-javascript
2704. To Be Or Not To Be
*/
/**
 * @param {string} val
 * @return {Object}
 */
var expect = function(val) {
    var myObject = {
        toBe(otherVal) {
            if(val === otherVal) {
                return true
            } else {
                throw new Error("Not Equal")
            }
        }, 
        notToBe(otherVal) {
            if(val !== otherVal){
                return true
            } else {
                throw new Error("Equal")
            }
        }
    };
    return myObject
};

/**
 * expect(5).toBe(5); // true
 * expect(5).notToBe(5); // throws "Equal"
 */
