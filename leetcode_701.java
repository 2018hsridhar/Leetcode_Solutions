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

Given the root node of a BST
Also a value to insert into this BST
Return root node AFTER insertion
Guaranteed that value DNE in the original BST
    -> no need to worry about duplicate insertion as well

Note that BST still maintains its BST property
Return ANY valid insertion as well
Assume BST lacks duplicate values as well. That is, given a node x
    values of nodes in LST are <, x
    values of nodes in RST are >, x

Insertion tends to be logarithmic
We may perform rebalancing operations or not ( it depends ) 

The element inserted is either going to be a new LEAF node
or a new INTERMEDIARY node. Latter case is harder to reason about
Since value DNE -> there must exist a max low bound and a min upper bound as well ( e.g. for 5, we have {4,6}, {4,7}, {3,7} to suffice as example bounds )

Complexity
Let N := #-nodes in the BST
Let H := height of the BST := log_2(N)
Time = O(H)
Space = O(1) => explicit && O(H) => implicit ( unless done iteratively -> O(1) )

Edge Case Testing : 
(A) [] val = 5
    [5]
(B) [1] val = 5
    [1,5]
(C) [5] val = 2
    [2,5]
(D) [4,2,7] val = -1
    [4,2,7,-1]
(E) [4,2,7] val = 8
    [4,2,7,null,null,null,8]
(F) [4,2,7] val = 3
    [4,2,7,null,3]
    [4,3,7,2]

(G)
[8,null,55,39,null,11,null,null,23,null,null]
17
-> failing test case. Woah!

*/

class Solution 
{
    public TreeNode insertIntoBST(TreeNode root, int val) 
    {
        TreeNode head = root;
        TreeNode cur = root;
        if(cur == null)
            return new TreeNode(val);
        // Other logic sure is more human readable
        // From now on, you do if-elseif-else logic : not if-if weird logic. Something about missing case handling here for sure ( with the catch all 0 )
        while((cur.left != null || cur.right != null))
        {
            if(val > cur.val)
            {
                if(cur.right != null)
                    cur = cur.right;
                else
                    break;
            }
            if(val < cur.val)
            {
                if(cur.left != null)
                    cur = cur.left;
                else
                    break;
            }
        }
        // Now perform insertion
        if(val > cur.val)
            cur.right = new TreeNode(val);
        else
            cur.left = new TreeNode(val);
            
        return head;
    }
}
