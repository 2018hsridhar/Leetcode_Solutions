/*
Leetcode 684 - Redundant Connectin
URL = https://leetcode.com/problems/redundant-connection/

*/

/*

Idea : Still the same boiler plate MST algoritm
But inclulde a return type in the unify() method, since that iterates across each edge
If an edge could be unified, return a positive/true status, and based on that, update a caller method's Edge index tracking array
Iterate over said array, and find out if an index is not set to true - this is your corresponding edge from the initial list

*/

public class UnionFind
{
    private int[] size;
    private int[] ids;
    public int numComponents;
    public ArrayList<int[]> mst;
    
    public UnionFind(int n)
    {
        this.numComponents = n;
        size = new int[n];
        ids = new int[n];
        mst = new ArrayList<int[]>();
        for(int i = 0; i < n; ++i)
        {
            size[i] = 1; // all are self looping here
            ids[i] = i; // each node remains its own root here        
        }   
    }
    
    public int find(int p)
    {
        int root = p;
        while(root != ids[root])
            root = ids[root];
            
        // Path compression portion now
        while(p != root)
        {
            int temp = p;
            p = ids[p];
            ids[temp] = root; // go update your predecssor as you move p up the chain :-)
        }
        return root;
    }
    
    public boolean unify(int p, int q)
    {
        int root_1 = find(p);
        int root_2 = find(q);
                
        if(root_1 != root_2)
        {
            if(size[root_1] >= size[root_2])
            {
                size[root_1] += size[root_2];
                ids[root_2] = root_1;
            }
            else
            {
                size[root_2] += size[root_1];
                ids[root_1] = root_2;
            }
            int[] newEdge = {p,q};
            mst.add(newEdge);
            --numComponents;   
            return true;
        }
        return false; 
    }
    
    
    
}

// What if <n> was not known ahead of time? how to resolve?
class Solution {
    public int[] findRedundantConnection(int[][] edges) 
    {
        HashSet<Integer> vertexes = new HashSet<Integer>();
        for(int i = 0; i < edges.length; ++i)
        {
            vertexes.add(edges[i][0]);
            vertexes.add(edges[i][1]);
        }
        int n = vertexes.size();
        
        boolean isValidTree = true;
        UnionFind uf = new UnionFind(n);
        int[] redundantConnection = edges[0]; // presume first edge, by default here
        for(int i = 0; i < edges.length; ++i)
        {
            int[] myEdge = edges[i];
            int src = myEdge[0] - 1;
            int dst = myEdge[1] - 1;
            boolean canUnify = uf.unify(src,dst);
            if(!canUnify)
                return myEdge;
        }
        
        
        
        return redundantConnection;
    }
}
