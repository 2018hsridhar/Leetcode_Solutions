/**
 * @param {string[]} logs
 * @return {string[]}
 */
 /*
 937. Reorder Data in Log Files
 URL := https://leetcode.com/problems/reorder-data-in-log-files/

Compleity
Let L := #-letter Logs
Let D := #-digit logs

Time := O(LlogL) + O(L+D)
Space := O(L+D) ( EXPLICIT ) O(1) ( IMPLICIT ) 

var to store result of a single function call in a variable?
var within function scope too?

Approach : String Parsing, Regexes, Lexicographic Sorting

 */
var reorderLogFiles = function(logs) {
    var reorderedLogs = []; // array as empty splice init?
    var letterLogs = [];
    var digitLogs = [];
    // for of loop : range loop array
    // for in loop : range loop/enumerate object properties
    for(let log of logs) {
        // Greedy Regex ( safer than single Space Regex )
        var logTokens = log.split(/(\s+)/);
        allWordsLowerCase = true;
        allWordsDigit = true;
        for(let i = 1; i < logTokens.length; i++) {
            var logToken = logTokens[i];
            if(isNumeric(logToken)){
                allWordsLowerCase = false;
            } else if ( !isNumeric(logToken)) {
                allWordsDigit = false;
            }
        }
        if(allWordsLowerCase) {
            letterLogs.push(log);
        } else {
            digitLogs.push(log);
        }
    }
    // console.log("letter logs = ");
    // logArray(letterLogs);
    // console.log("digit logs = ");
    // logArray(digitLogs);
    // Log lengths can vary as well -> not standardized
    letterLogs.sort(function(a,b) {
        let aTokens = a.split(/(\s+)/);
        let bTokens = b.split(/(\s+)/);
        let ptr = 1;
        let m = Math.min(aTokens.length, bTokens.length);
        while(ptr < m) {
            let conA = aTokens[ptr];
            let conB = bTokens[ptr];
            let strCmp = conA.localeCompare(conB);
            if(strCmp != 0) {
                return strCmp;
            }
            ptr++;
        }
        if(ptr < aTokens.length) {
            return 1;
        }
        else if ( ptr < bTokens.length) {
            return -1;
        } else { // now by the identifier
            let idA = aTokens[0];
            let idB = bTokens[0];
            // strigns compared in current locale
            return idA.localeCompare(idB);
        }
    })
    // Push is variadic : leverage spread operator
    // Spread to pass entire array as n-args pattern
    reorderedLogs.push(...letterLogs);
    reorderedLogs.push(...digitLogs);
    return reorderedLogs;
};

/*
No good suport to console log entire array in JS
*/
function logArray(array) {
    for(let i = 0; i < array.length; i++){
        console.log(array[i]);
    }

}

function isNumeric(s) {
    let isNum = /^\d+$/.test(s);
    return isNum;
}
