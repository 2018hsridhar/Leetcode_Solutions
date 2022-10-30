/*
n can grow large : up to 5K here
39 minutes to a working solution!p

935. Knight Dialer
URL = https://leetcode.com/problems/knight-dialer/

Base case : n == 1.
Your algo si right
10 mins debugging bad assignment expression :-O
Handle the modulo now!

Complexity
TIME = O(N*CONST)
SPACE = O(N*CONST)

*/
class Solution {
public:
    int knightDialer(int n) {
        int modConst = static_cast<int>(pow(10,9))+7;
        int numR = 4;
        int numC = 3;
        long long glblNumDial = 0;
        vector<pair<int,int>> moves{{-2,1},{-2,-1},{-1,2},{-1,-2},{1,2},{1,-2},{2,1},{2,-1}};
        // what an atrocious initialization too!
        vector<vector<vector<long long>>> memo(n+1,vector<vector<long long>>(4,vector<long long>(3))); // and we know where not to go too!
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 3; ++j){
                memo[1][i][j] = 1;
                if(i == 3 && j != 1)
                    memo[1][i][j] = 0;
            }
        }
        // NOTE : do not count already visited places again by accident
        // Should be 20 for n = 2! Take note!
        // Wait a second ... it this mathematical actually?
        for(int curLen = 2; curLen <= n; ++curLen){
            for(int i = 0; i < 4; ++i){
                for(int j = 0; j < 3; ++j){
                    int curSubProblemNum = 0;
                    if(i == 3 && (j == 0 || j == 2))
                        continue;
                    for(auto& move : moves){
                        pair<int,int> nextMove = make_pair(i + move.first, j + move.second);
                        if(isInBoundsAndNumeric(nextMove,numR,numC)){
                            // resetting curSubProblemNum : never incrs enough! This is your bug!
                            curSubProblemNum += memo[curLen-1][nextMove.first][nextMove.second];
                            curSubProblemNum = curSubProblemNum % modConst;
                        }
                    }
                    memo[curLen][i][j] = curSubProblemNum;
                }
            }
        }
        // get sum here
        for(int i = 0; i < numR; ++i){
            for(int j = 0; j < numC; ++j){
                glblNumDial += memo[n][i][j];
            }
        }
        return glblNumDial % modConst;
    }
    
private:
    bool isInBoundsAndNumeric(pair<int,int>& cur, int m, int n){
        bool isInBounds = ((0 <= cur.first && cur.first < m) && ( 0 <= cur.second && cur.second < n));
        bool isNumeric = !(cur.first == 3 && (cur.second == 0 || cur.second == 2));
        return (isInBounds && isNumeric );
    }
};
