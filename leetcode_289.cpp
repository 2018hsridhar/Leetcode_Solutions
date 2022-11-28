/*
URL = https://leetcode.com/problems/game-of-life/
289. Game of Life

14 mins to in-place

In-place solution
*/
class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
        const size_t m = board.size();
        const size_t n = board[0].size();
        std::vector<std::vector<int>> hood = {
            {-1,-1}, // NW
            {-1,0}, // N
            {-1,1}, // NE
            {0,1}, // E
            {1,1}, // SE
            {1,0}, // S
            {1,-1}, // SW
            {0,-1} // W
        };
        for(int r = 0; r < m; ++r){
            for(int c = 0; c < n; ++c){
                int countLiveHood = 0; // count1
                for(int k = 0; k < hood.size(); ++k){
                    int hoodR = r + hood.at(k).at(0);
                    int hoodC = c + hood.at(k).at(1);
                    if(isInBounds(board,hoodR,hoodC)){
                        if(board.at(hoodR).at(hoodC) == 1 || board.at(hoodR).at(hoodC) == 3){
                            countLiveHood++;
                        }
                    }
                }
                if(board.at(r).at(c) == 1){
                    if(countLiveHood < 2){
                        board.at(r).at(c) = 3; // live -> dead
                    } else if ( countLiveHood > 3 ) {
                        board.at(r).at(c) = 3;
                    }
                } else {
                    if(countLiveHood == 3){
                        board.at(r).at(c) = 2; // dead -> live
                    }
                }
            }
        }
        for(int r = 0; r < m; ++r){
            for(int c = 0; c < n; ++c){
                if(board.at(r).at(c) == 2){
                    board.at(r).at(c) = 1;
                }
                else if ( board.at(r).at(c) == 3){
                    board.at(r).at(c) = 0;
                }
            }
        }
    }

private:
    bool isInBounds(vector<vector<int>>& grid, int r, int c){
        int m = grid.size();
        int n = grid[0].size();
        return (0 <= r && r < m) && (0 <= c && c < n);
    }
};
