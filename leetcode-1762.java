/*
1762. Buildings With an Ocean View
URL = https://leetcode.com/problems/buildings-with-an-ocean-view/

Sunset Views on AlgoExpert.IO :-)
*/
class Solution {
    public int[] findBuildings(int[] heights) {
        if(heights == null || heights.length == 0)
            return new int[]{}; // 0-len int array prefrred to null output
        List<Integer> indices = new ArrayList<Integer>();
        indices.add(heights.length - 1); // a `de facto` answer
        int curMax = heights[heights.length - 1];
        for(int i = heights.length - 2; i >= 0; --i){
            if(heights[i] > curMax){
                indices.add(i);
                curMax = heights[i];
            }
        }
        
        
        // ArrayList to Array conversions ( is why std::vector<T> helps ) 
        int[] results = new int[indices.size()];
        for(int i = indices.size() - 1; i >= 0; --i){
            results[indices.size() - 1 - i] = indices.get(i);
        }
        return results;
    }
}
