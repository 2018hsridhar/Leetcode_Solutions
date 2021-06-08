/*
1779. Find Nearest Point That Has the Same X or Y Coordinate
https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
*/
class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) 
    {
        int minIdx = -1;
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; ++i)
        {
            int[] myP = points[i];
            int myP_x = myP[0];
            int myP_y = myP[1];
            if(myP_x == x || myP_y == y)
            {
                int curMD = getManhattanDist(x,y,myP_x, myP_y);
                if(curMD < minDist)
                {
                    minIdx = i;
                    minDist = curMD;
                }
            }
        }
        return minIdx;
    }
    
    public int getManhattanDist(int x1, int y1, int x2, int y2)
    {
        return (int)(Math.abs(x2-x1)) + (int)(Math.abs(y2-y1));
    }
    
}
