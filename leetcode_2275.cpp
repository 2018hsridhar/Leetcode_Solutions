/*
2275. Largest Combination With Bitwise AND Greater Than Zero
https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/description/
*/
class Solution {
public:
    int largestCombination(vector<int>& candidates) {
        vector<int> countOneInPos(32,0);
        for(auto el : candidates){
            bitset<32> numInBinary(el);
            for(int a = 0; a <= 31; ++a){
                if(numInBinary[a] == 1){
                    countOneInPos.at(a)++;
                }
            }
        }
        int maxComb = 0;
        for(int x : countOneInPos){
            maxComb = max(maxComb,x);
        }
        return maxComb;
    }
};
