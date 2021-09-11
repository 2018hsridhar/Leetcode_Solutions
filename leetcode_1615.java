/*
1615. Maximal Network Rank
URL = https://leetcode.com/problems/maximal-network-rank/

THOUGHT PROCESS

COMPLEXITY
Time = O(N^2) + O(E) = O(N^2) + O(E)
Space = O(N^2)

The maximal network rank - MNR -  is unique, BUT, not the set of pairs which generates a valid
maximal network rank [ MNR ] 

Let us boudn total number of pairs : (0,1) and (1,0) are the exact same
(0,1),(0,2),(0,3),(1,2),(1,3),(2,3) => 6 pairs ( 4 nodes ) 
For n = 4, [n(n-1)/2] => 4(3)/2 = 6
So O(N^2) number of pairs to test out here

n is bounded by 100, and we are guaranteed at least two nodes ( have a maximal rank of 0 at min ) 
Note : nodes can be SCCs - seperately connected components - too!
Max matrix size = 100*100 = 1e2*1e2 = 1e4
No self-loops here : ( a_i != b_i )
Given an edge list, construct an adjacency matrix/adjacency list is stupidly ease to

N is a known bound as well
(1) Set up an Neighborhood array for N(v)
(2) Set up adjacent matrix of (nxn) too


EDGE CASES
(a) Disconnected nodes : n = 2, roads = []
Expect : 0

(b) All disconnected nodes : n = 4, roads = []
Expect : 0

(c) Base case of two nodes : n = 2, roads = [[0,1]] 
Expect : 1

(d) Case of two disconnected components, but single node in each comp yields max pairwise value
n = 8, roads = [[3,0],[3,2],[3,1],[7,5],[7,6],[7,4]]
Expect : 6

(e) (n-1:1) pairing 
n = 4, roads = [[3,1],[3,2],[3,0]]
Expect : 3

(f) Continuous sequence ( akin to SLL ) 
n = 5, roads = [[0,1],[1,2],[2,3],[3,4]]
expect : 4

n = 4, roads = [[0,1],[1,2],[2,3]]
expect : 3

(g) A K_n graph ( naturally endows a cycle to test here ) . Let us try K_4 


*/
class Solution
{
    public int maximalNetworkRank(int n, int[][] roads) 
    {
        // [1] FILL UP INITIAL DATA STRUCTURES
        int mnr = 0; // always defaults to a zero anyways
        // O(N^2) + O(N) space here
        int[][] adjMat = new int[n][n];
        int[] hood = new int[n];
        
        // O(E) time for edges
        for(int i = 0; i < roads.length; ++i)
        {   
            int[] edge = roads[i];
            int src = edge[0];
            int dst = edge[1];
            adjMat[src][dst] = 1;
            adjMat[dst][src] = 1;
            hood[src]++;
            hood[dst]++;
        }
        
        // [2] EXECUTE THY ALGORITHM
        // O(N^2) number pairs to iterate over
        for(int i = 0; i < n; ++i)
        {
            for(int j = i + 1; j < n; ++j)
            {
                int cur_mnr = hood[i] + hood[j] - adjMat[i][j];
                mnr = Math.max(mnr, cur_mnr);
            }
        }
        
        return mnr;
    }
}

