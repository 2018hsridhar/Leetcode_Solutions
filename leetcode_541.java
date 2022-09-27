/*
541. Reverse String II
URL = https://leetcode.com/problems/reverse-string-ii/
Goal : Code via passing of StringBuilders.

*/
class Solution {
    public String reverseStr(String s, int k) {
        // Notice use of `res` to avoid multiple copies of a string.
        StringBuilder res = new StringBuilder(s); // ask if a memcpy step takes place here
        int i = 0;
        int n = s.length();
        while(i < n){
            rev(res,i,k);
            i += (2 * k);
        }
        return res.toString();
        // return s; // return the parameter passed in itself ( this is weird )
    }
    
    private void rev(StringBuilder s, int i, int k){
        int n = s.length();
        if(i+(k-1) >= n)
            helper(s,i,s.length() - 1);
        else
            helper(s,i,i+(k-1));
    }
    
    private void helper(StringBuilder s, int left, int right){
        while(left < right){
            swap(s,left++,right--);
        }
    }
    
    private void swap(StringBuilder s, int i, int j){
        char temp = s.charAt(i);
        s.setCharAt(i,s.charAt(j));
        s.setCharAt(j,temp);
    }
    
}
