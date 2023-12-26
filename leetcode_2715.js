/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
 /*
 5 mins
 URL := https://leetcode.com/problems/timeout-cancellation/description/?envType=study-plan-v2&envId=30-days-of-javascript
2715. Timeout Cancellation

 The cancel function pattern
 Expect cancelFn invocation after a time delay

Cancellation of a delayed invocation

Promises : one func fires after another func fires ( sync behavior )


 */
var cancellable = function(fn, args, t) {
    // Once only call
    var timeoutId = setTimeout(fn,t,...args)
    function cancelFunc(){
        // cancel previous established timeout
        clearTimeout(timeoutId)
    }
    return cancelFunc
};

/**
 *  const result = [];
 *
 *  const fn = (x) => x * 5;
 *  const args = [2], t = 20, cancelTimeMs = 50;
 *
 *  const start = performance.now();
 *
 *  const log = (...argsArr) => {
 *      const diff = Math.floor(performance.now() - start);
 *      result.push({"time": diff, "returned": fn(...argsArr)});
 *  }
 *       
 *  const cancel = cancellable(log, args, t);
 *
 *  const maxT = Math.max(t, cancelTimeMs);
 *           
 *  setTimeout(cancel, cancelTimeMs);
 *
 *  setTimeout(() => {
 *      console.log(result); // [{"time":20,"returned":10}]
 *  }, maxT + 15)
 */*
