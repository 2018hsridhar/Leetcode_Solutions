/*
2352. Equal Row and Column Pairs
URL = https://leetcode.com/problems/equal-row-and-column-pairs/

Note : the problem constraints allow us to use the brute force approach here.
(2*10^2)^3 = 2*(10^6) = 2,000,000 = 2 MIL comparison operations total!
Fits within bounds of an integer.

Complexity
Let N := #-rows/#-cols in the grid
Time = O(N^3)
Space = O(1) ( EXP & IMP ) 

*/
class Solution {
    public int equalPairs(int[][] grid) {
        // Would be same dimensions anyways
        int countEqualPairs = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j)
            {
               countEqualPairs = checkRowColEqual(grid,i,j) ? countEqualPairs + 1 : countEqualPairs + 0;
            }
        }
        return countEqualPairs;
    }
    
    private boolean checkRowColEqual(int[][] grid, int row, int col){
        int n = grid.length;
        for(int i = 0; i < n; ++i){
            if(grid[row][i] != grid[i][col]) {
                return false;
            }
        }
        return true;
    }
    
    
    
}
