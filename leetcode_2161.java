/*

2161. Partition Array According to Given Pivot
URL = https://leetcode.com/problems/partition-array-according-to-given-pivot/

COMPLEXITY CLASSES :
An in-place approach is O(N^2) in Time, but O(1) in Space
And entails a reduced memory footprint.
We have a faster Time = O(N), Space = O(N) Solution, but this warrants creating a stack-allocated static array.

Edge Cases
(A)
(B)
(C)
(D)

Inline a function such as `swap()` for efficiency :-).
Let compiler optimize away extraneous function calls.

*/
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int lIdx = 0;
        int rIdx = n-1;
        // Fill the lower end : vals < pivot
        for(int i = 0; i < n; ++i){
            if(nums[i] < pivot){
                int j = i;
                // At end of loop, assert that we wrote to nums[lIdx]
                while(j > lIdx){
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                    j--;
                }
                lIdx++;
            }
        }
        // Fill the higher end : vals > pivot ( but reverse directionality of iteration )
        for(int i = n-1; i >= 0; --i) {
            if(nums[i] > pivot){
                // At end of loop, assert that we wrote to nums[lIdx]
                int j = i;
                while(j < rIdx){
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                    j++;
                }
                rIdx--;
            }
        }
        
        return nums;
    }
}
