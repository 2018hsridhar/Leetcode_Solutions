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

Bottom-up approach.
Pass data abstractions up the tree .
We can keep global state / memory ( or avoid it too -> your choice ).
Number of nodes max = 10,000, BUT, the depth is reasonable too.
Path need NOT pass through the root.

If longest path is a seperate subtree -> example 2 -> this is really it's own case, in a sense too.
    * Both subtree root values must equal the root's value.
Akin to a zig-zag esque tree traversal.    

Test Case Generation
--- basic trees ---
(Z) [] => 0
(A) [5] => 0
(B) [5,4,null] => 0
(C) [5,5,null] = 1
(D) [5,5,5] => 2
(E) [4,4,null,4,4,4,null,null,4,null,4]
(F) [5,5,null] => 1
(G) [5,null,5] => 1

--- advanced trees ---
Throw off at object instantiation possible?
(H) [5,null,5,null,5]
 
Complexity
LET N := number of nodes in the binary tree.
LET H := the height of the binary tree , where H = log_2(N)
TIME = O(N)
SPACE = O(1) ( explicit ) O(log(N)) ( recursive call stack ) 


*/

class Info
{
    public int longest;
    public int leftLongest;
    public int rightLongest;
    
    // Default initialization preferable.
    public Info() 
    {
        longest = 0;
        leftLongest = 0;
        rightLongest = 0;
    } // JAVA and other languages mandate constructors for their classes.
    
    public Info(int l, int ll, int rl) 
    {
        longest = l;
        leftLongest = ll;
        rightLongest = rl;
    } // JAVA and other languages mandate constructors for their classes.
}


// Tree problems are useful for straining ability to handle condition logic and boolean expressions :-)
class Solution {
    
    
    public int longestUnivaluePath(TreeNode root) 
    {
        int[] longestPath = new int[1];
        longestPath[0] = 0; // trick with memory :-)
        if(root == null) return 0;
        helper(root,longestPath);
        return longestPath[0];
    }
    
    // Can we avoid returning additional data?
    // TBH, overengineered => can get the left and right subtrees at node for each state.
    private Info helper(TreeNode root, int[] path)
    {
        if(root == null || ( root.left == null && root.right == null)) // leaf
        {
            return new Info();
        }
        Info lstInfo = helper(root.left, path);
        Info rstInfo = helper(root.right, path);
        if ( root.left != null && root.right == null) // LST
        {
            if(root.val == root.left.val) {
                path[0] = Math.max(path[0], lstInfo.longest + 1);
                return new Info(lstInfo.longest + 1, 0, 0);
            } else {
                return new Info(0,0,0);
            }
        }
        else if ( root.left == null && root.right != null) // RST
        {
            if(root.val == root.right.val) {
                path[0] = Math.max(path[0], rstInfo.longest + 1);
                return new Info(rstInfo.longest + 1, 0, 0);
            } else {
                return new Info(0,0,0);
            }
        } else {
            if(root.val == root.left.val && root.val == root.right.val) {
                path[0] = Math.max(path[0], 2 + lstInfo.longest + rstInfo.longest);
                return new Info(Math.max(1 + lstInfo.longest, 1 + rstInfo.longest),0,0);
            } else if ( root.val == root.left.val && root.val != root.right.val ) {
                path[0] = Math.max(path[0], 1 + lstInfo.longest);
                return new Info(1 + lstInfo.longest,0,0);
            } else if ( root.val != root.left.val && root.val == root.right.val ) {
                path[0] = Math.max(path[0], 1 + rstInfo.longest);
                return new Info(1 + rstInfo.longest,0,0);
            } else {
                return new Info(0,0,0);
            }
        }
    }
    
}
