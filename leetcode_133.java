
/*
133. Clone Graph
https://leetcode.com/problems/clone-graph/


THOUGHT PROCESSES : 

Deep-copying vs. Shallow-Copying
Occurs only for objects
In deep-copying, references are dereferenced, and the dereference values will be copied over newly
In shallow-copying, rerfrences are copied, but not dereferenced. Memory remains shared

The gist is whether the copies possess any shared memory or not
It's kinda cool how we can store memory references, and still basically work off of primitives - imagine never scaling more than 8-bit wide data types for an object ( just size of primitive types only! ) - might be efficient too for computer registers!

Shallow-copying : sharing of reference objects

Node value = Node index ( for test purposes ) 
Akin to tree copying too?

Node cardinality remains bounded by the closed interval [1,100]
Node values are unique per node
No repeated edges/self-loops :-). 
Is an undirectded graph
Graph is connected - is a single component ( hence the term "Connected Components"! )

Since graph is undirected too, then each edge - (a,b) and (b,a) - must be copied over accordingly!
At each vertex, copy the neighbors, and then add to a visited set - prevent re-exploration again!

Worst case with graph : 1 node adjacent to them all ( or K^n - the complete graph )
Benefit of adjacency list : ordering of vertices does not matter - it just matters that they get processed!

Have to create each node newly - need an efficient look-up to accompany them - thus, hashmap

*/




// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


// I need a way to store those initial nodes too
// And I do not want to recreate any already encountered nodes

class Solution {
    public Node cloneGraph(Node node) 
    {
        if(node == null)
            return null;
        
        // Make initial node for the graph
        HashSet<Integer> visited = new HashSet<Integer>();
        HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();
        Node clone = new Node(node.val);
        
        dfs(node, visited, nodes);
        return clone;
    }
    
    // "Hash"Set and "Hash"Map, underneath, utilize hashing algorithms to ensure uniqueness of their keysets
    // Both a <set> and a <map> have "keySets" - but a map has an accompanying "valueSet" too. It is how we iterate over keys, which matters
    
    
    // Have a tough time understanding why an approach will converge, or how convergence will ensue too!
    
    public void dfs(Node node, HashSet<Integer> visited, HashMap<Integer,Node> nodes)
    {
        if(!visited.contains(node.val))
        {
            
            for(Node e : node.neighbors)
            {
                
                
                dfs(e,visited,nodes);
            }
            visited.add(node.val);
        }
        else
            continue; // current branch of DFS terminates if visited set already contains element!
    }
    
}
