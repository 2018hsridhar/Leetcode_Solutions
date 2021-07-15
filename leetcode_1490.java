/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/*
Reasonable depth : [1,1000] in levell
Number of nodes bounded from [0,10,000]


URL = https://leetcode.com/problems/clone-n-ary-tree/
1490. Clone N-ary Tree

Computational Complexity : 
Time = O(b^d) = O(|V| + |E| ) for standard BFS algo
Space = O(b^d) = O(|V|) for hashmap
---> consider (1:n-1) partitioning case here

Edge case testing
(1) Null node
(2) Singleton nodes
(3) Complicated serialiations [ e.g. [1,null,3,2,4,null,5,6] ]
(4) Serialization equivalent to a linked list [ e.g. [1,null,3,null,5]]

*/

class Solution {
    public Node cloneTree(Node root) 
    {
        if(root == null)
            return null;
        
        Queue<Node> traversal = new LinkedList<Node>(); // Why is a queue implemented as a lsit underneath?
        HashMap<Node,Node> newTreeNodes = new HashMap<Node,Node>();
        traversal.add(root);
        Node rootOriginal = root;
        
        // Can utilize a queue, since pointers are populated later only anyways!
        // Let _one = original tree and _two = deep-copied tree
        // Object memory stays the same, ideally, in VM landscape - no need to worry about non-unique addresses too!
        
        while(!traversal.isEmpty())
        {
            Node cur_one = traversal.remove();
            Node cur_two;
            if(!newTreeNodes.containsKey(cur_one))
            {
                cur_two = new Node(cur_one.val);
                newTreeNodes.put(cur_one, cur_two);
            }
            else
                cur_two = newTreeNodes.get(cur_one);
                
            
            List<Node> children_one = cur_one.children;
            List<Node> children_two = new LinkedList<Node>();
            for(Node x : children_one)
            {
                traversal.add(x);
                Node newChild = new Node(x.val);
                children_two.add(newChild);
                newTreeNodes.put(x, newChild);
            }
            cur_two.children = children_two;
        }
        
        
        Node rootDeepTreeCopy = newTreeNodes.get(rootOriginal);
        return rootDeepTreeCopy;
        
    }
}
