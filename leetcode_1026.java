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
N = 5K nodes max ( and 2 nodes guaranteed ) 
Nodes range from 0 to 10,000 only

URL = https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
1026. Maximum Difference Between Node and Ancestor
it is a binary tree -> not a bst
    a must be an ancestor of b too!
    may have to work pairwise here!
    Can we sort the results as we backtrack too?
    Ancestor property reigns -> why not a top->bottom approach too?
    . . . pass down sorted lists as we traverse?
    . . . can we track the min and maxes as we traverse down too ( as they lead to the hugest differences )?
    . . . given [min,max] diff, you can surpass only if newEl < min or newEl > max too
    . . . ensure to check for [ left, right ] nodes : if no existence -> return INT_MIN
    . . . ensure [min,max] always correspond to a node value too!
    . . . better : why not "psuedo" node the root with a min val too?
    
    

COMPLEXITY
Let N := #-nodes
Let H := height of the binary tree
TIME = O(N)
Space = O(H) ( IMP ) O(1) ( EXP )

*/

class Solution {
    public int maxAncestorDiff(TreeNode root) 
    {
        int maxDiff = helper(root,root.val,root.val);
        return maxDiff;
    }
    
    private int helper(TreeNode root, int curMin, int curMax)
    {
        int curMaxDiff = curMax - curMin;
        if(root.left != null)
        {
            int leftChildVal = root.left.val;
            if(leftChildVal < curMin)
            {
                curMaxDiff = Math.max(curMaxDiff, helper(root.left,leftChildVal,curMax));
            }
            else if ( leftChildVal > curMax )
            {
                curMaxDiff = Math.max(curMaxDiff, helper(root.left,curMin,leftChildVal));                
            }
            else
            {
                curMaxDiff = Math.max(curMaxDiff, helper(root.left,curMin,curMax));                
            }
        }
        if(root.right != null)
        {
            int rightChildVal = root.right.val;
            if(rightChildVal < curMin)
            {
                curMaxDiff = Math.max(curMaxDiff, helper(root.right,rightChildVal,curMax));
            }
            else if ( rightChildVal > curMax )
            {
                curMaxDiff = Math.max(curMaxDiff, helper(root.right,curMin,rightChildVal));                
            }
            else 
            {
              curMaxDiff = Math.max(curMaxDiff, helper(root.right,curMin,curMax));                               
            }
        }
        return curMaxDiff;
    }
    
}



