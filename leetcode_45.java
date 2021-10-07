/*
Show the optimal substructure property - OSP - for this problem

45. Jump Game II
URL = https://leetcode.com/problems/jump-game-ii/

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

-1 => can jump to end
-2 => can not jump to the end

Assume in this problem we an reach the last index : so minNumJumps = len(arr) - 1 anyways 
In this case, we replace current problem with num steps now
Asume guaranteed length > 2 here
*/


class Solution 
{
    public int jump(int[] nums)
    {
        if(nums == null || nums.length < 2)
            return 0;
        
        int n = nums.length;
        int minNumJumps = n - 1;
        nums[n-1] = 0; // last elem by default ( and min num from last el ) 
        for(int i = n - 2; i >= 0; --i)
        {
            int minJumpsFromPos = (n-i); // we ran into data overflow somehow? OH ... I see
            // int minJumpsFromPos = Integer.MAX_VALUE - 1; // wow this is kinda crummy for sure!
            int numSteps = nums[i];
            for(int j = 1; j <= numSteps; ++j) // perform bounds check as well
            {
                int nextPos = i + j;
                if(nextPos < n)
                    minJumpsFromPos = Math.min(minJumpsFromPos, 1 + nums[nextPos]);
            }
            nums[i] = minJumpsFromPos;
        }
        minNumJumps = nums[0];
        return minNumJumps;
    }
}
