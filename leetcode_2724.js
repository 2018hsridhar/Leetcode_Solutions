/**
 * @param {Array} arr
 * @param {Function} fn
 * @return {Array}
 */
 /*
 URL = https://leetcode.com/problems/sort-by/description/?envType=study-plan-v2&envId=30-days-of-javascript
 2724. Sort By

 */
var sortBy = function(arr, fn) {
    // sort by the order of the function fn here
    // Pass a function to map
    // array is a JSON array : not an int arr -> be aware. Apply on each element of the array too!
    // fn is correctly applied -> just how to sort, based on the other elemnets properly.
    // sortedArr = arr.map(fn)
    // console.log(sortedArr)
    // return sortedArr.sort()

    // Lambda notation passes scope better
    arr.sort((a,b) => {
        if(fn(a) < fn(b)) {
            return -1
        } else if ( fn(a) > fn(b)) {
            return 1
        } else {
            return 0
        }
    });

    return arr
};
