/*
2695. Array Wrapper
URL = https://leetcode.com/problems/array-wrapper/description/
*/
/**
 * @param {number[]} nums
 */
var ArrayWrapper = function(nums) {
    this.arr = nums // seems like other OOP languages
};

ArrayWrapper.prototype.valueOf = function() {
    sum = 0
    for ( var i = 0; i < this.arr.length; i++ ) {
        sum += this.arr[i]
    }
    return sum
}

// Array or array-like object?
// Like other PLs -> allow for operator syntax on string cocnat
ArrayWrapper.prototype.toString = function() {
    return "[" + this.arr.join() + "]"
}

/**
 * const obj1 = new ArrayWrapper([1,2]);
 * const obj2 = new ArrayWrapper([3,4]);
 * obj1 + obj2; // 10
 * String(obj1); // "[1,2]"
 * String(obj2); // "[3,4]"
 */
