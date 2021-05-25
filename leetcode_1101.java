// Leetcode 1101 - https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/


class Solution {
    
    
    /*
    
        [THOUGHT PROCESSES]
        
        If the given input is provided as a series of timestamps - is it already sorted by the timestamps or not?
        Timestamped input tends to be doubles or triplets - array of array ( processing of log files ) 
        
        Can we utilize union find algorithm?
        Union find algorithm is ideal algo for attempting to connect all nodes ( if problem statement asked for that )
        DSUF and MSTs both wire up a given graph with (n-1) number of edges. Thus, DSUF algo is used to build up our MSTs
        
        Code up the trivial union find data structure
        
        Logs are unique too!
        Logs are not neccessarily guaranteed their timestamp ordering - up to the programmer to sort that
        Number of logs maximum = 100 : easy to quickly sort out here!
        
    
    */

    public class UnionFind
    {
        private int numComponents = 0;
        private int[] ids;
        private int[] sz;
        
        public UnionFind(int V)
        {
            ids = new int[V];
            sz = new int[V];
            
            for(int i = 0; i < ids.length; ++i)
                ids[i] = i;
            
            numComponents = V;
            
        }
        
        public int find(int p)
        {
            int root = p;
            while(root != ids[root])
            {
                root = ids[root];
            }
            
            while(p != root)
            {
                int temp = ids[p];
                p = ids[p];
                ids[temp] = root;
            }
            
            return root;
        }
        
        public void unify(int p, int q)
        {
            int root_p = find(p);
            int root_q = find(q);
            
            if(root_p != root_q)
            {
                if(sz[root_p] > sz[root_q])
                {
                    sz[root_p] += sz[root_q];   
                    ids[root_q] = root_p;
                }
                else
                {
                    sz[root_q] += sz[root_p];
                    ids[root_p] = root_q;
                }
            }
            
            --numComponents;
        }
        
        public int getNumberComponents()
        {
            return this.numComponents;
        }
    }
    
    public void sortLogsByTimestamps(int[][] logs)
    {
        // The new Comparator object, possess a function for us, whcih acts as a lambda function
        // But a pure lambda function is not allowed, as a function must be a member of an object or a member of a class
        Arrays.sort(logs,new Comparator<int[]>(){
 
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] < o2[0])
                    return -1;
                else if(o1[0] > o2[0])
                    return 1;
                return 0;
            }});
         
    }
    
    public int executeUnionFind(int[][] logs, int n)
    {
        int etAq = -1;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < logs.length; ++i)
        {
            int[] log = logs[i];
            int myTime = log[0];
            int src = log[1];
            int dst = log[2];
            
            int root_src = uf.find(src);
            int root_dst = uf.find(dst);
            
            if(root_src != root_dst)
                uf.unify(root_src,root_dst);
            
            if(uf.getNumberComponents() == 1)
            {
                etAq = myTime;
                break;
            }
            
        }
        return etAq;
    }
    
    // remember that input nodes are 0-indexed from [0,n-1], but you input [n] number of nodes strictly to our method
    // break up approach into two methods - sort logs by timestamps, and then union find algorithm
    public int earliestAcq(int[][] logs, int n) 
    {
        int etAq = -1;
        
        // sort logs
        sortLogsByTimestamps(logs);
        etAq = executeUnionFind(logs, n);
        
        return etAq;
    }
}
