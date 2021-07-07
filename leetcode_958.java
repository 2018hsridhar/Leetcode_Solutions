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

958. Check Completeness of a Binary Tree
URL = https://leetcode.com/problems/check-completeness-of-a-binary-tree/

Idea : 
One : use power-of-2 series here ( 1 + 2 + 4 + 8 = num_nodes in a complete tree ) 
Additionally, consider serialization of tree, into an array, and then check from (n/2,n) nodes. We can easily check from nodes (1,n/2-1) without difficulty.
How to perform serialization and account for null children as accurately as possible too?
[ 1,2,3] should not be [1,2,3,null,null,null,null]
BUT
[1,2,null,null,3,5] should be what it is here
Add a non-null check flag as we reverse itetate over the last level too?


*/

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        return false;
    }
}
