/*

URL = https://leetcode.com/problems/maximum-units-on-a-truck/
1710. Maximum Units on a Truck

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
        
        for(int[] e : boxTypes)
            System.out.printf("[%d,%d]\n", e[0],e[1]);
        
        return maxNumUnits;
    }
}
