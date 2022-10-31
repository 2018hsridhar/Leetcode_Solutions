/*
URL = https://leetcode.com/problems/maximum-sum-score-of-array/submissions/
2219. Maximum Sum Score of Array

*/
class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        long long maxRunSumFwd = 0;
        long long maxRunSumBwd = 0;
        long long mss = INT_MIN;
        int n = nums.size();
        for(int i = 0; i < n; ++i){
            maxRunSumFwd += nums.at(i);
            mss = std::max(mss,maxRunSumFwd);
        }
        for(int i = n-1; i >= 0; --i){
            maxRunSumBwd += nums.at(i);
            mss = std::max(mss,maxRunSumBwd);
        }
        return mss;
    }
};
