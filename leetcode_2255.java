/*
2255. Count Prefixes of a Given String
URL = https://leetcode.com/problems/count-prefixes-of-a-given-string/
*/
\class Solution {
    public int countPrefixes(String[] words, String s) {
        int prefixCount = 0;
        for(String el : words){
            if(isPrefix(s,el)){
                prefixCount += 1;
            }
        }
        return prefixCount;
    }
    
    private boolean isPrefix(String s, String word)
    {
        int lPtr = 0;
        int rPtr = 0;
        int sLen = s.length();
        int wLen = word.length();
        if(wLen > sLen)
            return false;
        while(lPtr < sLen && rPtr < wLen){
            if(s.charAt(lPtr) == word.charAt(rPtr)){
                lPtr++;
                rPtr++;
            } else {
                return false;
            }
        }
        return true;
    }
}
