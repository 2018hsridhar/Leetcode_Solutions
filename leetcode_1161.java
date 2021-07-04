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
            
    public int maxLevelSum(TreeNode root)
    {
        
        // Notice pattern : AbstractClass<DataType> name = new OverridingClass<DataType>();
        // Superclasses to the left -> subclasses unto the right
        
        int maximumLevelSum = Integer.MIN_VALUE;
        int maximumLevel = Integer.MIN_VALUE; // guaranteed a solution anyways!
        if(root == null)
            return 0;
        List<TreeNode> candidates = new LinkedList<TreeNode>();
        candidates.add(root);
        int depth = 1;
        // traverseInLevelOrder(candidates, maximumLevelSum, depth, maximumLevel); - > can't do this fun trick : pass-by-refernce != pass-by-value!
        int[] maximumInfo = { maximumLevelSum, maximumLevel };
        traverseInLevelOrder(candidates, depth, maximumInfo); // - > can't do this fun trick : pass-by-refernce != pass-by-value!
        return maximumInfo[1];
        // return maximumLevel;
    }
    
    // Issue : pass by reference engenders ability for VOID return type methods -> but needs derefenrencing operations
    // Pass-by-reference needed for more complex data types
    
    // The good news about abstract classes : they can be passed in as parameters in method signatures
    // In the process, they extend support to generic programming
    // But limited to base class methods -> no support for abstract methods or specific subclass methods ( e.g. addFirst()/addLast() -> only add() )
    // public void traverseInLevelOrder(List<TreeNode> candidates, int maximumLevelSum, int depth, int maximumLevel)
    public void traverseInLevelOrder(List<TreeNode> candidates, int depth, int[] maximumInfo)
    {
        if(candidates.size() == 0)
            return;
        
        // [1] Calculate level sum and next candidates in process
        int levelSum = 0;
        Iterator<TreeNode> itr = candidates.iterator();
        List<TreeNode> nextLevel = new LinkedList<TreeNode>();
        while(itr.hasNext())
        {
            TreeNode cur = itr.next();
            levelSum += cur.val;
            if(cur.left != null)
                nextLevel.add(cur.left);
            if(cur.right != null)
                nextLevel.add(cur.right);
        }
        
        // [2] Check if current level Sum >= maximumLevel sum : updated accordingly
        if(levelSum > maximumInfo[0])
        {
            maximumInfo[0] = levelSum;
            maximumInfo[1] = depth;
        }
        // [3] Continue level order traversal onwards
        traverseInLevelOrder(nextLevel, ++depth, maximumInfo);
    }
    
}
