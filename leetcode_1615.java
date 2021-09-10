/*
1615. Maximal Network Rank
URL = https://leetcode.com/problems/maximal-network-rank/

THOUGHT PROCESS

COMPLEXITY
Time = 
Space = 

The maximal network rank - MNR -  is unique, BUT, not the set of pairs which generates a valid
maximal network rank [ MNR ] 

Let us boudn total number of pairs : (0,1) and (1,0) are the exact same
(0,1),(0,2),(0,3),(1,2),(1,3),(2,3) => 6 pairs ( 4 nodes ) 
For n = 4, [n(n-1)/2] => 4(3)/2 = 6
So O(N^2) number of pairs to test out here

n is bounded by 100
Max matrix size = 100*100 = 1e2*1e2 = 1e4
No self-loops here : a_i != b_i411\\\\\44`
Given an edge list, construct an adjacency matrix/adjacency list is stupidly ease to

EDGE CASES
(a)
(b)
(c)
(d)
(e)
(f)
(g)
(h)

*/
class Solution
{
    public int maximalNetworkRank(int n, int[][] roads) 
    {
        
    }
}
