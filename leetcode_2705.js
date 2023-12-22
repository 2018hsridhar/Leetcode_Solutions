/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
 /*
 URL := https://leetcode.com/problems/compact-object/?envType=study-plan-v2&envId=30-days-of-javascript
 Arrays are obj -> indices = keys
 2705. Compact Object
 JSON Object handling

Compactify objects : save on space
removal of `falsy` values
JS -> test handling of nestedness

Recursive method

 */
var compactObject = function(obj) {
    var newObj = {}
    newObj = compactifyObj(obj)
    return newObj
};

function isObject(o) {
  return o !== null && typeof o === 'object' && Array.isArray(o) === false;
}

function isArray(val) {
    // return val instanceOf Array;
    // return val !== null && Array.isArray(val) === true; 
    return val !== null && val.constructor === Array;
}

/* 
To remove from initial object OR to create brand new?
Remove all values?
    [] : falsy value ( https://stackoverflow.com/questions/33567147/empty-array-is-falsy-yet-0-1-evaluates-to-0 ) 

Multiple return type support?
10/56 test cases passing

23 minutes passing
gaaah variadic return types here
*/
function compactifyObj(origObj){
    var isObjArray = isArray(origObj)
    var compactArray = []
    var compactObj = {}
    Object.entries(origObj).forEach((entry) => {
        var key = entry[0]
        var val = entry[1]
        if(isObject(val) || isArray(val)) {
            var nestedObj = compactifyObj(val)
            if(Boolean(nestedObj) === true) {
                if(isObjArray) {
                    compactArray.push(nestedObj)
                } else {
                    compactObj[key] = nestedObj
                }
            }
        } else {
            if(Boolean(val) === true) {
                if(isObjArray) {
                    compactArray.push(val)
                } else {
                    compactObj[key] = val
                }
            }
        }
    })
    if(isObjArray) {
        return compactArray
    }
    return compactObj
}
