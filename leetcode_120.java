/*
120. Triangle
URL = https://leetcode.com/problems/triangle/

*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) 
    {
        // Base Case Handling
        if(triangle == null || triangle.size() == 0)
            return 0;
        
        int minSum = Integer.MAX_VALUE;
        int numLevels = triangle.size();
        int[] lvlSums = new int[numLevels];
        
        // [1] Fill up initial level with base case / terminating cases
        List<Integer> lastLevel = triangle.get(numLevels - 1);
        for(int i = 0; i < lastLevel.size(); ++i)
            lvlSums[i] = lastLevel.get(i);
        
        // [2] Iterate over inductive/larger subproblem cases
        for(int i = triangle.size() - 2; i >= 0; --i)
        {
            List<Integer> level = triangle.get(i);
            for(int j = 0; j < level.size(); ++j)
            {
                lvlSums[j] = level.get(j) + Math.min(lvlSums[j], lvlSums[j+1]);
            }
        }
        minSum = lvlSums[0];
        return minSum;
    }
}
