class Solution {
    public int maximumProduct(int[] nums) 
    {
        Arrays.sort(nums);
        int idx_1 = nums.length - 1;
        int idx_2 = idx_1 - 1;
        int idx_3 = idx_2 - 1;
        int res = nums[idx_1] * nums[idx_2] * nums[idx_3];
        // this assumes strict positivity. handle neg case too!
        
        // handle neg case
        int neg_1 = nums[0];
        int neg_2 = nums[1];
        int other_pos = neg_1 * neg_2 * nums[idx_1];
        if(other_pos > res)
            return other_pos;
        
        return res;
    }
}


/*
Your two stumpers here
Range issues [ multiplying 3 huge ints, can cause an overflow err ]
Handling of negative numbers
*/


// LINK :: https://leetcode.com/problems/maximum-product-of-three-numbers/description/















