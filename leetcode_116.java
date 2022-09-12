/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }
    

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

/*
The easy answer here is level-order traversal
BUT let us use ONLY constant extra space -> so no explicit queue or explicit stack made readily available.
Recursive approach is fine here :-).

116. Populating Next Right Pointers in Each Node
URL = https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
The binary tree is a perfect binary tree : 
    all internal nodes have two children
    all leaves are on the same leve
1->2->4->8->... and so on.
It fills up level-by-level too.






*/


class Solution {
    public Node connect(Node root) 
    {
        fillAsExpected(root);
        fillInternally(root);
        return root;    
    }
    
    private void fillInternally(Node root)
    {
        if(root != null)
        {
            if(root.left != null && root.right != null)
            {
                Node lst = root.left;
                Node rst = root.right;
                Node lst_right = lst.right;
                Node rst_left = rst.left;
                while(lst_right != null)
                {
                    lst_right.next = rst_left;
                    lst_right = lst_right.right;
                    rst_left = rst_left.left;
                }
                fillInternally(lst);
                fillInternally(rst);
            }
        }
    }
    
    private void fillAsExpected(Node root)
    {
        if(root != null)
        {
            if(root.left != null && root.right != null)
            {
                root.left.next = root.right;
                fillAsExpected(root.left);
                fillAsExpected(root.right);
            }
        }
    }
    
    
    
}
