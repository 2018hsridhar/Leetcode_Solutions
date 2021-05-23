/*

    This is really just a fancy MST, via Kruskal's algorithm
    Utilize union-find approach to code up Kruskal's algorithm
    Need all initial edge-weights though
    Set up number of edges in a complete graph, with their corresponding weights
    Formula is : N(N-1)/2 edges here
    We are provided a point-set instead : convert to an edge set too
    But advantage of point sets : the number of vertices is known ahead of time.
    
    
    
    
    Hyperlinks to Kruskal's Algorithm
    1. https://www.youtube.com/watch?v=_Iz-QLBGKpM&list=PLEJXowNB4kPzByLnnFYNSCoqtFz0VKLk5&index=19
    2. https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

    It is easy to index into the points array : use this for tracking source and destination indices

 */

class Solution {
    
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
        int minCost = -1;
        // STEP 1 : CREATE AND SORT THE WEIGHTED-EDGE LIST
        // Weighted-edge list too : expect K_N=n(n-1)/2 number edges to outpuht
        
        ArrayList<Edge> edgeList = new ArrayList<Edge>();
        for(int i = 0; i < points.length; ++i)
        {
            int[] srcPoint = points[i];
            for(int j = i+1; j < points.length; ++j)
            {
                int[] dstPoint = points[j];
                double dist = getManhattanDist(srcPoint, dstPoint);
                System.out.printf("srcEdge = %s, dstEdge = %s, dist = %f\n", i, j, dist);
                Edge newEdge = new Edge(i,j,dist);
                edgeList.add(newEdge);
            }
        }
        
        Collections.sort(edgeList);
        for(Edge e : edgeList)
        {
            System.out.print(e.toString());
        }
        
        
        
        
        
        return minCost;
    }
}
