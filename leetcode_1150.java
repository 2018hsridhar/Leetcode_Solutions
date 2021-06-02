/*

URL = https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
1150. Check If a Number Is Majority Element in a Sorted Array

*/


// Goal : this is the linear time solution?
// Can we get a binary search solution instead?

class Solution {
    public boolean isMajorityElement(int[] nums, int target) 
    {
        int n = nums.length; 
        int div = (int) n / 2; // cast down to lower value - e..g 9/2 = 4
        int count = 0;
        for(int i = 0; i < nums.length; ++i)
        {
            if(nums[i] == target)
            {
                ++count;
                if(count > div )
                    return true;
            }
        }
        
        return false;
    }
} 
