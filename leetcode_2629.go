/**
 * @param {Function[]} functions
 * @return {Function}
 */
 /*
 URL = https://leetcode.com/problems/function-composition/description/
 2629. Function Composition

 */
var compose = function(functions) {
    // Apply this binding and get accesibiliyty in returned function? HUH?
    // let myFunctions = functions
	return function(x) {
        // functions is an array here
        // return a composition of functions here
        // console.log(myFunctions.length)
        if (functions.length == 0) {
            return x
        } else {
            ans = x
            for(i = functions.length - 1; i >= 0; i--) {
                ans = functions[i](ans)
            }
            return ans
        }
        return 0
    }
};

/**
 * const fn = compose([x => x + 1, x => 2 * x])
 * fn(4) // 9
 */
