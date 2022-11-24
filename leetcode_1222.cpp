/*
URL = https://leetcode.com/problems/queens-that-can-attack-the-king/
1222. Queens That Can Attack the King

Complexity
Const time, const space solution ( 8x8 chessboard only ) 

13 mins to solution
*/
class Solution {
public:
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        int n = 8;
        vector<vector<int>> board(n, vector<int>(n,0));
        for(const vector<int> queen : queens){
            int qX = queen.at(0);
            int qY = queen.at(1);
            board.at(qX).at(qY) = 1;
        }
        int kX = king.at(0);
        int kY = king.at(1);
        board.at(kX).at(kY) = -1;
        vector<vector<int>> steps = {
            {-1,-1}, // Northwest
            {-1,0}, //North
            {-1,1}, // NORTHEAST
            {0,1}, // EAST
            {1,1}, // SOUTHEAST
            {1,0}, // SOUTH
            {1,-1}, // SOUTHWEST
            {0,-1}, // West
        };
        vector<vector<int>> attackingQueens;
        for(vector<int>& step : steps){
            checkAttackInDirOfStep(board,king,step, attackingQueens);
        }    
        return attackingQueens;
    }

private:
    void checkAttackInDirOfStep(const vector<vector<int>>& board, const vector<int>& king, const vector<int>& step,vector<vector<int>>& attackingQueens){
        int curX = king.at(0);
        int curY = king.at(1);
        while(isInBounds(board,curX,curY)){
            if(board.at(curX).at(curY) == 1){
                attackingQueens.push_back({curX,curY});
                break;
            }
            curX += step.at(0);
            curY += step.at(1);
        }
    }

    inline bool isInBounds(const vector<vector<int>>& board, int x, int y){
        return (0 <= x && x < board.size()) && (0 <= y && y < board.at(0).size());
    }
};
