/*
Imagine like, octagonal prismic dice here, if that assists

1155. Number of Dice Rolls With Target Sum
URL = https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/

Modulo 10^9 + 7 ( I am unsure wy the '7' offset too )

COMPLEXITY
Let N := #-dice
Let K := #-faces per die 
Let T := the target sum
Bottom-up DP efficiently solves all problems : not invaluable here
POLY-time algo desireable
Time = O(NKT) ( cubic :-O ) 
Space = O(NT)

Is it really NK, or N*Target
K := #-choices that can made each time too

TEST CASES:
(A)
(B)
(C)
(D)

Leverage Integer.MAX_VALUE to denote unsolveable states as well
    +1 : we risk data overflow here as well too!
    
Sum of FACE-UP numbers equals target : 0 out many answers here
Man hashmap caching is a bit nicer here for sure :-) 


If target = 9, k = 6, n = 1 : Integer.MAX_VALUE expected
elif target = 5, k = 9, n = 1 : 1 expected!
*/
class Solution 
{
    public int numRollsToTarget(int n, int k, int target)
    {
        // [1] Precomputation phase : initialize the DP matrix here
        int[][] DP = new int[n + 1][target + 1];
        for(int i = 0; i < DP.length; ++i)
            for(int j = 0; j < DP[0].length; ++j)
                DP[i][j] = 0;
        
        // Fill out the base case here : top row, if target sufficiently low as well
        for(int j = 0; j < target + 1; ++j )
        {
            DP[0][j] = 0;   // can not function with zero-die anywhere
            if(j <= k)
            {
                DP[1][j] = 1;
            }
            else if ( j > target)
            {
                DP[1][j] = 0;
            }
        }
        // base case $2 : leftmost column
        for(int i = 0; i < n; i++)
        {
            DP[i][0] = 0;
        }
        // non-base cases : at least two die here
        for(int i = 2; i < n + 1; ++i)
        {
            for(int j = 0; j < target + 1; ++j )
            {
                // Initialily assume that no face is valid 
                for(int face = 1; face <= k; ++face)
                {
                    // It is number of ways : not number steps to get to zero. Whoops this is a slight mistake now.
                    // Oh gosh. I should have just done top-down memoization instead!
                    if ( j - face >= 0 )
                    {
                        if(DP[i-1][j-face] != 0)
                        {
                            DP[i][j] += DP[i-1][j-face];
                            DP[i][j] %= ((int)Math.pow(10,9) + 7);
                        }
                    }
                }
            }
        }
        int numRolls = DP[n][target] % ((int)Math.pow(10,9) + 7);
        return numRolls;
    }
}
