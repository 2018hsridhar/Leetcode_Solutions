/*
THOUGHT PROCESS ::

Is going to use recursion ( BFS/DFS ) underneath as well as backtracking to 
eliminate failing candidates


Complexity
Let N := #-nodes
Let H := longest path
Time = O(N)
Space = O(H) ( implicit ) O(N) ( explicit )

TEST BENCH 
(A) ( *** self loop *** ) [[0],[1],[3],[2]]
(B) [[],[0,2,3,4],[3],[4],[]]
    *** wrong answer ***
(C)
(D)
(E)

Luckily it is a 0-indexed graph ( ALWAYS as this )
Arguement with DFS
(1) That we need only one visited set, as if we DFS(B) from a DFS(A), if \exist outgoing (A,B), then we need not perform DFS(B) again as 
the DFS(A) would have performed its visitation
(2) That we can set up two distinct sets - terminal nodes, visited nodes - and ask in the recursive func body before the callee function if
our current node is a member of the following
(a) visited & terminal
(b) non-visited
(c) visited & non-termianl
At (c), we ended up in a cycle -> so all paths invalidated here ( return false on the BACKTRACK upwards ) 
At (b), we continue the algo
At (a), we set the value to TRUE on the backtrack / callback

Make distinction : root->internal->leaf nodes
Each node labelled with unique values

Correct BUT order incorrect
We passed the given test cases

*/
class Solution 
{
    // Provided our iterative approach, it will be in ASC order
    public List<Integer> eventualSafeNodes(int[][] graph) 
    {
        List<Integer> eventualSafe = new LinkedList<Integer>();
        Set<Integer> terminal = new HashSet<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int n = graph.length;
        for(int i = 0; i < n; ++i)
        {
            int[] adj = graph[i];
            if(adj.length == 0)
            {
                terminal.add(i);
            }
        }
        
        for(int i = 0; i < n; ++i)
        {
            if(!visited.contains(i))
            {
                boolean isSafe = dfs(graph, i, terminal, visited, eventualSafe);
            }
        }
        Collections.sort(eventualSafe);
        return eventualSafe;
    }
    
    // Base case : minimal subproblem
    // terminating case : when we have a means to end the recursion
    // Must check case where we land on a safe node ( but NOT a terminal node ) !
    private boolean dfs(int[][] graph, int node, Set<Integer> terminal, Set<Integer> visited, List<Integer> eventualSafe)
    {
        // BASE / TERMINATING CASE ( recursion needs its terminating case )
        if(visited.contains(node))
        {
            if(terminal.contains(node) || eventualSafe.contains(node))
                return true;
            else
                return false;
        }
        else
        {
            visited.add(node);
            int[] adj = graph[node];
            int sz = adj.length;
            boolean isSafe = true; // assume is a safe node until proven otherwise
            for(int i = 0; i < sz; ++i)
            {
                int childNode = adj[i];
                boolean childIsSafe = dfs(graph, childNode, terminal, visited, eventualSafe);
                // System.out.printf("parent = %d \t Child node = %d \t isSafe = %s\n", node, childNode, childIsSafe);
                if(childIsSafe == false)
                    isSafe = false;
            }
            // Notice update is strictly in backtrack here
            // Could sort as well too
            if(isSafe)
                eventualSafe.add(node);
            return isSafe;
        }
    }
}









