
/*

1133. Largest Unique Number
https://leetcode.com/problems/largest-unique-number/

THOUGHT PROCESSES : 

utilize sort - Pre-computation = O(NlogN), where N := number of elements in array
However, sort should entail an efficient O(N) linear scan, and reduce amount of store space needed

Ideal performance : [T,S] = [O(NLOGN), O(N)]

Start iteration backwards from array, since that will contain our guaranteed maximums

*/


class Solution {
    public int largestUniqueNumber(int[] A) 
    {
        int curMax = -1;
        int curMaxCount = 0;
        if(A.length == 1)
            return A[0];
        Arrays.sort(A);
        for(int i = A.length - 1; i >= 0; --i )
        {
            if(A[i] > curMax)
            {
                curMax = A[i];
                ++curMaxCount;
            }
            else if ( A[i] == curMax )
            {
                ++curMaxCount;
            }
            else
            {
                if(curMaxCount > 1)
                {
                    curMax = A[i];
                    curMaxCount = 1;
                }
            }
            
        }
        if(curMaxCount > 1)
            return -1;
        return curMax;
        
    }
}
