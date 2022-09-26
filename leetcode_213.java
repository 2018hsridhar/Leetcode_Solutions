/*

213. House Robber II
URL = https://leetcode.com/problems/house-robber-ii/

Edge Cases
(A) [1,1,1,1,1,1,1,1] => 4
(B) [1,4,65,87,21,43,21,87,1000,213,34,12,76,98,32,65,87,9,23] => 1386
(C) [0,0,0,0,0,0,1] => 1
(D) [23,12,45,45,45,23,65,76,8,5,8,98,98,32,432,83,321,639,432,987,932,29,71,50,23,42,100,1,12]
    -> correct too

*/
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int maxMoney = 0;
        int n = nums.length;
        int[] subProblemsIgnoreLast = new int[n];
        int[] subProblems = new int[n];
        int i = n-1;
        subProblems[i] = nums[i];
        subProblemsIgnoreLast[i] = 0;
        maxMoney = nums[n-1];
        i--;
        // You alwyas set current max = the first house you rob too!
        // You may do a max across multiple subproblems too.
        while(i >= 0){
            int curMax = nums[i];
            int curMaxIgLast = nums[i];
            for(int j = i+2; j < n; ++j){
                int subProb = subProblems[j];
                int subProbIg = subProblemsIgnoreLast[j];
                if(nums[i] + subProb > curMax) {
                    curMax = nums[i] + subProb;
                } 
                if(nums[i] + subProbIg > curMaxIgLast) {
                    curMaxIgLast = nums[i] + subProbIg;
                }
            }
            // Remember to write back and SOLVE your subproblems too!
            subProblems[i] = curMax;
            subProblemsIgnoreLast[i] = curMaxIgLast;
            if(i == 0){
                maxMoney = Math.max(maxMoney, curMaxIgLast);
            } else {
                maxMoney = Math.max(maxMoney, Math.max(curMax,curMaxIgLast));
            }
            --i;
        }
        return maxMoney;
    }
}
