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
(3) (n-1:1) partition such as [0,0,0,......,0,1] : and say, target = {0,1} . Make sure to return valid if either a single element, but not if target is outside the set of elements encompassed
(4) Case where target is not found in array : return 0
(5) [1,2,3] : target = 6 : all elements to sum up case

Luckily, input lacks any negatives or zero : strictly natural numbers within reasonable range [1,10^5]
Max summation possible = [10^5 TIMES 10^5] = 10^10 ( target = 10^9 ). 
10000000000 should not overflow the itneger datatype
// int test = 10000000000;
// if(test<minLen) System.out.println("test value < max value of a INT ");
// Needs further exploration here

Can one binary search for minimal subarray length? Versus testing out all subarray lengths?
Total number of contiguous subranges, of an array, naturall equals sum of natural numbers from 1 to n, where n := length of array/number of elements . It entails a BFS solution of O(N^2) then, as summation formula = (N^2 + N ) / 2


*/

class Solution 
{
    public int minSubArrayLen(int target, int[] nums) 
    {
        int minLen = Integer.MAX_VALUE;
        
        
        
        if(minLen == Integer.MAX_VALUE)
            return 0;
        return minLen;
    }
}
