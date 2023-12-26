/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
 /*
2725. Interval Cancellation
URL := https://leetcode.com/problems/interval-cancellation/

 Creating higher-order function
 Used for func currying : outer->inner funcs
 Set up for closures

 15 minutes
 Yo closure works

 args as a JSON array ( spread syntax )
 */
var cancellable = function(fn, args, t) {
    // no future call to clearInterval() or website closure
    // callback as a function in setInterval(...)
    // Call once at least -> start up function?
   fn(...args)
   let intervalId = setInterval(fn,t,...args) // default to 0 ( t )
   function cancelFn(){
       // closure idea : clearInterval once this is called?
       clearInterval(intervalId)
   }
   // ok : ret func -> no args passed : just name
   return cancelFn
};

/**
 *  const result = [];
 *
 *  const fn = (x) => x * 2;
 *  const args = [4], t = 35, cancelTimeMs = 190;
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
 *  setTimeout(cancel, cancelTimeMs);
 *   
 *  setTimeout(() => {
 *      console.log(result); // [
 *                           //     {"time":0,"returned":8},
 *                           //     {"time":35,"returned":8},
 *                           //     {"time":70,"returned":8},
 *                           //     {"time":105,"returned":8},
 *                           //     {"time":140,"returned":8},
 *                           //     {"time":175,"returned":8}
 *                           // ]
 *  }, cancelTimeMs + t + 15)    
 */
