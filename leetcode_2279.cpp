/*
2279. Maximum Bags With Full Capacity of Rocks
URL = https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/description/'

Complexity
Let n := len(rocks)
TIME = O(NLGN)
SPACE = O(1) ( EXP & IMP ) 

7 mins to solution
*/
class Solution {
public:
    int maximumBags(vector<int>& capacity, vector<int>& rocks, int additionalRocks) {
        int maxBags = 0;
        int n = rocks.size();
        vector<int> remRocks(n,0);
        for(int i = 0; i < n; ++i){
            remRocks.at(i) = capacity.at(i) - rocks.at(i);
        }
        std::sort(remRocks.begin(), remRocks.end());
        for(int i = 0; i < n; ++i){
            if(remRocks.at(i) == 0){
                maxBags++;
            } else if ( remRocks.at(i) > 0){
                int restRocks = additionalRocks - remRocks.at(i);
                if(restRocks >= 0){
                    maxBags++;
                    additionalRocks = restRocks;
                } else {
                    break;
                }
            }
        }
        return maxBags;
    }
};
