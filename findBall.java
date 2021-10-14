/*

Highly akin to the problem of binomial coefficients
Last row will definitely be the terminating condition / easiest case to handle

URL = https://leetcode.com/problems/where-will-the-ball-fall/
1706. Where Will the Ball Fall

Complexity :
Let M,N := dimensions of your box/grid
Let N := number of <n> balls here
Time = O(MN)
Space = O(N)

Careful with example 2 as well : take heed! 
Must check your neighbor as well too

Edge Case Testing :
(A)
(B)
(C)
(D)


*/

class Solution 
{
    public int[] findBall(int[][] grid) 
    {
        int M = grid.length;
        int N = grid[0].length;
        int[][] DP = new int[M][N];
        
        // 1. Initialize the terminating condition
        
        
        
    }
}
