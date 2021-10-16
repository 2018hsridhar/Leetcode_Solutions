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

Nodes values reasonably bounded by [-3*10e4, 3*10e4] and are in closed interval of [1,e*10e4]
Def seems like a problem doable via DP/backtracking
Longest consecutive sequence path : must be from parent->child
Parent-child connections strikes again
Default value of 1 here

URL = https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/

Complexity : 
N := number nodes in the binary tree
H := height of the tree ( log_2(n) balanced, n unbalanced ) 
Time = O(N^2) or O(N) if more ideal
        O(N) function calls to dfs for sure
        O(1) operations per function call
Space = O(1) (explicit) O(H) implicit
    
Careful - BT lacks the BST property here for sure

Pseudocode : 

dfs( TreeNode root ) :
    int[] lcs = {0,0,0};
    if(root == null)
        return lcs; 

    int[] lhs = {0,0,0};
    int[] rhs = {0,0,0};
    int root_max_diam = 1;
    int root_incr_len = 1;
    int root_decr_len = 1;
    if(root.left != null && root.right != null) // have both leaves here
    {
        lhs = dfs(root.left);
        rhs = dfs(root.right);
        if(root.val == root.left.val - 1 && root.val == root.right.val + 1) // decr left, incr right
        {
            root_incr_len = 1 + rhs[1];
            root_decr_len = 1 + lhs[0];
            root_max_diam = 1 + lhs[0] + rhs[1];
        }
        if(root.val == root.left.val + 1 && root.val == root.right.val - 1) // decr right, incr left
        {
            root_incr_len = 1 + lhs[1];
            root_decr_len = 1 + rhs[0];
            root_max_diam = 1 + lhs[1] + rhs[0];
        }
    }
    if(root.left != null )
    {
        lhs = dfs(root.left);
        if(root.val == root.left.val - 1)
        {
            root_max_diam = lhs[0] + 1;
            root_decr_len = 1 + lhs[0];
        }
        if(root.val == root.left.val + 1)
        {
            root_max_diam = lhs[1] + 1;
            root_incr_len = 1 + lhs[0];
        }
    }
    if(root.right != null)
    {
        rhs = dfs(root.right);
        if(root.val == root.right.val - 1)
        {
            root_max_diam = rhs[0] + 1;
            root_decr_len = 1 + rhs[0];
        }
        if(root.val == root.right.val + 1)
        {
            root_max_diam = rhs[1] + 1;
            root_incr_len = 1 + rhs[0];
        }
    }
    lcs[0] = root_decr_len;
    lcs[1] = root_incr_len;
    lcs[2] = Math.max(Math.max(lhs[2],rhs[2]),root_max_diam);
    return lcs;
    
    
Tabulation [ For test case = (K) ]
NodeID     incr_len     decr_len        max_diam
v1          1               1               1
v2          2               1               2
v3          3               4               6                   **8
v4          1               3               3
v5          1               2               2
v6          1               1               1
v7          1               2               2
    
Edge Case Testing : 
(A) [4,null,3,2,2,null,null,null,1]
    4
(B) [1,null,2,3,3,null,null,null,4]
    4
(C) [1,null,1,1,1,null,null,null,1]
    1
(D) [11,10,null,9,null,9,null,10,10,11,null,null,11,12]
    4
(E) [1,null,3,5,4,7,null,null,5]
    3
(F) [1]
    1
(G) [1,-1,-1]
    1
(H) [1,0,2]
    3
(I) [1,2,2]
    2
(J) [1,2,3]
    2
(K) [10,11,null,12,10,8,null,null,9,9,7,null,null,10,null,null,6,null,null,5]
    6
    
*/

class Solution 
{
    // All functions, including main, need a return ... so that we may pop functions OFF the func call stack
    public int longestConsecutive(TreeNode root) 
    {
        if(root == null)
            return 0;
        int[] res = dfs(root);
        return res[2];
    }
  
    // Use a wrapper class or a pair<int,int> or int[] array for the return as well here
    private int[] dfs(TreeNode root)
    {
        int[] lcs = new int[]{0,0,0};
        if(root == null)
            return lcs; 

        int[] lhs = new int[]{0,0,0};
        int[] rhs = new int[]{0,0,0};
        int root_max_diam = 1;
        int root_incr_len = 1;
        int root_decr_len = 1;
        if(root.left != null )
            lhs = dfs(root.left);
        if(root.right != null)
            rhs = dfs(root.right);
        if(root.left != null && root.right != null) // have both leaves here
        {
            if(root.val == root.left.val - 1 && root.val == root.right.val + 1) // decr left, incr right
            {
                root_incr_len = 1 + rhs[1];
                root_decr_len = 1 + lhs[0];
                root_max_diam = lhs[0] + rhs[1] + 1;
            }
            if(root.val == root.left.val + 1 && root.val == root.right.val - 1) // decr right, incr left
            {
                root_incr_len = 1 + lhs[1];
                root_decr_len = 1 + rhs[0];
                root_max_diam = lhs[1] + rhs[0] + 1;
            }
            // mess up here : what if we can go either way now!
        }
        if(root.left != null )
        {
            if(root.val == root.left.val - 1)
            {
                root_max_diam = Math.max(root_max_diam,lhs[0] + 1);
                root_decr_len = Math.max(root_decr_len,1 + lhs[0]);
            }
            if(root.val == root.left.val + 1)
            {
                root_max_diam = Math.max(root_max_diam,lhs[1] + 1);
                root_incr_len = Math.max(root_incr_len,1 + lhs[1]);
            }
        }
        if(root.right != null)
        {
            if(root.val == root.right.val - 1)
            {
                root_max_diam = Math.max(root_max_diam, rhs[0] + 1);
                root_decr_len = Math.max(root_decr_len, 1 + rhs[0]);
            }
            if(root.val == root.right.val + 1)
            {
                root_max_diam = Math.max(root_max_diam,rhs[1] + 1);
                root_incr_len = Math.max(root_incr_len,1 + rhs[1]);
            }
        }
        lcs[0] = root_decr_len;
        lcs[1] = root_incr_len;
        lcs[2] = Math.max(Math.max(lhs[2],rhs[2]),root_max_diam);
        // System.out.printf("@ node = %d\t decr_len = %d \t incr_len = %d \t diam = %d\n", root.val, lcs[0], lcs[1], lcs[2]);
        return lcs;
    }    
}
