/*

343. Integer Break
URL = https://leetcode.com/problems/integer-break/

Strategies : DP, Combinatorics, Greedy ( if possible ) -> constant maximization of values?

COMPLEXITY
TIME = O(N^2) POLY
SPACE = O(N)

Check if all subproblems need to be solved as well

TEST CASES
(A)
(B)
(C)
(D)
(E)

Can subtract from 2 to the (maxProd-2) here
Check if any FIB sequences ( as this is DP ) 
Testing at higher numbers, in a bottom-up approach, naturally accounts for testing of lower numbers as lower #'s correspond to subproblems already solved
for the higher numbers here, luckily.

*/
class Solution 
{
    public int integerBreak(int n) 
    {
        // 2 is a base case here, honestly
        int maxProd = 1;
        if(n == 2)
        {
            return 1;
        }
        else if ( n == 3)
        {
            // 3 = 2 + 1 = 1 + 1 + 1 = 1 + 2
            return 2;
        }
        int[] DP = new int[59]; // Storage decently const in this problem though
        DP[0] = 0; 
        DP[1] = 0;
        DP[2] = 1;
        DP[3] = 2;
        for(int i = 4; i <= n; ++i)
        {
            int localMaxProd = 1;
            for(int j = 2; j <= (i - 2); j++)   // Forgot to account for bug here though when testing
            {
                int diff = i - j;
                int curProd = DP[diff] * j;
                if(curProd > localMaxProd)
                {
                    localMaxProd = curProd;
                }
                if(diff * j > localMaxProd) // Does not correct for 6 here -> take note of that ( 3+3 : 3 * 3 = 9 ) 
                {
                    localMaxProd = diff * j;
                }
            }
            DP[i] = localMaxProd;
        }
        maxProd = DP[n];
        return maxProd;
    }
}
