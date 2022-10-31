/*

PASSED ON FIRST SUBMISSION :-O !

URL = https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips/
2128. Remove All Ones With Row and Column Flips

Edge Cases
(A) [[1,0,0,1],[1,0,0,1],[0,1,1,0],[0,1,1,0]] => TRUE
(B) [[1,0,1,1],[1,0,0,1],[0,1,1,1],[0,1,1,0]] => FALSE

Complexity
let M,N := dims(grid)
TIME = O(MN)
SPACE = O(1) ( EXP & IMP ) 

*/
class Solution {
public:
    bool removeOnes(vector<vector<int>>& grid) {
        bool status = true;
        int m = grid.size();  // numR
        int n = grid[0].size();   // numC
        for(int c = 0; c < n; ++c) {
            if(grid[0][c] == 0){
                changeCol(grid,c);
            }
        }
        // Sum up 1's and 0's, and ensure consistency over the grid
        for(int i = 0; i < m; ++i){
            int oneCount = 0;
            for(int j = 0; j < n; ++j){
                if(grid[i][j] == 1){
                    oneCount++;
                }
            }
            if(oneCount != n && oneCount != 0){
                status = false;
                break;
            }
        }
        return status;
    }
    
private:
    void changeCol(vector<vector<int>>& grid, int col){
        int m = grid.size();
        int n = grid[0].size();
        for(int i = 0; i < m; ++i){
            grid[i][col] = 1 - grid[i][col];
        }
    }
};
