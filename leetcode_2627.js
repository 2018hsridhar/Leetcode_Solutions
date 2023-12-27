/**
 * @param {Function} fn
 * @param {number} t milliseconds
 * @return {Function}
 */
 /*
2627. Debounce
URL := https://leetcode.com/problems/debounce/description/?envType=study-plan-v2&envId=30-days-of-javascript

 The function is called at a time, with a given delay in t milliseconds
 Do we know the time a function starts though?

 Like a queue -> clear out ( all prior funcs ) ( or the first prior func )?
 How to transform events?

clearTimeout() stops a function call
setTimeout() no spawn new threads : single-threaded only

15 minutes spent
Debounce ensures single execution event only
Need closures to do this problem :-)

 */
var debounce = function(fn, t) {
    // function scope the intervalsIds ( window obj no def no global scope ) 
    var myIntervalIds = []
    return function(...args) {
        // clear what's in the intervals : if you end up making call later -> func already called
        while(myIntervalIds.length > 0) {
            let earliestId = myIntervalIds.shift()
            clearTimeout(earliestId)
        }
        // then call next function
        // Pass the ...args ( implicit here )
        intervalId = setTimeout(fn,t, ...args) // intervalId always Z+
        myIntervalIds.push(intervalId)
    }
};

/**
 * const log = debounce(console.log, 100);
 * log('Hello'); // cancelled
 * log('Hello'); // cancelled
 * log('Hello'); // Logged at t=100ms
 */
