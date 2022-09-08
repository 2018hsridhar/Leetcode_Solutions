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

URL = https://leetcode.com/problems/largest-bst-subtree/

Note the strictness of the inequality
    LST : < parent node val
    RST : > parent node val

Finding largest BST subtree ( it can be the tree itself ) 
Strictly return number only.
Strategies : bottom-up or top-down.
Top-down is luckily O(N) but it requires additional data passing
    -> could be done iteratively via a stack : tradeoff : implicit for explicit space
The single node case is treatable as a fixed point.
Max size is desired piece of information => return what you want for the { left, right } here


Test Cases
(A)
(B) [10,10,10] => 1
(C) [10,5,null,4,null] => 3
(D) [10,null,11,null,12] => 3
(E)
(F)
(G)
(H)

Complexity
Let N := number of nodes
Let H := height of the tree ( log_2(N) balanced, N skewed ) 
Time = O(N)
Space = O(N) ( explicit ) O(H) ( implicit ) 

Theoretically, you can have a valid BST without all have tow nodes per child as well.
Check inequalities properly here

*/

// Correct term to [ leftMin ] -> not [ leftMin ] 
class Data {
    public int leftMin;
    public int rightMax;
    public int maxSize;
    public boolean isBST; 
    
    public Data() {
        leftMin = 0;
        rightMax = 0;
        maxSize = 0;
        isBST = true;
    }
    
    public Data(int leftMin, int rightMax, int maxSize, boolean isBST) {
        this.leftMin = leftMin;
        this.rightMax = rightMax;
        this.maxSize = maxSize;
        this.isBST = isBST;
    }
}

class Solution {
    public int largestBSTSubtree(TreeNode root) 
    {
        Data largestBSTInfo = new Data();
        if(root == null) return 0;
        largestBSTInfo = helper(root);
        return largestBSTInfo.maxSize;
    }
    
    // Once you hit a level of the tree that no longer works -> you have lost the largest BST for ANY levels above!
    private Data helper(TreeNode root)
    {
        // default initialization safer and preferable to uninitialized random memory :-)
        Data curData = new Data();
        if(root == null) {
            return new Data(0,0,0,false);
        } else if ( isLeaf(root)) {
            return new Data(root.val,root.val,1,true);
        } else {
            if(root.left != null && root.right != null){
                Data lst = helper(root.left);    
                Data rst = helper(root.right);    
                if(lst.rightMax < root.val && root.val < rst.leftMin && lst.isBST && rst.isBST) {
                    System.out.printf("here\n");
                    curData.leftMin = lst.leftMin;
                    curData.rightMax = rst.rightMax;
                    curData.maxSize = 1 + lst.maxSize + rst.maxSize;
                    curData.isBST = true;
                } else {
                    // Just having max size here is buggy ( unless you flagged it! ) 
                    curData.maxSize = Math.max(lst.maxSize, rst.maxSize);
                    curData.isBST = false;
                }
            }
            else // one child available only !
            {
                if(root.left != null) {
                    Data lst = helper(root.left);
                    if(lst.rightMax < root.val && lst.isBST) {
                        curData.leftMin = lst.leftMin;
                        curData.rightMax = root.val;
                        curData.maxSize = 1 + lst.maxSize;
                        curData.isBST = true;
                    } else {
                        curData.maxSize = lst.maxSize;
                        curData.isBST = false;
                    }                    
                } 
                if(root.right != null) {
                    Data rst = helper(root.right);
                    if(root.val < rst.leftMin && rst.isBST) {
                        curData.rightMax = rst.rightMax;
                        curData.leftMin = root.val;
                        curData.maxSize = 1 + rst.maxSize;
                        curData.isBST = true;
                    } else {
                        curData.maxSize = rst.maxSize;
                        curData.isBST = false;
                    }                           
                }
            }
        }
        return curData;
    }
    
    // Is preferred to see this code to test the leaf node property versus internal node property.
    private boolean isLeaf(TreeNode root) { 
        return (root != null && root.left == null && root.right == null);
    };
    
    
}





