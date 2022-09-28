/*
1886. Determine Whether Matrix Can Be Obtained By Rotation
URL = https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
*/
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        boolean status = (
            check0DegRead(mat,target) || 
            check90DegRead(mat,target) || 
            check180DegRead(mat,target) || 
            check270DegRead(mat,target));
        return status;
    }
    
    private boolean check0DegRead(int[][] mat, int[][] target)
    {
        boolean isSame = true;
        int m = mat.length;
        int n = mat[0].length;
        for(int i= 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(mat[i][j] != target[i][j]){
                    isSame = false;
                    break;
                }
            }
        }
        return isSame;
    }
    
    private boolean check90DegRead(int[][] mat, int[][] target)
    {
        boolean isSame = true;
        int m = mat.length;
        int n = mat[0].length;
        for(int i= 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(mat[i][j] != target[j][n-1-i]){
                    isSame = false;
                    break;
                }
            }
        }
        return isSame;
    }
    
    private boolean check180DegRead(int[][] mat, int[][] target)
    {
        boolean isSame = true;
        int m = mat.length;
        int n = mat[0].length;
        for(int i= 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(mat[i][j] != target[m-1-i][n-1-j]){
                    isSame = false;
                    break;
                }
            }
        }
        return isSame;
    }
    
    private boolean check270DegRead(int[][] mat, int[][] target)
    {
        boolean isSame = true;
        int m = mat.length;
        int n = mat[0].length;
        for(int i= 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(mat[i][j] != target[m-1-j][i]){
                    isSame = false;
                    break;
                }
            }
        }
        return isSame;
    }
    
}
