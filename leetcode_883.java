/*

URL = https://leetcode.com/problems/projection-area-of-3d-shapes/
883. Projection Area of 3D Shapes

THOUGHT PROCESS : 

[T,S]
Desired : O(N^2) time, O(1) space, where N := length of a grid row or grid column
Projections = shadows : map 3-D figures to 2-D planes
-> 2D space proves easier to work with in general

Area of XY plane project is known : is a count of all cube towers

Grid is guaranteed, at minimum, to be a single element matrix
Cubes placed guaranteed in range of [0,50] too

Grid[i][j] = placed atop cell (i,j)

On XZ or YZ planes - look at what be highest observable z-values of the cubes


*/



class Solution 
{
    public int projectionArea(int[][] grid) 
    {
        int xy_sum = 0;
        int xz_sum = 0;
        int yz_sum = 0;
        
        // [1] Solve for xy_sum
        for(int i = 0; i < grid.length; ++i)
        {
            for(int j = 0; j < grid[0].length; ++j)
            {
                if(grid[i][j] != 0)
                    xy_sum += 1;
            }            
        }
        
        // [2] Solve for xz_sum ( columns ) 
        for(int j = 0; j < grid[0].length; ++j)
        {
            int col_sum = Integer.MIN_VALUE;
            for(int i = 0; i < grid.length; ++i)
                col_sum = Math.max(col_sum,grid[i][j]);
            xz_sum += col_sum;
        }
            
        // [3] Solve for yz_sum ( by row ) 
        for(int i = 0; i < grid.length; ++i)
        {
            int row_sum = Integer.MIN_VALUE;
            for(int j = 0; j < grid[0].length; ++j)
                row_sum = Math.max(row_sum,grid[i][j]);
            yz_sum += row_sum;
        }  
        
        
        
        int projectArea = ( xy_sum + xz_sum + yz_sum );
        return projectArea;
    }
}
