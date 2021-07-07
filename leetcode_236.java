/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
236. Lowest Common Ancestor of a Binary Tree


Thought processes : 

Most tree problems seem highly recursive in their nature -> BFS level-order fails here : need DFS as it is not in level-order : it be a path-specific algorithm

Ideal Computational Complexity : 
T = O(b^d), where b := 2 ( branch factor ) AND d := max possible depth of a node ( from root ) 
S = O(1) OR O(d) if using a stack

In all problems, the root is guaranteed to be a possible LCA ( unless the root is null ) 
A node is allowed to be a descendant of itself ( according to LCA definition ) 

Node values are guaranteed their uniqueness here, thankfully
Can have a high number of nodes, but guaranteed [2,100,000] : so at least one child in this tree and one root node in this tree ( 1,2 ) or (3,1) are minimum structures

Answer is guaranteed in three types of places
(a) the current root
(b) the left subtree
(c) the right subtree 

Do we desire a post-order traversal : left->right->root check? A in-order traversal may not be sensical here [ left->root->right ] NOR a pre-order [ left->right->root ] 


Edge case testing : 
[2,1]
[2,1,3]
[3,5,null,6,null,7,null ] - fully left
[ 3,null,4,null,6,null,7] - fully right 
[3,5,1,6,2,0,8,2,4,3,5,6,7,8,9]- complete tree case ( ) - enjoy 2 power series trick for complete trees :-)

Complexity when handling two children cases ( if one child -> just traverse that path and check ) 



*/
 
 
 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
    {
        if(root == null)
            return null;
        
        
    }
}
