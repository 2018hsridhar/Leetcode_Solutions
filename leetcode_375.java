/*
375. Guess Number Higher or Lower II
URL = https://leetcode.com/problems/guess-number-higher-or-lower-ii/

Cases
(A) N = 1 : 0
(B) N = 2 : 1
(C) N = 3 : 2
(D) N = 5 : 6
(E) N = 6 : 8


*/
class Solution {
    public int getMoneyAmount(int n) {
        if(n == 1)
            return 0;
        int[][] memo = new int[n+1][n+1];
        for(int i = 1; i <= n; ++i){
            for(int j = 1; j <= n; ++j){
                memo[i][j] = -1;
                if(j == i)
                    memo[i][i] = 0;
            }
        }
        return solve(1,n,memo);
    }
    
    // Compiler will complain about identifiers/names lacking.
    private int solve(int left, int right,int[][] memo){
        if ( left > right) { // handle boundary/single child cases
            return 0;
        }else if ( left == right) {
            return memo[left][right]; // leaf node case : technically zeroed out
        }
        else if(memo[left][right] != -1) {
            return memo[left][right];
        } else {
            int subProblem = Integer.MAX_VALUE;
            for(int root = left; root <= right; ++root){
                int leftCost = root + solve(left,root-1,memo);
                int rightCost = root + solve(root+1,right,memo);
                subProblem = Math.min(subProblem,Math.max(leftCost,rightCost));
            }
            memo[left][right] = subProblem;
            return subProblem;
        }
    }
}
