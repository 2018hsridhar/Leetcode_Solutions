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
2265. Count Nodes Equal to Average of Subtree

Similar to most other tree problems
-> EASIER THAN YOU THINK!
-> bottom up or top-down approaches
-> recursive or iterative approache
-> need to propogate a value as we go up too ( and a count of the number of nodes )
    * can we try to focus on passing one value only ?
-> focus is on only a count here

    
Test Cases
(A) [1]
(B) [1,1,null] => 2
(C) [1,null,1] => 2
(D) [1,1,1] => 3
(E) [1,1,1,1,1,1,1] => 7
(F) [8,1,1] => 2
(G) [2,5,5,1,1,1,1] => 5
(H)
(I)
(J)

    
* Prefer to use Math.ceil function for safety ( versus implicit type conversions ) 

Complexity
N reasonable ( 1K nodes only ). Expect reasonable data inputs ( minimial overflow exceptions )
0 <= X*1000 <= INT_MAX, where X \in [0,1000]
Let N := # of nodes in the tree
Let H := height of the tree ( log_2(N) best balanced case, N skewed worst case ) 
Time = O(N)
Space = O(1) ( explicit ) O(H) ( implicit ) 
    
*/

// JAVA lacks structs/pairs and . . . you need the default constructors :-( )
class OrderStats
{
    public int sum;
    public int count;
    public int curCount;
    
    public OrderStats()
    {
        sum = 0;
        count = 0;
        curCount = 0;
    }
    
    public OrderStats(int sum, int count, int curCount)
    {
        this.sum = sum;
        this.count = count;
        this.curCount = curCount;
    }
    
}

class Solution {
    public int averageOfSubtree(TreeNode root) 
    {
        OrderStats rootStats = helper(root);
        return rootStats.curCount;
    }
    
    // private recursive helper function
    private OrderStats helper(TreeNode root)
    {
        if(root == null) {
            return new OrderStats(); // all zeroes out
        }
        OrderStats curNodeStats = new OrderStats(root.val, 1, 0);
        // Leaf node case -> de facto added too!
        if(root.left == null && root.right == null) {
            curNodeStats.curCount = 1;
        } 
        else {
            if(root.left != null) {
                OrderStats lst = helper(root.left);
                curNodeStats.sum += lst.sum;
                curNodeStats.count += lst.count;
                curNodeStats.curCount += lst.curCount;
            }
            if(root.right != null) {
                OrderStats rst = helper(root.right);
                curNodeStats.sum += rst.sum;
                curNodeStats.count += rst.count;
                curNodeStats.curCount += rst.curCount;
            }
            // Perform order stats
            int average = (int)Math.ceil(curNodeStats.sum / curNodeStats.count);
            if(root.val == average) {
                curNodeStats.curCount += 1;
            }
        }
        return curNodeStats;
    }
    
    
}
