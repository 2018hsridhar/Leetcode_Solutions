/*
We can take ANY number of courses, provided that all prereqs have been taken before the antecedent courses

URL = https://leetcode.com/problems/parallel-courses/
1136. Parallel Courses

Handle cases with cycles as well
Try to recreate topogical sort from DFS ( vs. Kahn's algorithm ) here BTW
All pairings are unique, and prevCourse_i != nextCourse_i

Complexity
Let V := number vertices
Let E := #-edges
Note that an adjacency list representation is sadly needed in order to perform DFS ( Kahn's is fater for edge lists ) 
Time = 
Space = 

Luckily, all node IDs are unique here as well
Why the heck do we have to allocate these many data structures with DFS-based toplogical sorting? Just ... why!!
No self-loops or PARALLEL edges here present luckily

TEST BENCH
(A)  5 [[1,2],[2,3],[3,4],[4,5]]
    Expected : 5
(B)  7 [[1,2],[2,3],[3,4],[1,5],[1,6],[6,7]]
    Expected : 4
(C) 8 [[1,2],[2,3],[3,4],[1,5],[1,6],[6,7],[15,2]]
    4
(D) 7 [[1,2],[2,3],[3,4],[4,1],[5,6],[6,7],[7,1],[6,2]]
    -1
(E) 3
[[1,2],[2,3],[1,3]]
*** failing ***

*/

class Solution 
{
    Set<Integer> hitParent;
    Set<Integer> execDFS;
    HashMap<Integer, Integer> orderLoc;
    List<List<Integer>> topOrder;
    HashMap<Integer, List<Integer>> adjList;
    
    public int minimumSemesters(int n, int[][] relations) 
    {
        // [1] Set up thy adjacency list
        adjList = new HashMap<Integer, List<Integer>>();
        constructAdjList(n, relations, adjList);

        // [2] Exeute disjoint-set toplogical sorting algorithm
        hitParent= new HashSet<Integer>();
        execDFS = new HashSet<Integer>();
        orderLoc = new HashMap<Integer, Integer>();
        topOrder = new ArrayList<List<Integer>>();
        for(int i = 1; i <= n; ++i)
        {
            hitParent.clear();
            if(!execDFS.contains(i))
            {
                int res = dfs(i);
                if(res == -1)
                {
                    return -1;
                }
            }
        }
        
        return topOrder.size();
    }
    
    // Set<Integer> hitParent;
    // Set<Integer> execDFS;
    // HashMap<Integer, Integer> orderLoc;
    // List<List<Integer>> topOrder;
    // -1 return is immediate invalidation BTW!
    // Has to be the maximum level across your children ( yep not too easily :-) )
    // You wil always avoid a node which has already been DFS-ed as well!
    private int dfs(int parent)
    {
        // 1. Work of the parent
        int maxLevel = 0;   // 0 = max level for singleton node / a leaf ( terminal ) node in the graph structure
        List<Integer> hood = adjList.get(parent);
        hitParent.add(parent);
        execDFS.add(parent);
        for(int i = 0; i < hood.size(); ++i)
        {
            int child = hood.get(i);
            if(hitParent.contains(child))
            {
                return -1;
            }
            else if ( execDFS.contains(child))
            {
                int childLevel = orderLoc.get(child);
                if(childLevel == -1)
                {
                    return -1;
                }
                maxLevel = Math.max(maxLevel, 1 + childLevel);                
            }
            else
            {
                int childLevel = dfs(child);
                if(childLevel == -1)
                {
                    return -1;
                }
                maxLevel = Math.max(maxLevel, 1 + childLevel);
            }
        }
        // add parent after getting level info as well
        // The top sort list should allow for this anyways!
        if(maxLevel == topOrder.size())
        {
            topOrder.add(new ArrayList<Integer>(parent));
        }
        else
        {
            List<Integer> ordering = topOrder.get(maxLevel);
            ordering.add(parent);
        }
        orderLoc.put(parent,maxLevel);
        hitParent.remove(parent);
        return maxLevel; // always return your proper level info as well
    }
    
    
    private void constructAdjList(int n, int[][] relations, HashMap<Integer, List<Integer>> adjList)
    {
        // Construct init hashmap ( avoids check key existence operation later down ) 
        // One-indexed graph 
        for(int i = 1; i <= n; ++i)
        {
            adjList.put(i, new ArrayList<Integer>());
        }
        
        // Now go over the list of edges
        for(int i = 0; i < relations.length; ++i)
        {
            int[] edge = relations[i];
            int src = edge[0];
            int dst = edge[1];
            List<Integer> adj = adjList.get(src);
            adj.add(dst);
        }
        return;
    }
}
