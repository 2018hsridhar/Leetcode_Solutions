/**
 * @param {Array} arr1
 * @param {Array} arr2
 * @return {Array}
 */
 /*
 URL := https://leetcode.com/problems/join-two-arrays-by-id/description/?envType=study-plan-v2&envId=30-days-of-javascript
 2722. Join Two Arrays by ID

 Value ( arr2 ) overrides value ( arr 1 ) 
 can we hashmap this and rederive the result latter

 */
var join = function(arr1, arr2) {
    var myRetArray = Array()
    // arr1.forEach((el) => console.log(el.id))
    // arr1.forEach((el) => console.log(el.x))
    var myOutputMap = new Map()
    arr1.forEach(arrObject1 => {
        var myIdValue1 = arrObject1.id
        if(!myOutputMap.has(myIdValue1)) { // ids unique : may not even be needed
            myOutputMap.set(myIdValue1, arrObject1)
        }
    })
    // console.log(myOutputMap)
    arr2.forEach(arrObject2 => {
        var myIdValue2 = arrObject2.id
        if(myOutputMap.has(myIdValue2)) {
            var curObject = myOutputMap.get(myIdValue2)
            var futureObject = arrObject2
            var futureObjectMap = new Map()
            Object.entries(curObject).forEach(el => {
                var keyEl = el[0]
                var valEl = el[1]
                if(keyEl !== 'id') {
                    if(!arrObject2.hasOwnProperty(keyEl)) {
                        // Object.defineProperty(arrObject2,keyEl,valEl)
                        arrObject2[keyEl] = valEl
                    }
                }
            });
            myOutputMap.set(myIdValue2,arrObject2) 
        } else {
            myOutputMap.set(myIdValue2,arrObject2) 
        }
    })
    // dear lord  ... this much : lamda function for sort in either map
    sortedKeysMap = new Map([...myOutputMap].sort((a, b) => Number(a[0]) - Number(b[0])))
    for(let pair of sortedKeysMap) {
        myRetArray.push(pair[1])
    }
    return myRetArray
};
