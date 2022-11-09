/*
934. Shortest Bridge
URL = https://leetcode.com/problems/shortest-bridge/description/

Is a binary matrix : we can mutate our grid too!
45 mins spent
Feeling tired for some reason
You probably have your algo here too :-)

*/
class Solution {
public:
    // MOdularize this code well too!
    int shortestBridge(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> traversalDistance(m,vector<int>(n,0));
        vector<vector<bool>> visited(m,vector<bool>(n,false));
        vector<vector<int>> cellsFirstIsland;
        vector<vector<int>> cellsSecondIsland;
        vector<vector<bool>> dfs_visited(m,vector<bool>(n,false));
        int initLevel = 0;

        pair<int,int> firstIslandIdx = getFirstIsland(grid);
        dfs(firstIslandIdx, grid, cellsFirstIsland, dfs_visited);
        pair<int,int> secondIslandIdx = getFirstIsland(grid); // ree-execs as grid has been modified too!
        dfs(secondIslandIdx, grid, cellsSecondIsland, dfs_visited);
        // printf("First island @ [%d,%d]\n", firstIslandIdx.first, firstIslandIdx.second);
        // printf("Second island @ [%d,%d]\n", secondIslandIdx.first, secondIslandIdx.second);
        computeTraversals(cellsFirstIsland, grid, traversalDistance, visited,initLevel);
        int shortestBridge = getShortestBridge(traversalDistance,cellsSecondIsland);
        return shortestBridge;
        // return 0;
     }
    
private:

    bool inBounds(int r, int c, vector<vector<int>>& grid){
        int m = grid.size();
        int n = grid[0].size();
        return ((0 <= r  && r < m) && (0 <= c && c < n));
    }

    int getShortestBridge(vector<vector<int>>& traversalDistance, vector<vector<int>>& cellsSecondIsland){
        int minDist = INT_MAX;
        for(auto parent : cellsSecondIsland){ // see structured bindings here
                int x = parent.at(0);
                int y = parent.at(1);
                minDist = std::min(minDist,traversalDistance.at(x).at(y));
        }
        return minDist - 1;
    }

    // traversalDist luckily 0 initialized here
    // easier to do recursively IMHO
    void computeTraversals(vector<vector<int>>& currentLevel, vector<vector<int>>& grid,
        vector<vector<int>>& traversalDistance, vector<vector<bool>>& visited, int myLevel){
            if(currentLevel.size() == 0){
                return;
            }
            int m = grid.size();
            int n = grid[0].size();
            vector<vector<int>> dirs = {{-1,0},{0,-1},{0,1},{1,0}};
            vector<vector<int>> childLevel;
            for(auto parent : currentLevel){
                // [1] parent check
                int x = parent.at(0);
                int y = parent.at(1);
                if(inBounds(x,y,grid) && !visited.at(x).at(y)){
                    visited.at(x).at(y) = true;
                    traversalDistance.at(x).at(y) = myLevel;
                    for(int dir = 0; dir < dirs.size(); ++dir){
                        vector<int> nextChild = {parent.at(0) + dirs[dir][0], parent.at(1) + dirs[dir][1]};
                        childLevel.push_back(nextChild);
                    }
                }
            }
            computeTraversals(childLevel,grid,traversalDistance,visited,myLevel+1);
    }

    void dfs(pair<int,int>& firstIslandIdx, vector<vector<int>>& grid, vector<vector<int>>& cells, vector<vector<bool>>& visited){
        int r = firstIslandIdx.first;
        int c = firstIslandIdx.second;
        vector<vector<int>> dirs = {{-1,0},{0,-1},{0,1},{1,0}};
        if(inBounds(r,c,grid) && grid.at(r).at(c) == 1){
            if(!visited.at(r).at(c)){
                // printf("r,c = %d,%d\n", r, c);
                visited.at(r).at(c) = true;
                grid.at(r).at(c) = 2; // change value
                cells.push_back({r,c});
                for(int dir = 0; dir < dirs.size(); ++dir){
                    pair<int,int> nextChild = make_pair(r + dirs[dir][0],c + dirs[dir][1]);
                    // printf("r,c = %d,%d\n", nextChild.first, nextChild.second);
                    dfs(nextChild,grid,cells,visited);
                }
            }          
        }
    }

    pair<int,int> getFirstIsland(vector<vector<int>> grid){
        int m = grid.size();
        int n = grid[0].size();
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(grid.at(i).at(j) == 1){
                    return make_pair(i,j);
                }
            }
        }
        return make_pair(-1,-1);        
    }
};
