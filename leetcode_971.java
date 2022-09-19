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

971. Flip Binary Tree To Match Preorder Traversal
URL = https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/

Preorder traversal : root -> left -> right
Min #-flip operations too
Return values of ALL flipped nodes

Do we actually need to flip the tree here too?

[1,3,4,5,2,...]
Remember that all values in TREE and in VOYAGE are unique too ( they do NOT differ ) 
Does the minimum even make sense here? 
    -> can we focus on first checking if we can equal here?

Some weird state tracking over a recursive call stack again?
Also both #-nodes and voyage.length = n here.

TEST CASES ( on small trees ) 
(A) [1,2],[2,1] => [-1]
(B) [1,2,3],[1,2,3] => []
(C) [1,2,3],[1,3,2] => [1]
(D) [1,2,3,4,5,6,7] => [1]
[1,3,6,7,2,4,5]
(E) [1,2,3,4,5,6,7] => [1,3,2]
[1,3,7,6,2,5,4]
(F)  [1,2,3,4,5,null,null] => [1,2]
[1,3,2,5,4]
(G) [1,2,3,4,5,null,null,null,6,null,null,null,7]
[1,3,2,5,4,6,7] => [1,2]
(H)
* [1,2,3,4,5,null,null,null,6,null,null,8,7]
[1,3,2,5,4,6,7,8] *** fix this *** 
(I)


*/

// +5 mins remaining


class Solution {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) 
    {
        List<Integer> toFlip = new ArrayList<Integer>();
        int n = voyage.length;
        int[] idx = new int[1];
        idx[0] = 0;
        boolean stat = helper(toFlip,root,voyage,idx);
        if(!stat) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(-1);
            return temp;
        }
        return toFlip;
    }
    
    // Voyage is NOT null : keep note of this
    // Track and update index as we traverse too!
    private boolean helper(List<Integer> toFlip, TreeNode root, int[] voyage, int[] idx)
    {
        int myIdx = idx[0];
        if(root != null) {
            if(root.val == voyage[myIdx]) {
                if(isLeaf(root)){ 
                    // #TODO(hsridhar) You may or may not inr index here
                    return true;
                }
                else if(root.left != null && root.right == null){
                    idx[0]++; // incr before going left
                    // if(root.left != null)
                    return helper(toFlip,root.left,voyage,idx);
                    // else
                        // return true;
                } else if ( root.left == null && root.right != null) {
                    idx[0]++; // incr before going left
                    return helper(toFlip,root.right,voyage,idx);
                } else {
                    // Change the order of evaluation here!
                    if(root.left.val != voyage[myIdx+1]){
                        toFlip.add(root.val);
                        idx[0]++; // incr before going left
                        boolean rst_stat = helper(toFlip,root.right,voyage,idx);
                        idx[0]++; // incr before going right
                        boolean lst_stat = helper(toFlip,root.left,voyage,idx);
                        return (lst_stat && rst_stat);
                    } else {
                        idx[0]++; // incr before going left
                        boolean lst_stat = helper(toFlip,root.left,voyage,idx);
                        idx[0]++; // incr before going right
                        boolean rst_stat = helper(toFlip,root.right,voyage,idx);
                        return (lst_stat && rst_stat);
                    }
                }
            } else {
                return false; // root.val != idx : no flipping heree
            }
        } else if ( root == null) { 
            return true;
        }
        return true;
    }
    
    private boolean isLeaf(TreeNode root) {
        return ((root != null) && ( root.left == null && root.right == null));
    }
}


// Keep this all into working memory posing a challenge here.
// Remembering to track all states of computation too.




