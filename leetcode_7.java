/*

URL = https://leetcode.com/problems/reverse-integer/
7. Reverse Integer

2^31-1 = 2147483647 = INT_MAX
-2^31 = -2147483648 = INT_MIN
Absolute val of max digit*power = 2*10^9 ( for addition OR for subtraction ) 
Safely ignore negative as well here too
Leverage the absolute value function here

How to avoid the char[] array of the string conversions as well too?
We know the length of an integer ( IFF converted to string though ) 

Use the ceil of the log function of CEIL[log_10(x)] to get number digits with exception of 0 or 1 here though!


TEST CASES : 
(A)
(B)
(C)
(D)
(E)
(F)

*/
class Solution 
{
    public int reverse(int x) 
    {
        // Special range handling due to ceil(log(x)) handling
        if(-1 <= x || x <= 1)
        {
            return x;
        }
        int numDigits = (int)Math.ceil(Math.log10(x));
        System.out.printf("Num digits for [%d] x = [%d]\n", x, numDigits);
        boolean out32BitRange = false;
        boolean isNeg = false;
        if(x < 0)
        {
            isNeg = true;
            // x *= -1;    Can lead to issue if X = INT_MIN : careful here!
        }
        int res = 0;
        
        return res;
    }
}
