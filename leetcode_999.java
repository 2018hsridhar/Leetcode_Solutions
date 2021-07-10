/*

URL = https://leetcode.com/problems/available-captures-for-rook/
999. Available Captures for Rook

Dealing with only one chess board at a time
Board is only 4 configurations/pieces - { rook, bishop, pond, or empty }
Only one cell on the (8x8) board is a rook
No need to really recurse or do fancy DP here

Edge cases : 
(1) Rooks at edges of game
(2) Rooks at the corner of boards
(3) Rooks in the center as usua
(4) No ponds or bishops on the board

Num available captures : bounded from [0,4] only
(8x8) chessboard is indicative of a near constant time approach anyways

If two ponds in line - do not attack - stop at first pond
If bishop encountered -- stop loop iterations too



*/

class Solution 
{
    public int numRookCaptures(char[][] board) 
    {
        int numPondsCaptured= 0;        
        // [1] Find out which piece is the rook
        int[] rook_pos = new int[]{-1,-1};
        for(int i = 0; i < board.length; ++i)
        {
            for(int j = 0; j < board[0].length; ++j)
            {
                if(board[i][j] == 'R')
                {
                    rook_pos[0] = i;
                    rook_pos[1] = j;
                    break;
                }
            }
        }
                
        // Check north south cardinal direction
        int[] nPos = new int[]{rook_pos[0]-1, rook_pos[1]};
        int[] sPos = new int[]{rook_pos[0]+1, rook_pos[1]};
        while(nPos[0] >= 0)
        {
            char cell = board[nPos[0]][nPos[1]];
            if(cell == 'p')
            {
                ++numPondsCaptured;
                break;
            }
            else if ( cell == 'B')
                break;
            --nPos[0];
        }
        while(sPos[0] < board[0].length)
        {
            char cell = board[sPos[0]][sPos[1]];
            if(cell == 'p')
            {
                ++numPondsCaptured;
                break;
            }
            else if ( cell == 'B')
                break;
            ++sPos[0];
        }
        
        // Check east-west cardinal directions
        int[] wPos = new int[]{rook_pos[0], rook_pos[1]-1};
        int[] ePos = new int[]{rook_pos[0], rook_pos[1]+1};
        while(wPos[1] >= 0)
        {
            char cell = board[wPos[0]][wPos[1]];
            if(cell == 'p')
            {
                ++numPondsCaptured;
                break;
            }
            else if ( cell == 'B')
                break;
            --wPos[1];
        }
        while(ePos[1] < board.length)
        {
            char cell = board[ePos[0]][ePos[1]];
            if(cell == 'p')
            {
                ++numPondsCaptured;
                break;
            }
            else if ( cell == 'B')
                break;
            ++ePos[1];
        }
        
        
        return numPondsCaptured;
    }
}
