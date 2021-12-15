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
(A) -10000
    => -1
(B) -INT_MIN -2147483648 
    => fails
(C) INT_MAX =2147483647
    => succeeds
(D) 0,1, -1
    => base case handling
(E) 70023
    => sure, mod (1000,100) leaves the dangling "0023" for a whilst.
    => eventually resolves later though
(F) 121
    => Check iteration bug too
(G) 2147483647
    7463847412 = rev(INT_MAX) by digits : > INT_MAX
     746384741 = rev(INT_MAX) by digits with suffix = 2 dropped off
(H)
(I)
(J)

*/
class Solution 
{
    public int reverse(int x) 
    {
        // Special range handling due to ceil(log(x)) handling
        if(-1 <= x && x <= 1 )
        {
            System.out.printf("here\n");
            return x;
        }
        boolean out32BitRange = false;
        boolean isNeg = false;
        if(x < 0)
        {
            isNeg = true;
            if(x == Integer.MIN_VALUE)
            {
                return 0;
            }
            // Can lead to issue if X = INT_MIN : careful here!
            x *= -1; 
        }
        // Heads up : logarithms fail on NEGATIVE inputs. Must be positive inputs strictly!
        int cur = x;
        int numDigits = (int)Math.floor(Math.log10(x)) + 1;  // Shit : not fully right here -> exert caution again! -> is a floor function : NOT a ceil function
        // System.out.printf("Num digits for [%d] x = [%d]\n", x, numDigits);
        int sigDig = numDigits - 1; // So for 123, sigDig = 2 then. Offset for 0-indexing of digit powers
        int pow = 0;
        int res = 0;
        int maxAdd = 2 * (int)Math.pow(10,9);
        while(cur >= 1)  // Check if eq to 1 or 0 here -> careful
        {
            int divisor = (int)Math.pow(10, sigDig);
            int digit = (int) cur / divisor;
            // System.out.printf("Digit = %d\n", digit);
            int toAdd = (int)Math.pow(10,pow) * digit;
            int diff = Integer.MAX_VALUE - res;
            // System.out.printf("Res = %d\n", res);
            if((pow >= 9 && digit >= 3) || toAdd > diff) // Order of eval in circuit evaluations matters a lot
            {
                return 0;
            }
            else
            {
                res += toAdd;
                // System.out.printf("Res = %d\n", res);
            }
            cur = cur % divisor;
            sigDig--;
            pow++;
        }
        if(isNeg) 
        {
            res *= -1;
        }
        return res;
    }
}
