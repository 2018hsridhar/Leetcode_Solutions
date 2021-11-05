/*
We can try to decrement our k here, when maintaining the DP state
So the DP matrix may actuall be 3-dimensional : ( src,dst,k) 

787. Cheapest Flights Within K Stops
URL = https://leetcode.com/problems/cheapest-flights-within-k-stops/

THOUGHT process : 
We know naive DFS -> but can we memoized it or apply it in a top-down DP fashion as well?
We need not worry about self-loops as well
Given an edge list as well : denotes adjacency with speific prices as well
Return cheapest route ( >= 0 ) or -1, if no such route even exists


COMPLEXITY ( DP ) 
Let V := #-vertices
Let K := depth
TIME =  O(V^2*K)                POLY ( well we expect budp to be the size of our cache)
SPACE = O(V^2*K) ( explicit ) O(K) implicit [ call stack depth can get this big ] 

In general, for bottom up DP, time = space. Not always for TD memo though

TEST BENCH
(A)
(B)
(C)
(D)
(E)

Price should enver exceed 10^6 here ( 10^4 for highest edge price * 100 nodes max ) 
Compare to max value to discern here
// Could use 'n' as an adjacency list as well? 

Ok so recursive solution ran into a TLE issue. Perfect

*/
class Solution
{
    int[][][] memo;
    // n = 3 : nodes inexed from [0-2] : should suffice as expected
    // but may tank with the k values at lesat ( must solve from k + 1))
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) 
    {
        memo = new int[n][n][k+2];    // alreayd set to 0 here
        int cheapest = 0; // helps to initialize outside method return : for debug purposes, at least
        HashMap<Integer, List<int[]>> adjList = new HashMap<Integer, List<int[]>>();
        createAdjandency(flights,n, adjList);
        cheapest = dfs(adjList, flights, src, dst, k+1);
        return cheapest; 
    }
    
    /*
    Time = 
    Space = 
    */
    private void createAdjandency(int[][] flights, int n, HashMap<Integer, List<int[]>> adjList)
    {
        // Remember : we may have multiple nodes, but not enough in flight info for sure
        for(int i = 0; i < n; ++i)
        {
            List<int[]> novel = new ArrayList<int[]>();
            adjList.put(i, novel);
        }
        
        for(int i = 0; i < flights.length; ++i)
        {
            int[] flight = flights[i];
            int src = flight[0];
            int dst = flight[1];
            int price = flight[2];
            
            List<int[]> novel = adjList.get(src);
            novel.add(new int[]{dst,price});
        }
        
    }   
    
    // Memoized DFS really
    // need not keep a visited set as well ... can come back to the same path later
    private int dfs(HashMap<Integer, List<int[]>> adjList, int[][] flights, int src, int dst, int k) 
    {
        
        // Check memoized cache here
        if(memo[src][dst][k] != 0)
        {
            return memo[src][dst][k];
        }
        
        
        int cheapest = Integer.MAX_VALUE;
        // need a -1 case as well ( occurs when k == 0, but src != dst)
        if(k == 0 && src != dst)
        {
            cheapest = -1;
        }
        else if(src == dst)  // k never matters here BTW!
        {
            cheapest = 0;            
        }
        else
        {
            // [1] Go thru the adjacency of a graph here
            List<int[]> neighborhood = adjList.get(src);
            for(int i = 0; i < neighborhood.size(); ++i)
            {
                int[] metadata = neighborhood.get(i);            
                int child = metadata[0];
                int price = metadata[1];
                
                int childCheapestPrice = dfs(adjList, flights, child, dst, k-1);
                if(childCheapestPrice != -1)
                {
                    cheapest = Math.min(cheapest, price + childCheapestPrice);
                }
                // Must have logic in call back : first check that -1 was not returned by dfs invoked in parent caller to adj list
            }
        }
        if(cheapest == Integer.MAX_VALUE)
        {
            cheapest = -1;
        }
        memo[src][dst][k] = cheapest;
        return memo[src][dst][k]; 
    }
    
}
