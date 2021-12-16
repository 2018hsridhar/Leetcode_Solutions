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

We are provided the root of a binary tree
Luckily, each node has a DISTINCT value here, and we have a set of values to delete as well
Forest = a disjoint union of trees
Return the roots of remaining forest

COMPLEXITY
Let N := #-nodes, H := height ( log_2(N) balanaced and (N) worst unbalanced ) 
TIME = O(N)
SPACE = O(H) or O(N)

TEST CASES
(A) [1], [1]
    => [] ( *** null set case *** ) 
(B)
(C)
(D)
(E)
(F)

1K nodes max, and to delete never exceeds this, and is always valid input too
O(N^2) polynomial also works too
Is it akin to a SLL deletion problem here?
Can also attempt to hash map or hash set these values as well.
Sorting to delete array does not assist
Could do negative marking for each node here as well
Roots have no parent nodes ( they'd point to NULLPTR here ) '
I would think bottom-up if a best approach here as well for checking root nodes or not too
The topmost node of a tree is a very special case as well -> check seperately too ( outside scope
of the recursive calls ) 
Your modifications may work -> but check more cases as well!
There is a possible top-down method still ( works for non-leaf cases too ) : pass the status of the roots though?
Work off of subtrees instead?
Can toggle an "isRoot" parameter as well here!
    Being a member of deletion set here is a massive toggle of "isRoot" as well
    TD is a O(N) algo too
    

*/

class Solution 
{
    
    // Aim for a method with more "permanent" deletion instead
    // Akin to deletino from the middle of a SLL ( or the end )
    // Beginning case is a unique case though!
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) 
    {
        List<TreeNode> forest = new ArrayList<TreeNode>();
        HashSet<Integer> delSet = new HashSet<Integer>();
        for(int i = 0; i < to_delete.length; ++i)
        {
            delSet.add(to_delete[i]);
        }
        dfs(root, delSet, forest);
        if(delSet.contains(root.val))
        {
            if(root.left != null && !delSet.contains(root.left))
            {
                forest.add(root.left);
            }
            if(root.right != null && !delSet.contains(root.right))
            {
                forest.add(root.right);
            }
            root.left = null;
            root.right = null;
        }
        else
        {
            forest.add(root);
        }
        return forest;
    }
    
    // Parent->child partitioning here
    // You technically answered this, BUT, forgot to do the actual deletion of the nodes!
    // make sure to DELETE those nodes as well
    // The two child case makes setting a node immediate to null not always possible -> take careful note of this too
    // Why not return a boolean, as that is an indicator to the parent from the child ( callee-> caller ) to set its nodes accordingly
    
    private boolean dfs(TreeNode root, HashSet<Integer> delSet, List<TreeNode> forest)
    {
        // The leaf case really does not matter honestly
        if(root.left == null && root.right == null)
        {
            return delSet.contains(root.val);                
        }
        if ( root.left != null )
        {
            boolean delLeft = dfs(root.left, delSet, forest);
            if(delSet.contains(root.val))
            {
                if(!delSet.contains(root.left.val))
                {
                    forest.add(root.left);
                }
            }
            if(delLeft)
            {
                root.left = null;
            }
        }
        if ( root.right != null)
        {
            boolean delRight = dfs(root.right, delSet, forest);
            if(delSet.contains(root.val))
            {
                if(!delSet.contains(root.right.val))
                {
                    forest.add(root.right);
                }
            }
            if(delRight)
            {
                root.right = null;
            }
        }
        // Arg here : the subtrees thesmeles can have dangling pointers -> but their traversal starts downards trajectories anyways
        // Focus on setting the parent nodes pointers for sure here!
        if(delSet.contains(root.val))
        {
            root.left = null;
            root.right = null;
        }
        return delSet.contains(root.val);
    }
    
    // Can we avoid a weird iterativev deletion here as well? Do NOT emulate BST or something here
    
}
