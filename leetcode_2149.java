/*
2149. Rearrange Array Elements by Sign
URL = https://leetcode.com/problems/rearrange-array-elements-by-sign/

Can we do this solution in-place too?
Constraints of this problem help impose good bounds too.
Rearrange one sign : the other sign will follow accordingly.

Complexity
Let N := len(nums)
Time = O(N^2)
Space = O(1) [ EXPL & IMPL ] 

Case Testing
(A) [-1,-2,-3,3,2,1]
    PASS
(B) [-1,1,-2,2,-3,3]
    PASS
(C) [1,-1,-2,2,3,-3]
    PASS
(D) [3,1,-2,-5,2,-4]
    PASS
(E) [-5,-6,-7,-8,1,2,-10,20,3,4]
    PASS
(F)

*/
class Solution {
    public int[] rearrangeArray(int[] nums) {
        // Positives are written to indices 0,2,4, ... so on
        // The other recc : start iteratoin from right -> avoid the double reading case!
        // The solution works, but yields a TLE
        // We have a better solution in O(N) Time
        
        int n = nums.length;
        int posPtrOne = 0;
        int negPtrOne = 1;
        int[] results = new int[n];
        for(int i : nums){
            if(i > 0) {
                results[posPtrOne] = i;
                posPtrOne = posPtrOne + 2;
            } else {
                results[negPtrOne] = i;
                negPtrOne = negPtrOne + 2;
            }
        }
        
        
        return results;
//         int n = nums.length;
//         int posPtrOne = 0;
//         int i = 0;
//         while(i < n){
//             if(nums[i] > 0){
//                 for(int j = i; j > posPtrOne; --j){
//                     swap(nums,j,j-1);
//                 }
//                 posPtrOne++;
//             }
//             ++i;
//         }        
//         int posPtr = n-2;
//         i = (n/2) - 1;
//         while(i >= 0) {
//             for(int j = i; j < posPtr; ++j){
//                 swap(nums,j,j+1);
//             }
//             posPtr = posPtr - 2;
//             --i;
//         }
//         return nums; // we typically avoid returning inputs in non-void function calls!
    }
    
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
