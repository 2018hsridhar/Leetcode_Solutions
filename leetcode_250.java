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

THOUGHT PROCESSES : 
- As usual, aim for a backtracking/backpropagation solution here
- Typical problems entail child node cases : leaves, single children, dual children nodes ( for intermediaries ) 
- Check for null case too in the recursive calls
- Only need to check for isUnivalue subtree : if it is, increment a global count ( or its address ) outside the scope of the recursive call stack as well

COMPLEXITY
TIME = O(N)
SPACE = O(H) where H := height of the binary tree ( log_2(N) balanced, (N) if skewed ) 

TEST CASES
(A) []
( *** 4 for the children cases *** ) 
(B) [1]
    => 1
(C) [1,1,null]
    => 2
(D) [1,null,1]
    => 2
(E) [1,1,1]
    => 3
( *** complexity *** ) 
(F) [1,2,3]
    => 2 ( just the leaves here ) 
(G) [1,null,1,null,1,null,1]
    => 4 ( skewed tree ) 


Forgot to read constraints : number nodes not too high
1000 * (1000) or 1000(-1000) = [-1e6,1e6] : no signed INT data overflow runtime exception 
)
*/

class Solution 
{
    int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) 
    {
        if(root == null)
        {
            return 0;
        }
        isUnivalSubTree(root);
        return count;
    }
    
    private boolean isUnivalSubTree(TreeNode root)
    {
        boolean status = false;
        if(root.left == null && root.right == null)
        {
            count++;
            status = true;
        }
        else if ( root.left != null && root.right == null)
        {
            boolean leftIsUnival = isUnivalSubTree(root.left);
            if(leftIsUnival && root.val == root.left.val)
            {
                count++;
                status = true;
            }
            else
            {
                status = false;
            }            
        }
        else if ( root.left == null && root.right != null)
        {
            boolean rightIsUnival = isUnivalSubTree(root.right);
            if(rightIsUnival && root.val == root.right.val)
            {
                count++;
                status = true;
            }
            else
            {
                status = false;
            }
        }
        else if ( root.left != null && root.right != null )
        {
            boolean leftIsUnival = isUnivalSubTree(root.left);
            boolean rightIsUnival = isUnivalSubTree(root.right);
            if(leftIsUnival && rightIsUnival && root.val == root.left.val && root.val == root.right.val)
            {
                count++;
                status = true;
            }
            else
            {
                status = false;
            }
            
        }
        return status;
    }
    
}
