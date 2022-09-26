/*

376. Wiggle Subsequence
URL = https://leetcode.com/problems/wiggle-subsequence/

Edge Cases
(A) [0,0,0,0,0,0] -> 0
    ^ what if no real wiggle here?
(B)  [1,7,4,9,2,5] -> 6
(C) [5,5] => 1
(D)[5,6] -> 2
(E) [0,0,0,0,1] -> 2
(F) [1,2,3,4] -> 2
(G) [1,17,5,10,13,15,10,5,16,8] -> 7
(H) [132,432,45,7,65,87,98,90,12,1,1,1,23,43,12,76,45,89,76,43] -> 11
(I) [5,4,3,2,1] -> 2
(J)
(K)


*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // Handle the trivial wiggle sequences here
        if(nums == null || nums.length == 1) 
            return 1;
        int n = nums.length;
        if(n == 2) {
            if(nums[0] == nums[1] )
                return 1;
            return 2;
        }
        int wiggleLen = 0;
        int i = 0;
        // Eradicate the prefix of all values equalling the same here.
        while(i+1 < n && nums[i] == nums[i+1]) {
            i++;
        }
        if(i == n-1)
            return 1;
        
        // Test both possible directions here.
        boolean goPos = false;
        boolean goNeg = false;
        if(i+1 < n) {
            if(nums[i] < nums[i+1])
                goPos = true;
            else if ( nums[i] > nums[i+1])
                goNeg = true;
            i++;
        }
        while((i+1) < n){
            if(goPos) {
                if(nums[i] > nums[i+1]){
                    goNeg = true;
                    goPos = false;
                    wiggleLen++;
                }
            } else if ( goNeg ) {
                if(nums[i] < nums[i+1]){
                    goNeg = false;
                    goPos = true;
                    wiggleLen++;
                }                
            }
            ++i;
        }
        wiggleLen += 1; // for the first edge not "WIGGLED" in the while loop
        wiggleLen += 1; // property of trees : (n-1) edges => (n) vertices
        return wiggleLen;
    }
}
