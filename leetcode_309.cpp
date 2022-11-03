/*
URL = https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
309. Best Time to Buy and Sell Stock with Cooldown

*/
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        std::vector<int>DP(n,0);
        for(int i = n-2; i >= 0; --i){
            int curSubProblemSolution = 0;
            for(int j = i + 1; j < n; ++j){
                int transProfit = prices.at(j) - prices.at(i);
                curSubProblemSolution = (j >= n-2) ? 
                    std::max(curSubProblemSolution, transProfit) : 
                    std::max(curSubProblemSolution, transProfit + DP[j+2]);
            }
            DP[i] = std::max(curSubProblemSolution, DP[i+1]);
        }
        int globalMaxProfit = DP[0];
        return globalMaxProfit;
    }
};
