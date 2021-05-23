


/*

    This is really just a fancy MST, via Kruskal's algorithm
    Utilize union-find approach to code up Kruskal's algorithm
    Need all initial edge-weights though
    Set up number of edges in a complete graph, with their corresponding weights
    Formula is : N(N-1)/2 edges here
    We are provided a point-set instead : convert to an edge set too
    But advantage of point sets : the number of vertices is known ahead of time.
    
    Time Complexity of Algorithm
    T = O(ElogE) + O(ElogV)
    O(ELogE) for sorting the edge list by their corresponding edge weights
    O(ElogV) - max of E edges to check for adding to graph, and DSUF algorithm, on V vertices, takes logV time
    DSUF = Disjoint-Set Union Find
    
    
    
    Hyperlinks to Kruskal's Algorithm
    1. https://www.youtube.com/watch?v=_Iz-QLBGKpM&list=PLEJXowNB4kPzByLnnFYNSCoqtFz0VKLk5&index=19
    2. https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
    3. https://www.youtube.com/watch?v=Ub-fJ-KoBQM&list=PLEJXowNB4kPzByLnnFYNSCoqtFz0VKLk5&index=20
    
    It is easy to index into the points array : use this for tracking source and destination indices

 */

class Solution {
    
    // Set up the UnionFind / DCUF data structure to track two arrays and key methods find(), union()
    public class UnionFind
    {
        private int numElems;
        
        // size of each element
        private int[] sz;
        
        // At index i, provide the id of the parent node
        private int[] id;
        
        // track number of components Union Find establishes for us
        private int numComponents;
        
        public UnionFind(int size)
        {
            numElems = size;
            numComponents = size;
            
            sz = new int[size];
            id = new int[size];
            
            for(int i = 0; i < size; ++i)
            {
                id[i] = i; // each component's parent or root is just itself
                sz[i] = 1; // each component is originally size = 1
            }
        }
        
        // Find component / subgroup a node belong too
        // Perform path compression
        // 2 while loops - the first, to find the root, and the second, to execute path compression
        // Where "amortized" time complexity comes up
        // Iterate approach to path compression remains easier than the recursive approach

        public int find(int p)
        {
            // Find root of component/set
            int root = p;
            while(root != id[root])
                root = id[root];
            
            // Now at parent node
            // Execute path compression too ( starting at p ) 
            // Akin to swap algorithms really ( use temporary storage ) 
            while(p != root)
            {
                int next = id[p];
                id[p] = root;
                p = next;
            }
            
            return root; // returned at end of algorithm
        }
        
        // Just checking connectedness yields path compression
        public boolean isConnected(int p, int q)
        {
            return find(p) == find(q);
        }
        
        // Best to obtain component size, from the root nodes
        // Root nodes foudn at "end" of the chain ; thus they possess the size
        public int componentSize(int p)
        {
            return sz[find(p)];
        }
        
        public int components()
        {
            return numComponents;
        }
        
        // Unify by their roots
        
        public void unify(int p, int q)
        {
            int rootP = find(p);
            int rootQ = find(q);
            
            // In-group check
            if(rootP == rootQ)
                return; // it's not continue - it is the blank function return
            
            // Perform rank/size test here
            if(sz[rootP] < sz[rootQ])
            {
                sz[rootQ] += sz[rootP];
                id[rootP] = rootQ;
            }
            else
            {
                sz[rootP] += sz[rootQ];
                id[rootQ] = rootP;
            }
            
            // num components decrease per each UF iteration
            numComponents--;
        }
    }
    

    
    // Benefits of Comparable interface : can quickly invoke an Arrays.sort method
    public class Edge implements Comparable<Edge>
    {
        int src, dest;
        double weight;
        
        public Edge(int _src, int _dest, double _weight)
        {
            this.src = _src;
            this.dest = _dest;
            this.weight = _weight;
        }
        // Comparator function
        // Used to sort edges based on their weight
        public int compareTo(Edge e)
        {
            if(this.weight < e.weight)
                return -1;
            else if ( this.weight > e.weight)
                return 1;
            return 0;            
        }
        
        public String toString()
        {
            String result = String.format("Edge (%d,%d) has weight = [%f]\n", this.src, this.dest, this.weight);
            return result;
        }
    }
    
    public double getManhattanDist(int[] point1, int[] point2)
    {
        double manDist = Math.abs(point1[1]-point2[1]) + Math.abs(point1[0] - point2[0]);
        return manDist;
    }
    
    public int minCostConnectPoints(int[][] points) 
    {
        int minCost = 0; // 0 = default minimum cost 
        
        // STEP 1 : CREATE AND SORT THE WEIGHTED-EDGE LIST
        // Weighted-edge list too : expect K_N=n(n-1)/2 number edges to outpuht
        
        ArrayList<Edge> edgeList = new ArrayList<Edge>();
        int V = points.length;
        for(int i = 0; i < V; ++i)
        {
            int[] srcPoint = points[i];
            for(int j = i+1; j < V; ++j)
            {
                int[] dstPoint = points[j];
                double dist = getManhattanDist(srcPoint, dstPoint);
                // System.out.printf("srcEdge = %s, dstEdge = %s, dist = %f\n", i, j, dist);
                Edge newEdge = new Edge(i,j,dist);
                edgeList.add(newEdge);
            }
        }
        int E = edgeList.size();
        
        Collections.sort(edgeList);
         
        // Part 2 : Iterate over edge list, starting at min edge weight
        // check if edge can be added to MST, without creating a cycle in the undirected graph
        // Verify via count of edges too = (n-1)
        
        UnionFind uf = new UnionFind(V);
        int countE = 0;
        int i = 0;
        while(i < E )
        {
            Edge e = edgeList.get(i);
            if(uf.find(e.src) != uf.find(e.dest))
            {
                uf.unify(e.src,e.dest);
                minCost += e.weight;
                ++countE;
            }
            ++i;
        }
        
        
        return minCost;
    }
}
