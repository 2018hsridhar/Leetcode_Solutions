/*

PROBLEM : GRAPH VALID TREE
CURRENT APPROACH SEEMS RATHER INEFFICIENT THOUGH!

Idea : all edges in this setting possess equal weighting
Attempt Prim's Algorithm from earlier problem, but this time ... pretend you are coding up the algo for an equal weighting case
A.k.a. your priority queue really will not be much utilized here


Realize that DSUF algorithm is meant for unweighted graphs.
When they are weighted graphs, they become Krushkal's/Prims algorithm



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
    
    public void unify(int p, int q)
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
            
        }
    }
    
    
    
}


class Solution {
    public boolean validTree(int n, int[][] edges) 
    {
        // Base case : n = 1
        if(n == 1)
            return true;
        
        boolean isValidTree = true;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < edges.length; ++i)
        {
            int[] myEdge = edges[i];
            int src = myEdge[0];
            int dst = myEdge[1];
            uf.unify(src,dst);
        }
                
        // If we got down to one component, check if all edges in edge list are found in the MST
        if(uf.numComponents == 1)
        {
            for(int i = 0; i < edges.length ; ++i)
            {
                int[] myEdge = edges[i];
                int foundEdgeIdx = -1;
                for(int j = 0; j < uf.mst.size(); ++j)
                {
                    int[] mst_edge = uf.mst.get(j);
                    if(mst_edge[0] == myEdge[0] && mst_edge[1] == myEdge[1])
                    {
                        foundEdgeIdx = j;
                        break;
                    }
                }
                if(foundEdgeIdx == -1)
                    return false; 
            }
        }
        else
            isValidTree = false;
        
        return isValidTree;
    }
}
