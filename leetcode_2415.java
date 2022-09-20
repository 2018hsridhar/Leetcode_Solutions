/*
2415. Reverse Odd Levels of Binary Tree
URL = https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/
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
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        int level = 0;
        List<TreeNode> myParents = new ArrayList<TreeNode>();
        myParents.add(root);
        helper(myParents,level);
        return root;
    }
    
    // this problem is open to a level order traversal approach
    // Leverage the property of the tree being a perfect tree too
    // Reversal of a level does imply what it implies too : [ 8,13,21,34] -> [34,21,13,8].
    // BFS level order traversal eases this problem the best IMO.
    
    // Tricky : An object may have a `null` pointed object, technically speaking
    private void helper(List<TreeNode> parents, int level)
    {
        if(parents == null || parents.size() == 0) {
            return;
        } else {
            // System.out.printf("Size of parents = %d\n", parents.size());
            if(level % 2 == 1) {
                int lPtr = 0;
                int rPtr = parents.size() - 1;
                while(lPtr < rPtr) {
                    // System.out.printf("Ptrs = [%d,%d]\n", lPtr,rPtr);
                    TreeNode lNode = parents.get(lPtr);
                    TreeNode rNode = parents.get(rPtr);
                    int temp = lNode.val;
                    lNode.val = rNode.val;
                    rNode.val = temp;
                    ++lPtr;
                    --rPtr;
                }
            }
            // [3] Iterate on over to the next level ( unless not possible ) 
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            for(TreeNode parent : parents) {
                if(parent.left != null) {
                    nextLevel.add(parent.left);
                }
                if(parent.right != null) {
                    nextLevel.add(parent.right);
                }
            }
            helper(nextLevel, level + 1);
        }
    }
    

    private void flip(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    
}
