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

Non-empty special binary tree ( guaranteed @ least one node ) 
Values are NON-NEG ( can be 0 or positive ) ( can be duplicates )
Is a full tree -> every node has EXACTLy [zero,two] child sub-nodes
Let us exploit the property : root.val = min(root.left.val, root.right.val) and construct some cases as well
We return -1 in only two cases
(a) We have a singleton node as the root
(b) All values in the tree are the same.
Otherwise, there must be an ordering, as Z_{nonneg} is a well-ordered set [ given a,b, \in Z, a!=b, then a < b or b < a holds true ] 
4 comparisons total
Post-order : ( left->right->root ) bottom-up approach

Strategies : recursion, divide-&-conquer, induction, non-overlapping subproblems with OSP

If min DNE - output -1 ( as this is < 0)


TEST BENCH : 
(A) [2]
    => -1
(B) [2,2,5]
    => 2
(C) [2,2,2]             ( PROVIDED ) 
    => -1
(D) [2,2,2,2,2,2,3]
    => 3
(E) [2,5,2]
    => 2
(F) [2,2,2,3,2,2,3]
    => 3
(G) [2,2,5,null,null,5,7] ( PROVIDED ) 
    => 5
(H) [2,2,2,2,3,6,2]
    => 3
(I) [1,1,1,null,null,1,1,null,null,1,2,null,null,2,2]
    => 2

Failed test case : 
[1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1]
Now passed
[2,2,2147483647]
2147483647


Complexity : 
Let N := # nodes in the binary tree
Let H := height of the tree ( balanced ~ O(log_2(n)), unbalanced ~ O(n))
Time = O(N)
Space = O(1) ( explicit ) O(H) ( implicit )

URL = https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/

*/

class Solution 
{
    public int findSecondMinimumValue(TreeNode root) 
    {
        int min = -1;
        min = (int)dfs(root);
        return min;
    }
    
    // 4 comparisons test may be making this more difficult than expected!
    
    private long dfs(TreeNode root)
    {
        if(root.left == null && root.right == null)
        {
            return -1;
        }
        else
        {
            // int secondMin = Integer.MAX_VALUE; // ( yep ... we can have integer.max_value here sadly :-( )
            long secondMin = Long.MAX_VALUE; // ( yep ... we can have integer.max_value here sadly :-( )
            
            // it is not math.max here btw : is is just the min of return values
            long left_second_min = dfs(root.left);
            long right_second_min = dfs(root.right);
            
            if(left_second_min != -1)
            {
                secondMin = Math.min(secondMin, left_second_min);
            }
            if(right_second_min != -1)
            {
                secondMin = Math.min(secondMin, right_second_min);
            }
            if(root.left.val > root.val)
            {
                secondMin = Math.min(secondMin, root.left.val);
            }
            if(root.right.val > root.val)
            {
                secondMin = Math.min(secondMin, root.right.val);           
            }
            
            if(secondMin == Long.MAX_VALUE)
            {
                secondMin = -1;
            }
            return secondMin;
        }
    }
    
    
}
