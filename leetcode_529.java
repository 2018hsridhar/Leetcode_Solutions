/*

URL = https://leetcode.com/problems/minesweeper/
529. Minesweeper

The Minesweeper game : 

We are provided a next click position among unrevealed squares as a two-value array

Is a typical BFS/DFS problem : return board when no more squares can be revealed

Strategies : Recursive, BFS/DFS, Iterative, Bounds Checking
Must eval if there is a square that can be revealed or not

Luckily, board click is either 'M' or 'E', but do check for 'X' the mines!

Complexity
Let M,N := dimensions of the game board
Time = O(MN)
Space = O(MN) ( implicit ) O(1) ( explicit ) 

Test Cases
(A)
(B)
(C)
(D)
(E)

Ahh : it is an unrevealed mine => changed unto a revealed mine later

*/
class Solution 
{
    // Let us try this coding style :-)
    List<Integer> adj = new LinkedList<Integer>(){[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]};
    public char[][] updateBoard(char[][] board, int[] click) 
    {
        if(board == null)
        {
            return null;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][m]; // default initilized to false
        bfs(board, visited, click);
        return board;
    }
    
    // A void DFS will ensure to terminate our recursive call paths, across EACH independent path as well
    private void dfs(char[][] board, boolean[][] visited, int[] parent)
    {
        // Easier to just do a boundary check at parent, versus at a child
        // Sadly we expense more functino call stacks ( do ask if you mock the question ) 
        int px = parent[0];
        int py = parent[1];
        if(!inMatrix[px][py] || visited[px][py])
        {
            return;
        }
        else
        {
            // Shouldn't we change all the adjacents ... and then perform the BFS/DFS on those unrevelaed squares?
            // First off all -> we evaluate the parent and do the reveal
            // Then we go thru children, IFF they are UNREVEALED empty squares
            // If they are revealed, we no longer BFS/DFS those states!
            // Do make sure to ask if the state we are potentially considering is an unrevealed mine as well
            char state = board[px][py];
            visited[px][py] = true;
            for(int i = 0; i < adj.size(); ++i)
            {
                int[] offset = adj.get(i);
                int[] child = new int[2]{parent[0] + offset[0], parent[1] + offset[1]};
                if(inMatrix(child))
                {
                    int cx = child[0];
                    int cy = child[1];
                    if(board[cx][cy] == 'E')
                    {
                        dfs(board, visited, child);
                    }
                    else if (board[cx][cy] == '')
                }
            }
        }
        return;
    }
    
    // Hey it is an array reference : we're not passing in entire matrices :-)
    private boolean inMatrix(char[][] board, int[] coords)
    {
        int m = boards.length;
        int n = boards[0].length;
        return (0 <= coords[0] < m && 0 <= coords[1] < n);
    }
    
}
