/*
994. Rotting Oranges
URL = https://leetcode.com/problems/rotting-oranges/
*/
class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> parentOranges;
        for(int r = 0; r < m; ++r){
            for(int c = 0; c < n; ++c){
                if(grid.at(r).at(c) == 2){
                    parentOranges.push_back({r,c});
                }
            }
        }
        int minTimeToRot = floodFill(grid,parentOranges);
        for(int r = 0; r < m; ++r){
            for(int c = 0; c < n; ++c){
                if(grid.at(r).at(c) == 1){
                    return -1;
                }
            }
        }
        return minTimeToRot;    
    }

private:
    // write into the grid to denote a visit cell : use <3> here
    // You ahve to do this problem as if each rotting orange started : not as if only one rotting orange started too!
    // Leverage order traversal, but - from all the oranges!
    int floodFill(vector<vector<int>>& grid, vector<vector<int>>& parentOranges){
        int timeElap = 0;
        vector<vector<int>> childOranges;
        cout << parentOranges.size() << endl;
        if(parentOranges.size() == 0){
            return 0;
        }
        for(vector<int>& parentOrange : parentOranges){
            int r = parentOrange.at(0);
            int c = parentOrange.at(1);
            printf("CUR R-C {%d,%d}\n", r, c);
            if(grid.at(r).at(c) != 3){
                grid[r][c] = 3;
                // 4 dir check
                vector<vector<int>> delta = {{-1,0},{0,1},{0,-1},{1,0}};
                for(int i = 0; i < 4; ++i){
                    int nextR = r + delta.at(i).at(0);
                    int nextC = c + delta.at(i).at(1);
                    if(inBounds(nextR,nextC,grid)){
                        if(grid.at(nextR).at(nextC) == 1){ // do not proceed to empty cells : useless
                            childOranges.push_back({nextR,nextC});
                            grid[nextR][nextC] = 0;
                        } 
                    }
                }
            }
        }
        if(childOranges.size() > 0){
            timeElap = std::max(timeElap,1 + floodFill(grid,childOranges));
        }
        return timeElap;
    }

private:
    bool inBounds(int r, int c, vector<vector<int>>& grid){
        return ((0 <= r && r < grid.size()) && (0 <= c && c < grid[0].size()));
    }
};
