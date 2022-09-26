/*
URL = https://leetcode.com/problems/minimum-cost-for-tickets/
983. Minimum Cost For Tickets

The problem @ hand strongly resembles the minimum change problem.

Current thinking is to proceed left->right here
Also to store minCosts at each different day here too
May consider using a hashmap too

*/
class Solution {
    public int mincostTickets(int[] days, int[] costs) 
    {
        int glblCost = Integer.MAX_VALUE;
        int n = days.length;
        int finalDay = days[n-1];
        int firstDay = days[0];
        int[] minCosts = new int[finalDay + 1];
        // Here, we ignore the zeroth index too!
        int i = finalDay;
        int dayPtr = n-1;
        while(i >= firstDay)
        {
            if(i == days[dayPtr]) {
                int oneDayBuy = costs[0];
                if(i+1 <= finalDay) {
                    oneDayBuy += minCosts[i+1];
                }
                int sevenDayBuy = costs[1];
                if(i + 7 <= finalDay)
                    sevenDayBuy += minCosts[i+7];
                int thirtyDayBuy = costs[2];
                if(i+30 <= finalDay)
                    thirtyDayBuy += minCosts[i+30];
                int minCost = Math.min(oneDayBuy, Math.min(sevenDayBuy,thirtyDayBuy));
                minCosts[i] = minCost;
                --dayPtr; // proceed to day before now
            } else if ( i > days[dayPtr]){
                minCosts[i] = minCosts[i+1];
            }
            glblCost = minCosts[i];
            --i;
        }
        return glblCost;
    }
}





