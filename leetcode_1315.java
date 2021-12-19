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
Problem is far far easier than you think
Very easily expressible as recursion
Only a few select operations needed here as well

URL = https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
1315. Sum of Nodes with Even-Valued Grandparent

*/

class Solution {
    public int sumEvenGrandparent(TreeNode root)
    {
        return recurse(root);
    }
    
    private int recurse(TreeNode root)
    {
        int sumGrandKids = 0;
        if(root == null)
        {
            return 0;
        }
        if(root.val % 2 == 0)
        {
            TreeNode lst = root.left;
            TreeNode rst = root.right;
            if(lst != null)
            {
                if(lst.left != null)
                {
                    sumGrandKids += lst.left.val;
                }
                if(lst.right != null)
                {
                    sumGrandKids += lst.right.val;
                }
            }
            if(rst != null)
            {
                if(rst.left != null)
                {
                    sumGrandKids += rst.left.val;
                }
                if(rst.right != null)
                {
                    sumGrandKids += rst.right.val;
                }
            }
        }
        sumGrandKids += recurse(root.left);
        sumGrandKids += recurse(root.right);
        return sumGrandKids;
    }
}
