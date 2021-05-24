class Solution {
    
    
    /*
    
      Approach : Utilize Union-Find Algorithm, and something
      akin to that DFS flood-fill approach with {0,1,2} values being used as flags
      Avoid double counting of edges - e.g. (i,j) and (j,i) , by setting isConn[i][j] and isConn[j][i] to values = 2. This is the visited set value, in a sense
      Do count total number of vertices ( check i == j, when iterative over isConn matrix)
      Luckily, matrix is a (nxn) input, so utilize symmetry as much as possible!
      Base case is a matri, of size = 1
      Code up a DSUF class here too
      Default value : number of components will be bounded by [1,V]
      If all components are isolated - they'll report accordingly as an identity matrix
      
      Technically, path compression is not needed here
      Remember in the unify() operation, you increment your passed-in parameter :-)
      
     */
    
    public class UnionFind
    {
        private int[] ids; 
        private int numComps;
        
        public UnionFind(int V)
        {
            ids = new int[V];
            for(int i = 0; i < V; ++i)
                ids[i] = i;
            numComps = V;
        }
        
        public int getNumComponents()
        {
            return this.numComps;
        }
        
        public int find(int p)
        {
            int root = p;
            while(root != ids[root])
                root = ids[root];
            
            return root;
        }
        
        public void unify(int p, int q)
        {
            int root1 = find(p);
            int root2 = find(q);
            
            if(root1 != root2)
            {
                ids[root1] = root2;
                --numComps;
            }
            
        }
        
    }
    
    // Can we circumnavigate needing to construct a seperate class to represent edges?
    
    public int findCircleNum(int[][] isConnected) 
    {
        int V = isConnected.length;
        UnionFind uf = new UnionFind(V);
        
        for(int i = 0; i < isConnected.length; ++i)
        {
            for(int j = 0; j < isConnected.length; ++j)
            {
                if(i == j)
                {
                    isConnected[i][j] = 2;
                    continue;
                }
                else if (i != j && isConnected[i][j] == 1)
                {
                    int p1 = uf.find(i);
                    int p2 = uf.find(j);
                    if(p1 != p2)
                        uf.unify(i,j);
                    
                    // Add to the visited set here
                    isConnected[i][j] = 2;
                    isConnected[j][i] = 2;
                }
            }
        }
        
        return uf.getNumComponents();
        
    }
}
