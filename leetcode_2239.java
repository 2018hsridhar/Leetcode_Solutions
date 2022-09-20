/*
2239. Find Closest Number to Zero
URL = https://leetcode.com/problems/find-closest-number-to-zero/

*/
class Solution {
    public int findClosestNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if(nums[0] >= 0) {
            return nums[0];
        } else if ( nums[n - 1] <= 0) {
            return nums[n-1];
        }
        int i = 0;
        while(i+1 < n){
            if(nums[i] < 0 && nums[i+1] >= 0) {
                break;
            } else { 
                ++i;
            }
        }
        if(nums[i+1] == 0) {
            return nums[i+1];
        }
        // have the discontinuity -> do checks here
        int d1 = (int)(Math.abs(0 - nums[i]));
        int d2 = (int)(Math.abs(0 - nums[i+1]));
        if(d1 < d2) {
            return nums[i];
        } else if ( d1 >= d2 ) {
            return nums[i+1];
        }
        return nums[0]; // single el case
    }
}
