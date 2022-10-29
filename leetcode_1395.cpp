/*
1395. Count Number of Teams
URL = https://leetcode.com/problems/count-number-of-teams/

Edge Cases
(A) [1,1,1,1,1] => 0
(B) [1,2,1] => 0
(C) [1,2,3,4] => 3
(D) [1,3,2] => 0
(E) [1,5,2,3,67,89,54,678,32,12,90,43,234,876,396,201]=> 282

*/
class Solution {
public:
    int numTeams(vector<int>& rating) {
        int myNumTeams = 0;
        int n = rating.size();
        vector<vector<int>> DP(4,vector<int>(n,0)); // 0 init DP vector here!
        vector<vector<int>> DP2(4,vector<int>(n,0)); // 0 init DP vector here!
        // [1] Fill out our base cases here!
        // INCR case
        for(int idx = n-1; idx >= 0; --idx){
            DP[1][idx] = 1;
        }
        for(int seqLen = 2; seqLen <= 3; ++seqLen){
            for(int idx = n-1; idx >= 0; --idx){
                int numTeamsSubProb = 0;
                for(int j = idx + 1; j < n; ++j){
                    if(rating.at(j) > rating.at(idx)){
                        numTeamsSubProb += DP[seqLen-1][j];
                    }
                }
                DP[seqLen][idx] = numTeamsSubProb;
                if(seqLen == 3){
                    myNumTeams += DP[seqLen][idx];
                }
            }
        }
        
        // DECR case
        for(int idx = n-1; idx >= 0; --idx){
            DP2[1][idx] = 1;
        }
        for(int seqLen = 2; seqLen <= 3; ++seqLen){
            for(int idx = 0; idx <= n-1; ++idx) {
                int numTeamsSubProb = 0;
                for(int j = idx - 1; j >= 0; --j){
                    if(rating.at(j) > rating.at(idx)){
                        numTeamsSubProb += DP2[seqLen-1][j];
                    }
                }
                DP2[seqLen][idx] = numTeamsSubProb;
                if(seqLen == 3){
                    myNumTeams += DP2[seqLen][idx];
                }
            }
        }
        return myNumTeams;
    }
};
