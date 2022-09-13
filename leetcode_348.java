/*
URL = https://leetcode.com/problems/design-tic-tac-toe/
348. Design Tic-Tac-Toe

Idea
(1) We know to initialize a board
(2) Check a diagonal is done ONLY in some cases!

*/
class TicTacToe {
    
    // default TicTacToe : everything all zeroed out
    int[][] board;

    public TicTacToe(int n) 
    {
        board = new int[n][n];
    }
    
    // return 1 if a winning move
    // return 0 if a loosing move
    public int move(int row, int col, int player) 
    {
        int wonMoveStatus = 0;
        if(player == 1){
            board[row][col] = 1;
            if(checkIfWin(board,row,col,1))
                wonMoveStatus = 1;
        } else if ( player == 2 ) {
            board[row][col] = 2;        
            if(checkIfWin(board,row,col,2))
                wonMoveStatus = 2;
        }
        return wonMoveStatus;
    }
    
    // Player number indicates number to check on the board too!
    // O(N) check operation per `move` operation here too!
    private boolean checkIfWin(int[][] board, int row, int col, int player)
    {
        boolean haveRow = true;
        boolean haveCol = true;
        boolean haveFwdDiag = true;
        boolean haveBkwdDiag = true;
        int n = board.length;

        // Check by row
        for(int c = 0; c < n; ++c){
            if(board[row][c] != player) {
                haveRow = false;
                break;
            }
        }
        
        // Check by col
        for(int r = 0; r < n; ++r){
            if(board[r][col] != player) {
                haveCol = false;
                break;
            }
        }
        
        // Check by fwdDiag
        if(row == col) {
            for(int i = 0; i < n; ++i){
                int j = i;
                if(board[i][j] != player) {
                    haveFwdDiag = false;
                    break;
                }
            }
        } else {
            haveFwdDiag = false;
        }
        
        // Check by bkwdDiag
        if(row == (n - 1 - col )) {
            for(int i = 0; i < n; ++i){
                int j = (n - 1) - i;
                if(board[i][j] != player) {
                    haveBkwdDiag = false;
                    break;
                }
            }
        } else { 
            haveBkwdDiag = false;
        }   
        
        return (haveRow || haveCol || haveFwdDiag || haveBkwdDiag );
    }
    
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
