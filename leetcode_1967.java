/*

1967. Number of Strings That Appear as Substrings in Word
URL = https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/

Return number of strings in patterns existing as a substring in word
Typical frequency counting/map problem
Contiguous sequence of characters! ( it is NOT a subsequence )
100 max for 
(I) # of pattenrs
(II) the length of the patterns and the length of words
(III) All are [[:lower:]] too.


*/
class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int numOcqs = 0;
        for(String pattern : patterns)
        {
            if(word.contains(pattern))
                numOcqs++;
            
        }
        return numOcqs;
    }
}
