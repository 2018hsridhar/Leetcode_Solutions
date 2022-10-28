/*
URL = https://leetcode.com/problems/combination-sum-iv/submissions/
377. Combination Sum IV

*/
class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        int numCombos = 0;
        int n = nums.size();
        vector<int> DP(target+1,-1);
        std::sort(nums.begin(), nums.end());
        helper(nums,DP,target);
        numCombos = DP[target];
        return numCombos;
    }
    
private:
    int helper(vector<int>& nums, vector<int>&DP, int target){
        if(DP[target] >= 0)
            return DP[target];
        int curNumWays = 0;
        for(int j = 0; j < nums.size(); ++j){
            if(target - nums.at(j) > 0){
                curNumWays += helper(nums,DP,target-nums.at(j));
            } else if ( target - nums.at(j) == 0){
                curNumWays++;
            }
        }
        DP.at(target) = curNumWays;
        return curNumWays;
    }

};
