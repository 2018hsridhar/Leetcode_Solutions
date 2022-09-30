/*

2148. Count Elements With Strictly Smaller and Greater Elements
URL = https://leetcode.com/problems/count-elements-with-strictly-smaller-and-greater-elements/

(A) [-3,-3,-3,3,3,90,90,90] => 3
(B) [3] => 0
(C) [1,2] => 0
(D) [1,2,3] => 1
(E) [-3,-3,-3,90,90,90] => 0
(F)  [0,0,0] => 0
(G)


*/
// But it is also a 
class Solution {
    public int countElements(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        int lPtr = 0;
        int n = nums.length;
        boolean jumpedOnce = false;
        int[] countJumps = new int[n];
        while(lPtr < n - 1){
            if(nums[lPtr] == nums[lPtr+1]){
                if(jumpedOnce)
                    countJumps[lPtr]++;
                lPtr++;
            }
            else {
                if(!jumpedOnce){
                    jumpedOnce = true;
                } else {
                    countJumps[lPtr]++;
                }
                lPtr++;
            }
        }
        jumpedOnce = false;
        int rPtr = n-1;
        while(rPtr > 0){
            if(nums[rPtr] == nums[rPtr-1]){
                if(jumpedOnce)
                    countJumps[rPtr]++;
                rPtr--;
            }
            else {
                if(!jumpedOnce){
                    jumpedOnce = true;
                } else {
                    countJumps[rPtr]++;
                }
                rPtr--;
            }
        }
        for(int x : countJumps){
            count = (x == 2) ? count + 1 : count + 0;
        }
        return count;
    }
}
