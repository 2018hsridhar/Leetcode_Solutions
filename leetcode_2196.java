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
 2196. Create Binary Tree From Descriptions
URL = https://leetcode.com/problems/create-binary-tree-from-descriptions/
 */
 
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        TreeNode result = null;
        int n = descriptions.length;
        Map<Integer,TreeNode> nodes = new HashMap<Integer,TreeNode>();
        Set<Integer> possibleParents = new HashSet<Integer>();
        for(int i = 0; i < n; ++i){
            possibleParents.add(descriptions[i][0]);
        }
        for(int i = 0; i < n; ++i) {
            // [1] Either create the parent OR update the parnet
            int[] descript = descriptions[i];
            
            // Get OR create our new child here
            TreeNode parent = getOrCreateNodeEntry(nodes, descript,0);
            TreeNode child = getOrCreateNodeEntry(nodes, descript,1);
            
            if(possibleParents.contains(child.val)){
                possibleParents.remove(child.val);
            }
            if(descript[2] == 1){
                parent.left = child;
            } else if ( descript[2] == 0) {
                parent.right = child;
            }
            
        }
        Iterator<Integer> itr = possibleParents.iterator();
        result = nodes.get(itr.next());
        return result;
    }
    
    // This is where absl::StatusOR<T> and getOrCreate methods get useful :-)
    private TreeNode getOrCreateNodeEntry(Map<Integer,TreeNode> nodes, int[] descript, int idx){
        TreeNode result = null;
        if(!nodes.containsKey(descript[idx]))
        {
            result = new TreeNode(descript[idx]);
            nodes.put(descript[idx],result);
        } else {
            result = nodes.get(descript[idx]);
        }
        return result;
    }
    
}
