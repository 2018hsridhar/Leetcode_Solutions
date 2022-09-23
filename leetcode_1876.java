/*
1876. Substrings of Size Three with Distinct Characters
URL = https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/

*/
class Solution {
    public int countGoodSubstrings(String s) {
        if(s.length() < 3) {
            return 0;
        }
        int freq = 0;
        int[] digFreq = new int[26];
        int lPtr = 0;
        int rPtr = 0;
        int n = s.length();
        while(rPtr < 3) {
            char c = s.charAt(rPtr);
            int idx = (int)(c - 'a');
            digFreq[idx]++;
            ++rPtr;
        }
        if(checkGood(digFreq)){
            ++freq;
        }
        while(rPtr < n){
            char c = s.charAt(rPtr);
            int idx = (int)(c - 'a');
            digFreq[idx]++;
            char d = s.charAt(lPtr);
            int idx2 = (int)(d - 'a');
            digFreq[idx2]--;
            if(checkGood(digFreq)){
                ++freq;
            }
            ++rPtr;
            ++lPtr;
        }
        return freq;
    }
    
    private boolean checkGood(int[] digFreq)
    {
        for(int x : digFreq) {
            if(x >= 2) {
                return false;
            }
        }
        return true;
    }
}
