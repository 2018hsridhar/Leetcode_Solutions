/**
 * @param {number[][]} items
 * @param {number[]} queries
 * @return {number[]}
 */
 /*
 URL := https://leetcode.com/problems/most-beautiful-item-for-each-query/
 2070. Most Beautiful Item for Each Query

Ok sort the items -> by (a) Price and then (b) By the beauty of each item -> Lexicographic
Max does seem like a running sum though?
Easier to solve if we sort the queries ( but index info preservation )? 

Tradeoff : additional space over binary searching across the items.
For each query value -> get set of indices they are in
Sort and process the keys in the query val structure :-)

Complexity
Let I := #-items
Let Q := #-queries
Time := O(IlgI) + O(Q)
Space := O(1) ( EXP ) O(___) ( IMP ) 

30 mins spent and feeling lethargy now
 */
var maximumBeauty = function(items, queries) {
    let n = queries.length;
    maxBeauty = Array(n).fill(0); // Array(length) constructor
    items.sort(function(a,b){ 
        if(a[0] > b[0]) {
            return 1;
        } else if ( a[0] < b[0]) {
            return -1;
        } else {
            return a[1] < b[1];
        }
    });
    // console.log("Items = ");
    // console.log(items);
    let queryMap = new Map(); 
    // Cool equiv to python .enumerate(...)
    for (let [index, q] of queries.entries()) {
        if(!queryMap.has(q)) {
            queryMap.set(q,[]);
        }
        queryMap.get(q).push(index); // push to add to an array
    }
    // console.log(queryMap);
    // failure to sort map iterator?
     //function(a,b) { return Number(a) - Number(b);}));
     // why didn't other methods work. really this explicit in the sort? GAAAAH JS
    // var ascQueryMap = new Map([...queryMap.entries()].sort((a,b) => a - b)); // not working
    var ascQueryMap = new Map([...queryMap.entries()].sort((a,b) => a[0] - b[0]));
    keys = ascQueryMap.keys(); 
    // keys.sort(); // sort not def on hashmap keys in JS?
    // keys.sort(function(a,b) { return a - b}); // ensure by numerical val
    // console.log(keys);
    let ptr = 0;
    let i = items.length;
    // sorted - once one hit, all hit too
    curBeauty = 0; // default if now val available
    for ( let qKey of keys) { // of keyword - not in
        let qKeyIdxs = queryMap.get(qKey);
        // console.log("Testing qKey = %d\n", qKey);``
        while(ptr < i) {
            curItem = items[ptr];
            if(curItem[0] <= qKey) {
                curBeauty = Math.max(curBeauty, curItem[1]);
            } else {
                break;
            }
            ptr++;
        }
        for(const idx of qKeyIdxs) {
            maxBeauty[idx] = curBeauty;
        }
    }
    return maxBeauty;
};
