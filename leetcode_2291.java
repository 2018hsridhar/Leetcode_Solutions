/*
URL = https://leetcode.com/problems/maximum-profit-from-trading-stocks/
2291. Maximum Profit From Trading Stocks

*/
class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int maxProfit = 0;
        int n = present.length;
        int[][] memo = new int[budget+1][n];
        for(int i = 0; i <= budget; ++i){ // why start at 0 too; nonsensical
            for(int j = 0; j < n; ++j){
                int curProfit = 0;
                int rem = i - present[j];
                if(rem >= 0){
                    curProfit = future[j] - present[j];
                    if(j == 0){
                        curProfit += 0;
                    } else {
                        curProfit += memo[rem][j-1];
                    }
                }
                memo[i][j] = curProfit;
                if(j > 0)
                    memo[i][j] = Math.max(memo[i][j-1],curProfit);
                // System.out.printf("%d,",memo[i][j]);
                maxProfit = Math.max(curProfit,maxProfit);
            }
            // System.out.println();
        }
        return maxProfit;
    }
}
