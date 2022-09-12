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

URL = https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
2385. Amount of Time for Binary Tree to Be Infected

ALGORITHM
1. DFS and find the node of interest -> backtracking algorithm.


EDGE CASE TESTING
(A)
(B)
(C)
(D)
(E)

COMPLEXITY
Let N := #-nodes in the binary tree
Let H := the height of the binary tree : log_2(N) balanced, (N) fully skewed
TIME = O() 
SPACE = O() ( EXP ) O() ( IMP ) 

*/

class Solution {
    public int amountOfTime(TreeNode root, int start) {
        int maxTimeTaken = 0;
        if(root == null)
            return 0;
        List<TreeNode> pathUp = new ArrayList<TreeNode>();
        // Child -> parent relationships here ( which is okay )
        findNodeOfInterest(root,start,pathUp);
        TreeNode target = pathUp.get(0);
        int stepsDown = getHeight(target);
        // System.out.printf("Steps down = %d\n", stepsDown);
        if(target == root)
            return getHeight(root);
        int stepsUp = 0;
        for(int i = 0; i < pathUp.size() - 1; ++i){
            TreeNode child = pathUp.get(i);
            TreeNode parent = pathUp.get(i+1);
            stepsUp += 1;
            // check directionality here
            if(parent.left == child) {
                int rst_height = getHeight(parent.right);
                int parentTimeTaken = rst_height + stepsUp;
                if(parent.right != null)
                    parentTimeTaken += 1;
                // System.out.printf("val = %d\t parentTimeTkaen = %d\n", parent.val, parentTimeTaken);
                int curTimeTaken = Math.max(stepsDown, parentTimeTaken);
                maxTimeTaken = Math.max(maxTimeTaken, curTimeTaken);
            } else if ( parent.right == child ) {
                int lst_height = getHeight(parent.left);
                int parentTimeTaken = lst_height + stepsUp;
                if(parent.left != null)
                    parentTimeTaken += 1;
                int curTimeTaken = Math.max(stepsDown, parentTimeTaken);
                maxTimeTaken = Math.max(maxTimeTaken, curTimeTaken);
            }
        }
        return maxTimeTaken;
    }
    
    private int getHeight(TreeNode root){
        if(root == null)
            return 0;
        else if(root.left == null && root.right == null)
            return 0;
        int height = 0;
        height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return height;
    }
    
    
    
    
    private boolean findNodeOfInterest(TreeNode root, int target, List<TreeNode> path)
    {
        if(root == null) {
            return false;
        }
        if(root.val == target){
            path.add(root);
            return true;
        }
        else {
            boolean findOnLeft = findNodeOfInterest(root.left,target,path);
            boolean findOnRight = findNodeOfInterest(root.right,target,path);
            if(findOnLeft || findOnRight) {
                path.add(root);
                return true;
            }
        }
        return false;
    }
    
    
    
    
}
