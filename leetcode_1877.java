/*

It's definetely go sort our input, and then iterate with the start_cursor and end_cursor meeting in the middle
Luckily, array length is guaranteed evenness too

1877. Minimize Maximum Pair Sum in Array
https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/

*/

class Solution {
    public int minPairSum(int[] nums) 
    {
        int minPairSum = Integer.MIN_VALUE;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length / 2; ++i)
            minPairSum = Math.max(minPairSum, nums[i] + nums[(nums.length - 1) - i]);
        return minPairSum;
    }
}
