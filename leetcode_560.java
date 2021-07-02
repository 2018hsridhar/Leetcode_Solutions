
/*
URL = https://leetcode.com/problems/subarray-sum-equals-k/
THOUGHT PROCESSES : 

The prefix sum approach is well known for dealing with the summations of continuous subarrays of varying sizes ( we test n(n+1)/2 continuous subarrays here )
Sliding window techniques, varying of length (1,n), are also known ( track running sum here, and incorporate two pointers technique )
Yes, a singleton element denotes a continuous subarray too

But what about the hashtable approach?
If using hash table : what will unique values be?

Idea one : {0,...,k} here ( but can possess negative numbers too ) 
Can try to divide-and-conquer ( partitioning ) and hash that?


Efficient solution : uses the hashmap
O(N) time, O(N) space, one-pass linear scanning

Also prefix sum arrays work only for positive integer values

*/

class Solution 
{
    public int subarraySum(int[] nums, int k)
    {
        int count = 0;
        int[] psa = new int[nums.length];
        for(int i = 0; i < psa.length; ++i)
            psa[i] = nums[i];
        for(int i = 1; i < psa.length; ++i)
            psa[i] += psa[i-1];
        
        for(int i = 0; i < nums.length; ++i)
        {
            for(int offset = i; offset <= (nums.length - 1 ) ; ++offset)
            {
                int psa_sum = 0;
                if(offset < nums.length)
                {
                    if(i == 0)
                        psa_sum = psa[offset];
                    else
                        psa_sum = psa[offset] - psa[i-1];
                    if(psa_sum == k)
                        ++count;
                }
            }
        }
        return count;
    }
}
