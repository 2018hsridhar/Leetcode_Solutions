/*
2283. Check if Number Has Equal Digit Count and Digit Value
URL = https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value/

*/
class Solution {
    public boolean digitCount(String num) {
        char[] c_arr = num.toCharArray();
        int[] digits = new int[10]; // 0-init here : use as freq map
        int n = c_arr.length;
        for(int i = 0; i < n; ++i) {
            char c = num.charAt(i);
            int idx = (int)(c - '0');
            digits[idx]++;
        }
        
        for(int i = 0; i < n; ++i) {
            char c = num.charAt(i);
            int numUnderTest = i;
            int expFreq = (int)(c - '0');
            int actualFreq = digits[numUnderTest];
            if(expFreq != actualFreq)
                return false;
        }
        return true;
    }
}
