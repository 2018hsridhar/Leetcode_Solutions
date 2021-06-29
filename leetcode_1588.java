
/*

VIDEO = https://www.youtube.com/watch?v=pVS3yhlzrlQ

1588. Sum of All Odd Length Subarrays
URL = https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
Utilize the 1-D prefix sum approach
Return total sum : 

Known : array elements within closed interval of integers - [1,1000]
Array length reasonable [ 1,100] - longest odd length array is of length = 99 anyways!
O(1) nature of prefix sums means this is a more manageable problem too
Utilize sliding window technique ( based on array lengths ) ... nah ... use ranges instead!

Ideal [T,S] = [O(M), O(1)] where M := number of issued queries
Execute "in-place" solution once "extra memory" solution is set up

Let "psa" := prefix sum array
Let "olsa" := odd-length subarrays
Utilize offset for odd length arrays too!



*/

class Solution 
{
    public int sumOddLengthSubarrays(int[] arr) 
    {
        // PREPROCESSING : Construct the prefix sum array, but do in-place!
        for(int i = 1; i < arr.length; ++i)
            arr[i] = arr[i] + arr[i-1];
        
        // ITERATE over all possible ranges
        // ENSURE offsets are valid and within array boundaries
        // edge case of singleton offsets too
        // Apply range query formula only when starting index != 0 too!
        
        int olsa_sum = 0;
        for(int i = 0; i < arr.length; i++)
        {
           for(int offset = 0; offset < arr.length; offset += 2) 
           {
               int low = i;
               int high = i + offset;
               if(high  <= (arr.length - 1))
               {
                   int range_query = 0;
                   if(low == 0)
                       range_query = arr[high];
                   else
                       range_query = arr[high] - arr[low-1];
                   // System.out.printf("Range = (%d,%d)\t Query Value = %d\n", low, high, range_query);
                   olsa_sum += range_query;
               }
               else
                   continue;
           }
            
        }
        return olsa_sum;
    }
}
