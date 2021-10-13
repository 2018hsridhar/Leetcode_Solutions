/*

URL = https://leetcode.com/problems/count-sorted-vowel-strings/

Hint : is a DP problem
Vowels are also lexicographically sorted

Edge Case Testing : honestly based off the value of "n" here
(A) n = 1
(B) n = 2
(C) n = 3
(D) n = 10
(E) n = 50

We know the max bound for n as well here

Complexity 
Given n here
Time = O(N)
Space = O(N) : drop the 5 -> that is a constant
Push comes to show, it's constant as N has a known max boudn as well of 50 here!


*/

class Solution 
{
    public int countVowelStrings(int n) 
    {
        int count = 0;
        if(n == 1)      // 1 is our base care here BTW
            return 5;
        
        // Fill in the terminatino condition
        int[][] DP = new int[5][n]; // if n = 1, we want a 5x1 then. 
        for(int i = 0; i < 5; ++i)
            DP[i][0] = 1;
        for(int j = 0; j < n; ++j)
            DP[4][j] = 1;
        /*
        [2] Fill up the buDP matrix
        Fill up by each column, column-wise
        */
        for(int j = 1; j < n; ++j)
        {
            for(int i = 3; i >=0; --i)
            {
                DP[i][j] = DP[i+1][j] + DP[i][j-1];
            }
        }
        
        for(int i = 0; i < 5; ++i)
            count += DP[i][n-1];
        
        return count;
    }
}
