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

URL = https://leetcode.com/problems/boundary-of-binary-tree/
545. Boundary of Binary Tree

root != a leaf in this boudnary
    leaves = childless nodes ( w/exception of the root case ) 
    
Boudnary of a binary tree = a concatenation operation
    No speciification on order or return -> just the values
    We may have duplicate values -> should NOT provde difficult to handle
    Could use a set or a dynamic array to denote TreeNodes visited or node ( addrs unique ) 
    Then convert TreeNodes -> list(values)
    Root, left boudnary, leaves ( left->right : preorder/postorder ) , reverse ( right boundary ) 
    
COMPLEXITY : 
Let N := number nodes
Let H := height ( log_2(n) balanced, (n) unbalanced ) 
Time = 
Space = 


TEST BENCH
Let P := provided
(A) [1]
P (B) [1,null,2,3,4]
P (C) [1,2,3,4,5,6,null,null,null,7,8,9,10]
(D) [1,2,3,4,5,6,7,null,null,null,8,null,9,null,null,10,null,11]
        [1,2,4,10,11,7,3]
        [8,9] are NOT a part of the boundary, according to specified conditions here
(E)
(F)
(G)
   

*/

class Solution 
{
    // Let us have this be list concatenation as well!
    // O(1) time, O(1) exp-imp space
    public List<Integer> boundaryOfBinaryTree(TreeNode root) 
    {
        List<Integer> boundary = new ArrayList<Integer>();
        if(root == null)
            return boundary;
        boundary.add(root.val); // default added
        if(root.left != null)
        {
            List<Integer> leftBoundary = getLeftBoundary(root);
            boundary.addAll(leftBoundary);  // use addAll when dealing with iterable data structures / list interfaces as well
        }
        if(root.left != null || root.right != null)
        {
            List<Integer> leaves = getLeaves(root);
            boundary.addAll(leaves);
        }
        if(root.right != null)
        {
            List<Integer> rightBoundary = getRightBoundary(root);
            boundary.addAll(rightBoundary);
        }
        return boundary;
    }
    
    // Let us establish the left boudnary and right boundaries iteratively instead
    // O(H) time, O(1) call stk space, O(1) explicit
    private List<Integer> getLeftBoundary(TreeNode root)
    {
        List<Integer> leftBoundary = new ArrayList<Integer>();
        while(root != null)
        {
            TreeNode lst = root.left;
            TreeNode rst = root.right;
            if(lst != null)
            {
                if(lst.left != null || lst.right != null)
                {
                    leftBoundary.add(lst.val);
                    root = lst;
                }
                else
                {
                    break;
                }
            }
            else if ( rst != null)   // make this an else if : not a seperate IF ( will break logic ) 
            {
                if(rst.left != null || rst.right != null)
                {
                    leftBoundary.add(rst.val);
                    root = rst;
                }
                else
                {
                    break;
                }
            }
        }
        return leftBoundary;
    }

    // O(H) time, O(1) call stk space, O(1) explicit
    private List<Integer> getRightBoundary(TreeNode root)
    {
        List<Integer> rightBoundary = new ArrayList<Integer>();
        while(root != null)
        {
            TreeNode lst = root.left;
            TreeNode rst = root.right;
            if ( root.right != null)   // make this an else if : not a seperate IF ( will break logic ) 
            {
                if(rst.left != null || rst.right != null)
                {
                    rightBoundary.add(0,rst.val);
                    root = rst;                
                }
                else
                    break;
            }
            else if(root.left != null)
            {
                if(lst.left != null || lst.right != null)
                {
                    rightBoundary.add(0,lst.val); // can we change direction here?
                    root = lst;
                }
                else
                    break;
            }
        }
        return rightBoundary;
    }
    
    // O(N) time, O(N) call stk space, O(1) explicit ( may end up visiting all ndoes in case of a perfect tree ) 
    // Recurse here ( iterative approach for both nodes needs a queue or a stack : does not do d-&-c in just one level ) 
    private List<Integer> getLeaves(TreeNode root)
    {
        // System.out.printf("Root.val = %d\n", root.val);
        List<Integer> leaves = new ArrayList<Integer>();
        if(root.left == null && root.right == null)
        {
            leaves.add(root.val);
            return leaves;
        }
        if(root.left != null)
        {
            leaves.addAll(getLeaves(root.left));
        }
        if ( root.right != null)
        {
            leaves.addAll(getLeaves(root.right));
        }
        return leaves;
    }
    
}


