/*
2108. Find First Palindromic String in the Array
URL = https://leetcode.com/problems/find-first-palindromic-string-in-the-array/
*/
class Solution {
    public String firstPalindrome(String[] words) {
        for(String x : words){
            if(isPalin(x)){
                return x;
            }
        }
        return "";
    }
    
    private boolean isPalin(String x) {
        int lPtr = 0;
        int rPtr = x.length() - 1;
        while(lPtr < rPtr) {
            if(x.charAt(lPtr) == x.charAt(rPtr))
            {
                lPtr++;
                rPtr--;
            } else {
                return false;
            }
        }
        return true;
    }
    
}
