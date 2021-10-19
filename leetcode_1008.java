/*
URL = https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
1008. Construct Binary Search Tree from Preorder Traversal


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
    public TreeNode bstFromPreorder(int[] preorder) 
    {
		int n = preorder.length - 1;
		TreeNode root = reconstructBst(preorder, 0, n);
		return root;
    }
    
    public TreeNode reconstructBst(int[] preOrderTraversalValues, int low, int high) 
	{
		TreeNode root = null;
		// [1] Perform search for the next greater element in the BST input
		if(low > high)
			return null;
		else if ( low == high)
		{
			int value = preOrderTraversalValues[low];
			root = new TreeNode(value);
			return root;
		}
		else
		{
			int value = preOrderTraversalValues[low];
			root = new TreeNode(value);
			
			// locate next maximal element : if idx = -1, then now maximal here
			int nextMaximalIdx = -1;
			for(int i = low + 1; i <= high; ++i) // change inequality
			{
				if(preOrderTraversalValues[i] >= value)	// other inequality
				{
					nextMaximalIdx = i;
					break;
				}
			}
			
			if(nextMaximalIdx == -1)
			{
				root.left = reconstructBst(preOrderTraversalValues, low + 1, high);
				root.right = null;
			}
			else
			{
				root.left = reconstructBst(preOrderTraversalValues, low + 1, nextMaximalIdx - 1);
				root.right = reconstructBst(preOrderTraversalValues, nextMaximalIdx, high);
			}
		}
		return root;
  }
}
