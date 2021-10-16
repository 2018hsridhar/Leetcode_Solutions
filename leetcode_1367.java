/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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

Idea 1 : We may need to search all nodes, in case the SLL is equal to just one node, and all node values are unique as well ( we know addresses are already unique ) 

URL = https://leetcode.com/problems/linked-list-in-binary-tree/
1367. Linked List in Binary Tree

Complexity
N := number nodes in the binary tree
H := height of the binary tree ( log_2(n) balanced case, n worst case ) 
Time = O(N)
Space
    Explicit = O(1)
    Implicit = O(H) ( recursive ) 

Edge Case Testing : 
(A) [4,2,8]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
path starts at intermediary
(B) [1], [3], [2]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
( root, leaf, intermediary ) testing
(C) [1,4,2,8,3]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
path starts at root
(D) [4,2,8,3]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
path terminates at a leaf
(E)[1,4,2,8,3]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
path starts @ root, ends @ leaf
(E)[4,2,8,3,5]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
path starts @ root, ends @ leaf, but shoudl still keep going here ( list != null, root == null ) 
(F)[1,4,2,3], [1,4,5]
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]

Inputs provided are actualy level order traersals ( base off the laf nodes as well too ) 
Do not just think of "success" cases ... also think of "failing" cases


Missed out this test case earlier : 

[1,10]
[1,null,1,10,1,9]

*/

class Solution 
{
    public boolean isSubPath(ListNode head, TreeNode root) 
    {
        // [1] Handling of base cases
        if(head == null)
            return true;
        if(root == null && head != null)
            return false;
        if(root.left == null && root.right == null && root.val == head.val)
            return true;
        return helper(head,root);
    }
    
    // Modularize and nest our recursive calls here as well
    private boolean helper(ListNode head, TreeNode root)
    {
        if(root == null)
            return false;
        if(root != null)
        {
            boolean lhs = false;
            boolean rhs = false;
            
            // Better : post-order traversal : left->root->right
            if(root.left != null)
                lhs = helper(head,root.left);
            if(root.right != null)
                rhs = helper(head,root.right);
            if (( lhs || rhs )== true )
                return true;
            else
            {
                if(root.val == head.val)
                    if(dfs(head, root) == true)
                      return true;
                    else
                        return false;
            }
        }
        return false;
    }
    
    private boolean dfs(ListNode head, TreeNode root)
    {
        if(head == null)
            return true;
        // if((head == null && root != null) || (head == null && root == null))
            // return true;
        else if ( root == null)
            return false;
        else if(root.val == head.val)
        {
            ListNode next = head.next;
            boolean lhs = dfs(next, root.left);
            boolean rhs = dfs(next, root.right);
            return (lhs || rhs );
        }
        return false;
    }
    
    
    
}
