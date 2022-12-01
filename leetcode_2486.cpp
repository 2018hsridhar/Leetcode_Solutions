/*
URL = https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/
2486. Append Characters to String to Make Subsequence
Trip up @ definition of a subsequence

Complexity
Let S := len(s) and T := len(T)
Time = O(S+T)
SPACE = O(1) ( EXP ) O(1) ( IMP ) 

Cases
(A)
(B)
(C)

*/
class Solution {
public:
    int appendCharacters(string s, string t) {
        int numToAppend = 0;
        size_t sLen = s.size();
        size_t tLen = t.size();
        size_t sPtr = 0;
        size_t tPtr = 0;
        while(sPtr < sLen && tPtr < tLen){
            if(s[sPtr] == t[tPtr]){
                ++sPtr;
                ++tPtr;
            } else {
                sPtr++;
            }
        }
        numToAppend += (tLen - tPtr);
        return numToAppend;
    }
};
