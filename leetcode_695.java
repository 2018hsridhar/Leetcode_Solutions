/*
695. Max Area of Island
URL = https://leetcode.com/problems/max-area-of-island/

Let us implmenet this BFS style, explicit space as a challenge.
And set up a C++-based implementation.
Doesn't C++ allow you to have functions outside of classes ( no strict OOP-adherence rules ).
*/
class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int maxIslandArea = 0;
        
        // std::vector<T> itself has overloaded useful operators.
        for(int i = 0; i < grid.size(); ++i){
            for(int j = 0; j < grid[0].size(); ++j){
                int el = grid[i][j];
                if(el == 1){
                    // Probs due to self-contained class, do not run into an `undefined / undeclared func` error too!
                    int curIslandArea = bfsTheIsland(grid,i,j);
                    maxIslandArea = std::max(curIslandArea, maxIslandArea);
                }
            }
        }
        return maxIslandArea;
    }
private:
    int bfsTheIsland(vector<vector<int>>& grid, const int startI, const int startJ){
        int curIslandArea = 0;
        // no need to explicilty initialize all containers on heap mem
        queue<std::pair<int,int>> toExplore;
        toExplore.push(std::make_pair(startI,startJ));
        while(toExplore.size() > 0){
            // An explicit static_cast is NOT always needed! Nonetheless it helps.
            // std::pair<int,int> pCell = static_cast<std::pair<int,int>>(toExplore.front());
            // std::pair<int,int> pCell = (toExplore.front());
            auto pCell = (toExplore.front());
            toExplore.pop();
            if(grid[pCell.first][pCell.second] == 1) // not visited
                curIslandArea++;
            // Seem like the visited set check is still needed for expeditious handling!
            else if ( grid[pCell.first][pCell.second] == 2)
                continue;
            // printf("%d,%d el val = %d\n", pCell.first,pCell.second, grid[pCell.first][pCell.second]);
            grid[pCell.first][pCell.second] = 2; // visited
            // check the 4 directions and bouds
            // North
            if(pCell.first - 1 >= 0 && grid[pCell.first - 1][pCell.second] == 1){
                toExplore.push(make_pair(pCell.first - 1, pCell.second));
            }
            // South
            if(pCell.first + 1 < grid.size() && grid[pCell.first + 1][pCell.second] == 1){
                toExplore.push(make_pair(pCell.first + 1, pCell.second));
            }
            // West
            if(pCell.second - 1 >= 0 && grid[pCell.first][pCell.second - 1] == 1){
                toExplore.push(make_pair(pCell.first, pCell.second - 1));
            }
            // East
            if(pCell.second + 1 < grid[0].size() && grid[pCell.first][pCell.second + 1] == 1){
                toExplore.push(make_pair(pCell.first, pCell.second + 1));
            }
        }
        return curIslandArea;
    }
};

