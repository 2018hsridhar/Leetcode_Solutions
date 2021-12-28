/*

873. Length of Longest Fibonacci Subsequence
URL = https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/

REASONING
- Leverage the strictly increasing values property here, as all numbers in the array guaranteed their uniqueness
- 0 is min length here
- Subsequences can be any elements, in some ordering as well
    * deleting ANY number of elements, WITHOUT changing the order of REMAINING elements ( subsequences defined by deletion ) 
- Fibonnaci is a reccurrence relationship too
- Use a hashmap ( for each x_{i+2} - x_{i+1} calculation ) 
- Still loop over subproblems calculated before ( from a left->right manner )
- 

BUDP COMPLEXITY
Let N := #-elements int the array
TIME = O(N^2)
SPACE = O(N)

TEST CASES ( guaranteed validity ) 
(A) [1]
    0
(B) [1,2]
    0
(C)
(D)
(E)
(F)

PSEUDOCODE

    for each element in the array from the second index until the final index
        

*/

class Solution 
{
    public int lenLongestFibSubseq(int[] arr) 
    {
        int lenLongest = 0;
        return lenLongest;
    }
}
