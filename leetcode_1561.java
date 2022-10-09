/*
1561. Maximum Number of Coins You Can Get
URL = https://leetcode.com/problems/maximum-number-of-coins-you-can-get/

Complexity
Time = O(NlgN)
Space = Space(Sort_Lib_Call) + O(1) ( my_code_logic : imp * expl )

*/
class Solution {
    public int maxCoins(int[] piles) {
        int myMaxCoins = 0;
        Arrays.sort(piles);
        int n = piles.length;
        int lPtr = 0;
        int rPtr = n-2;
        while(rPtr > lPtr){
            myMaxCoins += piles[rPtr];
            rPtr -= 2;
            lPtr++;
        }
        return myMaxCoins;   
    }
}
