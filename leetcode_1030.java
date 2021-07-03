/*

URL = https://leetcode.com/problems/matrix-cells-in-distance-order/
1030. Matrix Cells in Distance Order

Set up an initial grid of values to denote distance ( if value != Integer.MIN_VALUE :: denotes that cell has been visited! ) 

Return coordinates of cells, sorted by distance too 
-> Note that sort need not be in a lexicographic ordering ( e.g. any order be acceptable here ) 
That is, the following two answers be equivalent : 
[[0,1],[0,0],[1,1],[1,0]]
[[0,1],[1,1],[0,0],[1,0]]

Think of edge cases here too!

Ideal [T,S] = O(NM), O(1) , where NM := number of matrix elements
Since this is a recursive algorithm, iterative over children each time - [T] = O(4^k), where k := max-level of the search algorithm

Rows and cols are guaranteed to be reasonable ( bounded by the closed interval [1,100] ) 
(rCenter, cCenter) also guaranteed reasonability

As in former interview : utilize BFS level-order traversal
Solve for the children during each step
Make sure to perform bounds check too
Stop when the candidates set ( initial set, for each level traversed, from the root node ) becomes empty too


*/

class Solution 
{
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) 
    {
        // [1] INITIALIZE a matrix, fully with minimum values
        int[][] matrix = new int[rows][cols];
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                matrix[i][j] = Integer.MIN_VALUE;
        LinkedList<int[]> candidates = new LinkedList<int[]>();
        int[] initPoint = {rCenter, cCenter};
        candidates.addLast(initPoint);
        
        // [2] Execute recursive helper
        int[][] sortedCoords = new int[rows*cols][2];
        int wIdx = 0; //where to commence writes into array each time
        int level = 0;
        helper(matrix, candidates, sortedCoords, wIdx, level);
        
        return sortedCoords;
        
    }
    
    public void helper(int[][] matrix, List<int[]> candidates, int[][] sortedCoords, int wIdx, int level )
    {
        List<int[]> nextLevel = new LinkedList<int[]>();
        if(candidates.size() == 0)
            return;
        // [1] Go over existing candidates
        for(int i = 0; i < candidates.size(); ++i)
        {
            // [1.1] Add existing candidate to sortedCoords array
             int[] myCandidate = candidates.get(i);
            int x = myCandidate[0];
            int y = myCandidate[1];
            // if already visited -> skip its processing
            if(matrix[x][y] != Integer.MIN_VALUE)
                continue;
            
            sortedCoords[wIdx][0] = x;
            sortedCoords[wIdx][1] = y;
            ++wIdx;
            
            // [2] Set matrix space to candidate value
            matrix[x][y] = level;
            
            // [3] Add candidates to new list
            // Check coordinate space and perform bounds checks too
            if((x - 1) >= 0 && matrix[x-1][y] == Integer.MIN_VALUE )
            {
                int[] newCandidate = {x-1,y};
                nextLevel.add(newCandidate);
            }
            if((x + 1) <= (matrix.length - 1) && matrix[x+1][y] == Integer.MIN_VALUE )
            {
                int[] newCandidate = {x+1,y};
                nextLevel.add(newCandidate);
            } 
            if((y - 1) >= 0 && matrix[x][y-1] == Integer.MIN_VALUE )
            {
                int[] newCandidate = {x,y-1};
                nextLevel.add(newCandidate);
            }
            if((y + 1) <= (matrix[0].length - 1) && matrix[x][y+1] == Integer.MIN_VALUE )
            {
                int[] newCandidate = {x,y+1};
                nextLevel.add(newCandidate);
            } 
        }
        helper(matrix, nextLevel, sortedCoords, wIdx, ++level);
    }
}
