/*
Values are nonnegative :-).
URL = https://leetcode.com/problems/maximum-sum-of-an-hourglass/
2428. Maximum Sum of an Hourglass

*/
class Solution {
public:
    int maxSum(vector<vector<int>>& grid) {
        int maxSum = 0;
        int numR = grid.size();
        int numC = grid[0].size();
        int r = 0;
        int c = 0;
        for(int r = 0; r < numR; ++r){
            maxSum = std::max(maxSum,getMaxOfRow(grid,r));
            // printf("maxSum = %d\n", maxSum);// best example of variadic function too!
        }
        return maxSum;
    }
    
private:
    int getMaxOfRow(std::vector<std::vector<int>>& grid, int r){
        int maxOfRow = 0;
        int numR = grid.size();
        int numC = grid[0].size();
        vector<int> rowSums = {0,0,0}; // vector<int>{0,0,0}; / default init
        if(haveHourGlass(r,0,numR,numC)){
            rowSums.at(0) = grid[r][0] + grid[r][1] + grid[r][2];
            rowSums.at(1) = grid[r+1][1];
            rowSums.at(2) = grid[r+2][0] + grid[r+2][1] + grid[r+2][2];
            maxOfRow = rowSums.at(0) + rowSums.at(1) + rowSums.at(2);
        }
        for(int c = 1; c < numC; ++c){
            if(haveHourGlass(r,c,numR,numC)){
                rowSums.at(0) += grid[r][c+2] - grid[r][c-1];
                rowSums.at(1) += grid[r+1][c+1] - grid[r+1][c];
                rowSums.at(2) += grid[r+2][c+2] - grid[r+2][c-1];
                maxOfRow = std::max(maxOfRow, rowSums.at(0) + rowSums.at(1) + rowSums.at(2));
                // printf("max = %d\n", maxOfRow);
            }
        }
        return maxOfRow;
    }
    
    bool haveHourGlass(int r, int c, int numR, int numC){
        return ((0 <= r + 2 && r + 2 < numR) && (0 <= c + 2 && c + 2 < numC));    
    }
};
