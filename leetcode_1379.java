/*

LEETCODE 1379 : Find a Corresponding Node of a Binary Tree in a Clone of That Tree
https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/solution/

THOUGHT PROCESSES
- Answer must be a reference in the cloned tree
- For now, assume all values are unique
- Execute DFS ( in in-order traversal ) on the original and the cloned trees
- Target is a node in the original tree, and is NEVER  null too!

For n-ary trees, DSF, with have 3 subtypes
- Iterative with Stack
- Recursive
- Morris

For BFS, of a n-ary tree, we have only iterative with queue

*/



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) 
    {
        if(original == null && cloned == null)
            return null;
        if(original.val == target.val && cloned.val == target.val)
        {
            return cloned;
        }
        TreeNode lst = getTargetCopy(original.left, cloned.left, target);
        if(lst == null)
            lst = getTargetCopy(original.right, cloned.right, target);
        return lst;
        
    }
}
