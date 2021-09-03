/*
THOUGHT PROCESS : 
https://leetcode.com/problems/find-eventual-safe-states/
802. Find Eventual Safe States

[1,10,000] nodes here
Sorted in strictly increasing order
Can have self-loops : whoops 
Standard graph algos as usual : BFS, DFS, Kahn's Topological Sort   
'k' generalizes for ANY walk, and is supposed to be helpful for cycle avoidance too.
'k' also generalizes across an entire set of walks : imagine imposing a boundary on a SET!

COMPLEXITY : 
TIME = 
SPACE = 

Edge case testing : 
(1) [[1,2],[2,3],[5],[0],[5],[],[]]
    Output : [2,4,5,6]
    Why not : [0,1,3]? Because you have a cycle here with the directed edges!
    A cycle is a WALK. It can be any walk
(2) Isolated sets : [[], [], [], []]
    Output : [0,1,2,3]
(3) a Linked List : [[1],[2],[3],[4]]
    Output : 4
(4) Disjoint sets case : [[1],[2],[3],[5],[6],[7]]
    Output : [4,8]
(5) Self loops case : [[0],[1],[2],[3]]
    Output : []
    
    
Yes : dfs(1) explores dfs(2), and we have the potential of already visiting 2
now dfs(3) explores dfs(0), but we explored that too
Having a node already visited IS NOT always a gauarantee of cycle detection too!
Any WALK can include the empty walk too, as a subset

CALL STACK REPRESENTATION : 

dfs(0)
    dfs(1)
        dfs(2)
        dfs(3)
            dfs(0)
                dfs(1)
                    ...
                dfs(2)
                    ...
    dfs(2)
        dfs(5)

We already have "n" number of nodes
The default answer is : [0,...,n]
Suppose we have a cycle including nodes 
{0,2,4,6} 

Also nice : we get the zero-indexing property here too, as well!
    
Graph representation here be an adjacency list ( not an adjacency matrix ) 
*/

/*
Use integer array to represent following

0 => unexplored
1 => has cycle
2 => has no cycle

*/
class Solution 
{
    public List<Integer> eventualSafeNodes(int[][] graph) 
    {
        List<Integer> safeNodes = new LinkedList<Integer>();
        int n = graph.length;
        int[] cycles = new int[n];
        for(int i = 0; i < n; ++i)
            cycles[i] = 0;
        
        for(int i = 0; i < n; ++i)
        {
            int rootNode = i;
            HashSet<Integer> visited = new HashSet<Integer>();
            if(cycles[i] == 0)
                dfs(rootNode, cycles, visited, graph);
        }
        
        for(int i = 0; i < n; ++i)
        {
            if(cycles[i] != 1)
                safeNodes.add(i);
        }
        
        return safeNodes;
    }
    
    /*
    Ran inacto a TLE error for sure ( probably in deep copy step here ) 
     Two terminating conditions for DFS recursion
     1. At a leaf/childless node
     2. Potentially hit already visited node during the traversal
     Exceeds the memory limit though!
        => yep : deep copying in a recursive function will break you!
    */
    public void dfs(int root, int[] cycles, HashSet<Integer> visited, int[][] graph)
    {
        int[] adjList = graph[root];
        if(adjList == null || adjList.length == 0)
        {
            cycles[root] = 2;
            for(int i : visited)
            {
                if(cycles[i] == 0)
                    cycles[i] = 2;
            }
            return;
        }
        else
        {
            visited.add(root);
            for(int child : adjList)
            {
                if(visited.contains(child))
                {
                    cycles[root] = 1;
                    for(int i : visited)
                        cycles[i] = 1;
                    return;
                }
                else
                {
                    // Really hating this deep copy operation though!
                    HashSet<Integer> deepCopy_visited = new HashSet<Integer>();
                    for(int i : visited)
                        deepCopy_visited.add(i);
                    dfs(child, cycles, deepCopy_visited, graph);    
                }
                
            }
        }
    }
    
}
