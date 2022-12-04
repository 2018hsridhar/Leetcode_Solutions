/*
861. Score After Flipping Matrix
URL = https://leetcode.com/problems/score-after-flipping-matrix/

Complexity
Let M,N := dims(grid)
Time = O(MN)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int matrixScore(vector<vector<int>>& grid) {
        int maxScore = 0;
        int m = grid.size();
        int n = grid[0].size();
        // [1] toggle each row
        for(int i = 0; i < m; ++i){
            if(grid[i][0] == 0){
                toggleRow(i,grid);
            }
        }

        // [2] toggle each column, after the first column
        // Obtain column counts though
        vector<int> countOne(n,0);
        vector<int> countZero(n,0);
        for(int j = 0; j < n; ++j){
            int oneCount = 0;
            int zeroCount = 0;
            for(int i = 0; i < m; ++i){
                if(grid[i][j] == 0){
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            countOne.at(j) = oneCount;
            countZero.at(j) = zeroCount;
        }
        // Why not just take the max (countOne,countZero) ; we know which to toggle too..
        // Do we even have to toggle and check here?
        for(int j = 0; j < n; ++j){
            int numOnesInCol = std::max(countZero.at(j), countOne.at(j));
            maxScore += (numOnesInCol) * (pow(2,n-1-j));
        }
        return maxScore;
    }

private:
    void toggleRow(int i, vector<vector<int>>& grid)
    {
        int n = grid[0].size();
        for(int j = 0; j < n; ++j){
            if(grid[i][j] == 1){
                grid[i][j] = 0;
            } else {
                grid[i][j] = 1;
            }
        }
    }
};
