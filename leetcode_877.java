/*

877. Stone Game
URL = https://leetcode.com/problems/stone-game/

Edge Cases
(A) [4,3,5,6,2,1,9,6,5,7,4,3] => true
(B) [3,8] => true
(C) [3,3,3]

*/
class Solution {
    public boolean stoneGame(int[] piles) {
        boolean canWin = false;
        int n = piles.length;
        boolean[][] dp = new boolean[n+1][n+1];
        for(int i = n; i >= 1; --i)
        {
            for(int j = i; j <= n; ++j)
            {
                if(i == j){
                    dp[i][j] = true;
                } else if ( j == i + 1){
                    dp[i][j] = true; // alice can always choose the greatest of the two els
                } else{
                    boolean curStatus = false;
                    if(piles[i-1] >= piles[i] && dp[i+2][j])
                        curStatus = true;
                    if(piles[j-1] >= piles[j-2] && dp[i][j-2])
                        curStatus = true;
                    if(piles[i-1] >= piles[j-1] && dp[i+1][j-1])
                        curStatus = true;
                    if(piles[j-1] >= piles[i-1] && dp[i+1][j-1])
                        curStatus = true;
                    dp[i][j] = curStatus;
                }
            }
        }
        canWin = dp[1][n];
        return canWin;
    }
}
