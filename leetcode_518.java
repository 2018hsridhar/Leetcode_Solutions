/*

URL = https://leetcode.com/problems/coin-change-2/
518. Coin Change 2

Yes the denominations of coins are strictly integers 
Return the #-of-combinations

Strategies : Recursive, Backtracking, DP

Base cases : n = 0,1 : be careful with case of n = 0
Each coin is unique - we can perform a sort as well
amount does not get too large

COMPLEXITY [ BUDP ] 
Let N := amount
Let K := #-coins
Time = O(N*K)
Space = O(N) ( explicit ) 

Need <N> storage as we can have K seperate coins as well

TEST BENCH : 
(A) 
(B)
(C)
(D)
(E)
(F)

*/
class Solution 
{
    public int change(int amount, int[] coins) 
    {
        int numberCombos = 0;
        Arrays.sort(coins);
        int n = amount;
        int k = coins.length;
        int[][] memo = new int[n + 1][k]; // it is default initialized to zero anyways
        for(int j = 0; j < k; ++j)
        {
            memo[0][j] = 1; // always one way to make the zero coin ( and if set to zero ::: nothing added anyways )
        }
        for(int subAmount = 1; subAmount <= n; ++subAmount)
        {
            int currCombos = 0;
            for(int j = k-1; j >= 0; --j)
            {
                int difference = subAmount - coins[j];
                if(difference >= 0)
                {
                   currCombos += memo[difference][j];       
                }
                memo[subAmount][j] = currCombos;
            }
        }
        
        numberCombos = memo[n][0];  // shoudl be 0 here ( as that denotes total amount ) 
        return numberCombos;
    }
}
