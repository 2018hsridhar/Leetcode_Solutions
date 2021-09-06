/*
ATTEMPT THE UNION-FIND SOLUTION AGAIN HERE
URL = https://leetcode.com/problems/number-of-operations-to-make-network-connected/
1319. Number of Operations to Make Network Connected
Return min times needed to extract specific cables and place between disconnected cables to connect the entire network
If (#_extra_cables)<(#_seperate_connected_components), return -1

As UNION_FIND tends to be it's own data structure, we want to write up a class ( just like a SLL/HashMap )
Easiest to represent via an array too
Also easy to obtain root of a node too


COMPLEXITY : 
Let V := number vertices
Let E := number edges
TIME = O(E*time(unify)+time(get_root_src) + time(get_root_dst))
SPACE = O(V)

TEST CASES : 


Limitations of the Union-Find Algorithm
(1) Does NOT work without in-memory storage
(2) Does NOT work if we do NOT know the total number of nodes to perform operation on

*/

class UnionFind
{
    public int n;
    public int[] roots;
    
    public UnionFind()
    {
        
    }
    
    public UnionFind(int n)
    {
        this.n = n;
        for(int i = 0; i < roots.length; ++i)
            roots[i] = i;
    }
    
    public int find(int id)
    {
        
    }
    
    // Useful to return the root of the unified nodes too
    public int union(int node1, int node2)
    {
        
    }
    
    public int pathCompress(int node)
    {
        
    }
}

// 2 connected components => return 1 conection here
class Solution 
{
    public int makeConnected(int n, int[][] connections)
    {
        int num_extra_cables = 0;
        int num_conn_comps = 0;
        UnionFind uf = new UnionFind(n);
        
        if(num_conn_comps == 1)
            return 0;
        else if(num_extra_cables >= num_conn_comps)
            return (num_conn_comps - 1);
        return -1; // NOT doable here
    }
    
    
    
    
}
