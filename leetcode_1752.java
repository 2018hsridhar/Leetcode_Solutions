/*
1752. Check if Array Is Sorted and Rotated
https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
*/

class Solution {
    public boolean check(int[] nums) 
    {
        if(nums.length <= 1)
            return true;
        int freq = 0;
        for(int i = 0; i < nums.length; ++i)
        {
            int firstEl = nums[i];
            int secondEl = nums[(i+1) % nums.length];
            if(firstEl > secondEl)
                ++freq;
            if(freq > 1)
            {
                return false;
            }
        }
        if(freq <= 1)
            return true;
        return false;
    }
}
