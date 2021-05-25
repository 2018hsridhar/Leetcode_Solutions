class Solution {
    
    /*
    
    Code up PRIM'S ALGORITHM HERE IN PLACE OF KRUSHKAL'S ALGORITHM
    
    URL = https://www.youtube.com/watch?v=jsmMtJpPnhU&list=RDCMUCD8yeTczadqdARzQUp29PJw&start_radio=1&rv=jsmMtJpPnhU&t=0
    
    */
    
    public class Edge
    {
        int src;
        int dst;
        int cost;
        
        public Edge(int _src, int _dst, int _cost)
        {
            this.src = _src;
            this.dst = _dst;
            this.cost = _cost;
        }
                
        public String toString()
        {
            String s = this.src + ", " + this.dst + ", " + this.cost;
            return s;
        }
    }
    
    public class EdgeComparator implements Comparator<Edge>
    {
        public int compare(Edge e1, Edge e2)
        {
            if(e1.cost < e2.cost)
                return -1;
            else if (e1.cost > e1.cost )
                return 1;
            return 0;
        }
    }
    
    public int minimumCost(int n, int[][] connections) 
    {
        return lazyPrims(n,connections);
        
        
        
    }
    
    public int lazyPrims(int n, int[][] connections)
    {
        int minCost = -1;
        int edgeCount = 0; // count up to (V-1) edges for termination
        int s = 0; // denotes the starting node
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new EdgeComparator());
        for(int i = 0; i < connections.length; ++i)
        {
            int[] edge = connections[i];
            Edge e = new Edge(edge[0],edge[1],edge[2]);
            pq.add(e);
        }
        
        while(pq.size() != 0)
        {
            Edge e = pq.remove();
            System.out.println(e.toString());
        }
        return minCost;
    }
        
    
    // in future : eagerPrims
//     public int eagerPrims()
//     {
        
//     }
}
