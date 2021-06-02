/*

1827. Minimum Operations to Make the Array Increasing
https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/

*/

class Solution {
    public int minOperations(int[] nums) 
    {
        int numOps = 0;
        for(int i = 0; i < nums.length - 1; ++i)
        {
            if(nums[i] >= nums[i+1])
            {
                numOps += nums[i] - nums[i+1] + 1;
                nums[i+1] = nums[i] + 1;
            }
        }
        return numOps;
    }
}
