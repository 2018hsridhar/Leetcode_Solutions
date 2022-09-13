/*
1796. Second Largest Digit in a String
URL = https://leetcode.com/problems/second-largest-digit-in-a-string/

return the second largest numerical digit.
The string is an alphanumeric string : [[:lower:]] or [[:digit:]]


*/
class Solution {
    public int secondHighest(String s) {
        // Remember : digits appear only 0->9 here
        // Check if we have a hit too
        int[] digits = new int[10];
        for(int i = 0; i < digits.length; ++i)
        {
            digits[i] = -1; // indicates not existing!
        }
        char[] c_arr = s.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char ch = c_arr[i];
            if(isDigit(ch)) {
                int val = (int)(ch - '0');
                digits[val] = 0; // digit exists
            }
        }
        
        // Iterate right->left in hashmap
        int curMax = -1;
        boolean hitFirstMax = false;
        // start i from 9 until 0 here
        for(int i = digits.length - 1; i >= 0; --i)
        {
            if(digits[i] == 0)
            {
                if(!hitFirstMax) {
                    hitFirstMax = true;
                }
                else if ( hitFirstMax) {
                    curMax = i;   
                    break;
                }
            }
        }
        
        return curMax;
    }
    
    private boolean isDigit(char ch) {
        return ('0' <= ch && ch <= '9');
    }
}
