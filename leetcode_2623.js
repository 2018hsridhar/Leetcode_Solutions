/**
 * @param {Function} fn
 */
 // can stringify the args in itself instead. Avoid spread operator syntax
function memoize(fn) {
    let cache = {};
    // Expands iterables in place too
    // Spread op : in a function call
    return function(...args) {
        argString = (args).toString()
        // console.log(args)
        // console.log(...args)
        if(argString in cache) {
            return cache[argString]
        } else {
            let val = fn(...args)
            cache[argString] = val
            return val
        }
        
    }
}

// // a simple memoized function to add something
// const memoizedAdd = () => {
//   let cache = {};
//   return (n) => {
//     if (n in cache) {
//       console.log('Fetching from cache');
//       return cache[n];
//     }
//     else {
//       console.log('Calculating result');
//       let result = n + 10;
//       cache[n] = result;
//       return result;
//     }
//   }
// }



/** 
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1 
 */
