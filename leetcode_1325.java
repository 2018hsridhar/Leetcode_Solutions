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
Is for sure a recursive backtracking algorithm
Strongly consider DFS to work its way along each unique path downstream

URL = https://leetcode.com/problems/delete-leaves-with-a-given-value/

Complexity
Let N := number nodes
Let H := tree height ( log_2(n) best, n worst )
Time = O(N)
Space = O(H) (implicit), O(1) (explicit)

Delte all the leaf nodes and then check if parent is a leaf again
caller-callee syntax

Edge Case Testing
(A) [1,1,1] target = 1
    []
(B) [1,2,3] target = 1
    [1,2,3]
(C) [3,1,null,1,null,1,null] target = 1
    [3]
(D)[1,2,2,4,4,4,4], 4
    [1,2,2]
(E) [1,4,4,4,4,4,4], 4
    [1]
(F) [4,4,4,4,4,4,4], 4
    []
 
Yes, [] also corresponds to NULL here
Reasonable # nodes here
Target also bounded as well in closed interval [1,1000] within values defined per node

You def initially overcomplicated the problem-solving here

*/

class Solution 
{
    public TreeNode removeLeafNodes(TreeNode root, int target) 
    {
        // Handling of the typical base cases as well
        if(root == null)
            return null;
        // Non-base cases ( we have at least one child node / more than 1 level ) 
        dfs(root,target);
        // check leaf case one last time
        if((root.left == null && root.right == null) && root.val == target)
            return null;
        return root;
    }
    
    // Yes we have Kahn's TOP-SORT :: let us avoid this for sure
    
    private boolean dfs(TreeNode root, int target)
    {
        if(root == null)
            return true;    // null nodes always true anyways
        
        if(root.left == null && root.right == null)
        {
            if(root.val == target)
                return true;
            else
                return false;
        }
        else
        {
            // Need to recurse -> call helper before hand to clear up left sub tree and right subtrees
            boolean lst_met = dfs(root.left,target);
            boolean rst_met = dfs(root.right,target);
            
            if(lst_met)
                root.left = null;
            
            if(rst_met)
                root.right = null;
            
            if(root.left == null && root.right == null)
            {
                if(root.val == target)
                    return true;
            }
         }
         return false;
    }
    
}
