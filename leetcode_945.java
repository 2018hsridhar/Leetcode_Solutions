/*
945. Minimum Increment to Make Array Unique
// [3,2,1,2,1,7]
// [1,1,2,2,3,7] -> [1,2,3,4,5,7] -> [0,1,1,2,2] = 6 moves
// Greedy sort problem TBH
// Just order the elements :-)

URL = https://leetcode.com/problems/minimum-increment-to-make-array-unique/
*/
class Solution {
    public int minIncrementForUnique(int[] nums) {
        int minMoves = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; ++i)
        {
            if(nums[i+1] <= nums[i])
            {
                int diff = nums[i] - nums[i+1] + 1;
                minMoves += diff;
                nums[i+1] = nums[i] + 1;
            }
        }
        return minMoves;
    }
}

