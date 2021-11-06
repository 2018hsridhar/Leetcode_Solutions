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
Did this problem a while ago on the Interviewing.IO platform

Strategies : Recursion, Backtracking, DP
It be a set partition problem in the hiding as well

Step 1 : get total sum of tree
Step 2 : get subtree sums and check if a subtree sum could equal sum / 2



COMPLEXITY
Let N := #-nodes
Let H := height of tree ( log(n) balanced, (n) worst skew ) 
Time = O(N)
Space = O(H) ( implicit ) O(1) explicit

TEST BENCH
(A) [2] ( *** single root node should always fail *** ) 
    FALSE
(B) [2,2]
    TRUE
(C) [2,4]
(D) [2,3]
    FALSE
(E) [2,1,3]
    TRUE ( valid single leaf ) 
(F) [1,2,null,3,null,6]
    TRUE
    
*/

class Solution 
{
    public boolean checkEqualTree(TreeNode root) 
    {
        boolean hasPartition = false;
        int treeSum = getSum(root);
        // System.out.printf("%d\n", treeSum % 2);
        if(treeSum % 2 == 1 || treeSum % 2 == -1) // for odd cases
        {
            return false; // must be even anyways
        }
        int halfSum = treeSum / 2;
        // System.out.printf("whole sum = %d \t half sum = %d\n", treeSum, halfSum);
        hasPartition = checkHalfSumProperty(root, halfSum, root);
        return hasPartition;
    }
    
    // In the two leaf case, you'd check both ways anyways
    private boolean checkHalfSumProperty(TreeNode root, int halfSum, TreeNode head)
    {
        boolean status = false;
        if(root == null)
        {
            status = false; // no modification here anyways
        }
        else if(root.left == null && root.right == null)
        {
            if(root != head)
            {
                return (root.val == halfSum);
            }
            else
                return false;
        }
        else
        {
            if(root.left != null)
            {
                boolean lhs = checkHalfSumProperty(root.left, halfSum, root);
                if(lhs == true) status = true;
                root.val += root.left.val;
            }
            if(root.right != null)
            {
                boolean rhs = checkHalfSumProperty(root.right, halfSum, root);
                if(rhs == true) status = true;
                root.val += root.right.val;
            }
            if(root.val == halfSum && root != head) // oh we need to account for the negative cases as well ( we just did strictly positive cases )
            {
                status = true;
            }
        }
        return status;
    }
    
    private int getSum(TreeNode root)
    {
        int sum = 0;
        if(root.left != null)
        {
            sum += getSum(root.left);
        }
        if(root.right != null)
        {
            sum += getSum(root.right);
        }
        sum += root.val;
        return sum;
    }
    
}
