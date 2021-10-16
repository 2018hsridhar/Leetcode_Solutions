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
THOUGHT PROCESS : 

Def seems like a problem doable via DP/backtracking
Longest consecutive sequence path : must be from parent->child
Parent-child connections strikes again
Default value of 1 here
Both post-order traversals AND tail recursion tend to show up in Divide-and-Conquer algorithms

DFS is doable, but def takes quadratic POLY time here for the visitation ( O(N^2) time)

URL = https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/

Complexity : 
N := number nodes in the binary tree
H := height of the tree ( log_2(n) balanced, n unbalanced ) 
Time = O(N^2) or O(N) if more ideal
        O(N) function calls to dfs for sure
        O(1) operations per function call
Space = O(1) (explicit) O(H) implicit
    
Careful - BT lacks the BST property here for sure

Pseudocode : 

int glbl_max = 1;

longestConsecutive( TreeNode root ) :
    if(root == null)
        return 0; 
    int lcs = 1;
    int lhs_seq_len = 1;
    int rhs_seq_len = 1;
    if(root.left != null )
    {
        lhs = longestConsecutive(root.left);
        if(root.val == root.left.val - 1)
            lcs = lhs + 1;
    }
    if(root.right != null)
    {
        rhs = longestConsecutive(root.right);
        if(root.val == root.right.val - 1)
            lcs = Math.max(lcs, rhs + 1);
    }
    glbl_max = (Math.max(lhs,rhs),lcs);
    return lcs;
    
    
Edge Case Testing : 
(A) [4,null,3,2,2,null,null,null,1]
    1
(B) [1,null,2,3,3,null,null,null,4]
    4
(C) [1,null,1,1,1,null,null,null,1]
    1
(D) [11,10,null,9,null,9,null,10,10,11,null,null,11,12]
    4
    FAILING TEST CASE ... AMEND IT!
(E) [1,null,3,5,4,7,null,null,5]
    3 ( consecutive must be continuity ... no consecutive gaps )
(F) [1]
    1
(G) [1,-1,-1]
    1
(H) [1,-1,3]
    2
(I) [1,2,2]
    2
(J) [1,2,3]
    2
    
Preference for wrappers OVER globals in recursive functions as well

*/

class Solution 
{
    public int longestConsecutive(TreeNode root) 
    {
        if(root == null)
            return 0;
        int[] res = budp(root);
        return res[0];
    }
    
    // Use a wrapper class or a pair<int,int> or int[] array for the return as well here
    private int[] budp(TreeNode root)
    {
        int[] res = new int[]{0,0};
        if(root == null)
            return res; 

        int lcs = 1;
        int[] lhs = new int[]{0,0};
        int[] rhs = new int[]{0,0};
        if(root.left != null )
        {
            int[] left = budp(root.left);
            lhs[0] = left[0];
            lhs[1] = left[1];
            if(root.val == root.left.val - 1)
                lcs = Math.max(lcs,lhs[1] + 1);
        }
        if(root.right != null)
        {
            int[] right = budp(root.right);
            rhs[0] = right[0];
            rhs[1] = right[1];
            if(root.val == root.right.val - 1)
                lcs = Math.max(lcs, rhs[1] + 1);
        }
        res[0] = Math.max(Math.max(lhs[0],rhs[0]),lcs);
        res[1] = lcs;
        return res;
    }
}
