// 270. Closest Binary Search Tree Value


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
    public int closestValue(TreeNode root, double target) 
    {
        if(root == null)
			return 0;
		int closestVal = root.val;
		double minDist = Math.abs((double)closestVal - target);
		while(root != null)
		{
			if(Math.abs(root.val - target) < Math.abs((double)closestVal - target))
				closestVal = root.val;
			if(root.val == target)
				return (int)target;
			else if ( root.val < target)
				root = root.right;
			else
				root = root.left;
		}
        return closestVal;
    }
}
