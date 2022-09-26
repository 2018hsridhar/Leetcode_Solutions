/*
337. House Robber III
URL = https://leetcode.com/problems/house-robber-iii/

*/
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
class Solution {
    public int rob(TreeNode root) 
    {
        helper(root);
        return root.val;
    }
    
    // Note : houses need not be consecutive ( in fact, we may leverage roots too )
    // Real goal is to handle problem in a top-down fashion.
    private int helper(TreeNode root)
    {
        if(root == null) {
            return 0;
        } else if ( isLeaf(root)) {
            return root.val;
        } else {
            helper(root.left);
            helper(root.right);
            int maxFromRoot = root.val;
            int maxFromSubs = 0;
            TreeNode lst = root.left;
            TreeNode rst = root.right;
            if(lst != null) {
                maxFromSubs += lst.val;
                if(lst.left != null) {
                    maxFromRoot += lst.left.val;
                }
                if(lst.right != null) {
                    maxFromRoot += lst.right.val;
                }
            }
            if(root.right != null) {
                maxFromSubs += rst.val;
                if(rst.left != null) {
                    maxFromRoot += rst.left.val;
                }
                if(rst.right != null) {
                    maxFromRoot += rst.right.val;
                }
            }
            int myNodeMax = Math.max(maxFromRoot, maxFromSubs);
            root.val = myNodeMax;
            return root.val;
        }
    }
    
    private boolean isLeaf(TreeNode root)
    {
        return ((root != null) && (root.left == null && root.right == null));
    }
}
