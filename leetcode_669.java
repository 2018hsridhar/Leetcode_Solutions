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

    Given root of a BST and its [lowest,highest] boundaries as ( low, high ) 
    Trim the tree so all elements lie in that closed interval
    Do NOT change the relative existing structure of elements ( descendancy should still remaining here ) 
    BST property : do we have unique values OR duplicate values
    
    COMPLEXITY
    
    Let N := #-nodes
    Let H := height of BST [ log_2(n) balanced, (n) skewed ]
    Time = O(N)
    Space = O(H) ( implicit ) O(1) ( explicit ) 

    TEST BENCH :
    * passes website cases : may not pass all test cases though!
    (A)[28,12,45,4,24,35,47,2,9,14,25,31,42,46,48,0,3,8,11,13,20,null,26,30,33,41,43,null,null,null,49,null,1,null,null,7,null,10,null,null,null,17,22,null,27,29,null,32,34,36,null,null,44,null,null,null,null,6,null,null,null,16,18,21,23,null,null,null,null,null,null,null,null,null,37,null,null,5,null,15,null,null,19,null,null,null,null,null,40,null,null,null,null,null,null,39,null,38]
12
22
    (B)
    (C)
    (D)
    (E)

Root node handling
(1) The sentinel node trick : to the right ( sentinel val = -1 ) or to the left ( sentinel val > 10e4 ) 
(2) Node values also unique
(3) Partitioning : get the proper subroot -> then do a root-in-the-middle search


*/


class Solution 
{
    public TreeNode trimBST(TreeNode root, int low, int high) 
    {
        if(root == null)
        {
            return null;
        }
        TreeNode correctRoot = getRightRoot(root,low,high); // guaranteed to now be in the interval as well
        trimHelper(correctRoot,low,high);
        return correctRoot;
    }

    // Make sure to always ensure that guarantreed in interval property as awell
    private TreeNode trimHelper(TreeNode root, int low, int high)
    {
        if(root == null)
        {
            return null;
        }
        // Can do immediate settings here as well
        if(root.val == low)
        {
            root.left = null;
        }
        if ( root.val == high)
        {
            root.right = null;
        }
        TreeNode newLST = root.left;
        TreeNode newRST = root.right;
        // TLE either in recursion of while loops as well
        while(newLST != null)
        {
            if(newLST.val < low)
                newLST = newLST.right;
            else
                break;
        }
        // Oh second while loop can be causing a mess here
        while(newRST != null)
        {
            if(newRST.val > high)
                newRST = newRST.left;
            else
                break;
        }
        // Ok so the left and right should be correct here
        
        root.left = newLST;
        root.right = newRST;
        trimHelper(newLST, low, high);
        trimHelper(newRST, low, high);
        return root;
    }
    
    private TreeNode getRightRoot(TreeNode root, int low, int high)
    {
        while(root != null)
        {
            if(root.val < low)
                root = root.right;
            else if ( root.val > high)
                root = root.left;
            else
                break;
        }
        return root;
    }
        
    
    
    
    
    
}
