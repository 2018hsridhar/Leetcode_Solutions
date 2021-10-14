/*

Matrix can be composed of any of Z ( -, 0, + ) 

Input is always a square (nxn) matrix
Highly akin to the binomial problems coding exercise too

URL = https://leetcode.com/problems/minimum-falling-path-sum-ii/
1289. Minimum Falling Path Sum II

Falling paths start @ any element in the first row
chooses elem in next row either
    (b) diagonally left/right ( in 1 step ) 

Still need to perform boudns checking : but now every element in a row is fair game
But further increasing time-space complexity to O(n^3)
(row, col) 
    (row + 1, col - 1)
    (row + 1, col + 1)
    
Optimal substructure really be given here as well
Base case : the last row -> every value there is just itself

Complexity
Let N := dim of the matrix, in both directions
Time = O(N^3)
Space = O(N^2)
Luckily, N is reasonable ( strictly bounded by closed interval or [1,200] )
Grid values also stricty bounded by closed interval or [-99, 99]

*/


class Solution 
{
    public int minFallingPathSum(int[][] grid)
    {
        int n = grid.length;
        if(n == 1 ) 
            return grid[0][0];
        int[][] DP = new int[n][n];
        
        // 1. Fill out the base case mentioned
        for(int j = 0; j < n; ++j)
            DP[n-1][j] = grid[n-1][j];
        
        // 2. Fill out the subproblem cases
        for(int i = n-2; i >= 0; --i)
        {
            for(int j = 0; j < n; ++j)
            {
                DP[i][j] = Integer.MAX_VALUE;
                for(int k = 0; k < n; ++k)
                {
                    if(k != j)
                        DP[i][j] = Math.min(DP[i][j], grid[i][j] + DP[i+1][k]);
                }
            }
        }
            
        // 3. Iterate over the beginning entries and get the min falling path sum
        int mfps = Integer.MAX_VALUE;
        for(int j = 0; j < n; ++j)
            mfps = Math.min(DP[0][j], mfps);
        
        return mfps;
    }
}
