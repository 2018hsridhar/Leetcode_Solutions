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
 606. Construct String from Binary Tree

 */
class Solution {
    public String tree2str(TreeNode root) 
    {
        if(root == null)
            return "";
        String result = helper(root);
        return result;
    }
    
    private String helper(TreeNode root)
    {
        String cur = root.val + "";
        if(root.left != null)
        {
            cur += "(" + helper(root.left) + ")";
        }
        if(root.right != null)
        {
            if(root.left == null) {
                cur += "()";
            }
            cur += "(" + helper(root.right) + ")";
        }
        return cur;
    }
    
}
