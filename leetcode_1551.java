/*

URL - https://leetcode.com/problems/minimum-operations-to-make-array-equal/
THOUGHT PROCESSES : 

Seems mathematical in nature for sure
We know input is already sorted
Base case : arr len = 1 -> return 0
Each operation selects for two indice x,y with order relation ( 0 <= x,y < n ). Note
that x,y can be any order though ( x < y, x > y )


N is a positive integer in the closed interval [1,10000]
Operations to return bounded by [ 0, X ] where X := some natural number

Are number of operations well-correlated to the median of the sorted array?
[T,S] = [O(N), O(1)] where N := number of elements in the array

Traverse only half of array here

Time to solution : 15 minutes!
Note use of binary operations here : multiple by 2 ( << 1 ) and divide by 2 ( >> 1 ) :-)

Ideal : obtain O(1) time complexity, via a formula!
*/


class Solution 
{
    public int minOperations(int n) 
    {
        int numOps = 0;
        int min = 1;
        int max = ((n-1) << 1) + 1;
        int median = ((min + max ) >> 1);
        int midPoint = (n>>1); // ( divide by 2 ) 
        for(int i = 0; i < midPoint; ++i)
        {
            int curVal = ( i << 1 ) + 1;
            int ops = median - curVal;
            numOps += ops;
        }
        return numOps;
    }
}
