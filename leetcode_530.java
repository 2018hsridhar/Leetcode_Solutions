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

THOUGHT PROCESS : 

Naive : BFS -> collect all values and obtain min difference between each pair - O(N^2) time, O(N) space

Ideal : [T,S] = [O(log(N)), O(1)]
Desire logarithmic performance in a BST

Is a BST : exploit pre-sorted property
At root node, min_abs_diff = Math.min(|root.val - left.val|, |root.val - right.val|, min_abs_diff(left), min_abs_diff(right))
Also known : min absolute difference stays within the root of a tree cuz of BST property ( all elements on right hand side be greater anyways ) 

We know following cases quickly

(a) Null node or childless/singleton node - return 0
(b) Node with one child only ( 2 levels max ) => return min distance, based on strict comparisons

Node values bounded by [0,100,000]
Node values are guaranteed uniqueness ( I presume ) - may not hold true?
In singleton case, ( min = 0, max = 0 ) of the null children!
[min,max] in singleton case = [0,0] and Math.min(root-min,root-max) = root, honestly

Min absolute difference = 0 : will tank you in the case of the leaf node

*/

class Solution 
{
    public int getMinimumDifference(TreeNode root) 
    {
        if(root == null || (root.left == null && root.right == null)) return 0; // no absolute difference anyways!
        
        
    }
    
    
    
}




