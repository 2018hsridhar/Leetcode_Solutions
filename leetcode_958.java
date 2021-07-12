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

958. Check Completeness of a Binary Tree
URL = https://leetcode.com/problems/check-completeness-of-a-binary-tree/

Idea : 
One : use power-of-2 series here ( 1 + 2 + 4 + 8 = num_nodes in a complete tree ) 
Additionally, consider serialization of tree, into an array, and then check from (n/2,n) nodes. We can easily check from nodes (1,n/2-1) without difficulty.
How to perform serialization and account for null children as accurately as possible too?
[ 1,2,3] should not be [1,2,3,null,null,null,null]
BUT
[1,2,null,null,3,5] should be what it is here
Add a non-null check flag as we reverse itetate over the last level too?


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
Level max is reasonable - [1,100] only
Node values reasonably bounded by the closed interval [1,1000]

URL = https://leetcode.com/problems/check-completeness-of-a-binary-tree/
958. Check Completeness of a Binary Tree

Yes - the linked list representation can have null elements too!


*/

class Solution {
    public boolean isCompleteTree(TreeNode root) 
    {
        if(root == null)
            return true;
        
        boolean isCompleteTree = true;
        // [1] Create the BFS level-order traversal list
        List<List<TreeNode>> traversal = new ArrayList<List<TreeNode>>();
        ArrayList<TreeNode> candidates = new ArrayList<TreeNode>();
        candidates.add(root);
        createLevelOrderTraversal(traversal, candidates);
        int treeDepth = traversal.size();
        // [2] Check from depth in range [1,treeDepth-1] that this tree is full! ( no null elements )
        for(int i = 0; i <= (treeDepth - 3); ++i)
        {
            List<TreeNode> myLevel = traversal.get(i);
            for(TreeNode x : myLevel)
                if(x == null)
                    return false;
        }
        // [3] Check consecutive sequence of the final level at treeDepth
        List<TreeNode> lastLevel = traversal.get((treeDepth-2));
        boolean hitNull = false;
        for(TreeNode x : lastLevel)
        {
            if(x != null && hitNull == true)
                return false;
            if(x == null && hitNull == false)
                hitNull = true;
        }
        
        
        return isCompleteTree;
    }
    
    public void createLevelOrderTraversal(List<List<TreeNode>> traversal, ArrayList<TreeNode> candidates)
    {
        if(candidates.size() == 0)
            return;
        traversal.add(candidates);
        ArrayList<TreeNode> nextLevel = new ArrayList<TreeNode>();
        Iterator<TreeNode> itr = candidates.iterator();
        while(itr.hasNext())
        {
            TreeNode candid = itr.next();
            if(candid != null)
            {
                nextLevel.add(candid.left);
                nextLevel.add(candid.right);
            }
        }
        createLevelOrderTraversal(traversal, nextLevel);
    }
    
}
