/*
912. Sort an Array
https://leetcode.com/problems/sort-an-array/
THOUGHT PROCESS :
Implement the basic counting sort algorithm heret

912. Sort an Array
https://leetcode.com/problems/sort-an-array/

Ideal performance : [T,S] = [O(N), O(1)] : one-pass linear scan with no auxillary space allocated

Edge cases when breaking a sort algorithm : 
1. Strictly incresing order : [1,2,3,4,5]
2. Strictly decreasing order : [5,4,3,2,1]
3. All elements the same [5,5,5,5]
4. (n-1:1) partition of elements [0,0,0,0,1]
5.
6.
7.
8.


Counting sort fails for negative numbers -> need to modify via offsetting here
Add on the absolute value of the minima to every element - subtract out at end = cheak hack here
One key to counting sort : utilize prefix sum array to establish the end of each partition
Works off unique values/unique keys frequency map too!

*/

class Solution 
{
    public int[] sortArray(int[] nums) 
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i)
            min = Math.min(min, nums[i]);
        boolean hasNeg = false;
        if(min < 0)
        {
            min = Math.abs(min);
            hasNeg = true;
            for(int i = 0; i < nums.length; ++i)
                nums[i] += min;
        }
        int maximal_key = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; ++i)
        {
            maximal_key = Math.max(maximal_key, nums[i]);
        }
        
        // Suppose nums is all positive now -> after preprocessing step : apply counting sort
        int[] result = new int[nums.length];
        applyCountSort(nums, result, maximal_key);
        
        for(int i = 0; i < nums.length; ++i)
        {
            if(hasNeg)
                result[i] -= min;
        }
        return result;
    }

    
    public void applyCountSort(int[] nums, int[] results, int maximal_key)
    {
        /*
        * [1] Compute Histogram of values
        * Entails setting up a frequency array for the offset technique ( is not just a hashmap here ) 
        * Need to know the maximal key value though : pass in
        * Make sure to account for array index offsetting too
        *
        */
        int[] psa = new int[maximal_key+1];
        for(int i = 0; i < nums.length; ++i)
        {
            int key = nums[i];
            psa[key]++;
        }
        
        /*
         * [2] Compute prefix sums array for parititioning step of ranges of each element
         */
         for(int i = 1; i < psa.length; ++i)
            psa[i] += psa[i-1];
        
        /*
         * [4] Iterate over initial input ( nums ) in reverse, and set results array to proper values
         * Decrement the counts as expected too fill up partitions, from the rear end
         */
        
         for(int i = nums.length - 1; i >= 0; --i)
         {
             int key = nums[i];
            --psa[key];
            results[psa[key]] = nums[i];  
        }
          
        
    }

    
}
