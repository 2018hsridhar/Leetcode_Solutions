/*
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
*/

/*
133. Clone Graph
URL = https://leetcode.com/problems/clone-graph/

Node value = node index ( luckily :-) )
Using adjacency list representation graph - UNORDERED listing 
Always start at first node ( value = one ) 
Return copy of given node as a reference
Ask if node values are unique
Ask if graph is CONNECTED ( are all nodes visitable, from any or the starting given node ) 
Cardinality of nodes is reasonable too [ 0,100 ] 



Deep-copying generally requires the use of hashmaps


Computational Complexity : 
Time = O(B^D) where B = branch factor, D = max depth. So in this case, O(|V|+|E|)
- put all edges into consideration, even if not traversed here

Space = O(V) Hashmap

Edge case testing :
(1) The empty graph
(2) A singleton node case
(3) A complex K_n graph
(4) |V| nodes with no connections = |V| disjoint sets ( BUT  NOT CONNECTED - WOAH! ) 
- [[],[],[],[]] = a representation for 4 such nodes



Since this is a connected, undirected graph => unlike list cloning, you can return ANY node : not limited to the head of the SLL
For every single index in the adjList - a new node has to be created
We do not know the size of this graph initially ( see that is the gotcha ). Versus a data structure wheere size is known
-> IF the size were known : populating the pointers and the objects would be super easy!
-> Due to the connected property of the graph, it is guaranteed that a BFS/DFS will terminate upon the final explored node too!


It is possible that nodes in your neighbors have been visited -> but make sure to add edges too
Need to modify array lists as candidates change : must modify to the hashed version! 
GOod news with BFS - update adjacency list of parent : then proceed with the children!
*/


class Solution 
{
    public Node cloneGraph(Node node) 
    {
        if(node == null)
            return null;
        
        Node initNode = node;
        HashMap<Integer,Node> newGraph = new HashMap<Integer,Node>();
        Set<Node> visited = new HashSet<Node>();
        Queue<Node> stateSpace = new LinkedList<Node>();
        stateSpace.add(node);
        while(!stateSpace.isEmpty())
        {
            Node candidate = stateSpace.poll();
            if(!visited.contains(candidate))
                visited.add(candidate); 
            
            
            Node parentNode;
            if(!newGraph.containsKey(candidate.val))
            {
                parentNode = new Node(candidate.val);
                newGraph.put(candidate.val, parentNode);
            }
            else
                parentNode = newGraph.get(candidate.val);
            
            // Iterate over existing adjacency list AND construct the new adjacency list too
            List<Node> children = candidate.neighbors;
            List<Node> newChildren = new LinkedList<Node>();
            for(Node x : children)
            {
                if(!newGraph.containsKey(x.val))
                {
                    Node newEl = new Node(x.val);
                    newGraph.put(x.val, newEl);
                    newChildren.add(newEl);
                }
                else
                    newChildren.add(newGraph.get(x.val));
                if(!visited.contains(x))
                    stateSpace.add(x);
            }
            parentNode.neighbors = newChildren;
            
        }
        
        return newGraph.get(initNode.val);
    }
}
