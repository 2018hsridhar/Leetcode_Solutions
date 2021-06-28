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

530. Minimum Absolute Difference in BST
URL = https://leetcode.com/problems/minimum-absolute-difference-in-bst/

THOUGHT PROCESS : 

Naive : BFS -> collect all values and obtain min difference between each pair - O(N^2) time, O(N) space

Ideal : [T,S] = [O(log(N)), O(1)]
Desire logarithmic performance in a BST

Is a BST : exploit pre-sorted property
At root node, min_abs_diff = Math.min(|root.val - left.val|, |root.val - right.val|, { root.val - max(left)},|root.val - min(right)| )
Exert caution here cuz of weird edge cases!
Also known : min absolute difference stays within the root of a tree cuz of BST property ( all elements on right hand side be greater anyways ) 



*/

class Solution 
{
    public int getMinimumDifference(TreeNode root) 
    {
        
    }
}
