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
1372. Longest ZigZag Path in a Binary Tree
URL = https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/

Can be agnostic to node values here.
Number of nodes bounded : [1,50,000] : not unreasonable
Bound to values : [0,H] for a zigzag path

Complexity
Let N := number of nodes
Let H := height of tree ( log_2(N) balanced, N skewed/worst ) 
TIME = O(N)
SPACE = O(H) ( call stack ) O(1) ( explicit ) 

Test Cases
(A) [] -> 0
(B) [1,1,null] -> 1
(C) [1,null,1] -> 1
(D) [1,1,1] -> 1
(E) [1,1,1,1,1,null,null] -> 2
    ^ amend this test case


*/

// Safe initializations desireable too!
class Info
{
    public boolean startedLeftZigZag;
    public boolean startedRightZigZag;
    public int lengthZigZagLeft;
    public int lengthZigZagRight;
    public int maxLen;
    
    public Info()
    {
        startedLeftZigZag = false;
        startedRightZigZag = false;
        lengthZigZagLeft = 0;
        lengthZigZagRight = 0;
        maxLen = 0;
    }
    
    public Info(boolean slzz, boolean srzz, int lzzl, int lzzr, int mL)
    {
        startedLeftZigZag = slzz;
        startedRightZigZag = srzz;
        lengthZigZagLeft = lzzl;
        lengthZigZagRight = lzzr;
        maxLen = mL;
    }
    
    
}

class Solution {
    
    public int longestZigZag(TreeNode root) 
    {
        int longestPath = 0;
        Info results = helper(root);
        longestPath = results.maxLen;
        return longestPath - 1;
    }
    
    public Info helper(TreeNode root)
    {
        if(root == null)
        {
            return new Info();
        }
        else if ( root.left == null && root.right == null ) {
            return new Info(true,true,1,1,1); // all leaf nodes carry this structure.
        }
        else
        {
            Info cni = new Info(true,true,1,1,1); // assume internal nodes can start zig zags too!
            TreeNode lst = root.left;
            TreeNode rst = root.right;
            if(lst != null)
            {
                cni.startedLeftZigZag = true;
                Info leftInf = helper(lst);
                if(leftInf.startedRightZigZag)
                {
                    cni.lengthZigZagLeft = leftInf.lengthZigZagRight + 1;
                    cni.maxLen = Math.max(cni.maxLen, Math.max(leftInf.maxLen,cni.lengthZigZagLeft));
                }
            }
            if(rst != null)
            {
                cni.startedRightZigZag = true;
                Info rightInf = helper(rst);
                if(rightInf.startedLeftZigZag)
                {
                    cni.lengthZigZagRight = rightInf.lengthZigZagLeft + 1;
                    cni.maxLen = Math.max(cni.maxLen, Math.max(rightInf.maxLen,cni.lengthZigZagRight));
                }
            }
            return cni;
        }
    }
}

//     boolean startedLeftZigZag;
//     boolean startedRightZigZag;
//     int lengthZigZagLeft;
//     int lengthZigZagRight;
//     int maxLen;
    
