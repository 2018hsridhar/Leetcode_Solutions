/*

274. H-Index
URL = https://leetcode.com/problems/h-index/

We know the bounds of the hIndex for each problem span from [0,5000]
May be able to sort the array ahead of time
Possible execute binary search to find out the ideal hIndex value -> approximate this. Use iterativev method since a type of heuristic remains desireable here too

Computational complexity : 
Time = O(NLogN) + O(LogN) = O(NLogN)
Space = O(1) + O(1) = O(1) : binary search and heap sort /an efficient sort algo can be assumed to use near constant space in our situation
Solve for the maximum h-index ( oh ... bSearch can support this boyo! ) 

Common problem  partition and then find optimal value for said parititioning
BSearch solves the two partitino problem ( but can it be extended for multiple-partitioning )?

n is reasonably bounded - can sort efficiently
citations are reasonable - bounded by [0,1000]
citations can appear frequently ( all 0's report for 5K times, or say, 1000 report for 5K times ) 

Can induct on a sort array : we already know properties of values before us, AND, after us too

[1,1,1]  AND [1,1,3] both result in a 1 : (h/n) have at least h, and (n-h)/n have at most h. 
h := the balancing factor. Didn't realize this. Check bounds properly.

Edge case testing : 
(1) Singleton arrays such as {1} or {3} 
(2) Equivalent cases such as [1,1,1] or [1,1,3]
(3) Generic case - [3,0,6,1,5]
(4) Repeating values case at large scale - [0,0,0,0,0] OR [1000,1000,1000,1000,1000]
(5) [[11,15]]
*/

class Solution 
{
    public int hIndex(int[] citations) 
    {
        int maximalIndex = 0;
        int hIndex = 1;
        
        
        // Handle the singleton case
        if(citations.length == 1)
            if(citations[0] != 0) return 1;
            else return 0;
        
        Arrays.sort(citations);

        // Parition : (n-h):(h) papers - h/n have at least h citations
        for(int i = citations.length - 1; i >= 0; --i)
        {
            if(citations[i] >= hIndex )
            {
                if(i != 0 && citations[i-1] <= hIndex) // the other (n-h) have no more values
                {
                    maximalIndex = hIndex;
                }
                else
                {
                    maximalIndex = hIndex; // maximized here anyways!
                }
            }
            ++hIndex;
        }
        return maximalIndex;
    }
}
