/*
1254. Number of Closed Islands
URL = https://leetcode.com/problems/number-of-closed-islands/description/
Impl time = minutes

10 mins only :-)

Complexity
Let M,N := dim(grid)
TIME = O(MN)
SPACE = O(MN) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int closedIsland(vector<vector<int>>& grid) {
        int closedIslandCount = 0;
        int m = grid.size();
        int n = grid[0].size();
        for(int j = 0; j < n; ++j){
            dfs(grid,0,j);
            dfs(grid,m-1,j);
        }
        for(int i = 0; i < m; ++i){
            dfs(grid,i,0);
            dfs(grid,i,n-1);
        }
        for(int i = 1; i < m-1; ++i){
            for(int j = 0; j < n-1; ++j){
                if(grid.at(i).at(j) == 0){
                    dfs(grid,i,j);
                    closedIslandCount++;
                }
            }
        }
        return closedIslandCount;
    }

private:
    // Flip to '2' to denote visited set here
    // First work off the boundary
    // 0 = land, 1 = water, 2 = visited
    void dfs(vector<vector<int>>& grid, int i, int j){
        vector<vector<int>> dirs = {{-1,0},{0,-1},{0,1},{1,0}};
        if(isInBounds(i,j,grid) && grid.at(i).at(j) == 0){
            // process parent
            grid.at(i).at(j) = 2;
            for(int dir = 0; dir < dirs.size(); ++dir){
                int childR = i + dirs.at(dir).at(0);
                int childC = j + dirs.at(dir).at(1);
                dfs(grid,childR,childC);
            }
        }
    }

    bool isInBounds(int r, int c, vector<vector<int>>& grid){
        return (0 <= r && r < grid.size() && (0 <= c && c < grid[0].size()));
    }
};
