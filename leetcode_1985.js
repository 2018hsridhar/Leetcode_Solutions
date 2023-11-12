/**
 * @param {string[]} nums
 * @param {number} k
 * @return {string}
 */
 /*
 Count duplicates distinctly - not seperately
 Problem seems easy
 No leading 0 values
 Can we dictionary sort our input
 Only issue is edge case of negative integers : if all positive, we good here
 But no signs -> all digits only
 Wow numeric system also dictionary order
 Nope not dictionary order -> please compare element by element too

 Complexity
 Let N := len(nums)
 Time := O(nlgn)
 Space := O(1) ( EXP ) O(1) ( IMP ) 

 218/219
 Precision errors?

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/BigInt

 */
var kthLargestNumber = function(nums, k) {
    // Number() class
    nums.sort((a,b) => BigInt(a) < BigInt(b) ? -1 : 1);
    // nums.sort(); // use directly : no `localCompare()`
    // console.log(nums);
    var rPtr = nums.length - k;
    var kth = nums[rPtr];
    return kth;
};
