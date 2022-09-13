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
865. Smallest Subtree with all the Deepest Nodes
URL = https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
This problem is bottom-up in nature
We can propagate the height as we traverse down
Must get statistics from (left,right)
    -> the smallest subtree containing the deepest nodes
    
Not too many nodes here

Complexity
Let N := #-nodes in the binary tree
Let H := height of the binary tree [ log_2(N) balanced : (N) skewed ]
TIME = O(N)
SPACE = O(1) ( EXPLICIT ) O(H) ( IMPLICIT ) 

TEST CASES
(A) [0,1,null,2,null,3,null,4,null]
(B) [0,1,null,2,null,3,null,4,5]
    => 3
(C) [0,1,null,2,null,3,6,4,5,7,null]
    => 2

*/

class Info
{
    public TreeNode deepest;
    public int depth;
    
    public Info()
    {
        deepest = null;
        depth = 0;
    }
    
    public Info(TreeNode deepest,int depth)
    {
        this.depth = depth;
        this.deepest = deepest;
    }
    
}

class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) 
    {
        if(root == null)
            return null;
        Info deepestNodeInfo = helper(root);
        return deepestNodeInfo.deepest;
    }
    
    private Info helper(TreeNode root)
    {
        if(isLeaf(root))
        {
            return new Info(root,1);
        }
        else
        {
            if(root.left != null && root.right == null) 
            {
                Info leftInf = helper(root.left);
                return new Info(leftInf.deepest, leftInf.depth + 1);
            }
            else if(root.left == null && root.right != null)
            {
                Info rightInf = helper(root.right);
                return new Info(rightInf.deepest, rightInf.depth + 1);
            }
            else {
                Info leftInf = helper(root.left);
                Info rightInf = helper(root.right);
                if(leftInf.depth == rightInf.depth)
                {
                    return new Info(root, leftInf.depth + 1);
                } 
                else if ( leftInf.depth > rightInf.depth)
                {
                    return new Info(leftInf.deepest, leftInf.depth + 1);
                }
                else
                {
                    return new Info(rightInf.deepest, rightInf.depth + 1);
                }
            }
        }
    }
    
    private boolean isLeaf(TreeNode root)
    {
        return ((root != null) && (root.left == null && root.right == null));
    }
    
}









