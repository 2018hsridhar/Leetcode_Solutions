/*
2331. Evaluate Boolean Binary Tree
URL = https://leetcode.com/problems/evaluate-boolean-binary-tree/

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
    public boolean evaluateTree(TreeNode root) 
    {
        if(root == null)
            return true;
        else if ( root.left == null && root.right == null)
        {
            if(root.val == 0) return false;
            else if ( root.val == 1) return true;
        } else {
            if(root.val == 2){ // OR
                return (evaluateTree(root.left) || evaluateTree(root.right));
            } else if ( root.val == 3) { // AND
                return (evaluateTree(root.left) && evaluateTree(root.right));
            }
        }
        return true;
    }
}
