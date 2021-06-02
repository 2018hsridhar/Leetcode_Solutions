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

LEETCODE 144 : Binary Tree Preorder Traversal
URL = https://leetcode.com/problems/binary-tree-preorder-traversal/

 THOUGHT PROCESSES 

 Pre-order : root-left-right
 In order : left-root-right
 Post order : left-right-root
 
 Utilize a stack , or recursive means, to perform graph traversal in proper ordering
 
 
 
*/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) 
    {
        List<Integer> traversal = new LinkedList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        if(root == null)
            return traversal;
        stk.add(root);
        while(!stk.isEmpty())
        {
            TreeNode cur = stk.pop();
            traversal.add(cur.val);
            if(cur.left != null && cur.right != null)
            {
                stk.push(cur.right);
                stk.push(cur.left);
            }
            else if(cur.left != null && cur.right == null)
            {
                stk.push(cur.left);
            }
            else if(cur.right != null && cur.left == null)
            {
                stk.push(cur.right);
            }
            else
                continue;
        }
        
        return traversal;
    }
}
