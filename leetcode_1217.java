/*

1217. Minimum Cost to Move Chips to The Same Position
URL = https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/

THOUGHT PROCESSES : 

[T,S] = [O(n), O(1)] ideally - linear scan approach - where n denotes number of chips


Edge cases
Can have a position of length = 1 only
Can have all array values be the same too
Each position is guaranteed a chip : minCostToMove is guaranteed to be bounded by [0,x), in the integers

Shifting by 2 = no cost. Optimize for this first!
Shifting by 1 = incur a cost. Do not optimize for this first??


First idea : greedy scanning in an array checks for neighbors
E.g. at A[i], check your neighbors A[i+1], A[i+2]

Track for max number of chips in a position too?


Easy to intuit when dealing with a subarray of size = 2 here!


[1,1,1,3,3] - min cost = 0; no matter movement
[2,2,2,3,3] - min cost = 2, but how to know to move 3rd_pos to 2nd_pos here?
-> In this case, we know a move is neccessary, so we take the min of each position, and based on that, perform the increment of minNumMoves needed

Array length may also be known ahead-of-time too : find out parity - is it odd length or even length?

In some greedy approaches, pre-computation and scanning an entire list of items ie needed
Greedy is used predominantly in graph-walking



*/


class Solution {
    public int minCostToMoveChips(int[] position) {
        
    }
}
