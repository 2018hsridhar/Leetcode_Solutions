/*
361. Bomb Enemy
URL = https://leetcode.com/problems/bomb-enemy/

Ideas
1. Level-order traversal/BFS in each empty cecll
2. Precompute number of enemies facing you in a specific direction

*/
class Solution {
    public int maxKilledEnemies(char[][] grid) 
    {
        int maxKilled = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] numBombed = new int[m][n];
        // matrix 0-initialized
        
        // Bomb is placeable in ANY empty cell here
        for(int r = 0; r < m; ++r)
        {
            for(int c = 0; c < n; ++c)
            {
                if(grid[r][c] == '0')
                {
                    maxKilled = Math.max(maxKilled, detonate(grid,r,c));
                }
            }
        }
        return maxKilled;
    }
    
    // detonating is a "type-of" level-order traversal -> but really -> for loops assist out better
    private int detonate(char[][] grid, int r, int c)
    {
        int numKilled = 0;
        
        int m = grid.length;
        int n = grid[0].length;
        
        // [1] Go up
        int rUp = r - 1;
        int cUp = c;
        while(inBounds(rUp,cUp,m,n))
        {
            if(isEnemy(grid,rUp,cUp))
                numKilled++;
            else if ( isWall(grid,rUp,cUp))
                break;
            rUp -= 1;
        }
        
        // [2] Go down
        int rDown = r + 1;
        int cDown = c;
        while(inBounds(rDown,cDown,m,n))
        {
            if(isEnemy(grid,rDown,cDown))
                numKilled++;
            else if ( isWall(grid,rDown,cDown))
                break;
            rDown += 1;
        }
        
        // [3] Go left
        int rLeft = r;
        int cLeft = c - 1;
        while(inBounds(rLeft,cLeft,m,n))
        {
            if(isEnemy(grid,rLeft,cLeft))
                numKilled++;
            else if ( isWall(grid,rLeft,cLeft))
                break;
            cLeft -= 1;
        }
        
        // [4] Go right
        int rRight = r;
        int cRight = c + 1;
        while(inBounds(rRight,cRight,m,n))
        {
            if(isEnemy(grid,rRight,cRight))
                numKilled++;
            else if ( isWall(grid,rRight,cRight))
                break;
            cRight += 1;
        }
        
        return numKilled;
    }
    
    // Would inline these functions in a production setting.
    private boolean isWall(char[][] grid, int r, int c)
    {
        return (grid[r][c] == 'W');
    }
    
    private boolean isEnemy(char[][] grid, int r, int c)
    {
        return (grid[r][c] == 'E');
    }
    
    
    // quick way for bounds testing :-)
    private boolean inBounds(int r, int c, int m, int n)
    {
        if((0 <= r && r < m) && (0 <= c && c < n))
            return true;
        return false;
    }
    
}
