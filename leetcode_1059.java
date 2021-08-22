/*

THOUGHT PROCESS : 

URL = https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
1059. All Paths from Source Lead to Destination

Seems to just be a BFS/DFS in the hiding honestly
Make sure to make deep copies, for each path traversed too

COMPUTATIONAL COMPLEXITY : 
TIME = 
SPACE = 

Yes - can have 0 edges, but always guaranteed at least one node
Guranteed valid source and destinatino nodes ( bounded by [0,n-1]) too
Finite number of paths - no cycle in graph ( can have self-loops though ... wait a second )
=> the finite property of possible paths, from (src,dst) immediately entails ability to FAIL when provided a self-looping structure

Yes - can have a self-loop OR a cycle, but makes paths infinite along the way => failing case ( return FALSE ) !

Cool : we can see an infinite generating set, from a finite set. Like there exists a relationship atop of it ( path-finding )!
Max number edges = 10^4 = 10,000 edges ( is max number of nodes : suppose each node were a self-loop in this case )
Parallel edges : does this mean 
        [a,b] repeated OR
        [a,b] AND [b,a] type of edges?
        
All roads does mean all paths case here!

TEST CASES
(a) Node is non-existent case :: 3,[[0,1]], 0, 2
(b) Singleton node case : 1,[],0,0 = TRUE
(c) Node is existent AND reachable by all paths :: 3,[[0,1],[1,2],[2,3]], 0, 3 ( the sequence graph/tree here ) 
(d) The 4-node diamond graph test case ::  3,[[0,1],[0,2],[1,3],[2,3]], 0, 3
(e) Self loop case : 1,[[0,0]],0,0 = TRUE
(f) Parallel edge case : 2, [[0,1],[0,1]], 0, 1 = TRUE
(g) More than one possibilty case : 4, [[0,1],[0,3],[1,2],[2,1]], 0, 3
(h) Isolated sets : 5, [[]], 0, 0 = TRUE ( but not say : 0,1 OR 0,2 )
-> must account for (src==dst) case when performing any DFS, honestly
(i) Case where 0 is connected to {1,3}, BUT, has further downstream paths here too :: 5, [[0,1],[0,3],[1,3],[1,2],[3,4]], 0, 3

Given edge list -> convert to adjancey matrix ( initial precomputation overhead, but might help much ) 


*/


class Solution 
{
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) 
    {
        return false;
        
    }
}
