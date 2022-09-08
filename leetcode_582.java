/*
582. Kill Process

1. Any order is a powerful lack of an imposed constraint
2. Edge list represent fits the problem spec naturally
Assume well-formed inputs : validation steps not needed.
This just seems to be a graph problem - in the hiding.

Test Cases
(a)
(b)
(c)
(d)
(e)
(f)
(g)

Complexity
Let N := number of nodes
Let E := number of edges = N-1
Time = O(N)
Space = O(N) ( expicit ) O(1) ( implicit ) 

*/
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) 
    {
        // 1. Create our adjacent list of interest
        // Represents { parent -> child } process relationships.
        List<Integer> killed = new ArrayList<Integer>();
        Map<Integer,List<Integer>> adjList = new HashMap<Integer,List<Integer>>();
        for(int i = 0; i < ppid.size(); ++i) {
            int curPPid = ppid.get(i);
            int curPid = pid.get(i);
            if(!adjList.containsKey(curPPid)) {
                List<Integer> newList = new ArrayList<Integer>();       // init syntax not correct here
                newList.add(curPid);
                adjList.put(curPPid, newList);
            } else {
                adjList.get(curPPid).add(curPid);
            }
        }
        
        // 2. Go over the adjacent list, from given process to kill
        // Akin to a level order traversal ( BFS/DFS it ) ?
        dfs(adjList,kill,killed);
        return killed;
    }
    
    private void dfs(Map<Integer,List<Integer>> adjList, int kill, List<Integer> killed)
    {
        killed.add(kill);
        List<Integer> toKill = adjList.get(kill);
        if(toKill == null)
            return;
        for(Integer x : toKill) {
            dfs(adjList, x, killed);
        }
    }
}
