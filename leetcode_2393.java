/*
2393. Count Strictly Increasing Subarrays
URL = https://leetcode.com/problems/count-strictly-increasing-subarrays/

Edge Cases
(A) [1,1,1,1,1] => 5
(B) [1,2,3] => 6
(C) [1] => 1
(D) [5,4,3,2,1] => 5
(E) [1,2,3,4,5] => 15
* (F) [1,3,5,4,4,6] => 10

Let N := #-elements in the array (nums)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 
Array guaranteed to have at least a single element too.

*/
class Solution {
    public long countSubarrays(int[] nums) {
        long countSubArrs = 0;
        int i = 0;
        int n = nums.length;
        long curLen = 1;
        while(i+1 < n){
            if(nums[i] < nums[i+1]){
                curLen++;
            } else {
                countSubArrs += ((curLen*(curLen+1))/2);
                curLen = 1; // restart counter
            }
            ++i;
        }
        countSubArrs += ((curLen*(curLen+1))/2);
        // i now at element n here -> do we count it too?
        return countSubArrs;
    }
}
