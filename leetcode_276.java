/*
276. Paint Fence
https://leetcode.com/problems/paint-fence/

Since this is DP/Recursive, we need to maintain a notion of subproblems, excluding the base case, of course
WE have the two consecutive fence property as well

TEST CASES
(A) n = 1, k = 1
(b) n = 7, k = 2
(c) n = 4, k = 4
    => 150 ( expected = 228 :-O ) 
(d) n = 6, k = 4 => 3,276 is correct
Yep you ahve the DP property here :-)
(e) n = 1, k = 10
    => 10
(f) n = 10, k = 6 => really high but valid :-)

O(1) storage honestly, as we just need the subproblem immediately preceding us here
COMPLEXITY
SPACE = O(1) ( exp ) O(1) ( imp ) 
TIME = O(N)

PSEUDOCODE

    numPrefix = 0
    numNonPrefix = k
    numWays = k # initially here anyways
    for i in range(2,n+1,1):
        numWays = numPrefix*(k-1) + numNonPrefix*(k)
        numPrefix = numNonPrefix
        numNonPrefix = numWays - numPrefix
        
    ret numWays

*/
class Solution 
{
    public int numWays(int n, int k) 
    {
        int numPrefix = 0;
        int numNonPrefix = k;
        int numWays = k; // initially here anyways
        for(int i = 2; i < n+1; ++i)
        {
            numWays = numPrefix*(k-1) + numNonPrefix*(k);
            numPrefix = numNonPrefix;
            numNonPrefix = numWays - numPrefix;
        }        
        return numWays;      
    }
}
