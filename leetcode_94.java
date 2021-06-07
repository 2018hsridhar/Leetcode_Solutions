
/*

94. Binary Tree Inorder Traversal
https://leetcode.com/problems/binary-tree-inorder-traversal/

Ideas

1. In pre-order traversal, we stop exploring the parent, once the children have been added onto our stack
2. Add to our stack in order of expected traversal : right->root->left
3. Convergence occurs if no more child nodes available for exploration
4. We need to still track parent nodes, for backtracking -> however, we also need a notion of a "visited set" for root nodes, to know that we should not add back the children too
5. Base cases : singleton node, node with 2 children, node with 1 child ( on left AND on right ) 
6. In pre-order, we add children ( right->left ) since we explore later children as we traverse to the RHS
-> will help for n-ary pre-order iterative traversal!
7. If the values of a BT are unique - can just check the integers; else, check the node objects themselves, as their addresses are guaranteed uniqueness! 
8. Does stack store nodes, or store integers? If we need to push-and-pop our children and root nodes, it must default to a stack data structure.
9. Do we build traversal after exploring all nodes ( a full stack ), or does stack evolve dynamically as nodes are pushed in and popped out?

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
    public List<Integer> inorderTraversal(TreeNode root) {
        
    }
}
