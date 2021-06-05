/*

1469. Find All The Lonely Nodes
https://leetcode.com/problems/find-all-the-lonely-nodes/

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
    
    public List<Integer> getLonelyNodes(TreeNode root) 
    {
        List<Integer> lonelyNodes = new LinkedList<Integer>();
        helper(root, lonelyNodes);
        return lonelyNodes;
    }
    
    public void helper(TreeNode root, List<Integer> lonelyNodes)
    {
        if(root == null)
            return;
        else if ( root.left == null && root.right != null)
        {
            lonelyNodes.add(root.right.val);
            helper(root.right, lonelyNodes);
        }
        else if ( root.left != null && root.right == null)
        {
            lonelyNodes.add(root.left.val);
            helper(root.left, lonelyNodes);
        }
        else
        {
            helper(root.left, lonelyNodes);
            helper(root.right, lonelyNodes);
        }
    }
}
