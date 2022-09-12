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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
 * URL = https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 * 431. Encode N-ary Tree to Binary Tree
 
 
 
 
 


TEST CASES
(A)
(B)
(C)
(D)
(E)
(F)

CONSTSRAINTS
N = 10,000 nodes ( fortunatley ) 
Node vals in range [0,10K] => not too bad
Height not atrocious either :-) ( 10^3 ) 

COMPLEXITY
Let N := number of nodes in the N-Ary tree / Binary Tree
TIME = O()
SPACE = O() [ EXPLICIT ] O() [ IMPLICIT ]




*/

class Codec {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root == null)
            return null;
        TreeNode rootPrime = new TreeNode(root.val);
        encodeHelper(root, rootPrime);
        return rootPrime;
    }
    
    // We want the root created before we pass in values here
    // 2-ary -> n-ary
    private void encodeHelper(Node root, TreeNode rootPrime)
    {
        // Like a SLL : track the previous node we set
        TreeNode prev = rootPrime;
        int i = 0;
        while(i < root.children.size())
        {
            Node child = root.children.get(i);
            TreeNode childPrime = new TreeNode(child.val);
            if(i == 0)
            {
                prev.left = childPrime;
                prev = childPrime;
                encodeHelper(child,childPrime);
            } else if ( i > 0 )
            {
                prev.right = childPrime;
                prev = childPrime;
                encodeHelper(child,childPrime);
            }
            ++i;
        }
    }
	
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null)
            return null;
        Node rootPrime = new Node(root.val);
        decodeHelper(root,rootPrime);
        return rootPrime;
    }
    
    // n-ary -> 2-ary
    // Incorporate a boolean toggle too!
    private void decodeHelper(TreeNode root, Node rootPrime)
    {
        // Can the list be empty too ( seems correct ) ?
        List<Node> childrenPrime = new ArrayList<Node>();
        TreeNode firstChild = null;
        if(root.left != null)
        {
            firstChild = root.left;
            Node childPrime = new Node(firstChild.val);
            childrenPrime.add(childPrime);
            decodeHelper(firstChild,childPrime);
        }
        if(firstChild != null)
        {
            while(firstChild.right != null)
            {
                firstChild = firstChild.right;
                Node childPrime = new Node(firstChild.val);
                childrenPrime.add(childPrime);
                decodeHelper(firstChild,childPrime);
            }
        }
        rootPrime.children = childrenPrime;
    }
    
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
