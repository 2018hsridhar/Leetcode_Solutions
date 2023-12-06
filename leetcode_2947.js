/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/count-beautiful-substrings-i/
 2947. Count Beautiful Substrings I

Devolved into a brute force counting approach
Let N := len(S)
Time := O(N^2)
Space := O(1) ( EXP & IMP ) 

 */
var beautifulSubstrings = function(s, k) {
    let numBeauty = 0;
    for(let i = 0; i < s.length; i++) {
        let consCount = 0;
        let vowCount = 0;
        for(let j = i; j < s.length; j++) {
            if(isVowel(s.charAt(j))) {
                vowCount++;
            } else if ( isConsonant(s.charAt(j))) {
                consCount++;
            }
            // Guard against both consCount, vowCount = 0
            // if(consCount > 0 && vowCount > 0 && consCount == vowCount && (consCount * vowCount) % k == 0) {
            if(consCount == vowCount && (consCount * vowCount) % k == 0) {
                numBeauty += 1;
            }
        }
    }
    return numBeauty;
};

// No return type in JS method signatures?
function isVowel(c) {
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
}

function isConsonant(c) {
    return !isVowel(c)
}
