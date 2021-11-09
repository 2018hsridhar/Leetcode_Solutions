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
156. Binary Tree Upside Down
https://leetcode.com/problems/binary-tree-upside-down/
You must return our new root as well
    -> can we argue that the new root is always the leftmost node of a tree?

Strategies : Recursion, Top-Down, Bottom-Up, Iterative

COMPLEXITY
Let N := #-nodes in the tree
Let H := height of the tree ( log_2(N) balanced, N unbalanced ) 
Time = O(N)
Space = O(H)


Know that N is tiny here as well : only up till 10, and node vals also in a reasonable range
* Exploit that avery right node in the tree possesses NO children as well, but they always have a sibling

TEST BENCH
( *** provided *** )
(A) []
(B) [1]
(C) [1,2,3,4,5]




(D) [1,2,3]
(E) [1,2,null,4,5,6,7,8,9]
(F) [1,2,null,4,5]
(G) [1,2,null,4,5,6,7]

Make sure to destroy our cycles as well here
*/
class Solution 
{
    public TreeNode upsideDownBinaryTree(TreeNode root) 
    {
        if(root == null)
            return null;
        TreeNode newRoot = getNewRoot(root);
        helper(root);
        return newRoot;
    }
    
    private TreeNode getNewRoot(TreeNode root)
    {
        if(root.left == null)
            return root;
        else
            return getNewRoot(root.left);
    }
    
    // Tail recursive structure here as well
    private void helper(TreeNode root) 
    {
        if(root == null)
        {
            return;
        }
        else if ( root.left != null && root.right != null)
        {
            TreeNode leftChild = root.left;
            TreeNode rightChild = root.right;
            helper(root.left);
            helper(root.right);
            root.left = null;
            root.right = null;
            leftChild.left = rightChild;
            leftChild.right = root;
        }
        else if ( root.left != null)
        {
            TreeNode leftChild = root.left;
            TreeNode rightChild = root.right; // null anyways!
            helper(root.left);
            root.left = null;
            root.right = null;
            leftChild.left = rightChild;
            leftChild.right = root;
        }
        return;
    }
}
