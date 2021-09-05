/*

THOUGHT PROCESS : 
n cities at maximum here NUMBERED [0,n-1]
1466. Reorder Routes to Make All Paths Lead to the City Zero
URL = https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/

Input matrix is an edge list ( n x 2 ) ; not a (nxk, where k in 0..n-1) here
Return number of edges inverted/changed here
Network guaranteed a tree structure too ( n-1 roads only ) =? only one way to travel betweeen cities

COMPLEXITY : 
TIME = O(N+(N-1)) = O(2N-1) = O(N) ( think : standard BFS/DFS = O(V+E), but E is known to be V-1 here ) 
SPACE = O(N)

Can we perform a DFS from node zero itself?
We can collect out-deg and in-deg info for each node too
Wait a sec -> between each node is 1 edge only, and this is a tree
So it is guaranteed, in the worst case, for in-degree and out-degree to have a bound too, right?

Use sets in a hashmap when number of items to add it unknown, BUT, ordered is not needed
Hash entails faster lookup time too


Number nodes reasoably bounded to fit in mem : [2,50,000]
Trick here : start @ destination => not @ source

*/
class Solution
{
    public int minReorder(int n, int[][] connections) 
    {
        HashMap<Integer, HashSet<Integer>> IN_DEG = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> OUT_DEG = new HashMap<Integer, HashSet<Integer>>();
        HashSet<Integer> visited = new HashSet<Integer>();
        for(int key = 0; key < n; ++key)
        {
            IN_DEG.put(key,new HashSet<Integer>());
            OUT_DEG.put(key,new HashSet<Integer>());
        }
        // conns = [1,0] means 1 (src) has an outdegree to 0 ( dst)
        for(int i = 0; i < connections.length; ++i)
        {
            int[] conns = connections[i];
            int src = conns[0];
            int dst = conns[1];
            IN_DEG.get(dst).add(src);
            OUT_DEG.get(src).add(dst);
        }
        int start_node = 0;
        int numEdges = dfs(start_node,IN_DEG,OUT_DEG, visited);
        return numEdges;
    }
    
    public int dfs(int nodeID, HashMap<Integer, HashSet<Integer>> IN_DEG, HashMap<Integer, HashSet<Integer>> OUT_DEG, HashSet<Integer> visited)
    {
        if(visited.contains(nodeID))
            return 0;
        visited.add(nodeID);
        int count = 0;
        HashSet<Integer> outDeg = OUT_DEG.get(nodeID); 
        HashSet<Integer> inDeg = IN_DEG.get(nodeID);
        if(outDeg != null && outDeg.size() > 0)
        {
            for(int outChild : outDeg)
                if(!visited.contains(outChild))
                    count += (1 + dfs(outChild, IN_DEG, OUT_DEG, visited));                
        }
        if(inDeg != null && inDeg.size() > 0)
        {
            for(int inChild : inDeg)
                if(!visited.contains(inChild))
                    count += dfs(inChild, IN_DEG, OUT_DEG, visited);
        }
        return count;
    }
}
