/*
Show the optimal substructure property - OSP - for this problem

55. Jump Game
URL = https://leetcode.com/problems/jump-game/

Complexity : 
Let N := len(nums)
Time = O(N^2)
Space = O(1) [ explicit ] 
        ___  [ implicit ] 
        We could mutate the array in place anyways, to denote if last index is visitable -> no need to check that again. Mutation is useful when a part of our input is no LONGER considered under exploration by the algorithm in place. Helpful when dealing with subproblems as well ( greedy, DP, D-&-C ). 
        Based on the algo itself, and how computation can be expressed -> Bottom-up DP can sometimes do better spacewise than top-down memoization, but in itself, seldom does better time-complexity wise. Oftentimes, the time and space for TD-memo and Bottom-up DP tend to be shared.
        
Edge Case Testing
(A) [3,2,1,0,4]
    => FALSE : INT.MAX steps ( use to denote a maximum ) 
(B) [2,3,1,1,4]
    => TRUE : 2 steps
    Sequences = {2,3,1,1,4}, {2,3,4},{2,1,1,4},{2,3,1,4}. Min len = 3 here
(C) [4],[1],[0]
    => TRUE : always a 1 step problem
(D) [x,y]
    => TRUE ( for any x,y where x != 0)
(E) [0,y]
    => FALSE
(F) []
(G) [0,3,2,1]
    => FALSE
(H) [2,0,1,0,1,0]
    => FALSE
(I)
(J)

Each elem => maximum jump length as a position ( can jump less than what is specified )
Return if last index is reachable
You always start @ the array's first index
nums[i] guaranteed to be in Z_{nonneg}, and bounded in [0,1e5]
nums length bounded by [1,1e4]

*/


class Solution 
{
    public boolean canJump(int[] nums) 
    {
        boolean canJump = false;
        
        
        return canJump;
    }
}
