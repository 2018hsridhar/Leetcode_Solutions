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

URL = https://leetcode.com/problems/convert-bst-to-greater-tree/
538. Convert BST to Greater Tree

Given TreeNode objects : with val, left, right pointers

COMPLEXITY
Let N := #-nodes in the BST
Let H := height of the BST ( log_2(n) balanced, (n) skewed ) 
TIME = O()
SPACE = O()

TEST CASES
N bounded by 10,000 : a cubic algorithm could work ( 16.6777 minutes here ) . Do not go quartic or quintic
Always a valid BST
No data overflow runtime exception will ensue -> 10e4*10e4 = 10e8 < INT_MAX ( 2 * 10e9 ) 
(A) [1]
    => [1]
(B) [4,1,6]
    => [11,10,6]
(C) [4,1,null] ( only left child ) 
    [4,5,null]
(D) [4,null,6] ( only right child ) 
    [10,null,6]
    ^ basic trees : make more complex trees though ^ 
(E)  [4,null,6,5,7,null,null,null,8]
    => pass
(F) [4,1,6,null,2,5,7,null,null,null,null,null,8]
    => getting wrong here though!
(G)

Ideas
(1) the sum on the right hand side is easy to get : always add the right subtree
(2) On the left is tougher -> what to pass

We have this "reverse" in-order idea too
Can I pass in the sum from the root if we are going left bound though?
Analyze that backtrack carefully
Write to a node -> should NOT explore said node again

*/

class Solution {
    public TreeNode convertBST(TreeNode root) 
    {
        TreeNode result = root;
        if(root == null) return null;
        // Is fine to equal 0 : root has no parent node
        int toRightParentSum = 0;
        recurse(root, toRightParentSum);
        return result;
    }
    
    // Pass in the sum of nodes to your right, as parameter to the stack frames as well
    // Seems like a sum is not being passed in as expected here. Inspect carefull!
    private int recurse(TreeNode root, int toRightParentSum)
    {
        int sum_subtree = 0;
        if(root.right != null)
        {
            // This could be the bug too!
            sum_subtree += recurse(root.right, toRightParentSum);  // root.right never has a right parent too!    
            // sum_subtree += recurse(root.right, 0);  // root.right never has a right parent too!    
        }
        else if ( root.right == null)
        {
            sum_subtree += toRightParentSum;
        }
        sum_subtree += root.val;
        root.val = sum_subtree; // write here
        if(root.left != null)
        {
            // The left case needs the update, in this local scope though!
            sum_subtree = recurse(root.left, sum_subtree);
        }
        return sum_subtree;
    }
    
}
