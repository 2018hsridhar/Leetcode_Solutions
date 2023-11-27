/**
 * @param {number[]} nums
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/count-nice-pairs-in-an-array/
 1814. Count Nice Pairs in an Array

Complexity
Let N := len(nums)
Time := O(N)
Space := O(N) ( EXP ) O(1) ( IMP ) 

38 mins GAAAH learning Javascript here

 */
var countNicePairs = function(nums) {
    let numPairs = 0;
    let modulo = Math.pow(10,9) + 7;
    let myMap = new Map();
    for(let i = 0; i < nums.length; i++) {
        let val = nums[i];
        let revVal = revOp(nums[i]);
        // %v is very GoLang
        // console.log("For val = %d \t revVal = %d", val, revVal);
        let delta = val - revVal;
        // Remember : i < j condition must hold as well!! ( DOOH ) 
        if(!myMap.has(delta)) {
            myMap.set(delta, 0);
        }
        myMap.set(delta, myMap.get(delta) + 1);
    }
    console.log(myMap);
    let keys = myMap.keys();
    // for(var key in myMap) {
    // JS maps not array based accesed []
    for(let key of keys) {
        // console.log("key = %d \t Fact-num = %d\n", myMap.get(key), fact(myMap.get(key)));
        numPairs += nnSum(myMap.get(key)-1);
    }
    return numPairs % modulo;
};

// No specif return val in param signature
function nnSum(num) {
    return num * (num + 1) * 0.5;
}

// Need Math package for floor
function revOp(num) {
    // let radix = Math.floor(Math.log10(num) + 1);
    // let pow = radix - 1;
    // let revToRet = 0;
    // while(num >= 10) {
    //     r = num % 10;
    //     num /= 10;
    //     revToRet += r * Math.pow(10,pow);
    //     pow -= 1;
    // }
    // revToRet += num * Math.pow(10,pow);
    // return revToRet;
    strNum = num.toString();
    revNum = strNum.split("").reverse().join("");
    return Number(revNum);
}
