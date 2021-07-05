/*
581. Shortest Unsorted Continuous Subarray
URL = https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

Note : O(N) approach may need a monotonic stack => please read up on such concepts later
Remember length of continuous subarray formula = (ptr2-ptr1+1), assuming ptr2 >= ptr1. Handles case where both equal each other ( guaranteed length of 1 here! ) 
In some two pointer problems - race both at same time ; or race in own seperate while loops. One-pass or two-pass approaches entailed!

Typical greedy problem - sort the array
Find (ptr1,ptr2) corresponding to the beginning and the end of our continuous subarray in question

Time-Space Complexity : 
Time = O(NlogN) + O(N) = sort + one_pass_linear_scan = O(NLogN)
Space = O(1) [ local function variables needed only ]

Not returning this continuous subarray too : just its length
The max length is bounded by the closed interval [0,len(nums] here too
The continuous subarray is always guaranteed :-) ( we do not run into a case of a void solution ) or is 0.

Edge case testing
(1) Strictly increasing input ( already sorted )  - [1,2,3,4,5] => 0
(2) Worst-case input : strictly decreasing sequence - [ 5,4,3,2,1] - sort entirety => n
(3) An Alternating sequence - [0,1,0,1,0,1] => n
(4) Singeton array - [0] => 0
(5) Boundary at beginning - [5,4,3,2,1,6,7,8,9] => 5
(6) Boundary at end - [1,2,3,4,9,8,7,6,5]  => 5
(7) Array len = 2 - [1,2] => 0
(8) Array len = 2, decr order - [2,1] => 2


*/


class Solution 
{
    public int findUnsortedSubarray(int[] nums) 
    {
        // [1] O(NlogN) sort step ( in strictly increasing order ) 
        int minLen = 0;
        int[] sortCopy = nums.clone();
        Arrays.sort(sortCopy);
        
        // [2] O(N) two pointers pass
        int ptr1 = 0;
        int ptr2 = nums.length - 1;
        while(ptr1 < nums.length)
        {
            if(nums[ptr1] != sortCopy[ptr1])
                break;
            ++ptr1;
        }
            
        while(ptr2 > 0)
        {
            if(nums[ptr2] != sortCopy[ptr2])
                break;
            --ptr2;
        }
            
        if(ptr1 >= ptr2)
            minLen = 0;
        else
            minLen = (ptr2 - ptr1 + 1);
        return minLen;
    }
}
