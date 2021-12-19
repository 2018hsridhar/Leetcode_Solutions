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

URL = https://leetcode.com/problems/maximum-average-subtree/
1120. Maximum Average Subtree

Floating-point error of margin is permissible here. Do not get histrionic about that

COMPLEXITY
Let N := #-nodes in the tree, H := height of the tree ( log_2(N) balanced, (N) skewed ) 
Time = O(N)
Space = O(H) ( imp -> recursvie ) O(1) ( exp ) 

TEST CASES
(A) [1,2,1,4,3]
(B) [2,6,3,null,5,12,7,null,null,0,4,null,10,null,null,11,null,null,9,null,1]
    => is correct
(C)
(D)

*/

class Solution
{
    public double maximumAverageSubtree(TreeNode root) 
    {
        double[] maxAvg = new double[]{Double.MIN_VALUE};
        recurse(root, maxAvg);
        return maxAvg[0];
    }
    
    public int recurse(TreeNode root, double[] maxAvg)
    {
        int numNodesInTree = 1;
        double newSum = 0;
        if(root.left != null)   // Confirm your write operations ordering!
        {
            numNodesInTree += recurse(root.left, maxAvg);
            newSum += (double)root.left.val; 
        }
        if(root.right != null)
        {
            numNodesInTree += recurse(root.right, maxAvg);
            newSum += (double)root.right.val;
        }
        newSum += root.val;
        double treeAvg = (double)(newSum / numNodesInTree);
        root.val = (int)newSum;
        if(treeAvg > maxAvg[0])
        {
            maxAvg[0] = treeAvg;
        }
        return numNodesInTree;
    }
    
}

