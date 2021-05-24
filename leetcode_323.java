// PROBLEM : 323. Number of Connected Components in an Undirected Graph
// HYPERLINK : https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

class Solution {
    
    /*
    
        Attempting a Union-Find Solution Here
        Denote size of DSUF - a.k.a. UnionFind - via <V> to correspond to number of vertexes
        Initially, number of components = V. Decrease this over time with each unification
        Will iterate only over number of edges anyways - this resolves it
        
        Evolution of the ids array : 
        
        Beginning : [-1,-1,-1,-1,-1]
        End : [1,1,1,3,3] ( one such example, at least )
        
        
        
     */
    
    public class UnionFind
    {
        
        private int[] sizes;
        private int[] ids;
        private int numComps;
        
        public UnionFind(int V)
        {
            // numElems = V;
            numComps = V;
            
            sizes = new int[V];
            ids = new int[V];    
            
            for(int i = 0; i < V; ++i)
            {
                ids[i] = i; // each node is its own parent / self-root
                sizes[i] = 1; // each starting component is a single node
            }
            
        }
        
        public int getNumComponents()
        {
            return this.numComps;
        }
        
        // Execute iterative path compression here
        public int find(int p)
        {
            int root = p;
            while(root != ids[root])
            {
                root = ids[root];
            }
            
            while(p != root)
            {
                int next = ids[p];
                ids[p] = root;
                p = next;
            }
            
            return root;
        }
        
        // Rederive by remembering the following
        // (a) you must execute find operations, on both graph nodes, during unification, since unification 
        // will work off the ancestor root of each nodes
        // (b) You need to perform two sizes comparisons, to know how to absorb the CCs - Connected Components
        // (c) Must decrease number of CCs by 1, since a unification is guaranteed to connect two connected componenets
        // the if condition with sizes naturally handles the equals case too!
        // (d) the sizes array helps with expediting the process
        
        public void unify(int p, int q)
        {
            
            int root1 = find(p);
            int root2 = find(q);
            
            if(root1 == root2)
            {
                return; // same ancestor to both graph nodes
            }
            
            else
            {
                if(sizes[root1] >= sizes[root2])
                {
                    sizes[root1] += sizes[root2];
                    ids[root2] = root1;
                }
                else
                {
                    sizes[root2] += sizes[root1];
                    ids[root1] = root2;
                }
            }
            
            --numComps;
        }
        
    }
    
    // Union Find works best with an edge list provided
    public int countComponents(int n, int[][] edges) 
    {
        // N is already known : work off the edges
        int count = 0;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < edges.length; ++i)
        {
            int[] e = edges[i];
            int src = e[0];
            int dst = e[1];
            if(uf.find(src) != uf.find(dst))
            {
                uf.unify(src,dst);
            }
        }
        
        count = uf.getNumComponents();
        return count;
    }
}
