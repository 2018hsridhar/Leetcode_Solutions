/*
URL = https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/
2369. Check if There is a Valid Partition For The Array

*/
class Solution {
public:
    bool validPartition(vector<int>& nums) {
        bool status = false;
        int n = nums.size();
        vector<vector<bool>> stat(3,vector<bool>(n,false));
        vector<bool> DP(n,false);
        // 1st dim = 2 eq el
        // 2nd dim = 3 eq el
        // 3rd dim = 3 consec incr els
        for(int j = n-2; j >= 0; --j){
            if(nums.at(j) == nums.at(j+1)){
                stat[0][j] = true;
            }
        }
        // Set your base cases too!
        for(int j = n-3; j >= 0; --j){
            if(nums.at(j) == nums.at(j+1) && nums.at(j+1) == nums.at(j+2)){
                stat[1][j] = true;
            }
            // cout << "j = " << j << "\t" << stat[1][j] << endl;
            if(nums.at(j) == nums.at(j+1) - 1 && nums.at(j+1) == nums.at(j+2) - 1){
                stat[2][j] = true;
            }
        }
        for(int j = n - 1; j >= 0; --j){
            if(stat[0][j]) {
                if(j + 2 < n) {
                    DP[j] = DP[j+2];
                } else if ( j + 2 == n){
                    DP[j] = true;
                }
            }
            if(stat[1][j] || stat[2][j]){
                if(j + 3 < n) {
                    DP[j] = DP[j] || DP[j+3];
                } else if ( j + 3 == n){
                    DP[j] = true;
                }
            }
        }
        status = DP[0];
        return status;
    }
};
