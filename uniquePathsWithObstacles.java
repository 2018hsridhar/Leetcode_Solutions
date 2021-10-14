/*
    Robot starts @ top-left corner of a (mxn) grid : dimensions NOT even
    Robot can move in only two cardinal directions - {DOWN, RIGHT}
    Goal : reach the bottom-right corner of the grid
    Grid now has some obstacles
    Count total-# unique paths here
    
    1 => obstacle
    0 => space
    
    The Optimal Substructure Property wouldn't change much from the basic case where we lacked obstacles
    We can mark the locations with an obstacle using some type of sentinel value as well
    
    URL = https://leetcode.com/problems/unique-paths-ii/
    63. Unique Paths II
    
    Complexity ( Bottom-up DP ) 
    Let M,N := dimensions of the grid ( row, col ) 
    Time = O(MN)
    Space = O(MN)
    

*/
class Solution 
{
    public int uniquePathsWithObstacles(int[][] obstacleGrid) 
    {
        int M = obstacleGrid.length;
        int N = obstacleGrid[0].length;
        int[][] DP = new int[M][N];
        int numUniquePaths = 0;
        
        // Fill in the top and the left as well ( always known to equal 1 ) unless an obstacle -> then 0
        // We might have a simpler means of expression here as well
        int filler = 1;
        for(int j = 0; j < N; ++j)
        {
            if(obstacleGrid[0][j] == 1)
                filler = 0;
            DP[0][j] = filler;
        }
        
        filler = 1;
        for(int i = 0; i < M; ++i)
        {
            if(obstacleGrid[i][0] == 1)
                filler = 0;
            DP[i][0] = filler;
        }
        
        // Now fill in the rest of the array here
        for(int i = 1; i < M; ++i)
        {
            for(int j = 1; j < N; ++j)
            {
                if(obstacleGrid[i][j] == 1)
                    DP[i][j] = 0;
                else
                {
                    DP[i][j] = DP[i-1][j] + DP[i][j-1];
                }
            }
        }

        numUniquePaths = DP[M-1][N-1];
        return numUniquePaths;
    }
}
