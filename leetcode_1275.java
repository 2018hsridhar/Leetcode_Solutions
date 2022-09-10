/*
1275. Find Winner on a Tic Tac Toe Game

*/
class Solution {
    public String tictactoe(int[][] moves) 
    {
        String[][] board = new String[3][3];
        // [1] ""-initialize the board
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                board[i][j] = "";
            }
        }
        
        boolean isX = true; // X plays first
        for(int i = 0; i < moves.length; ++i) {
            int[] move = moves[i];
            if(isX) {
                board[move[0]][move[1]] = "X";
            } else {
                board[move[0]][move[1]] = "O";                
            }
            isX = !isX; // toggle state here
        }
        
        // Check each row/col if they all match : and if so, how!
        if(haveThree(board,"X"))
        {
            return "A";
        }
        if(haveThree(board,"O"))
        {
            return "B";
        }
        
        if(moves.length < 9)
            return "Pending";
        return "Draw";
    }
    
    // you could hardcode it . . . but can we be faster?
    // hmmmm
    private boolean haveThree(String[][] board, String ch) {
        // Check all rows
        for(int i = 0; i < 3; ++i)
        {
            boolean haveARow = true;
            for(int j = 0; j < 3; ++j)
            {
                if(!board[i][j].equals(ch))
                    haveARow = false;
            }
            if(haveARow)
                return true;
        }
        
        // Check all cols
        for(int j = 0; j < 3; ++j)
        {
            boolean haveACol = true;
            for(int i = 0; i < 3; ++i)
            {
                if(!board[i][j].equals(ch))
                    haveACol = false;
            }
            if(haveACol)
                return true;
        }
        
        // Check the two diagonals
        boolean haveAForwardDiagonal = true;
        boolean haveABackwardsDiagonal = true;
        for(int i = 0; i < 3; ++i) {
            if(!board[i][i].equals(ch))
                haveAForwardDiagonal = false;
            if(!board[i][2 - i].equals(ch))
                haveABackwardsDiagonal = false;
        }
        if(haveAForwardDiagonal || haveABackwardsDiagonal)
            return true;
        return false;
    }
}
