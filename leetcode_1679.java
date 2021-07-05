/*

1679. Max Number of K-Sum Pairs
URL = https://leetcode.com/problems/max-number-of-k-sum-pairs/

THOUGHT PROCESESS : 

Computational Complexity : 
Ideal [T,S] Complexity = O(N) + O(1) after a O(NLogN) sort
Pairs testing - be greedy and sort; then utilize two pointers
Reduces complexity down from O(N^2) to O(N) as { N(N-1)/2 => N/2 }

Edge Case Testing :
(1) Singleton arrays with varying k values -  ( [1], 5 ), ( [1], 10 )
(2) No pairs case - [1,2,3,4] k = 1 OR [1,2,3,4] k = 10
(3) All pairs case - [1,1,1,1] k = 2 
(4) Middle pairs case - [1,3,3,3,4] k = 6 => [1,3,4]
(5) 
(6)


*/


class Solution 
{
    public int maxOperations(int[] nums, int k) 
    {
        int maxNumOps = 0;
        
        Arrays.sort(nums);
        int ptr1 = 0;
        int ptr2 = nums.length - 1;
        // Equal case pointers technically handle singleton arrays too!
        while(ptr1 < ptr2)
        {
            int curSum = nums[ptr1] + nums[ptr2];
            if(curSum == k)
            {
                ++maxNumOps;
                ++ptr1;
                --ptr2;
            }
            else if ( curSum > k )
                --ptr2;
            else
                ++ptr1;
        }
        
        return maxNumOps;
    }
}
