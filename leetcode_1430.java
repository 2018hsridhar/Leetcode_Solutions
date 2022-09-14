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

1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
URL= https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/
Check if a string is a valid sequence
We can pass an index into a recursive method -> or use a stack instead for the BFS/DFS.
Enjoy the binary tree property too

Path from root to leaf!
    Must assert the `isLeaf` property too!

EDGE CASES
(A)
(B)
(C)
(D)
(E)
(F)

18 mins to implement
5 mins spent on attempting explicit solution
12 mins on implicit solution

COMPLEXITY
Let N := #-nodes in the tree
Let H := height of the tree [ log_2(N) balanced, (N) skewed ]
TIME = O(N)
SPACE = O(1) ( EXP ) O(N) ( IMP ) 

Challenge : an explicit DFS here.

*/

class Solution {
    public boolean isValidSequence(TreeNode root, int[] arr) 
    {
        boolean isValidSeq = false;
        if(root == null)
            return false;
        int ptr = 0;
        isValidSeq = helper(root,arr,ptr);
        return isValidSeq;
    }
    
    // Easier to express the problem recursively versus iteratively.
    // Easier to pass state via recursive function args/parameters.
    // Condition logic encapsulates most of code and associated codepaths/return paths.
    // Explicit solution difficulty = lacking a boolean return here.
    // May need to store additional state too.
    private boolean helper(TreeNode root, int[] arr, int ptr)
    {
        int n = arr.length;
        if(root == null) {
            return false;
        } else if ( ptr == n-1 ) {
            if(root.val == arr[ptr] && isLeaf(root))
                return true;
            else
                return false;
        }
        else if(root.val == arr[ptr]) { // we can proceed
            int nextLevel = ptr + 1;
            if(root.left != null) {
                boolean checkLst = helper(root.left,arr,nextLevel);
                if(checkLst)
                    return true;
            }            
            if(root.right != null) {
                boolean checkRst = helper(root.right,arr,nextLevel);
                if(checkRst)
                    return true;
            }
            return false;
        }
        return false;
    }
    
    private boolean isLeaf(TreeNode root)
    {
        return ((root != null) && (root.left == null && root.right == null));
    }
    
    
    
}

        /*
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        if(arr[0] != root.val)
            return false; // this can never be the case : no valid path here
        int ptr = 1; // ptr into the 2nd elemnet of the array.
        while(!stk.isEmpty()) // check arr[ptr] == curNode.val
        {
            TreeNode cur = stk.peek();
            if(cur.left != null)
            {
                if(arr[ptr] == cur.left.val)
                {
                    stk.push(cur.left);
                    ptr++;
                }
            } 
            if(cur.right != null)
            {
                if(arr[ptr] == cur.right.val)
                {
                    stk.push(cur.right);
                    ptr++;
                }
            }
            // you pushed new node into the stack here
        }
        */





