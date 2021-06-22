/*
370. Range Addition
https://leetcode.com/problems/range-addition/
*/

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) 
    {
        // Figure out total increments needed in first place
        // Then perform one final update operation
        int[] totalUpdates = new int[length];
        int[] results = new int[length];
        for(int i = 0; i < totalUpdates.length; ++i)
        {
            totalUpdates[i] = 0;
            results[i] = 0;            
        }
        for(int j = 0; j < updates.length; ++j)
        {
            int[] op = updates[j];
            for(int i = op[0]; i <= op[1]; ++i)
                totalUpdates[i] += op[2];
        }
        
        // Apply total updates
        for(int i = 0; i < totalUpdates.length; ++i)
            results[i] += totalUpdates[i];
        
        return results;
        
    }
}
