/*

THOUGHT PROCESS : 
URL = https://leetcode.com/problems/minimum-size-subarray-sum/
209. Minimum Size Subarray Sum

Whenever contiguous subarrays are mentioned, of varying lengths, bring up the following techniques
1. Prefix sums
2. Sliding windows via priority queues

Ideal [T,S] = [O(N), O(1)] one-pass linear scan, "in-place" performance

Edge cases : 

(1) Singleton arrays : len = 1 such as [11]. Quickly check if equal to target or not
(2) [0,0,0,.....,0] Arrays with repeating values 
(3) (n-1:1) partition such as [0,0,0,......,0,1] : and say, target = {0,1} . Make sure to return valid if either a single element, but not if target is outside the set of elements encompassed1
(4) Case where target is not found in array : return 0
(5) [1,2,3] : target = 6 : all elements to sum up case
(6) Case such as [2,3,1,2,3,4], and target = 5 : we have two contiguous subarrays whose sum >= target 


Luckily, input lacks any negatives or zero : strictly natural numbers within reasonable range [1,10^5]
Max summation possible = [10^5 TIMES 10^5] = 10^10 ( target = 10^9 ). 
10000000000 should not overflow the itneger datatype
// int test = 10000000000;
// if(test<minLen) System.out.println("test value < max value of a INT ");
// Needs further exploration here

Can one binary search for minimal subarray length? Versus testing out all subarray lengths?
Total number of contiguous subranges, of an array, naturall equals sum of natural numbers from 1 to n, where n := length of array/number of elements . It entails a BFS solution of O(N^2) then, as summation formula = (N^2 + N ) / 2

Current time-space complexity : [T,S] = [O(NlogN), O(1)]
O(N) + O(NlogN) = time : preprocessing + ( testing each subarray, during binary search )

*/

class Solution 
{
    public int minSubArrayLen(int target, int[] nums) 
    {
        int minLen = Integer.MAX_VALUE;
        
        // Quickly handle base case
        if(nums.length == 1 )
        {
            if(nums[0] >= target) return 1;
            return 0;
        }
        
        // Build prefix array sum
        // int[] prefix_arr = new int[nums.length];
        // for(int i = 0; i < nums.length; ++i)
        //     prefix_arr[i] = nums[i];
        // for(int i = 1; i < nums.length; ++i)
        //     prefix_arr[i] = prefix_arr[i] + prefix_arr[i-1];
        for(int i = 1; i < nums.length; ++i)
            nums[i] += nums[i-1];
        
        
        // Then execute binary search algorithm
        // Offsets into lengths can equal 0 too!
        int low = 1;
        int high = nums.length;
        while(low <= high)
        {
            int mid = (low + high) / 2; // In production, account for Integer overflow, OFC
            // boolean greaterThanTarget = checkPrefixSumArrays(prefix_arr, mid, target);
            boolean greaterThanTarget = checkPrefixSumArrays(nums, mid, target);
            if(greaterThanTarget)
            {
                minLen = mid;
                high = mid - 1;
            }
            else 
                low = mid + 1;
        }
        
        if(minLen == Integer.MAX_VALUE)
            return 0;
        return minLen;
    }
    
    // Make sure to correct for ( offset - 1 ), in array indexing
    // Is a linear time algorithm : low bounded by [0,n] here
    public boolean checkPrefixSumArrays(int[] prefix_arr, int offset, int target)
    {
        int sum = 0;
        for(int low = 0; low < prefix_arr.length; ++low)
        {
            int high = low + ( offset - 1 );
            if(high <= (prefix_arr.length - 1))
            {
                if(low == 0)
                    sum = prefix_arr[high];
                else
                    sum = prefix_arr[high] - prefix_arr[low - 1];
                if(sum >= target)
                    return true;
            }
        }
        return false;
    }
    
}
