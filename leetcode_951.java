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
951. Flip Equivalent Binary Trees
https://leetcode.com/problems/flip-equivalent-binary-trees/

Test Cases
(A) [1,2,3] => true
(B) [1,3,2] => true
(C)
[1,2,3,4,5,6,7]
[1,3,2,5,4,7,6] => false
(D)
[1,2,3,4,5,6,7]
[1,3,2,7,6,4,5] => true
(E) [1],[] => false
(F) [], [1] => false
(G)
[1,null,2]
[1,2,null] => true
(H)
[1,null,2]
[1,2,null] => true
(I)
[1,2,null,3,null,4,null]
[1,null,2,null,3,null,4] => true
(J) [1,null,3,null,5]
[1,2,3,null,null,5,null] -> false


COMPLEXITY
LET N := #-nodes in the tree
LET H := height of the tree
TIME = O(N)
SPACE = O(H) ( IMP ) O(1) ( EXP ) 

// REVIEW YOUR EDGE CASES CAREFULLy ( CLOSE! ) 
[1,null,3]
[1,2,3] --> OMG 76/77 cases
*/

// Number of operations agnostic.
// Hey -> can we try to keep to one method only :-)

class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) 
    {
        // [1] Basic Null CHECKS
        if(root1 == null && root2 == null)
            return true;
        if((root1 == null && root2 != null) || ( root1 != null && root2 == null))
            return false;
        
        // [2] CHECKING VALUES
        if(root1.val != root2.val)
            return false;
        
        TreeNode root1_left = root1.left;
        TreeNode root1_right = root1.right;
        
        TreeNode root2_left = root2.left;
        TreeNode root2_right = root2.right;
        
        
        // ONE TREE UNDER THE MANIPULATIONS ONLY
        
        // Two child case
        if(root1_left != null && root1_right != null)
        {
            if(root2_left != null && root2_right != null)
            {
                // Check equality aspects too
                if(root1_left.val == root2_left.val && root1_right.val == root2_right.val)
                    return (flipEquiv(root1_left,root2_left) && flipEquiv(root1_right,root2_right));    
                else
                    return (flipEquiv(root1_left,root2_right) && flipEquiv(root1_right,root2_left));
            }
            else
                return false;
        }
        
        // Root 1's left null, right not null
        // Expect root 2's left not null, right nul.
        if(root1_left != null && root1_right == null)
        {
            if(root2_left == null && root2_right != null)
            {
                if(root1_left.val == root2_right.val) {
                    flipTheNode(root1);
                    return (flipEquiv(root1_left,root2_right) && flipEquiv(root2_left,root1_right));
                }
            }
            else if ( root2_left != null && root2_right == null) {
                if(root1_left.val == root2_left.val) {
                    return (flipEquiv(root1_left,root2_left) && flipEquiv(root1_right,root2_right));
                }
            }
            else
                return false;
        }

        
        // Root 1's left null, right not null
        // Expect root 2's left not null, right nul.
        if(root1_left == null && root1_right != null)
        {
            if(root2_left != null && root2_right == null)
            {
                if(root1_right.val == root2_left.val) {
                    flipTheNode(root1);
                    return (flipEquiv(root1_left,root2_right) && flipEquiv(root2_left,root1_right));
                }
            }
            else if ( root2_left == null && root2_right != null ) {
                if(root1_right.val == root2_right.val) {
                    return (flipEquiv(root1_left,root2_left) && flipEquiv(root1_right,root2_right));
                }
            }
            else
                return false;
        }
        return true; // should never execute
    }    
    // assuming root is not null here
    public void flipTheNode(TreeNode root)
    {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    
}


