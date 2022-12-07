/*
1219. Path with Maximum Gold
URL = https://leetcode.com/problems/path-with-maximum-gold/description/

*/
class Solution {
public:
    int getMaximumGold(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int maxGold = 0;
        vector<vector<int>> visited(m,vector<int>(n,-1)); // -1 = not visited, 0 = visited
        vector<vector<int>> delta = {
            {-1,0}, // north
            {0,1}, // east
            {1,0}, // south
            {0,-1} // west
        };
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(grid[i][j] != 0){
                    int myMaxGold = dfs(grid,i,j,visited,delta);
                    maxGold = std::max(maxGold, myMaxGold);
                }
            }
        }
        return maxGold;
    }

    // Use visited set harder for cell tracking : prefer manipulating grid directly
    // Ran into a TLE ( yet algo right )
    // Chekc bad copy steps here
    // Constnat memo allocations, such as what was done, can wrekc you weirdly!
    int dfs(vector<vector<int>>& grid, int parentR, int parentC, vector<vector<int>>& visited, vector<vector<int>>& delta){
        visited.at(parentR).at(parentC) = 0;
        int curPathSum = grid.at(parentR).at(parentC); // get current gold!
        int myMax = 0; // why was this set to -1 ( weird offsetting ) 
        for(int i = 0; i < delta.size(); ++i){
            int childR = parentR + delta.at(i).at(0);
            int childC = parentC + delta.at(i).at(1);
            if(isInBounds(grid,childR,childC)){
                if(visited.at(childR).at(childC) == -1 && grid[childR][childC] != 0){
                    int remPathSum = dfs(grid, childR,childC,visited,delta); // this is each loop iteration
                    myMax = std::max(myMax, remPathSum);
                }
            }
        }
        curPathSum += myMax;
        visited.at(parentR).at(parentC) = -1;
        return curPathSum;
    }

    inline bool isInBounds(vector<vector<int>>& grid, int i, int j){
        int m = grid.size();
        int n = grid[0].size();
        return ((0 <= i && i < m) && (0 <= j && j < n));
    }

};
