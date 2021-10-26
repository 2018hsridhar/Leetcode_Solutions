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

URL = https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
889. Construct Binary Tree from Preorder and Postorder Traversal

COMPLEXITY : 
Let N := len(preorder/postorder)
Let H := height of resultant binary tree ( log_2(N) in balanced case, N in worst case ) 
Time = 
Space = 

TEST BENCH : 
Y (A) preorder = [3], postorder = [3]



F (B)        preorder = [3,1,2], postorder = [1,2,3]



Y (C) preorder = [1,2], postorder = [2,1] ( right-sided tree ) 
Y (D) preorder = [2,1], postorder = [1,2] ( left-sided tree ) 

Y $ (E) preorder = [1,2,3,4,5], postorder [1,2,3,4,5]
[1,null,2,null,3,null,4,null,5]
Y $ (F) preorder = [1,2,3,4,5], postorder [5,4,3,2,1]
    [1,2,null,3,null,4,null,5]
(G) The provided -> also passes

*/

class Solution 
{
    int readIdx = 0;
    int n = 0;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) 
    {
        if(preorder == null || postorder == null)
            return null;
        if(preorder.length == 0 || postorder.length == 0)
            return null;

        // Len > 2 cases only here
        n = preorder.length;
        int low = 0;
        int high = n-1;
        TreeNode root = helper(preorder,postorder, low, high);
        return root;
    }
    
    
    // Ahhh I see ... we have to increment and search for our next root node for the dac algo
    // Always increment the readIdx by 1, in the parent call ( before recursion ) 
    // Head recursive method as well
    // Return a NULL node if (low > high) as well
    private TreeNode helper(int[] preorder, int[] postorder, int low, int high)
    {
        if(low > high)
        {
            return null;
        }
        // handle the single length case as well too
        else if ( readIdx == n-1)
        {
            return new TreeNode(preorder[readIdx]);
        }
        else if ( low == high)  // needs a base case handling as well too
        {
              ++readIdx;
              return new TreeNode(postorder[low]);
        }
        else
        {
            int subRootVal = preorder[readIdx];
            int nextPreOrderRoot = preorder[readIdx+1];

            int mid = 0;
            for(int i = low; i <= high; ++i)
            {
                if(postorder[i] == nextPreOrderRoot)
                {
                    mid = i;
                    break;
                }
            }
            
            // An inaccurate decrement operation here as well?
            int lhs_low = low;
            int lhs_high = mid;
            
            int rhs_low = mid + 1;
            int rhs_high = high-1;

            ++readIdx;
            TreeNode root = new TreeNode(subRootVal);
            TreeNode lst = helper(preorder, postorder, lhs_low, lhs_high);
            TreeNode rst = helper(preorder, postorder, rhs_low, rhs_high);
            root.left = lst;
            root.right = rst;
            return root;
        }
    }
    
}
