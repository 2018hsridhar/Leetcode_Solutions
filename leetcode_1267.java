/*
Level order traversal seems easiest here
Also easy to goal 

1267. Count Servers that Communicate
https://leetcode.com/problems/count-servers-that-communicate/


*/
class Solution {
    public int countServers(int[][] grid) 
    {
        int serverCount = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 1) { // we can explore this server
                    boolean hitServer = checkIfServerHit(grid,i,j);
                    if(hitServer)
                        serverCount += 1;
                } else if ( grid[i][j] == 0 || grid[i][j] == 2) {
                    continue; // this server was in communication at an earlier time!
                }
            }
        }
        return serverCount;
    }
    
    public boolean checkIfServerHit(int[][] grid, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        boolean hitServer = false;
        int c = j-1;
        // TO THE LEFT
        while(c >= 0) {
            int val = grid[i][c];
            if(val == 1) {
                return true;
            }
            c--;
        }
        // TO THE RIGHT
        c = j + 1;
        while(c < n) {
            int val = grid[i][c];
            if(val == 1) {
                return true;
            }
            c++;
        }
        // UPWARDS
        int r = i - 1;
        while(r >= 0) {
            int val = grid[r][j];
            if(val == 1) {
                return true;
            }
            r--;
        }
        // DOWNWARDS
        r = i + 1;
        while(r < m) {
            int val = grid[r][j];
            if(val == 1) {
                return true;
            }
            r++;
        }  
        return false;
    }
}
