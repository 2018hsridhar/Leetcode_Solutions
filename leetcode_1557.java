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
    *** not passing *** 
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
        Set<Integer> minimalSpan = new HashSet<Integer>();
        HashMap<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        constructAdjList(n, edges, adjList);
        Set<Integer> execDFS = new HashSet<Integer>();
        //
        for(int i = 0; i < n; ++i)
        {
          if(!execDFS.contains(i))  
          {
              // System.out.printf("Executing dfs = %d\n", i);
              dfs(i, adjList, execDFS, minimalSpan);
          }
        }
        List<Integer> vertexCover = new ArrayList<Integer>(minimalSpan);
        return vertexCover;
    }
    
    private void constructAdjList(int n, List<List<Integer>> edges, HashMap<Integer, List<Integer>> adjList)
    {
        // Construct init hashmap ( avoids check key existence operation later down ) 
        for(int i = 0; i < n; ++i)
        {
            adjList.put(i, new ArrayList<Integer>());
        }
        
        // Now go over the list of edges
        for(int i = 0; i < edges.size(); ++i)
        {
            List<Integer> edge = edges.get(i);
            int src = edge.get(0);
            int dst = edge.get(1);
            List<Integer> adj = adjList.get(src);
            adj.add(dst);
        }
        return;
    }
    
    private void dfs(int parent, HashMap<Integer, List<Integer>> adjList, Set<Integer> execDFS, Set<Integer> minimalSpan)
    {
        // ideally, you need not check exeuted
        // work in the parent
        execDFS.add(parent);

        // work for each child
        // Does not execute if no adjacency -> must add parent ( single node default caes ) 
        // Unfortunately, ordering will matter here
        List<Integer> children = adjList.get(parent);
        for(int i = 0; i < children.size(); ++i)
        {
            int child = children.get(i);
            if(!execDFS.contains(child))
            {
                dfs(child, adjList, execDFS, minimalSpan);
            }
        }
        for(int i = 0; i < children.size(); ++i)
        {
            int child = children.get(i);
            if(minimalSpan.contains(child))
            {
                // System.out.printf("Removing child = %d\n", child);
                minimalSpan.remove(child);
            }
        }

        // Always add the parent at the end anways
        minimalSpan.add(parent);
        return;
    }
    
}
