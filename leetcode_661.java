class Solution {
    public int[][] imageSmoother(int[][] M) 
    {
        int rowB = M.length;
        int colB = M[0].length;
        int[][] filtered = new int[rowB][colB];
        for(int i = 0; i < M.length; ++i)
        {
            for(int j = 0; j < M[0].length; ++j)
            {
                int num_cells = 0;
                int num_ones = 0;
                // yes, check all 8 cells. YAY! 
                // meat of the algo, TBH
                
                // the default cell [ known ahead of time]
                num_ones += M[i][j];                           
                num_cells++;
                
                // TOP ROW [ do not go above 0]
                if(i - 1 >= 0 && j - 1 >= 0)
                {
                    num_ones += M[i-1][j-1];
                    num_cells++;
                }
                if(i - 1 >= 0) 
                {
                    num_ones += M[i-1][j];
                    num_cells++;
                }
                if(i - 1 >= 0 && j + 1 < colB)
                {
                    num_ones += M[i-1][j+1];
                    num_cells++;
                }
                
                // MIDDLE ROW
                if(j-1 >= 0)
                {
                    num_ones += M[i][j-1];
                    num_cells++;
                }
                if(j + 1 < colB)
                {
                    num_ones += M[i][j+1];
                    num_cells++;
                }
                
                // BOTTOM ROW [ do not go below A.length]
                if(i + 1 < rowB && j - 1 >= 0)
                {
                    num_ones += M[i+1][j-1];
                    num_cells++;
                }
                if(i + 1 < rowB) 
                {
                    num_ones += M[i+1][j];
                    num_cells++;
                }
                if(i + 1 < rowB && j + 1 < colB)
                {
                    num_ones += M[i+1][j+1];
                    num_cells++;
                }
                
                int fil_val = (int)Math.floor(num_ones / num_cells);
                filtered[i][j] = fil_val;
            }
        }
        return filtered;
    }
}

// https://leetcode.com/problems/image-smoother/description/

/*
NOTED ::
Bounds checking, is the annoying part here. Diagramming the bounds, is always useful. It is easy to get lost here.
Seperating the bounds also proves useful here.
The if-else conditions, also prove useful. 
Branch from 4-bounds, to 8-bounds, checking here.

*/
