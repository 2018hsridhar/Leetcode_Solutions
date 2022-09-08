/*

1099. Two Sum Less Than K
Website = https://leetcode.com/problems/two-sum-less-than-k/
Always going least -> greatest here

Complexity
Let N := len(input)
Time = O(NLogN) + O(N) = O(NlgN)
Space = O(1) ( explicit and implicit ) ( excluding the sort ) 

*/
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        int maxSum = 0; // why go to INT MIN when constrain is known too!
        boolean found = false;
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while(i < j) {
            int curSum = nums[i] + nums[j];
            if(curSum < k) {
                maxSum = Math.max(maxSum,curSum); 
                ++i;
                found = true;
            } else
            {
                --j;
            }
        }
        if(!found)
            maxSum = -1;
        return maxSum;
    }
}
