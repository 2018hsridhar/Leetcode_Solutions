/*
    
    Code up PRIM'S ALGORITHM HERE IN PLACE OF KRUSHKAL'S ALGORITHM
    
    URL = https://www.youtube.com/watch?v=jsmMtJpPnhU&list=RDCMUCD8yeTczadqdARzQUp29PJw&start_radio=1&rv=jsmMtJpPnhU&t=0
    https://www.geeksforgeeks.org/prims-mst-for-adjacency-list-representation-greedy-algo-6/
    
    Issues : 
    Requires serious amount of coding upfront : does not work well if provided input is a weighted edge list
    - Krushkal's implementation remains the faster one
    - also messy since adj list must store both (dest,weight) for each vertex! wow!
    
    Utilize object serialization/tostring methods for quick debugging
    
    This is an out-degree of each node algorithm, working in real time sorting! Hence the optimization
    Q : Why is the worst case performance O(ElogV) and not O(ElogE)?
    A : Your sort takes placeat each node in real-time, not across the entirety of the edge set, and the maximum out degree of a given node can equal only (V-1), as we possess V 
    nodes in our graph ( assuming no double connections or self-loops ). During this (V-1) processing, each weight on that edge may or may not be pronounced stale too.
    
    Notice how bounds of a graph performance may be O(V) something, since max out/in degrees of vertices equals (V-1)
    Ideally, we can approach that out degree maximum
    
    */
    
    
// 51/63 test cases passed here

class Solution {
    boolean visited[];
    PriorityQueue<Edge> pq;
    ArrayList<ArrayList<Pair<Integer,Integer>> > adj;
    
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
            return this.src + ", " + this.dst + ", " + this.cost;
        }
    }
    
    public class EdgeComparator implements Comparator<Edge>
    {
        public int compare(Edge e1, Edge e2)
        {
            if(e1.cost < e2.cost)
                return -1;
            else if (e1.cost > e2.cost )
                return 1;
            return 0;
        }
    }
    
    public int minimumCost(int n, int[][] connections) 
    {
        adj = new ArrayList<ArrayList<Pair<Integer,Integer>> >(n);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Pair<Integer,Integer>>());    

        for(int i = 0; i < connections.length; ++i)
        {
            int src = connections[i][0] - 1;
            int dst = connections[i][1] - 1;
            int weight = connections[i][2];
            
            Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(src,weight);
            Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(dst,weight);
            
            adj.get(src).add(p2);             // makes sense : array list are objects - no operators defined a top them!
            adj.get(dst).add(p1);             // makes sense : array list are objects - no operators defined a top them!
        }

        int V = n;
        visited = new boolean[V];
        pq = new PriorityQueue<Edge>(new EdgeComparator());

        int m = V - 1; // max number of edges
        int edgeCount = 0; // count up to (V-1) edges for termination
        int mstCost = 0; // not negative 1 : always a cost is 0, until the end
        Edge[] mstEdges = new Edge[m];
        addEdges(0);
        
        // add the first node's edges ( node 0 here )
        // must iterate over edge list to option this!

        while(pq.size() != 0)
        {
            Edge e = pq.remove();
            int nextNode = e.dst;
            
            if(visited[nextNode])
                continue;
            
            mstEdges[edgeCount++] = e;
            mstCost += e.cost;
                
            addEdges(nextNode);
        }
        
        // check proper edge count has been reached
        if(edgeCount != m)
            return -1;
        
        return mstCost;
    }
    
    public void addEdges(int x)
    {
        visited[x] = true; // starting visitations from this edge!
        ArrayList<Pair<Integer,Integer>> edges = adj.get(x);

        for(Pair<Integer,Integer> outgoing : edges )
            if(!visited[outgoing.getKey()])
                pq.add(new Edge(x, outgoing.getKey(), outgoing.getValue()));
    }
    
}
