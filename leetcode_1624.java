/*
1624. Largest Substring Between Two Equal Characters
URL = https://leetcode.com/problems/largest-substring-between-two-equal-characters/

Edge Cases
(A) "abbaba" => 4
(B) "aa" => 0
(C) "a" => -1
(D) "cbzxy" => -1
(E) "abaabaaaabbbbba" => 13
(F) 

*/
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        // not the concept of compilers warning of uninitialied memory 
        int maxLen = -1;
        for(char c = 'a'; c <= 'z'; ++c){
            boolean hitC = false;
            int lPtr = 0;
            int rPtr = 0;
            int n = s.length();
            while(rPtr < n){
                char curC = s.charAt(rPtr);
                if(curC == c){
                    if(!hitC){
                        hitC = true;
                        lPtr=rPtr;
                    } else {
                        int curDist = (rPtr-lPtr-1);
                        maxLen = Math.max(maxLen,curDist);
                    }
                }
                ++rPtr;
            }
        }
        return maxLen;
    }
}
