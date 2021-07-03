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

Follows the classic pattern of level-order traversal, where the list is computed at each stage
Make sure to pass the depth in the recursive method where the list is generated
Terminate once the possible list to produce be empty

Worst-case level-order tree : (n-1:n) partitioning

Time-Space Complexity : 
Let k := max_recursion depth = max level
Space = O(N) where N := number of nodes in tree ( a level can have at worst (n-1) nodes to store in each linked list passed thru each iteration ) 
Time = O(Nk) where N = number of nodes in tree ( think 1:n-1 case where n-1 nodes get added to next level + a for-loop in each depth operation ) + k ( number of times we execute method ) 


*/

class Solution {
    public boolean isEvenOddTree(TreeNode root) 
    {
        ArrayList<TreeNode> candidates = new ArrayList<TreeNode>();
        candidates.add(root);
        int depth = 0;
        boolean evenOddFlag = executeEvenOddCheck(candidates, depth);
        return evenOddFlag;
    }
    
    // Handle root level case ( depth = 1 here )
    // Handle case of singleton sets in each level too
    public boolean executeEvenOddCheck(ArrayList<TreeNode> candidates, int depth)
    {
        // Add the candidates in each level to new candidates
        if(candidates.size() == 0)  // O(1)
            return true;  
        // Parity(depth) does not matter here anyways!
        
        if(candidates.size() == 1) // O(1)
        {
            if(depth % 2 == 0 && candidates.get(0).val % 2 != 1) return false;
            else if(depth % 2 == 1 && candidates.get(0).val % 2 != 0) return false;
        }
        
        // Perform order check : strictly increasing OR strictly decreasing
        ArrayList<TreeNode> nextLevel = new ArrayList<TreeNode>();
        for(int i = 1; i < candidates.size(); ++i) // O(N)
        {
            int cur = candidates.get(i).val;
            int prev = candidates.get(i-1).val;
            if(depth % 2 == 0) 
            {
                if(cur <= prev || cur % 2 == 0 || prev % 2 == 0) return false;
            }
            else
            {
                if(cur >= prev || cur % 2 == 1 || prev % 2 == 1) return false;
            }
        }
        
        
        // Parity check performed : go to next level
        Iterator<TreeNode> itr = candidates.iterator();
        while(itr.hasNext()) // O(N)
        {
            TreeNode cur = itr.next();
            if(cur.left != null) nextLevel.add(cur.left);
            if(cur.right != null) nextLevel.add(cur.right);
        }
        return executeEvenOddCheck(nextLevel, ++depth);
    }
}
