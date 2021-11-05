/*
1557. Minimum Number of Vertices to Reach All Nodes

Idea : can we utilize the DFS technique here?


Complexity
Let V := number vertices, E := number edges
Time = O(V*dfs()) = O(V^2)
Space = O(V) ( implicit ) O(V+E) ( explicit - adj list )  

May need to convert the edge list into an adjacency list data structure isntead of ease of processing
If we visit an already visited vertex -> terminate DFS early as well

Unique solutions are guaranteed
Find smallest set ( optimizatino problem ) 

Strategies : DFS, Recursion, Backtracking, DP? 

TEST BENCH
(A) n = 5, edges = [[4,3],[3,2],[2,1],[1,0]]
    [4]
(B) n = 4, edges = [[0,1],[0,2],[0,3],[1,3],[2,3]]
    [0]
(C) n = 5, edges = [[0,1],[0,2],[0,3],[0,4],[1,2],[2,3],[3,4],[4,1]]
    [0]
(D)

n is always >= 2, and #-edges is valid as well
Each pairing is distinct as well
What about the cycle case?
    > this is a DAG ( oh thank god ) 


*/
class Solution 
{
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) 
    {
        List<Integer> vertexCover = new ArrayList<Integer>();
        HashMap<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        constructAdjList(n, edges, adjList);
        return vertexCover;
    }
    
    public void dfs(int vertex)
    {
        
    }
    
}
