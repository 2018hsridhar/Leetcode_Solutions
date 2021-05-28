/*
URLs : 
1. https://www.youtube.com/watch?v=6ZRhq2oFCuo

*/


public class undirected_graph_has_cycle
{
  public static void main(String[] args)
  {
  
        // Problem statement is akin to cycle detection, within an undirected graph too
        // Most path-finding algorithms will require a recursive stack; hence, utilize DFS approach and recursive helper functions
        // Providing edge list here : let us convert to adjacency list representation too
        
        
        
        // CONSTRUCT adjacency-list representation
        // Does parent id also have to be set here?
        ArrayList<Integer> adjList[] = constructAdjacencyList(edges,n);
        
        // print adj list to verify correctness
        for(int i = 0; i < adjList.length; ++i)
        {
            ArrayList<Integer> myN = adjList[i];
            System.out.printf("%d\t->\t%s\n", i, myN.toString());
        }
        
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; ++i)
            visited[i] = false; // none have been visited here!
        
        // Check each vertex ( in case of scattered components too )!
        int parent = -1; // the first parent
        
        int rootNode = 0;
        boolean hasCycle = isCyclic(adjList, rootNode, visited, parent);
        if(hasCycle == false)
            retrun true; // edges make up a valid tree here
        return false;

        
    }
    
    public boolean isCyclic(ArrayList<Integer> adjList[], int curNode, boolean[] visited, int parent)
    {
        boolean status = false;
        // obtain neighbors before recursing
        ArrayList<Integer> neighbors = adjList[curNode];
            
        visited[curNode] = true; // set visited status before recursing or checking neighbors! 
        for(int i : neighbors)
        {
            System.out.printf("For node = [%d], neighrs = %s\n", curNode, neighbors.toString());
            if(!visited[i])
            {
                System.out.printf("check if %d is cyclic", i);
                if(isCyclic(adjList, i, visited, curNode))
                    return true;
            }
            else
                if(i != parent)
                {
                    System.out.printf("i=[%d] and parent = [%d]\n", i, parent);
                    return true;
                }
        }
        
        return status;
    }
    
    public ArrayList<Integer>[] constructAdjacencyList(int[][] edges, int n)
    {
        // OBTAIN INDEGREES OF EACH NODE
        ArrayList<Integer> adjList[] = new ArrayList[n];
        
        for (int i = 0; i < n ; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < edges.length; ++i)
        {
            int[] edge = edges[i];
            int a = edge[0];
            int b = edge[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }
        return adjList;
    }
}
