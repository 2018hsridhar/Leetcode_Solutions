/*
304. Range Sum Query 2D - Immutable
https://leetcode.com/problems/range-sum-query-2d-immutable/

THOUGHT PROCESSES : 

Uses the 2D version of the prefix sum algorithm
As desired earier, presum time complexity, for any rectagnular ranges, should be O(1)
Initialy time complexity for preprocessing of prefix sum matrix = O(MN), where M,N = dimensions of matrix
Space complexity is O(MN) for the prefix array

*/

class NumMatrix 
{
    // LET psm := prefix sum matrix
    int[][] psm;
    int m;
    int n;
        
    public NumMatrix(int[][] matrix) 
    {
        // SET array values 
        m = matrix.length;
        n = matrix[0].length;
        psm = new int[m][n];
        for(int i = 0; i < matrix.length; ++i)
        {
            for(int j = 0; j < matrix[0].length; ++j)
            {
                psm[i][j] = matrix[i][j];
            }
        }
        
        // COMPUTE PREFIX SUMMATIONS
        // FILL OUT TOPMOST ROW
        for(int j = 1; j < psm[0].length; ++j)
        {
            psm[0][j] = psm[0][j] + psm[0][j-1];
            // System.out.printf("For (%d,%d), psm = %d\n", 0,j,psm[0][j]);
        }
        
        // FILL OUT LEFTMOST COLUMN
        for(int i = 1; i < psm.length; ++i)
        {
            psm[i][0] = psm[i][0] + psm[i-1][0];
            // System.out.printf("For (%d,%d), psm = %d\n", i,0,psm[i][0]);
        }
        
        // FILL OUT REST OF RECTANGLE
        for(int i = 1; i < psm.length; ++i)
        {
            for(int j = 1; j < psm[0].length; ++j)
            {
                psm[i][j] = psm[i][j] + psm[i-1][j] + psm[i][j-1] - psm[i-1][j-1];
                // System.out.printf("For (%d,%d), psm = %d\n", i,j,psm[i][j]);
            }
        }
        
        
        
    }
    
    /* Current approach works only for middle rectangles -> not rectangles which are 0-indexed
      Handle 0-case specially here!!!!\
      Handle 1-dimensinoal cases too! ( single row / single columns ) 
      Handle case where either the first row or the first column equal 0, BUT, not both at the same time!
      Special case of singleton squares too!
        Can work off the following assumption : ( row1 <= row2 ) and ( col1 <= col2 ) : no degeneracy here
        
    */
    
    
    public int sumRegion(int row1, int col1, int row2, int col2) 
    {
        int sumRegion = 0;
        if(row1 == 0 && col1 == 0)
        {
            sumRegion = psm[row2][col2];
        }
        else if ( row1 == 0 && col1 != 0)
        {
            sumRegion = psm[row2][col2] - psm[row2][col1-1];
        }
        else if ( row1 != 0 && col1 == 0 )
        {
            sumRegion = psm[row2][col2] - psm[row1-1][col2] ;
        }
        else // a rectangle truly not pegged to the 0th row or the 0th column
        {
            sumRegion = psm[row2][col2] - psm[row2][col1-1] - psm[row1-1][col2] + psm[row1-1][col1-1];
        }
        return sumRegion;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
