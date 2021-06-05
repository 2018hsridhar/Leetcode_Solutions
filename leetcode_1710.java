/*
1710. Maximum Units on a Truck
https://leetcode.com/problems/maximum-units-on-a-truck/

*/


class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) 
    {
        int maxNumUnits = 0;
        Arrays.sort(boxTypes, new Comparator<int[]>(){
        
            //@override
            public int compare(int[] e1, int e2[])
            {
                if(e1[1] < e2[1])
                    return 1;
                else if ( e1[1] > e2[1])
                    return -1;
                return 0;
            }
        });

        // for(int[] e : boxTypes)
            // System.out.printf("[%d,%d],", e[0],e[1]);
        
        for(int i = 0; i < boxTypes.length; ++i)
        {
            int[] box = boxTypes[i];
            if(truckSize >= box[0])
            {
                maxNumUnits += box[0] * box[1];
                truckSize -= box[0];
            }
            else
            {        
                int diff = truckSize; // just remainder now
                maxNumUnits += diff * box[1];            
                break;
            }
        }
        
        
        return maxNumUnits;
    }
}
