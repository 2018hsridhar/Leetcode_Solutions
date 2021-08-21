/*

URL = https://leetcode.com/problems/sudoku-solver/
37. Sudoku Solver

THOUGHT PROCESS : 

Is a 9x9 matrix, with 9x9 matrices within themselves too : constant number of operations
Akin to backtracking problems ( think N-queens here ) 
Try recursion - might not actually be too bad, given constant space requirement [ O(1) ]
Try the really dumb idea of filling in a number for each matrix -> generate 9 children for following recursive function calls
Can it be thought of as highly akin to the N-Queens backtracking problem?

Terminating conditions to recursion/backtracking
(a) Enter a number that fails unique row criteria
(b) Enter a number that fails unique col criteria
(c) Enter a number that fails unique ( 9x9 ) criteria
(d) Board is filled up ( no empty spaces ) : is completely valid too

With given inputs ( assuming manner filled out ) -> only one solution tenable

COMPUTATIONAL COMPLEXITY : 
TIME = O(9^d), where d := number of empty spaces on the board ( in worst case, O(9^81))
-> Yes, iterative for loops needed, BUT, constant space and constant time here too
SPACE = O(1) [ 9x9(x9) = 81 char space board is already known ] 

Wait -> how are they even verifying the board here at the end ( no return type ) ? Well -> you verify the board, and type out these characters ( then just return at the end ) !



TEST CASES : 


*/
class Solution 
{
    char[][] masterBoard = new char[9][9];
    public void solveSudoku(char[][] board) 
    {
        // find first (i,j) pair that is empty -> perform funny increment operation along the way too!
        int[] initCoords = solveForNextCoords(board, 0,0);
        solveSudokuHelper(board, initCoords[0], initCoords[1]);
        copyBoard(masterBoard,board);
    }
    
    public int[] solveForNextCoords(char[][] board, int i, int j)
    {
        int[] nextCoords = new int[2];
        while(board[i][j] != '.')
        {
            j += 1;
            if(j > 8)
            {
                j = j % 9;
                i += 1;
            }
            if(i == 9)
            {
                nextCoords[0] = 0;
                nextCoords[1] = 0;
                return nextCoords;
            }
        }
        nextCoords[0] = i;
        nextCoords[1] = j;
        return nextCoords;
    }
    
    // copyBoard helper -> for recursive calls
    public void copyBoard(char[][] srcBoard, char[][] dstBoard)
    {
        for(int i = 0; i < srcBoard.length; ++i)
            for(int j = 0; j < srcBoard[0].length; ++j)
                dstBoard[i][j] = srcBoard[i][j];
    }
    
