/*

1361. Validate Binary Tree Nodes
URL = https://leetcode.com/problems/validate-binary-tree-nodes/

THOUGHT PROCESS :

Topological Sort approach
-> naturally works with edge lists OR with in-degrees
-> quick to implement via Kahn's Algorithm too

DFS approach : 
-> how to know which node to perform the DFS from?
-> how to ensure we count how many times a node has been hit/visited set?
-> how to scale to disjoint graphs case ( refer to example two here ) 
-> how to avoid performing DFS for each node ( can't we just perform for each unique path instead )?
E.g. in example 1 : just do DFS(0 ) : not DFS(1)/DFS(2)/DFS(3), as they were traversed on the path to DFS(0)





- Propogate parent->child relationships as we traverse the tree ( highly akin to Tarjan's SCC algo ) 
- Check that we do not have a collision
- propogate values and check if we have the same value in one of these graphs too ( using two arrays for allocated space ) 
- [-1,1] validation seems fairly easy for checking (left-child, right-child) relationships too

ASSUMPTIONS
Always given at least one node : up to 10,000 nodes for in-memory storage
Both array lengths equal each other, and correspond to valid nodes [0,n-1] OR -1 [ non-existent case ] 


COMPUTATIONAL COMPLEXITY 

EDGE CASE TESTING : 
(a) n = 2, leftChild=[1,0], rightChild=[-1,-1]

Note : 
1. Easy to build an adjacency list, from an edge list
2. Easy to obtain out-degree/in-degree information, from an edge list too

*/
class Solution 
{
    
    
    
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) 
    {
        return topologicalSortSolution(n,leftChild,rightChild);
    }
    
    
    public boolean topologicalSortSolution(int n, int[] leftChild, int[] rightChild)
    {
        int[] inDegs = new int[n];
        for(int i = 0; i < n; ++i)
        {
            int src = i;
            int left = leftChild[i];
            int right = rightChild[i];
            if(left != -1)
                inDegs[left]++;
            if(right != -1)
                inDegs[right]++;
        }
        
        //for(int i = 0; i < inDegs.length; ++i)
        //    System.out.printf("For node = %d\t Indegree = %d\n", i, inDegs[i]);
        
        // check number of nodes encountered in top sort -> should equal n here ( if not n : indicative of an issue! ) 
        // Perform O(N) operation on inDegs : check if zeroCount == 2, or we have a degree >= 2 node
        int zeroCount = 0;
        int zeroDegNode = -1;
        for(int i = 0; i < inDegs.length; ++i)
        {
            if(inDegs[i] == 0)
            {
                zeroDegNode = i;
                zeroCount++;
                if(zeroCount == 2)
                    return false;
            }
            else if ( inDegs[i] == 2)
                return false;
        }
        if(zeroCount == 0)
            return false;
            
        // TAKE HEAD : You need a true topological sort -> not just counting in degrees!! 
        // Kahn's algorithm requires some form of iteration/recursion too : need an accompanying data structure for sure
        // But we need a manner to represent adjacency unto each node : thus, need in-memory data structures!
        HashMap<Integer,List<Integer>> adjList = new HashMap<Integer,List<Integer>>();
        for(int i = 0; i < n; ++i)
            adjList.put(i, new LinkedList<Integer>());
        for(int i = 0; i < n; ++i)
        {
            int left = leftChild[i];
            int right = rightChild[i];
            if(left != -1)
                adjList.get(i).add(left);
            if(right != -1)
                adjList.get(i).add(right);
        }
        
        /*
        Queue<Integer> myQ = new LinkedList<Integer>();
        int numEncountered = 0;
        myQ.add(zeroDegNode);
        while(!myQ.isEmpty())
        {
            int curNode = myQ.poll();
            ++numEncountered;
            List<Integer> children = adjList.get(curNode);
            for(int child : children)
            {
                --inDegs[child];
                if(inDegs[child] == 0)
                    myQ.add(child);
            }
        }
        if(!myQ.isEmpty() || numEncountered != n)
            return false;
        */
        
        Stack<Integer> stk = new Stack<Integer>();
        int numEncountered = 0;
        stk.push(zeroDegNode);
        while(!stk.isEmpty())
        {
            int curNode = stk.pop();
            ++numEncountered;
            List<Integer> children = adjList.get(curNode);
            for(int child : children)
            {
                --inDegs[child];
                if(inDegs[child] == 0)
                    stk.push(child);
            }
        }
        if(!stk.isEmpty() || numEncountered != n)
            return false;
        
        
        
        return true;
    }
}
graph
