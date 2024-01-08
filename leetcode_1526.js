/**
 * @param {number[]} target
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 1526. Minimum Number of Increments on Subarrays to Form a Target Array
 
 Complexity
 Let N := #-integers to test for
 Time := O(N)
 Space := O(1) ( EXP ) O(1) ( IMP ) 

Wow
The solution was really easy
The conceptualization was the harder part

 */
var minNumberOperations = function(target) {
    let minOps = 0;
    let prevStep = 0;
    target.forEach((curStep) => {
        if(curStep > prevStep) {
            let delta = curStep - prevStep;
            minOps += delta;
        }
        prevStep = curStep;
    })
    return minOps;  
};
