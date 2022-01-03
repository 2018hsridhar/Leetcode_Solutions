/*

URL = https://leetcode.com/problems/knight-probability-in-chessboard/
688. Knight Probability in Chessboard

Def a recursive algo
n := dimensions of board
k := number_move remaining ( k = 0 , de-facto, returns a one )
    Probability on board with 0 moves : stay in place = 1

Constraints :
n <= 25, k <= 100 : should fit in memory
Must perform bounds check and counting too
Indepdent probabiltiies : they do sum up here
Careful with constraint : (row,col) can equal n too ( * could be typo )?

COMPLEXITY ( DP ) 
TIME = O(N^2*K)
SPACE = O(NK)

BUDP may be more inefficient than TD memo : must solve for entirety of board
Guaranteed square chessboard too based on <n>

TEST CASES : 
(A) 1,0,0,0
    => 1
(B) 1,0,0,2
    => 0
(C) 2,1,0,0 => 0, but 2,0,0,0 => 1
(D) 3,2,0,0
(E) 25,10,12,12
    => scale test
    => correct
(F)

*/
class Solution 
{
    public double knightProbability(int n, int k, int row, int column) 
    {
        int[][] moves = new int[][]{{-2,-1},{-1,-2},{1,-2},{2,-1},{-2,1},{-1,2},{1,2},{2,1}};
        double[][][] memo = new double[n][n][k+1]; // zero intializated alreayd ( k + 1, as can make 0/1 moves )
        double prob = recurse(n,k,row,column,moves,memo);
        return prob;
    }
    
    private double recurse(int n, int k, int row, int col, int[][] moves, double[][][] memo) 
    {
        double prob = 0;
        double multiplier = (double)1/8;
        if(memo[row][col][k] != 0)
        {
            return memo[row][col][k];
        }
        else
        {
            if(k == 0)
            {
                prob = 1;
            }
            else if ( k > 0 )
            {
                for(int j = 0; j < moves.length; ++j)
                {
                    int newR = row + moves[j][0];
                    int newC = col + moves[j][1];
                    boolean inRows = (0 <= newR && newR < n);
                    boolean inCols = (0 <= newC && newC < n);
                    if(inRows && inCols)
                    {
                        prob += multiplier * recurse(n,k-1,newR,newC,moves, memo);
                    }
                }

            }
            memo[row][col][k] = prob;
        }
        return prob;
    }
}
