/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) 
    {
        List<Double> avgLvlVals = new ArrayList<Double>();
        
//         // process current Level [ first level]
//         Queue curLevel = new Queue();
//         curLevel.push(root);
//         while(!curLevel.isEmpty())
//         {
//             // COMPTUE avg, for cur level, assuming all nodes
//             int divider = curLevel.length;
//             int lvlTot = 0;
//             for(int i = 0; i < curLevel.length; ++i)
//             {
//                 lvlTot += curLevel[i].val; 
//             }
//             // compute avg and add it
//             double lvlAvg = (lvlTot / divider);
//             avgLvlVals.add(lvlAvg);
//         }
        
        // 2x HM approach :: pass them down, update, and recurse. 
        // one with [level, level_tot_sum] and [level, level_tot_num_nodes]
        HashMap<Integer, Long> lvl_tot_sum = new HashMap<Integer,Long>();
        HashMap<Integer, Integer> lvl_tot_nodes = new HashMap<Integer,Integer>();
        int curLevel = 0;
        updateValues(root, lvl_tot_sum, lvl_tot_nodes, curLevel);
        
        // CONSTRUCT the new level list
        for(Map.Entry<Integer,Long> entry : lvl_tot_sum.entrySet())
        {
            int lvl = entry.getKey();
            Long cur_sum = entry.getValue();
            double num_nodes = lvl_tot_nodes.get(lvl);
            double lvl_avg = (double)(cur_sum / num_nodes);
            avgLvlVals.add(lvl_avg);
        }
        
        return avgLvlVals;
    }
    
    public void updateValues(TreeNode root, HashMap<Integer,Long> lts, HashMap<Integer,Integer> ltn, int lvl)
    {
        if(root == null)
            return;
        
        // update lts [ level total sum ]
        if(!lts.containsKey(lvl))
            lts.put(lvl, (Long)root.val);
        else
            lts.put(lvl, lts.get(lvl) + (Long)root.val);
        
        // update ltn [ level total num nodes ]
        if(!ltn.containsKey(lvl))
            ltn.put(lvl, 1);
        else
            ltn.put(lvl, ltn.get(lvl) + 1);
        
        // recurse down
        updateValues(root.left, lts, ltn, lvl + 1);
        updateValues(root.right, lts, ltn, lvl + 1);
    }
    
}

// https://leetcode.com/problems/average-of-levels-in-binary-tree/description/

/*
The range of node's value is in the range of 32-bit signed integer.
Even is a value is specified as all integers - ask bit range and signage range too.
*/
