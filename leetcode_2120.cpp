/*
2120. Execution of All Suffix Instructions Staying in a Grid
URL = https://leetcode.com/problems/execution-of-all-suffix-instructions-staying-in-a-grid/
Brute Forcing seems fine : string of len 500, 500^2 = 50,000 operations
14 mins to solution 

Compelxity
Let M,N := dims(grid)
Let S := len(string)
TIME = O(S^2)
SPACE = O(1) ( EXP  & IMP ) 
*/
class Solution {
public:
    // This is our start position.
    vector<int> executeInstructions(int n, vector<int>& startPos, string s) {
        vector<int> numExecutable;
        map<char,pair<int,int>> delta;
        delta['L'] = make_pair(0,-1);
        delta['U'] = make_pair(-1,0);
        delta['R'] = make_pair(0,1);
        delta['D'] = make_pair(1,0);
        int m = s.size();
        for(int lPtr = 0; lPtr < m; ++lPtr){
            int numExec = 0;
            int curX = startPos.at(0);
            int curY = startPos.at(1);
            int rPtr = lPtr;
            while(rPtr < m){
                char instToExec = s.at(rPtr);
                pair<int,int> shift = delta[instToExec];
                curX += shift.first;
                curY += shift.second;
                if(inBounds(curX,curY,n)){
                    numExec++;
                } else {
                    break;
                }
                ++rPtr;
            }
            numExecutable.push_back(numExec);
        }
        
        return numExecutable;        
    }

private:
    bool inBounds(int x, int y, int n){
        return (0 <= x && x < n) && (0 <= y && y < n);
    }
};
