/*

URL = https://leetcode.com/problems/missing-number-in-arithmetic-progression/
1228. Missing Number In Arithmetic Progression

First key : the valeu removed from the array will NOT be the first value or the last value 
Your array input will never be something such as [12,13,14], since both {11} and {15} can be valid answers

[T,S] computational complexity desired = O(N), O(1) where N := number of elements in the given array
Can sort the array to establish known starting point and known ending point

No degenerate arrays
- Array inputs were naturally in an arithmetic progression
- Array length guaranteed to be greater than or equal to 3
- Given a sequence, with some progression/differences known -> can a summation be applied too?


Attempt problem via edge case testing instead
Case 1 : A

We have the upper bound and lower bounds already known


*/

class Solution {
    public int missingNumber(int[] arr) 
    {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int el : arr)
        {
            max = Math.max(max, el);
            min = Math.min(min, el);
        }
        int n_minus_1 = arr.length;
        int n = (n_minus_1 + 1);
        int d = (max - min ) / ( n_minus_1 );
        
        // Solve for summation
        int sum = ( n * min ) + (( n * (n-1) * d) / 2 ) ;
        
        
        // Subtract this out
        for(int i = 0; i < arr.length; ++i)
        {
            sum -= arr[i];
        }
        return sum;
    }
}
