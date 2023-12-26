/**
 * @param {number[]} nums
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/
 2870. Minimum Number of Operations to Make Array Empty

Seems easy -> a basic hashmap
Complexity
Time := O(N)
Space := O(N) ( EXP ) O(1) ( IMP ) 
Let N := len(nums)

 */
var minOperations = function(nums) {
    var minOps = 0
    var freqMap = new Map()
    nums.forEach((num) => {
        if(!freqMap.has(num)) {
            freqMap.set(num,0)
        }
        freqMap.set(num,freqMap.get(num) + 1)
    })
    var nOps = false
    // Need k,v syntax -> not for each syntax -> iter over map
    // https://stackoverflow.com/questions/16507866/how-to-iterate-over-objects-property-value-pairs 
    // entries() over .forEach() syntax style
    for ([k, v] of freqMap.entries()) {
    // freqMap.forEach((k,v) => {
        console.log("Key = %d \t Freq(key) = %d\n", k, v)
        if(v % 2 === 1) { // -3 or -2 test
            if(v - 3 < 0 ) {
                nOps = true
                // return -1
            } else {
                minOps += 1
                v -= 3
            }
        }
        // if v is even -> either even by 2 or even by 0 : 2*EVEN property
        // v now even
        // fill by 6 as much
        var numSixIn = Math.floor(v / 6)
        v -= (numSixIn * 6)
        minOps += (numSixIn*2)
        // fill by 2 now
        var numTwoIn = Math.floor(v/2)
        minOps += numTwoIn
    }
    if(nOps) {
        return -1
    }
    return minOps
};
