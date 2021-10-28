/*
Return true IFF we can reach this board position in a valid tic-tac-toe game
tic-tac-toe : 3x3 = 9. now you see!
Board is a (3x3) matrix with three characters only

'X' starts before 'O' starts
It is a (9x9) board :: thus we can treat iterations as if a constant as well


TEST BENCH
PROVIDED ::

(A) board = ["XOX","O O","XOX"]
    true
(B) board = ["XXX","   ","OOO"]
    false
(C) board = ["XOX"," X ","   "]
    false
(D) board = ["O  ","   ","   "]
    false

COMING UP ::
(E) board = ["XOX","OXO","XOX"]
    false
(F) board = ["XXX","OOX","OOX"]
    false
(G) board = ["XXX","OXO","OXX"]
    false

Now passing 7 self-written test cases
(H) ["XXX","XOO","OO "]
    false
(I) ["XXX","OOX","OOX"]
    false


Other Cases
(J) ["X  ","OO "," OX"]
    true
(K) ["X  ","OO "," OX"]
    false
(L) ["XOX","O O","XOX"]
(M) ["   ","   ","   "]
(N) ["OXX","XOX","OXO"]
(O) ["XOX","OOX","XO "]

URL = https://leetcode.com/problems/valid-tic-tac-toe-state/
794. Valid Tic-Tac-Toe State


*/

class Solution 
{
    public boolean validTicTacToe(String[] board) 
    {
        // [1] Modularize this portion
        int countX = 0;
        int countO = 0;
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                if(board[i].charAt(j) == 'X')
                {
                    ++countX;
                }
                else if ( board[i].charAt(j) == 'O')
                {
                    ++countO;
                }
            }
        }
        if(countX == 0 && countO == 0) return true;
        int letDiff = (int)Math.abs(countX - countO);   // as long as inputs always (+) here, distance be accurate 
        if(letDiff >= 2)
        {
            return false;
        }
        if(countO > countX)
            return false;
        if(countX == 0 && countO > 0)
        {
            return false;
        }
        
        // [2] Modularize this portion : check the configs
        // [1] Use a hashmap ( pre-init ) 
        // [2] Code up extra .contains() methods
        HashMap<Character,Integer> encount = new HashMap<Character,Integer>();
        encount.put('X',0);
        encount.put('O',0);
        encount.put(' ',0); // empty character literal support lacking as well? Only space
        
        // Check verticals
        for(int j = 0; j < 3; ++j)
        {
            if(board[0].charAt(j) == board[1].charAt(j) && board[1].charAt(j) == board[2].charAt(j))
            {
                char elem = board[0].charAt(j);
                encount.put(elem, encount.get(elem) + 1);
            }
        }
        
        // Check horizontals
        for(int i = 0; i < 3; ++i)
        {
            if(board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2))
            {
                char elem = board[i].charAt(0);
                encount.put(elem, encount.get(elem) + 1);
            }
        }
        
        // Check diagonals
        char elem = board[1].charAt(1);
        if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2))
        {
            encount.put(elem, encount.get(elem) + 1);
        }     
        if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0))
        {
            encount.put(elem, encount.get(elem) + 1);
        }     
        
        // Check frequency of values in the hashmap now
        if(encount.get('X') >= 1)
        {
            if(encount.get('O') >= 1 || countX <= countO)
            {
                return false;
            }
        }
        if(encount.get('O') >= 1 && countO < countX)
        {
            return false;
        }
        return true;
    }
}
