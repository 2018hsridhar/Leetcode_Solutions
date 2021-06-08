/*
1848. Minimum Distance to the Target Element
https://leetcode.com/problems/minimum-distance-to-the-target-element/

*/
class Solution {
    public int getMinDistance(int[] nums, int target, int start) 
    {
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i)
        {
            if(nums[i] == target)
            {
                minDist = Math.min(minDist, Math.abs(i - start));
            }
        }
        return minDist;
    }
}
