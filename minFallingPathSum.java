/*

Matrix can be composed of any of Z ( -, 0, + ) 

Input is always a square (nxn) matrix
Highly akin to the binomial problems coding exercise too

URL = https://leetcode.com/problems/minimum-falling-path-sum/
931. Minimum Falling Path Sum

Falling paths start @ any element in the first row
chooses elem in next row either
    (a) directly underneath
    (b) diagonally left/right ( in 1 step ) 

(row, col) 
    (row + 1, col - 1)
    (row + 1, col)
    (row + 1, col + 1)
    
Optimal substructure really be given here as well
Base case : the last row -> every value there is just itself

Complexity
Let N := dim of the matrix, in both directions
Time = O(N^2)
Space = O(N^2)

*/


class Solution 
{
    public int minFallingPathSum(int[][] matrix) 
    {
        int n = matrix.length;
        if(n == 1 ) 
            return matrix[0][0];
        int[][] DP = new int[n][n];
        
        // 1. Fill out the base case mentioned
        for(int j = 0; j < n; ++j)
            DP[n-1][j] = matrix[n-1][j];
        
        // 2. Fill out the subproblem cases
        for(int i = n-2; i >= 0; --i)
        {
            for(int j = 0; j < n; ++j)
            {
                DP[i][j] = matrix[i][j] + DP[i+1][j];
                if(j-1 >= 0)
                    DP[i][j] = Math.min(DP[i][j], matrix[i][j] + DP[i+1][j-1]);
                if(j+1 < n)
                    DP[i][j] = Math.min(DP[i][j], matrix[i][j] + DP[i+1][j+1]);
            }
        }
            
        // 3. Iterate over the beginning entries and get the min falling path sum
        int mfps = Integer.MAX_VALUE;
        for(int j = 0; j < n; ++j)
            mfps = Math.min(DP[0][j], mfps);
        
        return mfps;
    }
}
