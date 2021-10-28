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

Guaranteed non-empty property here
TEST BENCH
(A) [1]
(B) [1,2,3,4,5]
(C) [1,2]
(D) [1,2,3,null,4,null,9]
(E) [1,2,3,null,5,null,9,4,6,null,null,10]

Complexity
Let N := #-vertices/nodes, H := height ( log_2(N) balanced, N unbalanced )
Time = O(N)
Space = O(H) ( implicit ) O(1) explicit
*/
class Solution 
{
    // Do problem without an initial height retrieval OR allocates all lists in the lists initially
    public List<List<Integer>> findLeaves(TreeNode root) 
    {
        List<List<Integer>> leaves = new ArrayList<List<Integer>>();
        if(root == null)
            return null;
        getLeaves(root, leaves);
        return leaves;
    }
    
    // Head recursive ( note traversal order as well ) 
    public int getLeaves(TreeNode root, List<List<Integer>> leaves)
    {
        if(root == null)
        {
            return 0;
        }
        int maxDepth = 1; // default
        if(root.left == null && root.right == null)
        {
            if(leaves.size() == 0)
            {
                List<Integer> placement = new ArrayList<Integer>();
                placement.add(root.val);
                leaves.add(placement);
            }
            else
            {
                List<Integer> placement = leaves.get(0);
                placement.add(root.val);
            }
            maxDepth = 1;
            return maxDepth;
        }
        else    // If either is null, that means either or : just do quick null checks here
        {
            if(root.left != null)
            {
                maxDepth = Math.max(maxDepth, 1+getLeaves(root.left,leaves));
            }
            if(root.right != null)
            {
                maxDepth = Math.max(maxDepth, 1+getLeaves(root.right,leaves));
            }
            if(leaves.size() < maxDepth)
            {
                List<Integer> placement = new ArrayList<Integer>();
                placement.add(root.val);
                leaves.add(placement);
            }
            else
            {
                List<Integer> placement = leaves.get(maxDepth - 1);
                placement.add(root.val);
            }
        }
        
        return maxDepth;
    }
}