    // let us suppose that (i,j) is the proper space here too
    public void solveSudokuHelper(char[][] board, int i, int j)
    {
        // System.out.printf("Solving sudokuo helper : (i,j) = (%d,%d)\n", i, j);
        // we are done when sudoku board meets all criteria!
        if(isValidSudoku(board))
        {
            // update global state in process too
            copyBoard(board,masterBoard);
            return;            
        }

        // Check if character/numeric value at index (i,j) is a valid character
        char[] candidates = {'1','2','3','4','5','6','7','8','9'};
        for(char myC : candidates)
        {
            char[][] childBoard = new char[9][9];
            copyBoard(board, childBoard);
            childBoard[i][j] = myC;
            if(isValidSudokuConfig(childBoard))
            {
                int[] nextCoords = solveForNextCoords(childBoard, i, j);
                solveSudokuHelper(childBoard, nextCoords[0], nextCoords[1]);
            }
        }
    }
    
    
    // This code solves the case for a valid sudoku board, but not a complete board!
    // You need an equiavlent for a full sudoku board!!!
     public boolean isValidSudokuConfig(char[][] board) 
     {
        
        int numRows = 9;
        int numCols = 9;
        int dimSubSq = 3;
        boolean statusFlag = true; 
        
        // ASSERT rows, for each row
        for( int i = 0; i < numRows; i++ )
        {
            int[] rowNumFreq = new int[10];
            for ( int j = 0; j < numCols; j++)
            {
                if(board[i][j] != '.') 
                {
                    int value = (int)(board[i][j] - '0');
                    int updatedEntryVal = rowNumFreq[value] + 1;
                    if(updatedEntryVal >= 2 ) {
                        return false;
                    }
                    rowNumFreq[value] += 1;
                }
            }
        }
         
        for( int j = 0; j < numCols; j++)
        {
            int[] colNumFreq = new int[10];
            // assert column
            for( int i = 0; i < numRows; i++ )
            {
                if(board[i][j] != '.') 
                {
                    int value = (int)(board[i][j] - '0');
                    int updatedEntryVal = colNumFreq[value] + 1;
                    if(updatedEntryVal >= 2 ) {
                        return false; 
                    }
                    colNumFreq[value] += 1;
                }
            }
        }
         
        // assert each (3x3) subsquare is valid
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                // loopping over elements in weird (3x3) fashion !
                int[] numFreq = new int[10];
                int startPos_x = 3 * i;
                int startPos_y = 3 * j;
                
                for ( int k = 0; k < 3; k++)
                {
                    for ( int l = 0; l < 3; l++)
                    {
                        int e_x  = startPos_x + k;
                        int e_y = startPos_y + l;
                        if(board[e_x][e_y] == '.') {
                            continue;
                        }
                        else {
                            int value = (int)(board[e_x][e_y] - '0');
                            int updatedEntryVal = numFreq[value] + 1;
                            if(updatedEntryVal >= 2 ) 
                            {
                                statusFlag = false;
                                return statusFlag; 
                            }
                            numFreq[value] += 1;    
                        }
                    }
                }
            }
        }    
        return statusFlag; 
    }
    
    // check validity, but of a FULLY-SOLVED SUDOKU BOARD
    public boolean isValidSudoku(char[][] board) 
     {
        
        int numRows = 9;
        int numCols = 9;
        int dimSubSq = 3;
        boolean statusFlag = true; 
        
        // ASSERT rows, for each row
        for( int i = 0; i < numRows; i++ )
        {
            int[] rowNumFreq = new int[10];
            for ( int j = 0; j < numCols; j++)
            {
                if(board[i][j] != '.') 
                {
                    int value = (int)(board[i][j] - '0');
                    int updatedEntryVal = rowNumFreq[value] + 1;
                    rowNumFreq[value] += 1;
                }
            }
            for(int k = 1; k < 10; ++k)
                if(rowNumFreq[k] != 1)
                    return false;
        }
         
        for( int j = 0; j < numCols; j++)
        {
            int[] colNumFreq = new int[10];
            // assert column
            for( int i = 0; i < numRows; i++ )
            {
                if(board[i][j] != '.') 
                {
                    int value = (int)(board[i][j] - '0');
                    int updatedEntryVal = colNumFreq[value] + 1;
                    colNumFreq[value] += 1;
                }
            }
            for(int i = 1; i < 10; ++i)
                if(colNumFreq[i] != 1)
                    return false;
        }
         
        // assert each (3x3) subsquare is valid
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                // loopping over elements in weird (3x3) fashion !
                int[] numFreq = new int[10];
                int startPos_x = 3 * i;
                int startPos_y = 3 * j;
                
                for ( int k = 0; k < 3; k++)
                {
                    for ( int l = 0; l < 3; l++)
                    {
                        int e_x  = startPos_x + k;
                        int e_y = startPos_y + l;
                        if(board[e_x][e_y] == '.') {
                            continue;
                        }
                        else {
                            int value = (int)(board[e_x][e_y] - '0');
                            int updatedEntryVal = numFreq[value] + 1;
                            numFreq[value] += 1;    
                        }
                    }
                }
                for(int n = 1; n < 10; ++n)
                    if(numFreq[n] != 1)
                        return false;
                
            }
        }
        return true; 
    }
    
    
}
