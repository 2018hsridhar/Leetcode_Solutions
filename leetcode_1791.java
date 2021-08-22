/*
THOUGHT PROCESS :: 
1791. Find Center of Star Graph

URL = https://leetcode.com/problems/find-center-of-star-graph/

Dumb idea : We know the total number of unique nodes here? Why not utilize hashmap and count number of distinct destination pairs between (u_i, v_i) here?
Do we really need to perform a DFS/BFS here? Sure a BFS on the valid node returns TRUE here
Input is always guaranteed -> only a certain form exists too!


ASSUMPTIONS
1. Edge list always represents valid star graph
2. Guaranteed [3,100,000] number of nodes here
3. Edges guaranteed their validity too
4. No self-looping edges ( where for (u_i, v_i), u_i == v_i ) 


COMPUTATIONAL COMPLEXITY : 
TIME = O(1)
SPACE = O(1)

TEST CASES : 
(1) What was provided : [[1,2],[2,3],[4,2]] = 1
(2) [[1,2],[5,1],[1,3],[1,4]] = 1

Highly dumb solution : all edges guaranteed to make up the star graph anyways
Just check first two edges : see which node - the center - apperas in both of them : the set intersection, effectively!

*/
class Solution 
{
    public int findCenter(int[][] edges) 
    {
        int[] e1 = edges[0];
        int[] e2 = edges[1];
        if(e1[0] == e2[0] || e1[0] == e2[1])
            return e1[0];
        else
            return e1[1];
    }
}
