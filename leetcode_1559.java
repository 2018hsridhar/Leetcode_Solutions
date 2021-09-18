/*Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:

https://assets.leetcode.com/uploads/2020/07/15/1.png

Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:
https://assets.leetcode.com/uploads/2020/07/15/11.png


Example 2:
https://assets.leetcode.com/uploads/2020/07/15/22.png


Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:
https://assets.leetcode.com/uploads/2020/07/15/2.png

Return if a cycle exists BUT not each different cycle here!


Example 3:

https://assets.leetcode.com/uploads/2020/07/15/3.png

Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 500
1 <= n <= 500
grid consists only of lowercase English letters [a-z]

Grid is a char : use 'X' to denote visited grid element


Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
*/
class Solution {
    boolean hasCycle = false;
  
    public boolean containsCycle(char[][] grid) 
    {
      boolean[][] visited = new boolean[grid.length][grid[0].length];
      for(int i = 0; i < visited.length; ++i)  
      {
        for(int j = 0; j < visited[0].length; ++j)  
        {
            visited[i][j] = false;
        }
      }   
      // Denote original parent as (-1,-1) here
      hasCycle = false;
      int pI = -1;
      int pJ = -1;
      for(int i = 0; i < grid.length; ++i)
      {
        for(int j = 0; j < grid[0].length; ++j)
        {
          // if(grid[i][j] != 'X') // MASSIVE BUG HERE WITH SET VISITATIONS
          if(visited[i][j] == false) // MASSIVE BUG HERE WITH SET VISITATIONS
          {
              // System.out.printf("Invoking BFS again from scratch at coords = (%d,%d)\n", i, j);
              bfs(visited, grid, i, j, pI, pJ, grid[i][j]);              
          }
        }
      } 
      return hasCycle;
    }
    
    
    /*
    bfs(grid, 1,1,-1,-1,a)
        bfs(grid, 2,1,1,1,a)
            bfs(grid, 3,1,1,1,a)
                ...
            bfs(grid, 2,0,1,1,a)
                ...
            bfs(grid, 2,2,1,1,a)
                ...
            bfs(grid, 1,1,1,1,a)
                X ( can not since we colllide with the parent )
                
    Parent=>Child=>Grandchild
    Check if (Grandchild == Parent ) 
    
    */
  
  public void bfs(boolean[][] visited, char[][] grid, int i, int j, int pI, int pJ, char explChar)
  {
    // Head recursion : process parent
    // System.out.printf("Executing bfs at indices = (%d,%d)\n", i, j);
    if(i == pI && j == pJ)
        return;
    
      // Terminating condition was tripping you up badly
    if(visited[i][j] == true)
     return;
    if(visited[i][j] == false)
        visited[i][j] = true;

    
  // Watch the local variables of your function call stack here
  // Useful to set up breakpoints at each recursive call BTW!
      
    // Then consider neighborhood of exploration
    // But only if the characters themselves are the same as current character
    if(i-1 >= 0 && grid[i-1][j] == explChar)
    // Logic statement tripped you up badly as well here : predicate logic matters boyo! && (i-1 != pI && j != pJ))
    {
      if(!(i-1 == pI && j == pJ) && visited[i-1][j]) 
      {
        hasCycle = true;
        return;
      }
        else
        bfs(visited, grid, i-1, j, i ,j, explChar);        
    }
    if(i+1 < grid.length && grid[i+1][j] == explChar)// && i+1 != pI && j != pJ)
    {
      if(!(i+1 == pI && j == pJ) && visited[i+1][j])
      {
        hasCycle = true;        
        return;
      }
    else
        bfs(visited, grid, i+1, j, i,j, explChar);
    }
    if(j-1 >= 0 && grid[i][j-1] == explChar)// && i != pI && j-1 != pJ)
    {
        if(!(i == pI && j-1 == pJ) && visited[i][j-1])
        {
            hasCycle = true;        
            return;
        } 
        else
            bfs(visited, grid, i, j-1, i,j, explChar);
    }
    if(j+1 < grid[0].length && grid[i][j+1] == explChar)// && i != pI && j+1 != pJ)
    {
        if(!(i == pI && j+1 == pJ) && visited[i][j+1])
        {
            hasCycle = true;        
            return;
        } 
        else
            bfs(visited, grid, i, j+1, i,j, explChar);
    }
          
      // your explChar check is wrecking you boyo! SHIT!
      // local variable really train wrecking traversal here
  }
  
  
}
