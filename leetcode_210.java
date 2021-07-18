/*

URL = https://leetcode.com/problems/course-schedule-ii/
210. Course Schedule II

THOUGHT PROCESS : 
- Utilizes standard topological sorting algorithm
- Graph may not be connected : can work with disjoint sets in a topological sort ( provided disjoint sets themselves form a DAG ) 
- 

DATA TYPES : 
- numCourses reasonable [ 1,2000]
- Num edges reasonable too [ K_n bound ] 


COMPUTATIONAL COMPLEXITY : 
TIME = O(|V| + |E|)
SPACE/Memory = O(|V|)
- Spaces matters for in-memory testing !

EDGE CASE TESTING : 
Ease to test graphs up to n = 7 nodes ( do not stray further than that ) 
Especially for generating permutations here
(0) The singleton node, zero edges - [0]
(1) n = 2 nodes graph : one edge [[1,0]] => output = [0,1]
(2) n = 3 nodes graph : [[2,1],[2,0],[1,0]] => output = [0,1,2]
(3) n = 4 nodes graph : [[3,0],[3,1],[3,2],[2,1],[2,0],[1,0]] => output = [0,1,2,3]
(4) n = 5 nodes graph : [[4,0],[4,1],[4,2],[4,3],[3,0],[3,1],[3,2],[2,0],[2,1],[1,0]] => output = [0,1,2,3,4]
(4.1) n = 6 nodes graph : [[5,0],[5,1],[5,2],[5,3],[5,4],[4,0],[4,1],[4,2],[4,3],[3,0],[3,1],[3,2],[2,0],[2,1],[1,0]] => output = [0,1,2,3,4,5]
(5) A SLL graph with n nodes, n-1 edges - [[5,4],[4,3],[3,2],[2,1],[1,0]] => [0,1,2,3,4,5,]
(6) A more randomized graph
-> generate as a subset from max edge graph relationships
-> Given n nodes, max #-of directed edges = (n-1) + (n-2) + ... + 1 = n(n-1)/2 = K_N. 
Easiest is to generate K_N graphs, and then delete random edges from those sets, to generate a test suite case barrage for this problem :-)
(7) All nodes disjoint : n = 6, edges = [[]] => output = any permutation from {0-5} here :-)
(8) n = 8 with 3 disjoint graphs : [[2,1],[2,0],[1,0],[4,3],[7,6],[7,5],[6,5]] => output = {0,3,5,2,1,4,6,7} :-)
(9) 3 with [[1,0],[1,2],[0,1]]


Kahn's In-Degree Ordering Algorithm be the easiest here
Requires additional preprocessing/information ahead of schedule ( DFS approach works in real-time ) 
Guaranteed at least one course -> return array here with min el case ( e.g. [0] for numCourses = 1, preqreqs = [] )


*/
class Solution 
{
    public int[] findOrder(int numCourses, int[][] prerequisites) 
    {
        HashMap<Integer,List<Integer>> outgoingEdges = new HashMap<Integer,List<Integer>>(); // adjacencyList
        HashMap<Integer,Integer> inDegrees = new HashMap<Integer,Integer>();
        // Linear time ; fill up O(N) hashmap of adjancey list and indegrees of node information
        // Notice in the counting increment here, THAT we must account for nodes with no in degree ( including singleton/disjoint nodes ) 
        for(int i = 0; i < numCourses; ++i)
        {
            inDegrees.put(i,0);
            outgoingEdges.put(i,new LinkedList<Integer>());            
        }
        for(int i = 0; i < prerequisites.length; ++i)
        {
            int[] edge = prerequisites[i];
            int src = edge[1];
            int dst = edge[0];
            if(inDegrees.containsKey(dst))
                inDegrees.put(dst, inDegrees.get(dst) + 1);
            if(outgoingEdges.containsKey(src))
                outgoingEdges.get(src).add(dst);
        }
        
        // Go over hashmap : add nodes with IN_DEG(v) = 0
        // Note : the first set should naturally adress all dijoint intervals ( including if every node is disjoint, worst case ) 
        
        int[] topologicalOrder = new int[numCourses];
        int wIdx = 0;
        Queue<Integer> traversal = new LinkedList<Integer>();
        Set<Map.Entry<Integer,Integer>> inDegEntries = inDegrees.entrySet();
        for(Map.Entry<Integer,Integer> kv_pair : inDegEntries )
        {
            int node = kv_pair.getKey();
            int inDeg = kv_pair.getValue();
            if(inDeg == 0)
                traversal.add(node);
        }
        
        // No - some inputs may not be DAGs. Must incorporate a visited set too
        Set<Integer> visited = new HashSet<Integer>();
        while(!traversal.isEmpty())
        {
            int candid = traversal.poll();
            if(visited.contains(candid))
                continue;
            else
            {
                visited.add(candid);
                topologicalOrder[wIdx++] = candid;
                List<Integer> outbound = outgoingEdges.get(candid);
                for(Integer x : outbound)
                {
                    inDegrees.put(x,inDegrees.get(x) - 1);
                    if(inDegrees.get(x) == 0)
                        traversal.add(x);
                }
            }
        }
        
        // Exited out prematurely : check wIdx now
        if(wIdx == 0 || wIdx != numCourses)
            return new int[]{}; // empty arr
        return topologicalOrder;
    }
}
