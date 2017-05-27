//GET A CONST SPACE SOLUTION LATER … PROB A SWAP OPERATION NEEDED
// https://leetcode.com/problems/rotate-image/

public class Solution {
   // amazon assessment prolbem, given to a colleague?
   // fiond the ocrrect position to go to ( i.e. (0,1) -> (1,N-0), or (1,1) -> (1, N-1 )) ... wait, thsi seems
really easy!
   // new row = old column ... can agree on that
   // new column = N - old row?
   // ... so I have a solution, that needs O(n^2) space, O(n^2) time ... how to do, in const space
though??? hmm??/
   public void rotate(int[][] matrix)
   {
       if(matrix == null)
       {
          return;
       }
       else
       {
          int[][] newMat = new int[matrix.length][matrix[0].length];
          for(int i = 0; i < matrix.length; i++)
          {
             for(int j = 0; j < matrix[0].length; j++)
             {
                newMat[i][j] = matrix[i][j];
             }
          }

            int N = matrix.length - 1;
            for(int i = 0; i < matrix.length; i++)
            {
               for(int j = 0; j < matrix[0].length; j++)
               {
                  matrix[j][N-i] = newMat[i][j];
               }
            }

        }
    }
}

