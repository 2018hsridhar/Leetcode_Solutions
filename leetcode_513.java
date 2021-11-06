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

513. Find Bottom Left Tree Value
URL = https://leetcode.com/problems/find-bottom-left-tree-value/

It is the leftmost value in the FINAL level of the subtree
Seems like level order traversal
Also akin to "rightmost viwe" or "leftmost view" as well
Can a DFS also be done here?

COMPLEXITY :
Let N := #-nodes in the binary tree
    -> yes, it is reasonable here
let H := height of the binary tree [ log_2(n) balanced, (n) full skew ] 
Time = O(N)
Space = O(1) ( explicit ) O(H) ( call stack space ) 
Is there a way to solve this problem without visiting all nodes in the tree?

Cheat the limitations of primitive statistics by using an array of primitives variables

TEST BENCH :
(A) [2]
    2
(B) [2,3]
    3
(C)[2,null,3] 
    3
(D) [2,1,3]
    1
(E) [1,2,3,4,null,5,6,null,null,null,null,7,null]
    7
(F) [1,2,3,null,null,null,4,5,null,null,6,7,null]
    7
(G) [1,2,3,null,null,5,6,7,8,9,11,null,null,null,null,null,10]
    10
*/

class Solution 
{
    public int findBottomLeftValue(TreeNode root)
    {
        if(root == null)
            return 0;
        // JAVA lacks nice references ( well we could cheat with an int array : NOT an Integer object )
        int[] leftBottomMeta = new int[2];
        leftBottomMeta[0] = root.val;   
        leftBottomMeta[1] = 0;
        dfs(root,0, leftBottomMeta);
        return leftBottomMeta[0];
    }
    
    // Gloval variable updates seldom work BTW
    // The usefulnes of manipulating memory addresses directly ... WOAH!
    private void dfs(TreeNode root, int level, int[] leftBottomMeta)
    {
        // Case where level > maxLevelEncount
        if(level > leftBottomMeta[1])
        {
            leftBottomMeta[1] = level;
            leftBottomMeta[0] = root.val;
        }
        
        // Check for children in both ways
        if(root.left != null)
        {
            dfs(root.left, level + 1, leftBottomMeta);
        }
        if(root.right != null)
        {
            dfs(root.right, level + 1, leftBottomMeta);
        }
    }
    
    
}
