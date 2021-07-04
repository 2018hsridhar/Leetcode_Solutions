/*

1846. Maximum Element After Decreasing and Rearranging
URL = https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/

Array values guaranteed reasonability within int range [1,10^9]
Array length also guaranteed reasonability

Must decrease array value strictly to a smaller positive integer ... BUT ... array values guaranteed positivity here
No need to even perform max El operations
Yes the problem is easier than you think

Time complexity = O(NLogN) + O(N) = O(NLogN)
Space complexity = O(1)

*/


class Solution 
{
    // Value of first element must be a one -> go decrement that accordingly!
    public int maximumElementAfterDecrementingAndRearranging(int[] arr)
    {
        Arrays.sort(arr);
        arr[0] = 1; // forced decreemnt here
        int maxEl = arr[0]; // by default
        for(int i = 1; i < arr.length; ++i)
        {
            int diff = arr[i] - arr[i-1];
            if(diff > 1)
                arr[i] = arr[i-1] + 1;
            if(diff >= 1)
                maxEl = arr[i];
        }
        return maxEl;
    }
}
