/*

1413. Minimum Value to Get Positive Step by Step Sum
https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/

THOUGHT PROCESSES : 

Desired time-space complexity : [T,S] = [O(N), O(1)]

1. Can we track a running sum here?
2. Can we find out what the largest possible dip can be? 

*/

class Solution 
{
    public int minStartValue(int[] nums) 
    {
        int runSum = 0;
        int runSum_min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i)
        {
            runSum += nums[i];
            runSum_min = Math.min(runSum_min, runSum);
            
        }
        if(runSum_min >= 0 )
            return 1;
        return ( 1 - runSum_min );
    }
}
