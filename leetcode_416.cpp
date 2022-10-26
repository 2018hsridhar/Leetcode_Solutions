/*
URL = https://leetcode.com/problems/partition-equal-subset-sum/
416. Partition Equal Subset Sum

Complexity
Let maxSum = n / 2 * max(nums[i])
Let n := size(nums)
SPACE = O(maxSum * n)
TIME = O(n)

Cases
(A) [1,10,11,5,6,5,5,1] => TRUE
(B) [1,6,2,3] => TRUE
(C) [1,6,2,4] => FALSE
(D) [19,17,8,4,2,1,1,15,3,23,34,54,12] => FALSE


*/
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        bool status = false;
        int startSum = 0;
        int n = nums.size();
        for(auto x : nums)
            startSum += x;
        int halfSum = (startSum/2);
        
        // [1] INIT the DP array
        vector<vector<bool>> DP(halfSum+1,vector<bool>(n));
        for (auto &i : DP)
            std::fill(i.begin(), i.end(), false);
        for(int j = 0; j < n; ++j){
            DP[0][j] = true;
        }
        
        // [2] Exec poly time DP algo
        if(startSum % 2 != 0){
            status = false;
            return status;
        }
        for(int i = 1; i <= halfSum; ++i){
            for(int j = n-1; j >= 0; --j){
                bool curStat = false;
                int remSum = i - nums.at(j);
                if(j + 1 < n)
                    DP[i][j] = DP[i][j+1]; // dammit C++
                if(remSum < 0){
                    curStat = false;
                } else if ( remSum == 0){
                    curStat = true;
                } else if ( remSum > 0) {
                    if(j+1 < n){
                        curStat = DP[remSum][j+1];
                    } else {
                        curStat = false;
                    }
                }
                DP[i][j] = DP[i][j] || curStat;
            }
        }
        status = DP[halfSum][0];
        return status;
    }
};
