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
URL = https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
105. Construct Binary Tree from Preorder and Inorder Traversal

Base case here is just a single element

Strategies : Divide-and-Conquer, Recursive, Tree Traversal, Combinations Testing

Complexity
Let N := len(preorder)
Let H := height of the binary tree
Time = O(N^2) + O(N)
Space = O(N) ( implicit ) O(1) (explicit)

Let $ := worst-case processing of a tree
TEST BENCH
Y (A) preorder = [3], inorder = [3]
Y (B)        preorder = [3,9,20], inorder = [9,3,20]
Y (C) preorder = [1,2], inorder = [1,2] ( right-sided tree ) 
Y (D) preorder = [2,1], inorder = [1,2] ( left-sided tree ) 
Y $ (E) preorder = [1,2,3,4,5], inorder [1,2,3,4,5]
[1,null,2,null,3,null,4,null,5]
Y $ (F) preorder = [1,2,3,4,5], inorder [5,4,3,2,1]
    [1,2,null,3,null,4,null,5]
(G) The provided -> also passes

Values guaranteed uniqueness in traversals as well too!

No support for nested functions ( assists with scoping ) 


*/

class Solution 
{
    int readIdx = 0;
    int n = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) 
    {
        readIdx = 0;
        if(preorder == null || inorder == null)
            return null;
        else if ( preorder.length == 0 || inorder.length == 0)
        {
            return null;
        }
        n = preorder.length;
        int low = 0;
        int high = n-1;
        TreeNode res = helper(preorder,inorder, low, high);
        return res;
    }
    
    // Always increment the readIdx by 1, in the parent call ( before recursion ) 
    // Head recursive method as well
    // Return a NULL node if (low > high) as well
    public TreeNode helper(int[] preorder, int[] inorder, int low, int high)
    {
        if(low > high)
        {
            return null;
        }
        else
        {
            int subRootVal = preorder[readIdx];
            int lhs_low = low;
            int lhs_high = 0;
            int mid = 0;
            int rhs_low = 0;
            int rhs_high = high;
            // go over the inorder list here
            for(int i = low; i <= high; ++i)
            {
                if(inorder[i] == subRootVal)
                {
                    mid = i;
                    break;
                }
            }
            lhs_high = mid - 1;
            rhs_low = mid + 1;
            TreeNode root = new TreeNode(subRootVal);
            ++readIdx;
            TreeNode lst = helper(preorder, inorder, lhs_low, lhs_high);
            TreeNode rst = helper(preorder, inorder, rhs_low, rhs_high);
            root.left = lst;
            root.right = rst;
            return root;
        }
    }
    
    
}
