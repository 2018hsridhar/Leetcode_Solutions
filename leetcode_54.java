/*
URL = https://leetcode.com/problems/spiral-matrix/discuss/1327836/JAVA-O(MN)-time-O(1)-Space-Solution-based-on-bounds-checks
54. Spiral Matrix

THOUGHT PROCESS : 

Edge case testing : 
No need to worry about lengths eqalling zero too : (m,n) reasonably bounded from [1,10]
(1) Singleton matrix - [1],[10]
(2) SIngle rows - [1,2,3,4,5] or single columns- [1,2,3,4,5]^T ( let x^T := transpose of a matrix )
(3) An actual matrix - [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
(4) Rectangular matrix cases

*/

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<Integer>();
        int lC = 0;
        int uR = 0;
        int rC = (matrix[0].length - 1); 
        int bR = (matrix.length - 1); 
    
        // while (lB != rB && uB != bB) - fails if the matrix be a single column OR a single row
        // Execute your bounds check within the methods too : handle single-row or single column cases, or weird cases
        
        while(lC < rC || uR < bR)
        {
            // TOP ROW
            for(int c = lC; c <= rC; ++c)
                result.add(matrix[uR][c]);
            uR++;
            if(uR > bR) 
                break;
            
            // RIGHTMOST COLUMN
            // at rightmost column ( denoted as c )
            for(int r = uR; r <= bR; ++r)
                result.add(matrix[r][rC]);
            --rC;
            if(lC > rC)
                break;
            
            // BOTTOM ROW
            for(int c = rC; c >= lC; --c) 
                result.add(matrix[bR][c]);
            --bR;
            if(uR > bR) 
                break;
            
            // LEFTMOST COLUMN
            
            for(int r = bR; r >= uR; --r)
                result.add(matrix[r][lC]);
            ++lC;
            if(lC > rC)
                break;
            
        }
        if(uR == bR && lC == rC)
            result.add(matrix[uR][lC]); 
        
        return result; 
    }
}
