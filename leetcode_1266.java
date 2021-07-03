/*

1266. Minimum Time Visiting All Points
URL = https://leetcode.com/problems/minimum-time-visiting-all-points/

THOUGHT PROCESSES : 

Ideal computational complexity : T = O(N), S = O(1)
Since points must be visited in their order of appearance, in the array => O(N) should be more possible
Can pass through points appearing later in order again


*/


class Solution 
{
    public int minTimeToVisitAllPoints(int[][] points) 
    {
        int minTime = 0;
        for(int i = 1; i < points.length; ++i)
        {
            int[] p1 = points[i-1];
            int[] p2 = points[i];
            int x_diff = Math.abs(p2[1] - p1[1]);
            int y_diff = Math.abs(p2[0] - p1[0]);
            int diff = Math.max(x_diff, y_diff);
            minTime += diff;
        }
        return minTime;
    }
}
