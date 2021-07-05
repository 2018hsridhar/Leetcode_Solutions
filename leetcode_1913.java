/*
1913. Maximum Product Difference Between Two Pairs
https://leetcode.com/problems/maximum-product-difference-between-two-pairs/

Nums length is guaranteed reasonability here
Nums is guaranteed to be reasonable - do not expect a datatype overflow
*/
class Solution 
{
    public int maxProductDifference(int[] nums) 
    {
        int maxProdDiff = 0;
        Arrays.sort(nums);
        int n = nums.length;
        int prodDiff = (nums[n-1] * nums[n-2]) - (nums[1]*nums[0]);
        maxProdDiff = (int)(Math.abs(prodDiff));
        return maxProdDiff;
    }
}
