/*
URL = https://leetcode.com/problems/find-leaves-of-binary-tree/
366. Find Leaves of Binary Tree

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
    public List<List<Integer>> findLeaves(TreeNode root) 
    {
        List<List<Integer>> leaves = new ArrayList<List<Integer>>();
        if(root == null)
            return null;
        getLeaves(root, leaves);
        return leaves;
    }
    
    // Head recursive ( note traversal order as well ) 
    public int getLeaves(TreeNode root, List<List<Integer>>)
    {
        if(root == null)
        {
            return 0;
        }
        int maxDepth = 1; // default
        if(root.left == null && root.right == null)
        {
            
            maxDepth = 1;
            return maxDepth;
        }
        if(root.left != null)
        {
        }
        if(root.right != null)
        {
        }
        
        return maxDepth;
    }
}
