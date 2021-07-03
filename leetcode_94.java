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

94. Binary Tree Inorder Traversal
https://leetcode.com/problems/binary-tree-inorder-traversal/

Always ask if a given binary tree is a binary search tree ( to optimize performance time )
- In this case, it isn't ( otherwise, node_3, in the example figure on the left, would be a right child of node 2 ) 
- Terminate recursion when TreeNode root = null here
- Iterative approaches need a data structure , to check if size=0, to entail a proper termination
- The singleton node and a leaf node are exactly the same type of processing anyways
- Are the values of the Bianry Tree unique or not?
- Can have a [0] node case too! ( but up to a 100 nodes - max depth level of stack ) 
- Space is better intuitied as the maximum depth a stack/support data structrue can go up to within an iterative method



Ideal performance time ( iteraive, using space ) 
Time = O(N) : visited nodes are not added again -> only new needs in each iteration!
Space = O(N)
N := total number of nodes contained in our tree

*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) 
    {
        LinkedList<Integer> traversal = new LinkedList<Integer>();
        if(root == null)
            return traversal;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.add(root);
        while(!stk.isEmpty())
        {
            TreeNode candidate = stk.pop();
            if(candidate.left == null && candidate.right == null)
            {
                traversal.addLast(candidate.val);
            }
            else if ( candidate.left == null && candidate.right != null)
            {
                stk.add(candidate.right);
                candidate.right = null;
                stk.add(candidate);
            }
            else if ( candidate.left != null && candidate.right == null)
            {
                stk.add(candidate);
                stk.add(candidate.left);
                candidate.left = null;
            }
            else
            {
                stk.add(candidate.right);
                stk.add(candidate);
                stk.add(candidate.left);
                candidate.left = null;
                candidate.right = null;
            }
        }
        
        return traversal;
    }
}
