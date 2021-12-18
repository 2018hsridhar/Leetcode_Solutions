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

URL = https://leetcode.com/problems/count-nodes-equal-to-sum-of-descendants/
1973. Count Nodes Equal to Sum of Descendants

Propagate the values upwards here as well
Sum of the values of descendants ( any node on path from node x to a leaf node )
Sum = 0 if node has NO descendants ( handle the 0 node case as well ) 
Yes handle the 0 node value case too!

Node  vals can be in range [0,10^5]
100,000 : an O(N^2) Quadratic time algorithm is fine here, but do NOT veer worse ( e.g. cubic time ) 
All valid trees -> no null inputs or empty trees here
Handle duplicate values case too

COMPLEXITY
Let N := #-nodes
Let H := height of tree ( log_2(N) balanced, (N) worst ) 
TIME = O(N)
SPACE = O(H)

TEST CASES
(A) [10]
    0
(B) [0]
    1
(C) [8,4,null,2,null,1,null,1,null]
    => 4
(D) [4,2,2]
(E) [8,2,1,1,2,1,1]
(F)

Strategies : Recursive, Bottom-up Propogation

*/

class Solution 
{
    public int equalToDescendants(TreeNode root) 
    {
        int[] A = new int[]{0};    
        solveForCount(root, A);
        int count = A[0];
        return count;
    }
    
    // Optimize stack frames by NOT passing them in when we point to null as well
    private int solveForCount(TreeNode root, int[] A)
    {
        int subtreeSums = 0;
        if(root.left != null)
        {
            subtreeSums += solveForCount(root.left, A);
        }
        if(root.right != null)
        {
            subtreeSums += solveForCount(root.right, A);
        }
        if(root.val == subtreeSums)
        {
            A[0]++;
        }
        subtreeSums += root.val;
        return subtreeSums;
    }
    
    
    
}
