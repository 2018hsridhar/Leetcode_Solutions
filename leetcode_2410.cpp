/*
2410. Maximum Matching of Players With Trainers
https://leetcode.com/problems/maximum-matching-of-players-with-trainers/

Complexity
Let P := len(players) and T := len(trainers)
Time = O(plgp) + o(tlgt)
Space = O(1) ( EXP & IMP ) 

*/
class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        int numMatches = 0;
        std::sort(players.begin(), players.end());
        std::sort(trainers.begin(), trainers.end());
        int pPtr = 0;
        int tPtr = 0;
        int m = players.size();
        int n = trainers.size();
        while(pPtr < m && tPtr < n){
            if(players.at(pPtr) <= trainers.at(tPtr)){
                numMatches++;
                ++pPtr;
                ++tPtr;
            } else {
                tPtr++;
            }
        }
        return numMatches;
    }
};
