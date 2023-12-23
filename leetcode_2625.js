/**
 * @param {Array} arr
 * @param {number} depth
 * @return {Array}
 */
 /*
 URL := https://leetcode.com/problems/flatten-deeply-nested-array/?envType=study-plan-v2&envId=30-days-of-javascript
 2625. Flatten Deeply Nested Array
 - recursive data structure handling

Complexity
Let D := max depth of a given array
Let N := #-elements in the array
Time := O(N) ( must process each array element )
Space := O(D) ( IMP ) O(N) ( EXP )  

 */
var flat = function (arr, n) {
    var flattened = Array() // empty array
    var startLevel = 0
    flatten(flattened, arr, n,startLevel)
    return flattened
};

function isArray(val) {
    return (val !== null && Array.isArray(val) === true)
}

function flatten(result, arr, n, curLevel) {
    arr.forEach((el) => {
        if(isArray(el) && curLevel < n) {
                flatten(result,el,n, curLevel+1)
        } else {
            result.push(el)
        }
    })
}
