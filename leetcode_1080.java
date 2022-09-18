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
1080. Insufficient Nodes in Root to Leaf Paths
URL = https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/
*/

/*
test Cases
(A) [1] 1 -> 1
(B) [1] 0 -> []
(C) [1,2,3] 1 -> []
(C) [1,2,3] 10 -> [1,2,3]
*(D) [1,2,3,4,5,6,7] 8 -> [1,null,3,null,5,6,7]
    ^ fixed : incorrect logic in passing of state
(E) 

*/

class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit)
    {
        boolean status = allUnderLimit(root,limit,root.val);
        if(!status) {
            return null;
        }
        return root; // tree still unchanged here
    }
    
    private boolean allUnderLimit(TreeNode root, int limit, int curPathSum)
    {
        if(root == null) { 
            return true;
        }
        else if(isLeaf(root)) {
            if(curPathSum < limit ) { // sum strictly less than limit
                // System.out.printf("curPathSum = [%d]\n", curPathSum);
                return false;
            }
            return true;
        } else { // failing at single path codes too!
            boolean lst_check = true;
            boolean rst_check = true;
            if(root.left != null)
            {
                lst_check = allUnderLimit(root.left,limit, curPathSum + root.left.val);
            }
            if(root.right != null)
            {
                rst_check = allUnderLimit(root.right,limit, curPathSum + root.right.val);
            }
            boolean myNodeStatus = true;
            if(root.left != null && root.right == null) {
                if(!lst_check) {
                    myNodeStatus = false;
                }
            }
            else if(root.left == null && root.right != null) {
                if(!rst_check) {
                    myNodeStatus = false;
                }
            }                
            else if ( root.left != null && root.right != null) {
                if(!lst_check && !rst_check) { 
                    myNodeStatus = false;
                }   
            }
            // NULLIFY your paths here. . . . after doing your checks too
            if(!lst_check) { 
                root.left = null;
            }
            if(!rst_check) { 
                root.right = null;
            }
            return myNodeStatus;
        }
    }
    
    private boolean isLeaf(TreeNode root) {
        return ((root != null) && (root.left == null && root.right == null));
    }
    
}


