/*
1317. Convert Integer to the Sum of Two No-Zero Integers

https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/

*/

class Solution {
    public int[] getNoZeroIntegers(int n) 
    {
        int[] pair = new int[2];
        int k = n/2;
        for(int i = 1; i <= k; ++i)
        {
            int a = i;
            int b = n - i;
            if(a + b == n)
            {
                String a_str = String.valueOf(a);
                String b_str = String.valueOf(b);
                if(a_str.indexOf("0") == -1 && b_str.indexOf("0") == -1)
                {
                    pair[0] = a;
                    pair[1] = b;
                    return pair;
                }
            }
        }
        return pair;
        
    }
}
