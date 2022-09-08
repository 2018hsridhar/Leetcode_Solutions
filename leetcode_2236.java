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
 2236. Root Equals Sum of Children
https://leetcode.com/problems/root-equals-sum-of-children/
1 minute, single expression -> do check for null case though :-)
 */
class Solution {
    public boolean checkTree(TreeNode root) {
        return ((root != null) && (root.val == (root.left.val + root.right.val)));
    }
}
