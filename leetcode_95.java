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
You need to GENERATE the structurally unique BST's 
n nodes with UNIQUE values [1,n] : no duplicates luckily
Answer returnable in ANY ORDER 

95. Unique Binary Search Trees II
URL = https://leetcode.com/problems/unique-binary-search-trees-ii/

Complexity
Let N := #-nodes
Time = O()
Space = O()

Edge Case Testing ( Let * := base cases ) 

* (A) n = 1
    [[1]]
    PASSED
* (B) n = 2
    [[1,null,2],[2,1,null]]
    NOT PASSING
(C) n = 3 
    [[1,null,3,2], [1,null,2,null,3]. [2,1,3], [3,2,null,1], [3,1,null,null,2]]
(D) n = 4

(E) n = 5

... increasing lengths here not that great
Also we are given that n is bounded here on closed interval of [1,8] : backtracking is possible
    -> they will use this constraining factor to trick you!
    -> For a given max height, we do know the number of choices as well

Can the person reason about the algo's time complexity as well?
You tell your students all the time to first come up with naive backtracking/recursion -> then DP it. You should follow as well too
Difficulty keeping track of all information under processing?


*/

class Solution 
{
    public List<TreeNode> generateTrees(int n) 
    {
        List<TreeNode> structures = new LinkedList<TreeNode>();
        structures = helper(1, n);
        return structures;
    }
    
    // Since our n is tiny ... the loop of values to check is tiny
    // Otherwise, copy over the sets in each recursive step, 
    private List<TreeNode> helper(int low, int high)
    {
        List<TreeNode> structures = new LinkedList<TreeNode>();
        if(low > high || high < low)
        {
            return structures; // just return an empty list here
        }
        if(low == high)
        {
            TreeNode singleton = new TreeNode(low);
            structures.add(singleton);
            return structures;
        }
        else
        {
            for(int k = low; k <= high; k++)
            {
                int mid = k;
                
                int lhs_low = low;
                int lhs_high = mid - 1;
                int rhs_low = mid + 1;
                int rhs_high = high;
                
                List<TreeNode> left_subtrees = helper(lhs_low, lhs_high);
                List<TreeNode> right_subtrees = helper(rhs_low, rhs_high);
               
                // Failure to account for the edge cases here as well too! 
                if(left_subtrees.size() == 0)
                {
                    for(int j = 0; j < right_subtrees.size(); ++j)
                    {
                        TreeNode rst = right_subtrees.get(j);
                        TreeNode root = new TreeNode(mid);
                        root.left = null;
                        root.right = rst;
                        structures.add(root);
                    } 
                }
                
                if(right_subtrees.size() == 0 )
                {
                    for(int j = 0; j < left_subtrees.size(); ++j)
                    {
                        TreeNode lst = left_subtrees.get(j);
                        TreeNode root = new TreeNode(mid);
                        root.left = lst;
                        root.right = null;
                        structures.add(root);
                    } 
                }
                
                // Iterate over both subtrees to generate new combos
                // Ideally, neither sizes are equal to zero though
                for(int i = 0; i < left_subtrees.size(); ++i)
                {
                    for(int j = 0; j < right_subtrees.size(); ++j)
                    {
                        TreeNode lst = left_subtrees.get(i);
                        TreeNode rst = right_subtrees.get(j);
                        TreeNode root = new TreeNode(mid);
                        root.left = lst;
                        root.right = rst;
                        structures.add(root);
                    }
                }
            }
        }
        return structures;
    }
    
}
