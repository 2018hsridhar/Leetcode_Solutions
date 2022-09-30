/*
2399. Check Distances Between Same Letters
URL = https://leetcode.com/problems/check-distances-between-same-letters/
Leverage each letter in s appearing EXACTLY TWICE
And that we use a 0-indexed integer array too!

*/
class Solution {
    public boolean checkDistances(String s, int[] distance) {
        boolean status = true;
        for(int i = 0; i < 26; ++i){
            char letter = (char)(i + 'a');
            boolean hitLetter = false;
            int lPtr = 0;
            int rPtr = 0;
            while(rPtr < s.length()){
                char c = s.charAt(rPtr);
                if(c == letter){
                    if(!hitLetter){
                        hitLetter = true;
                        lPtr = rPtr;
                    } else if ( hitLetter){
                        int curDist = rPtr - lPtr - 1;
                        if(curDist != distance[i]){
                            status = false;
                            break;
                        }
                    }
                }
                ++rPtr;
            }
        }
        return status;
    }
}
