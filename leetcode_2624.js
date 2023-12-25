/**
 * @param {number} rowsCount
 * @param {number} colsCount
 * @return {Array<Array<number>>}
 */
 /*
 URL := https://leetcode.com/problems/snail-traversal/
 2624. Snail Traversal

 It is a prototype - of array class itself
 how to print out the elements of itself

 Complexity
 Let M, N := #-rows & #-cols
 Time := O(MN)
 Space := O(MN) ( EXP ) O(1) ( IMP ) 

 All values positive for nums
 dimension->dimension input transformations
 Transformations preserve original ordering of elements.

 */
Array.prototype.snail = function(rowsCount, colsCount) {
    var n = this.length
    if(rowsCount * colsCount != n) {
        return []
    }
    var sto = Array(rowsCount).fill(-1).map(() => Array(colsCount).fill(-1))
    var steps = [1,-1]
    var counter = 0
    var stepIdx = 0
    var rW = 0
    var cW = 0
    for(var i = 0; i < n; i++) {
        var curEl = this[i]
        sto[rW][cW] = curEl
        rW = rW + steps[stepIdx]
        counter++
        if(counter > 0 && counter % rowsCount == 0) {
            stepIdx = 1 - stepIdx // easy complement one toggling
            rW = rW + steps[stepIdx]
            cW++
        }
    }
    return sto
}

/**
 * const arr = [1,2,3,4];
 * arr.snail(1,4); // [[1,2,3,4]]
 */
