/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*

URL = https://leetcode.com/problems/binary-tree-postorder-traversal/
145. Binary Tree Postorder Traversal

Most likely similar to the "inorder traversal" in a tree
Utilize auxillary data structrue such as a queue or a stack

Ideal Time-Space Complexity : [T,S] = [O(N), O(N)]
where N := number of nodes in the Binary Tree

When dealing with null inputs -> to return null, or empty set ( both differ : empty oject has a .length method to return 0, and other property information. Not a null object)

As usual, singleton node devolves to that of processing a leaf node


*/


class Solution
{
    public List<Integer> postorderTraversal(TreeNode root) 
    {
        LinkedList<Integer> traversal = new LinkedList<Integer>();
        if(root == null)
            return traversal;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.add(root);
        while(!stk.isEmpty())
        {
            TreeNode cur = stk.pop();
            if(cur.left == null && cur.right == null)
                traversal.addLast(cur.val);
            else if ( cur.left != null && cur.right == null)
            {
                stk.add(cur);
                stk.add(cur.left);
            }
            else if ( cur.left == null && cur.right != null)
            {
                stk.add(cur);
                stk.add(cur.right);
            }
            else
            {
                stk.add(cur);
                stk.add(cur.right);
                stk.add(cur.left);
            }
            cur.left = null;
            cur.right = null;
        }
        return traversal;
    }
}
