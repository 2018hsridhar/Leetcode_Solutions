/*
674. Longest Continuous Increasing Subsequence

https://leetcode.com/problems/longest-continuous-increasing-subsequence/
Default length of a LCIS = Longest Continuous Increasing Subsequence = 1 

Ironically, this handles the single element case/single array
        for(int i = 0; i < nums.length - 1; ++i)

Since length of a singleton array = 1; and ( len - 1 ) = 0

Use run length type of algorithms when dealing with continuous subsequences! ( elements of continguous )
And some property of them! 
hey - don't they help with limits too?

Sliding windows and run lengths are the same concept of continuous elements of a set ( ideally, ordered too ) 
Every (continuous) increasing subsequence is disjoint -> can we demark where discontinuities of sets exist?


Test cases 
[1]
[1,2,3,4,5]
*/

class Solution 
{
    public int findLengthOfLCIS(int[] nums) 
    {
        int runLen = 1;
        int lengthOfLCIS = 1;
        for(int i = 0; i < nums.length - 1; ++i)
        {
            if(nums[i] < nums[i+1])
            {
                ++runLen;
                lengthOfLCIS = Math.max(lengthOfLCIS, runLen);
            }
            else
                runLen = 1;
        }
        
        return lengthOfLCIS;
    }
}
