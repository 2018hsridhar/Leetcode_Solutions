/*
256. Paint House
URL = https://leetcode.com/problems/paint-house/

Complexity
Let n := number of houses/length(costs)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 
*/
class Solution {
    public int minCost(int[][] costs) {
        int glblMinCost = Integer.MAX_VALUE;
        int n = costs.length;
        int restRed = costs[n-1][0];
        int restBlue = costs[n-1][1];
        int restGreen = costs[n-1][2];
        glblMinCost = Math.min(restRed, Math.min(restBlue,restGreen));
        int i = n-2;
        while(i >= 0){
            int[] myHouse = costs[i];
            int caseRed = myHouse[0] + Math.min(restGreen,restBlue);
            int caseBlue = myHouse[1] + Math.min(restRed,restGreen);
            int caseGreen = myHouse[2] + Math.min(restRed, restBlue);
            glblMinCost = Math.min(caseRed, Math.min(caseBlue,caseGreen));
            restRed = caseRed;
            restBlue = caseBlue;
            restGreen = caseGreen;
            --i;
        }
        return glblMinCost;
    }
}
