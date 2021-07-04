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
    public List<Integer> largestValues(TreeNode root) 
    {
        
        // Notice pattern : AbstractClass<DataType> name = new OverridingClass<DataType>();
        // Superclasses to the left -> subclasses unto the right
        
        List<Integer> largestValues = new LinkedList<Integer>();
        if(root == null)
            return largestValues;
        List<TreeNode> candidates = new LinkedList<TreeNode>();
        candidates.add(root);
        traverseInLevelOrder(candidates, largestValues);
        return largestValues;
    }
    
    // The good news about abstract classes : they can be passed in as parameters in method signatures
    // In the process, they extend support to generic programming
    // But limited to base class methods -> no support for abstract methods or specific subclass methods ( e.g. addFirst()/addLast() -> only add() )
    public void traverseInLevelOrder(List<TreeNode> candidates, List<Integer> largestValues)
    {
        if(candidates.size() == 0)
            return;
        
        // Calculate level sum and next candidates in process
        int largestVal = Integer.MIN_VALUE;
        Iterator<TreeNode> itr = candidates.iterator();
        List<TreeNode> nextLevel = new LinkedList<TreeNode>();
        while(itr.hasNext())
        {
            TreeNode cur = itr.next();
            largestVal = Math.max(largestVal, cur.val);
            if(cur.left != null)
            {
                nextLevel.add(cur.left);
            }
            if(cur.right != null)
            {
                nextLevel.add(cur.right);
            }
        }
        
        largestValues.add(largestVal);
        traverseInLevelOrder(nextLevel, largestValues);
    }
}
