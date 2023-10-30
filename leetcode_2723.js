/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */

/*
2723. Add Two Promises
URL := https://leetcode.com/problems/add-two-promises/

Already know that promise1, promise 2 both resolve to a value
return a new promise : async await type of functionality
Get value of Promise object : assign to a var for future use
*/

var addTwoPromises = async function(promise1, promise2) {
    sum = 0;
    promise1.then(valOne => {
        sum += valOne;
    })
    promise2.then(valTwo => {
        sum += valTwo;
    })

    // Give 1 second for both promises above to execute
    // NO need to sepcify the reject here as well? Huh?
    // How to avoid TLE. Ensure the first two promises finish their evaluations as well.
    const promise = new Promise(resolve => setTimeout(() => resolve(sum), 200));
    return promise;
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */
