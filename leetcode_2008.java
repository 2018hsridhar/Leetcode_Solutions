/*
2008. Maximum Earnings From Taxi
URL = https://leetcode.com/problems/maximum-earnings-from-taxi/

Provided n points on a grid with points on road labeled from {1,n} in direction you traverse
Pick up passengers : direction immutable!
0-indexed 2D int array : a ride from start_i to end_i, willip to given tip_i
We have the earnings equation too
One passenger max @ a time

Ret max dollars earnable via an optimal pick up assignemnt
Can drop off AND pick up at same point too!
Can we sort the rides array ahead of time ( by their start_i's), which denote each passenger's pick up point?

All inputs will fit into RAM
start_i < end_i
N := length(road)
M := num(rides)
TIME = O()
SPACE = O()

TEST CASES:
(A)
(B)  [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
    ( *** correct this case *** )
(C)
(D)

Imagine if we had drive from right -> left : we have nested intervals, as usual!
End always guaranteed to be within n as well
Ride lengths guaranteed validity too!
Do I need a O(N) solution instead of O(N^2) now? huh?
Do I need to also sort by ending times as well ( to expedite traversal ) ?
Your hint is to apply binary search as well

*/
class Solution 
{
    public long maxTaxiEarnings(int n, int[][] rides) 
    {
        // So the array is sorted by the starts -> try to leverage a slight optimization, but unsure?
        long maxEarnings = 0;
        Arrays.sort(rides, new Comparator<int[]>()
        {
            public int compare(int[] ride1, int[] ride2)
            {
                if(ride1[0] < ride2[0])
                    return -1;
                else if ( ride1[0] > ride2[0])
                    return 1;
                else
                {
                    if(ride1[1] < ride2[1])
                        return -1;
                    else if ( ride1[1] > ride2[1])
                        return 1;
                    else
                        return 0;
                }
                    
            }
        });
        
        // for(int i = 0; i < m; ++i)
        // {
        //     for(int j = 0; j < 3; ++j)
        //         System.out.printf("%d, ", rides[i][j]);
        //     System.out.println();
        // }
        
        // Your code works, but it runs into a runtime TLE exception!
        
        // Fill up base case of final taxi here
        // Is bottom-up DP paradigm
        int m = rides.length;
        long[] DP = new long[m];
        DP[m-1] = rides[m-1][1] - rides[m-1][0] + rides[m-1][2];
        maxEarnings = DP[m-1];
        for(int i = m-2; i >= 0; --i)
        {
            long curEarnings = rides[i][1] - rides[i][0] + rides[i][2];
            long myRideEarnings = rides[i][1] - rides[i][0] + rides[i][2];
            int curEnd = rides[i][1];
            for(int j = i+1; j < m; ++j)
            {
                int nextStart = rides[j][0];
                if(nextStart >= curEnd)
                {
                    curEarnings = Math.max(curEarnings, myRideEarnings + DP[j]); // is a repeat summation -> exert caution
                }
            }
            DP[i] = curEarnings;
            maxEarnings = Math.max(maxEarnings, curEarnings);
        }
        
        return maxEarnings;
    }
}
