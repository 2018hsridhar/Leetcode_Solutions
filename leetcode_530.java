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

Num nodes bounded anyways from [2,10,000]
Node values strictly bounded by [0,100,000]

URL = https://leetcode.com/problems/minimum-absolute-difference-in-bst/
530. Minimum Absolute Difference in BST

It is any two nodes -> so efficiency is only so crucial too
We can be quuick and get the fist minimum
Can also sort the input and return the difference of first two elements in our BST

BST :- Binary Search Tree

It is a BST, so we know the difference of nodes quickly -> but do not just presume it is a left path reading problem
    
    
Example_1 ::

        4
       / \
      2   6
     / \
    0   3
    
RETURN |3-2| = 1

Example_2 ::

          8
        /   \
      6      14
     / \    /
    2   7  11
            \
             12

    RETURN |12-11| = 1
    
Example_3 ::

          10
        /   \
      5      15
     / \    /
    0   7  11

    RETURN |8-7| = 1
    Notice how min diff elements lie on two seperate paths here
    
BST property itself imbbues much strength here on finding min distances too

*/

class Solution 
{
    public int getMinimumDifference(TreeNode root) 
    {
        
    }
}



