/*
1029. Two City Scheduling
URL = https://leetcode.com/problems/two-city-scheduling/


*/
class Solution {
public:
    int twoCitySchedCost(vector<vector<int>>& costs) {
        int n = ( costs.size() / 2 );
        vector<vector<int>> dp(n+1,vector<int>(n+1,0));
        // Ensure to fill in your boundary cases too here
        // maybe there is a preference ( for dealing with sizes : e.g. 1 element left cases )
        for(int i = 0; i <= n; ++i){
            for(int j = 0; j <= n; ++j){
                int curCostIdx = (i + j) - 1;
                if(i == 0 && j == 0){
                    dp[i][j] = 0;
                } else if ( i == 0 && j > 0){
                    dp[i][j] = costs[curCostIdx][1] + dp[i][j-1];
                } else if ( i > 0 && j == 0){ 
                    dp[i][j] = costs[curCostIdx][0] + dp[i-1][j];
                } else {
                    dp[i][j] = std::min(
                        costs[curCostIdx][0] + dp[i-1][j],
                        costs[curCostIdx][1] + dp[i][j-1]
                    );
                }
            }
        }
        int minCost = dp[n][n];
        return minCost;
    }
};
