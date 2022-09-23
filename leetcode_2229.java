/*
2229. Check if an Array Is Consecutive
URL = https://leetcode.com/problems/check-if-an-array-is-consecutive/
*/
class Solution {
    public boolean isConsecutive(int[] nums) {
        boolean status = true;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; ++i){
            if(nums[i] != nums[i+1]-1) {
                status = false;
                break;
            }
        }
        return status;
    }
}
