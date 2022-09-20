/*
\Exists only two types of strings constructible.

URL = https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
1758. Minimum Changes To Make Alternating Binary String

*/
class Solution {
    public int minOperations(String s) {
        if(s.length() == 1) // BASE CASE
            return 0;
        // NON BASE CASES
        char[] c_arr = s.toCharArray();
        char expVal = c_arr[0];
        int zeroOff = getNumMismatches(c_arr,0);
        int oneOff = getNumMismatches(c_arr,1);
        return Math.min(oneOff,zeroOff);
    }
    
    public int getNumMismatches(char[] c_arr, int start)
    {
        int countOff = 0;
        int n = c_arr.length;
        int exp = start;
        for(int i = 0; i < c_arr.length; ++i)
        {
            if(c_arr[i] != (char)(exp + '0')) {
                countOff++;
            }
            exp = (1 - exp);
        }
        return countOff;
    }
}
