/*
URL = https://leetcode.com/problems/find-subarrays-with-equal-sum/
2395. Find Subarrays With Equal Sum

Let N := len(nums)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 

*/
class Solution {
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> visited = new HashSet<Integer>();
        for(int i = 1; i < n; ++i){
            int nextSum = nums[i] + nums[i-1];
            if(visited.contains(nextSum)){
                return true;
            }
            visited.add(nextSum);
        }
        return false;
    }
}
