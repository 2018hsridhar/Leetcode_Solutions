/*
URL = https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/
2414. Length of the Longest Alphabetical Continuous Substring

Sliding window technique ( classic ) 
Let n := len(s)
TIME = O(N)
SPACE = O(1) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int longestContinuousSubstring(string s) {
        int myMaxLen = 1; // default answer
        int lPtr = 0;
        int rPtr = 1;
        int n = s.size();
        while(rPtr < n){
            if(s[rPtr] - 'A' == s[rPtr-1] - 'A' + 1){
                rPtr++;
            } else {
                // printf("ptrs = [%d,%d]\n", lPtr, rPtr);
                int curLen = rPtr - lPtr;
                myMaxLen = std::max(myMaxLen, curLen);
                lPtr = rPtr;
                ++rPtr;
            }
        }
        myMaxLen = std::max(myMaxLen,rPtr-lPtr);
        return myMaxLen;
    }
};
