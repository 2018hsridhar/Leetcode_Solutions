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

The root is guaranteed to be a binary tree
Return the max sum of all keys of ANY subtree which is a BST
-> exploit the BST property here, if doable as well?
-> Can we aim for a bottom-up inductive approach?

BST property is a strict inequality here as well : 
    (a) left subtree(node) has keys < node's keys
    (b) right subtree(node) has keys > node's keys
A single node by itself is a BST
A left-rooted node, or a right-rooted node, is also a BST ( provided properties met )
A 2-child root node is, provided that ( left.val < root.val < right.val ) 
    
Recursive func that returns the summations as well

Complexity
Let N := # nodes in the BST
Let H := height of the BST
    balanced -> log(N)
    unbalanced -> N
Time = O(N)
Space = O(H)

TEST BENCH
(A) [1]
    1
(B) [1,2,3]
    3
(C) [1,2]
    2
(D) [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
    20
(E) [5,-4,null]
    1
(F) [5,-8,null]
    0
(G) [8,-2,100,-3,0,null,null]
    103
(H) [0,3,10,4,2,5,15]
    30
(I) [8,1,-1,0,2,null,null]
    3
(K) [1,null,3]
    4
(L) [2,1,3]
    6
(M) [1,null,10,-5,20]
    25
(N) [1,2,10,1,4,-5,20]
    25

* [1,null,10,-5,20]
    Not correct : should be 25, not 26


Double wrapper OR global scope technique as well
They attempt to trip you up -> vals can be negative as well
Even if it is one-sided, a BST CAN still maintain validity.

400,000 nodes at max, 1 node at min
Node values in range [-40,000, 40,000]

URL = https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/submissions/
1373. Maximum Sum BST in Binary Tree

*/

// Ideally, this would be a nested class
class Wrapper
{
    public boolean isBst;
    public int maxSum;
    public int leftMin;
    public int leftMax;
    public int rightMin;
    public int rightMax;
    
    public Wrapper(){};
    public Wrapper(boolean _isBst, int _maxSum, int _leftMin, int _leftMax, int _rightMin, int _rightMax)
    {
        this.isBst = _isBst; 
        this.maxSum = _maxSum;
        this.leftMin = _leftMin;
        this.leftMax = _leftMax;
        this.rightMin = _rightMin;
        this.rightMax = _rightMax;
    }
}

class Solution 
{
    int globalSum = 0;
    public int maxSumBST(TreeNode root) 
    {
        globalSum = 0;
        dfs(root);
        return globalSum;
    }
    
    // If not a BST -> set max path sum to 0 and isBST = false. We have to make the change somewhere
    private Wrapper dfs(TreeNode root)
    {
        Wrapper metadata = new Wrapper(true, root.val, root.val, root.val, root.val, root.val);
        if(root.left == null && root.right == null)
        {
            globalSum = Math.max(root.val, globalSum);
            // return metadata;
        }
        else if ( root.left != null && root.right == null)
        {
            Wrapper leftsubtree = dfs(root.left);
            if(root.val > root.left.val && leftsubtree.isBst && root.val > leftsubtree.rightMax)
            {
                metadata.isBst = true;
                metadata.maxSum = root.val + leftsubtree.maxSum;
                metadata.leftMin = leftsubtree.leftMin;
                metadata.leftMax = root.val;
                metadata.rightMin = root.val;
                metadata.rightMax = root.val;
                globalSum = Math.max(globalSum, metadata.maxSum);
            }
            else
            {
                metadata.isBst = false;
                metadata.maxSum = 0;
            }
        }
        else if ( root.left == null && root.right != null)
        {
            Wrapper rightsubtree = dfs(root.right);
            if(root.val < root.right.val && rightsubtree.isBst && root.val < rightsubtree.leftMin)
            {
                metadata.isBst = true;
                metadata.maxSum = root.val + rightsubtree.maxSum;
                metadata.leftMin = root.val;
                metadata.leftMax = root.val;
                metadata.rightMin = root.val;
                metadata.rightMax = rightsubtree.rightMax;
                globalSum = Math.max(globalSum, metadata.maxSum);
            }
            else
            {
                metadata.isBst = false;
                metadata.maxSum = 0;
            }
        }
        else
        {
            Wrapper leftsubtree = dfs(root.left);
            Wrapper rightsubtree = dfs(root.right);
            if(root.left.val < root.val && root.val < root.right.val 
               && leftsubtree.isBst && rightsubtree.isBst
              && leftsubtree.leftMax < root.val && root.val < rightsubtree.rightMin)
            {
                metadata.isBst = true;
                metadata.maxSum = root.val + leftsubtree.maxSum + rightsubtree.maxSum;
                metadata.leftMin = leftsubtree.leftMin;
                metadata.leftMax = rightsubtree.rightMax; 
                metadata.rightMin = leftsubtree.leftMin;
                metadata.rightMax = rightsubtree.rightMax;
                globalSum = Math.max(globalSum, metadata.maxSum);
            }
            else
            {
                metadata.isBst = false;
                metadata.maxSum = 0;
            }     
        }
        // System.out.printf("For val = %d, leftMin = %d \t leftMax = %d\t rightMin = %d \t rightMax = %d \n", root.val, metadata.leftMin, metadata.leftMax, metadata.rightMin, metadata.rightMax);
        return metadata;
    }
}




