/*
TOP_SORT(G) will always commence at first array element ( in this case, org[0] )
URL = https://leetcode.com/problems/sequence-reconstruction/
444. Sequence Reconstruction

Partition code into functions with SRP
1. constructGraph
2. TOP_SORT(graph)
3. check validity of top_sort generated list

COMPLEXITY :
Let N := number of elements in org array ( or V too ) 
Let E := number of edges ( size of seqs array * len(longest_seq))


Construction
Time = 
Space = 

Top sort
Time = 
Space = 

Check sequence validity
Time = 
Space = 

TEST CASES : 
(a) A cycle : seqs = [[2,5],[5,2]] => return FALSE
(b) Invalid case  org = [1,2,3], seqs = [[1,2]]
(c) Valid case org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
(d) 
(e)

*/

/*
    Let us contemplate represnetation hard here
    Adjancency lists make most sense ( with neighbors being a set) . 
    We desire a quick set remove method
    For all practical purposes, the sequences seen here are basically an edge list : ergo
        [5,2,3,6] === [5,2],[2,3],[3,6]
        
        
        
        
*/

class Node
{
    public int val;
    public List<Node> neighbors;
    
    public Node() {}
    public Node(int val)
    {
        this.val = val;
    }
    public Node(int val, List<Node> neighbors)
    {
        this.val = val;
        this.neighbors = neighbors;
    }
}




class Solution
{
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) 
    {
        //Node origin = new Node();
        // n := number of nodes := is NOT known here. In this case, shall we consider a hashmap instead in lieu of an array?
        HashMap<Integer, HashSet<Integer>> IN_DEG = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> adj = new HashMap<Integer, HashSet<Integer>>();
        constructGraphs(adj, IN_DEG, seqs);
        int src = org[0];
        LinkedList<Integer> topSort = new LinkedList<Integer>();
        boolean hasValidTopSort = TOP_SORT(adj, IN_DEG,src, topSort);
        if(!hasValidTopSort)
            return false;
        
        /*
        for(Integer key : IN_DEG.keySet())
        {
            System.out.printf("For key = %d\t", key.intValue());
            HashSet<Integer> srcNodes = IN_DEG.get(key);
            for(Integer src : srcNodes)
                System.out.printf("%d,", src.intValue());
            System.out.printf("\n");
        }
        */
        
        return false;
    }
    
    /*
     * Note : even for a top sort algo, you still need the following
     *  (a) A visit set ( in event of a cycle : ideally not encountered if graph is guaranteed DAG property )
     
     *
    */
    public boolean TOP_SORT(HashMap<Integer, HashSet<Integer>> adj, HashMap<Integer, HashSet<Integer>> IN_DEG, int src, LinkedList<Integer> result)
    {
        Queue<Integer> bfs = new LinkedList<Integer>();
        bfs.add(src);
        while(!bfs.isEmpty())
        {
            int parent = bfs.poll();
            result.add(parent);
            HashSet<Integer> neighbors = adj.get(parent);
            for(int child : neighbors)
            {
                Set<Integer> indeg_child = IN_DEG.get(child);
                indeg_child.remove(parent);
                if(indeg_child.size() == 0)
                {
                    bfs.add(child);
                }
            }
        }
        return (result.size() == IN_DEG.size());        
    }
    
    // Do NOT end up with repeated edges, by accident, either
    public void constructGraphs(HashMap<Integer, HashSet<Integer>> adj, HashMap<Integer, HashSet<Integer>> IN_DEG, List<List<Integer>> seqs)
    {
        // [1] Add the nodes
        for(int i = 0; i < seqs.size(); ++i)
        {
            List<Integer> seq = seqs.get(i);
            for(int x : seq)
            {
                if(!IN_DEG.containsKey(x))
                    IN_DEG.put(x, new HashSet<Integer>());
                if(!adj.containsKey(x))
                    adj.put(x, new HashSet<Integer>());
            }
                
        }
        
        // [2] add in-deg edges to the hashmap
        for(int j = 0; j < seqs.size(); ++j)
        {
            List<Integer> seq = seqs.get(j);
            for(int i = 1; i < seq.size(); ++i)
            {
                int dstKey = seq.get(i);
                HashSet<Integer> vals = IN_DEG.get(dstKey);
                int src = seq.get(i-1);
                if(!vals.contains(src))
                    vals.add(src);
            }
        }
        
        // [3] add out-deg edges to the other hashmap here
        for(int j = 0; j < seqs.size(); ++j)
        {
            List<Integer> seq = seqs.get(j);
            for(int i = 1; i < seq.size(); ++i)
            {
                int dst = seq.get(i);
                int src = seq.get(i-1);
                HashSet<Integer> adjacent = adj.get(src);
                if(!adjacent.contains(dst))
                    adjacent.add(dst);
            }
        }    
    }
    
}
