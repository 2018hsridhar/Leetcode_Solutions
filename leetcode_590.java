/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/*

590. N-ary Tree Postorder Traversal
https://leetcode.com/problems/n-ary-tree-postorder-traversal/submissions/

THOUGHT PROCESSES 
Most common parent/abtract class seen : Lists/Sets ( Linked/Array List or Hash/Tree Sets )
Left-Right-Root = Traversal order ( a.k.a. explore all subtrees, left->right, and then the root tree )
Add root node value last too
Recursive tree traversls remain easier compared to iteratiev tree traversals

*/

class Solution {
    public List<Integer> postorder(Node root) 
    {
        LinkedList<Integer> postOrderTrav = new LinkedList<Integer>();
        helper(root, postOrderTrav);
        return postOrderTrav;
    }
    
    public void helper(Node root, List<Integer> trav)
    {
        // Handles both a null root, and if the children of the parent node are null values too!
        if(root == null)
            return;
        List<Node> children = root.children;
        Iterator<Node> itr = children.iterator();
        while(itr.hasNext())
        {
            Node child = itr.next();
            helper(child, trav);
        }
        trav.add(root.val);
    }
}
