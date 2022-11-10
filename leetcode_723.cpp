/*
URL = https://leetcode.com/problems/candy-crush/
723. Candy Crush

*/
class Solution {
public:
    vector<vector<int>> candyCrush(vector<vector<int>>& board) {
        bool execSeq = true;
        while(true){
            // [1] check sequences to destroy
            bool toExecuteCrush = inspectBoard(board);
            if(!toExecuteCrush){
                break;
            } else {
                executeCrush(board);                
            }
        }
        return board;
    }

private:

    void executeCrush(vector<vector<int>>& board){
        int m = board.size();
        int n = board[0].size();
        for(int j = 0; j < n; ++j){ // all cols, and start from the bottom for the push
            int rIdx = m-1;
            int wIdx = m-1;
            while(rIdx >= 0 && wIdx >= 0){
                if(board.at(wIdx).at(j) == -1){
                    wIdx++;
                } else {
                    board.at(rIdx).at(j) = board.at(wIdx).at(j);
                    rIdx++;
                    wIdx++;
                }
            }
            while(rIdx >= 0){
                board.at(rIdx).at(j) = 0;
                rIdx--;
            }
        }
    }

    bool inspectBoard(vector<vector<int>>& board){
        const map<int,vector<vector<int>>> offsets;
        offsets[0] = {{1,0},{2,0}};
        offsets[1] = {{-1,0},{-2,0}};
        offsets[2] = {{0,1},{0,2}};
        offsets[3] = {{0,-1},{0,-2}};
        bool haveCandyToCrush = false;
        int m = board.size();
        int n = board[0].size();
        for(int r = 0; r < m; ++r){
            for(int c = 0; c < n; ++c){
                int el = board.at(r).at(c);
                if(el != 0){
                    // 4 cardinal dir check
                    for(int i = 0; i < 4; ++i){
                        int nextRFirst = r + offsets[i].at(0).at(0);
                        int nextCFirst = c + offsets[i].at(0).at(1);
                        int nextRSecond = r + offsets[i].at(1).at(0);
                        int nextCSecond = c + offsets[i].at(1).at(1);
                        if(isInBounds(nextRFirst,nextCFirst) && isInBounds(nextRSecond,nextCSecond)){
                            if(grid.at(nextRFirst).at(nextCFirst) == el && grid.at(nextRSecond).at(nextCSecond) == el){
                                // mark position ( via negation :-) )
                                grid.at(nextRFirst).at(nextCFirst) *= -1;
                                grid.at(nextRSecond).at(nextCSecond) *= -1;
                                haveCandyToCrush = true;
                            }
                        }
                    }
                }
            }
        }
        return haveCandyToCrush;
    }

    inline bool isInBounds(int i, int j, vector<vector<int>>& board){
        return (
            (0 <= i && i < board.size()) && 
            (0 <= j && j < board[0].size())
        );
    }
};
