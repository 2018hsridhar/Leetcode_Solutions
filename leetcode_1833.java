/*

URL = https://leetcode.com/problems/maximum-ice-cream-bars/
1833. Maximum Ice Cream Bars

Highly akin to the coin change problem
As in typical 1D array greedy problems, let us sort the input array in order of strictly increasing costs

Computational Complexity : 
Time = O(NLogN) + O(N) = O(NLogN), where N := number of elements in change array
Space = O(1)

Bounds and data type : 
Array lengths reasonable : min len = 1, max len = 100,000
Costs reasonable : natural numbers bounded by the closed interval [1,100,000]
Coins also reasonable - similar boundary too



Edge case testing : 
(1) Normal case of [1,6,3,1,2,5], coins = 20
(2) Failing case :  [10,6,8,7,7,8], coins = 5
(3) [1,1,1,1,1,1] coins = 6 : sum over all array elements
(4) [1,1,1,1,1,100] coins = 5 : (n-1:n) partition
-> singletons
(5) [1] coins = 100 - passes
(6) [100] coins = 90 - fails
(7) costs = [1,6,3], coins = 20 -> keep subtracting coins; BUT; do not go over board num indices





*/


class Solution 
{
    public int maxIceCream(int[] costs, int coins) 
    {
        int maxCream = 0;
        Arrays.sort(costs);
        int i = 0;
        while(coins >= 0 && i < costs.length)
        {
            int nextCoin = coins - costs[i];
            if(nextCoin >= 0)
                ++maxCream;
            coins = nextCoin;
            ++i;
            
        }
        
        return maxCream;
    }
}
