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
Add a ROW of nodes with value=val @ the given depth
Root node is always at depth = 1
Create two tree nodes for each treenode cur @ depth = (depth - 1 )
    -> a new lst and rst
Preserve the original lst and rst : just an append operation honestly
Handle special case of depth = 1
Seperate out case where the newly aded nodes are either new leaves, or new internal nodes, as well!

URL = https://leetcode.com/problems/add-one-row-to-tree/submissions/
623. Add One Row to Tree


COMPLEXITY
Let N := total number of nodes
Let H := height of the tree ( log_2(N) balanced, (N) worst ) 
Time = O(N)
Space = O(H) ( implicit ) O(1) explicit

TEST BENCH ::
(A) root = [4,2,6,3,1,5]    val = 1     depth = 2

(B) root = [4]    val = 1     depth = 1
[1,4]

(C) root = [4,2,6,3,1,5]    val=1   depth=1
[1,4,null,2,6,3,1,5]

(D) (*** complete tree ***)

(E) ( *** mixed internal *** ) 

(F) (*** max depth test *** )

(G) [4,2,6] val = 1 depth = 2, 3 passes too


*/

class Solution 
{
    public TreeNode addOneRow(TreeNode root, int val, int depth) 
    {
        TreeNode novel = new TreeNode(val);
        if(root == null)
        {
            return novel;
        }
        if(depth == 1)
        {
            novel.left = root;
            return novel;
        }
        else
        {
            helper(root,val,1,depth);
        }
        return root;
    }
    
    // Root,left,right traversal ( top-down / head-recursive ) 
    private void helper(TreeNode root, int val, int curDepth, int targetDepth)
    {
        if(root == null)
        {
            return;
        }
        else
        {
            // Akin to SLL addition honestly
            if(curDepth + 1 == targetDepth)
            {
                TreeNode curLST = root.left;
                TreeNode curRST = root.right;
                TreeNode newLST = new TreeNode(val);
                TreeNode newRST = new TreeNode(val);
                root.left = newLST;
                root.right = newRST;
                newLST.left = curLST;
                newRST.right = curRST;
                return;
            }
            else
            {
                if(root.left != null)
                    helper(root.left, val, curDepth + 1, targetDepth);
                if(root.right != null)
                    helper(root.right, val, curDepth + 1, targetDepth);
            }
        }
    }
}
