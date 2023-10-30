/**
 * @param {Function} fn
 * @return {Object}
 */
 /*
 Enhance the built-in array
 https://www.w3schools.com/jsref/jsref_prototype_array.asp
fn : item => item.id
use hashmap in javascript too?
any keys order : value list should be "as-is" though
Map as an object :-) Can return this too
 */
Array.prototype.groupBy = function(fn) {
    res = null;
    var myGroups = {};
    for(let i = 0; i < this.length; i++) {
        let item = this[i];
        key = fn(item);
        // check if key belongs in an object ( hashmap as object ) ?
        if(!(key in myGroups)){
            myGroups[key] = []; // https://stackoverflow.com/questions/10451893/javascript-create-simple-dynamic-array
        }
        myGroups[key].push(item);
    }
    return myGroups;
};

/**
 * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
 */
