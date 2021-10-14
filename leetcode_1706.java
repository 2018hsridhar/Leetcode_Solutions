/*

Highly akin to the problem of binomial coefficients
Last row will definitely be the terminating condition / easiest case to handle

URL = https://leetcode.com/problems/where-will-the-ball-fall/
1706. Where Will the Ball Fall

Complexity :
Let M,N := dimensions of your box/grid
Let N := number of <n> balls here
Time = O(MN)
Space = O(N)

Careful with example 2 as well : take heed! 
Must check your neighbor as well too
Grid lengths also reasonably bounded : values always known (-1,1)

Edge Case Testing :
(A)
(B)
(C) [[1,1,-1,-1]]       - a single row 
    [-1,0,1,2,3]
(D) [[1],[1],[-1],[-1]] - a single column
(E) [[1]], [[-1]]       - a singleton
(F)

OK ... the DP matrix can use -1 to denote a stuck cell ... but not the grid itself, if it were being modified in place!
Typical bug : using the wrong variables in the OSP

*/

class Solution 
{
    public int[] findBall(int[][] grid) 
    {
        int M = grid.length;
        int N = grid[0].length;
        int[][] DP = new int[M][N];
        
        if(N == 1)
            return new int[]{-1};
        
        // 1. Initialize the terminating condition ( but need to handle case of 1D arrays and singletons as well )
        for(int j = 0; j < N; ++j)
        {
            int dir = grid[M-1][j];
            if(j == 0)
            {
                if(dir == -1)
                    DP[M-1][0] = -1;
                else
                {
                    if(grid[M-1][1] == -1)
                        DP[M-1][0] = -1;
                    else
                        DP[M-1][0] = 1;
                }
            }
            else if ( j == N - 1)
            {
                if(dir == 1)
                    DP[M-1][N-1] = -1;
                else
                {
                    if(grid[M-1][N-2] == -1)
                        DP[M-1][N-1] = N-2;
                    else
                        DP[M-1][N-1] = -1;
                }
            }
            else    // guaranteed to be a middle element ( as boudnary checks done earlier )
            {
                if(dir == -1 && grid[M-1][j-1] == -1)
                    DP[M-1][j] = j-1;
                if(dir == -1 && grid[M-1][j-1] == 1)
                    DP[M-1][j] = -1;
                if(dir == 1 && grid[M-1][j+1] == 1)
                    DP[M-1][j] = j+1;
                if(dir == 1 && grid[M-1][j+1] == -1)
                    DP[M-1][j] = -1;
            }
        }
        
        // Go through the rest of the rows here, bottom-up style
        for(int i = M - 2; i >= 0; --i)
        {
            for(int j = 0; j < N; ++j)
            {
                int dir = grid[i][j];
                if(j == 0)
                {
                    if(dir == -1)
                        DP[i][0] = -1;
                    else
                    {
                        if(grid[i][1] == -1)
                            DP[i][0] = -1;
                        else
                            DP[i][0] = DP[i+1][1];
                    }
                }
                else if ( j == N - 1)
                {
                    if(dir == 1)
                        DP[i][N-1] = -1;
                    else
                    {
                        if(grid[i][N-2] == -1)
                            DP[i][N-1] = DP[i+1][N-2];
                        else
                            DP[i][N-1] = -1;
                    }
                }
                else    // guaranteed to be a middle element ( as boudnary checks done earlier )
                {
                    if(dir == -1 && grid[i][j-1] == -1)
                        DP[i][j] = DP[i+1][j-1];
                    if(dir == -1 && grid[i][j-1] == 1)
                        DP[i][j] = -1;
                    if(dir == 1 && grid[i][j+1] == 1)
                        DP[i][j] = DP[i+1][j+1];
                    if(dir == 1 && grid[i][j+1] == -1)
                        DP[i][j] = -1;
                }
            }        
        }
        
        // Print out our DP matrix as well
        // for(int i = 0; i < M; ++i)
        // {
        //     for(int j = 0; j < N; ++j)
        //         System.out.printf("%d,", DP[i][j]);
        //     System.out.println();
        // }
        
        // 2. Go through the top rows, and output the array answer as well
        int[] ans = new int[N];
        for(int j = 0; j < N; ++j)
            ans[j] = DP[0][j];
        return ans;
    }
}
