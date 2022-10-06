/*
URL = https://leetcode.com/problems/shift-2d-grid/
1260. Shift 2D Grid

Leverage modulo ops too.
Stress-test : can candidate perform this in-space, with no additional memory footprint involved?
Space = O(1) ( IMP & EXP ) 
Time = O(MN)

*/
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        int m = grid.length; // #-rows = sizeof(col)
        int n = grid[0].length; // #-cols = sizeOf(row)
        int numCells = m * n;
        k = (m*n) - ( k % ( numCells ));
        int startRow = k / n;
        int startCol = k % n;
        // lower right
        List<Integer> curRow = new ArrayList<Integer>();
        for(int i = startRow; i < m; ++i){
            int j = 0;
            if(i == startRow) {
                j = startCol;
            }
            while(j < n){
                curRow.add(grid[i][j]);
                if(curRow.size() == n){
                    ArrayList<Integer> nextRow = new ArrayList<Integer>();
                    nextRow.addAll(curRow);
                    results.add(nextRow);
                    curRow.clear();
                }
                ++j;
            }
        }
        // upper left
        for(int i = 0; i <= m; ++i){
            for(int j = 0; j < n; ++j){
                // check terminating condition does not forget to add a row too
                if(i == startRow && j == startCol){
                    return results;
                } else {
                    curRow.add(grid[i][j]);
                    if(curRow.size() == n){
                        ArrayList<Integer> nextRow = new ArrayList<Integer>();
                        nextRow.addAll(curRow);
                        results.add(nextRow);
                        curRow.clear();
                    }
                }
            }
        }
        
        return results;
    }
}
