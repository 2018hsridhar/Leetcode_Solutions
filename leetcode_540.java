/*

540. Single Element in a Sorted Array
URL = https://leetcode.com/problems/single-element-in-a-sorted-array/

Thought process
-> Most definitely need binary search here 
-> Needs to be a divide and conquer algo as well
-> Can we leverage following knowledge?
    (a) Array length
    (b) the index of our search
    (c) The direction we find the other element at?

Thee array lengths must be odd ==> can NOT be even as well

Complexity : 
Time = O(log(N))
Space = O(1)

Edge Case Testing : 
(A) NULL
(B) []
(C) [1]
(D) [1,1,2,3,3]
    => 2
(E) [1,1,2,2,3]
    => 3
(F) [1,2,2,3,3]
    => 1
(G) [1,1,4,4,9,9,10,11,11]
    => 10
(H)


*/

class Solution 
{
    public int singleNonDuplicate(int[] nums) 
    {
        // [1] BASE CASE HANDLING
        if(nums == null)
            return 0;
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        
        int low = 0;
        int high = nums.length - 1;
        int nonDupl = findNonDupl(nums, low, high);
        return nonDupl;
    }
    
    // Utilize length from (low,high_ here as well)
    public int findNonDupl(int[] nums, int low, int high)
    {
        int res = 0;
        if(low > high)
            return -1; 
        else
        {
            int mid = (low + high) / 2;
            // Perform bounds checks as well here
            int target = nums[mid];
            // Perform comparison checks at first before recursing on the children : with boudns in lace
            int left_len = (mid - low + 1);
            int right_len = (high - mid + 1);
            if(mid - 1 >= 0)
            {
                int leftVal = nums[mid-1];
                if(target == leftVal )
                {
                    if(left_len % 2 == 1)
                        return findNonDupl(nums, low, mid - 2);     
                    else if ( left_len % 2 == 0) // you go right here
                        return findNonDupl(nums, mid + 1, high);
                }
            }
            // needs to be if - not else if? HUH?
            if(mid + 1 < nums.length)
            {
                int rightVal = nums[mid+1];
                if(target == rightVal )
                {
                    if (right_len % 2 == 1)
                        return findNonDupl(nums, mid + 2, high);
                    if ( right_len % 2 == 0) // you go right here
                        return findNonDupl(nums, low, mid - 1);
                }
            }
            // Passed both checks : set res = mid
            res = target;
        }
        return res;
    }
    
}
