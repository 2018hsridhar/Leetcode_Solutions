/*

THOUGHT PROCESS : 

URL = https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
1059. All Paths from Source Lead to Destination

Seems to just be a BFS/DFS in the hiding honestly
Make sure to make deep copies, for each path traversed too


COMPLEXITY :
TIME = O()
SPACE = O() ( EXP ) O() ( IMP ) 

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

We are provided the edges of a directed graph ( note : always ask if we may have a cycle or if it be a DAG ) 
    -> edge list rep
Strategies : DFS/BFS, Recursive, Exhaustive Searching
#-possible path is a finite number ( \not\exists cycles ) 
At least one path exists
Do we need a different ADT/representation, or can we use pre-existing one?
We can not escape a visited set
-> example 4 is tricky you here : we may need cycle detection as well
-> if ANY path has an \inf self loop : terminate early

Does destination have to be a SINK vertex, and does source have to be  SOURCE/TAP vertex as well?

CONSTRAINTS ANALYSIS
- number nodes can get huge ( 10,000 ) 
- source and dest can equal each other ... but you stil have to check all eminating paths as well.

TEST CASES
(A) [[0,1],[1,2],[2,5],[0,3],[3,4],[4,5]]
    6,0,5
    TRUE
(B) [[0,1],[1,2],[2,5],[0,3],[3,4],[4,5],[6,7],[7,6]]
    8,0,5
    TRUE
    ( seperate components case : take note ) 
(C) [[0,1],[1,2],[2,5],[0,3],[3,4],[4,5],[6,7],[7,6],[6,3],[6,4],[7,5]]
    8,0,5
    TRUE
    ( 1 graph in entirety, but can be treated as if seperate components )
(D) [[0,1],[1,2],[2,0],[0,3],[3,4],[4,5],[5,0],[0,4],[2,3]]
    6,0,0
    FALSE ( wait what! ) 
    ok so source = dst here ... BUT we do not explore any of the destination nodes paths now?
    well it could be an infinite sequence, by accident ( e.g. 0,0,0,0,1,2,0,0,0 ... )?
(E) [[0,1],[1,2],[2,0]]
    3,0,0
    FALSE
(F) []
    1,0,0
    TRUE
(G) [[0,1],[1,2],[2,1]]
    3,0,2
    FALSE
(H) [[0,1],[1,2],[2,3],[3,4],[4,7],[0,7],[7,8],[8,3],[3,6],[6,5]]
    9,0,5
    TRUE
(I)

So if we have a cyclical path, then it would have been on a DFS call.
But let us avoid extra storage of DFS path info on the call stacks, and apply TOP_SORT 
after manipulating the adjaceny list represnetation

Yes we can establish that source = tap, dest = sink : if a path came back/come out, we engender a cycle
#-adjacent children is the deciding factor here for non-cyclical case ( if a leaf node & node val != dst val : ret false )

Ok. Let us make some formal args.
Let us define  node to be terminal if it has no outgoing edges.
Our algorithm can quickly discard cases where we have multiple terminal nodes on the DFS paths
from the source vertex, as only one terminal node may be equal to the dest value
However, we can not discard a case with cycles, as we can have a graph such as (G), which has
a cycle on the DFS path but zero terminal nodes. Thus, the lack of a terminal node is not enough
to establish the reachability invariant of the problem. We must perform some form of cycle detection
as well. 
And a node must be a parent node, on a parent edge, as a node which is an intermediary node with a positive outdegree,
is not enough to determine if a path leads to a cycle, as we can end up on such as a node if two independent DFS paths 
happen to share a common node on their traversals, but no cycles on their way to the shared terminal node. 

*/


class Solution 
{
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) 
    {
        boolean allPathsLead = true;
        // if no paths lead : said boolean gets set to false as well!
        return allPathsLead;        
    }
}
