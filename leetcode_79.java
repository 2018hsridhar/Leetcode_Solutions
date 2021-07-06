/*
THOUGHT PROCESS : 
- First establish that the first character of the word exists in this board
- Then perform depth search and see if one can find the letter in this board ( 4 cardinal directions )  [ recursive in nature ] 
- Akin to the Game "Connect 4"
- Do not use same cell more than once
- Yes strings can have repeating characters

Ideal computational complexity : Time = O(4^N), Space = O(1) where N = number of characters in word
WE know m,n are reasonable here [ boudned by the closed interval of integers {1,6}]
Word length also reasonable [ bounded by len = 15 ] 
Range of letters reasonable - only lowercase/uppercase here

In recursive method : pass in the index of existing depth
Akin to flood-fill/level-order BFS solutions

Board is sufficiently tiny, so a O(MN) lookup for first letter is not unreasonable performance here :-)
Use Character.MIN_VALUE / empty character literal trickto prevent searching over same block again
In many backtracking algorithms, lists of current candidates/state space need to be passed in as a global parameter

Careful here : BFS/DFS != typical backtracking. Backtracking is more index built - it's not a list of candidates at each level
Your skeleton here will tank you boyo!


Edge case testing : 


*/
class Solution 
{
    public boolean exist(char[][] board, String word) 
    {
        // [1] Check if first character of word even exists on the board. Make sure to add all candidates which can be the first character too!
        ArrayList<int[]> candidates = new ArrayList<int[]>();
        char firstLet = word.charAt(0);
        int m = board.length;
        int n = board[0].length;
        boolean foundFirstLet = false;
        int[] firstPos = new int[2];
        for(int i = 0; i < m; ++i)
        {
            for(int j = 0; j < n; ++j)
            {
                if(board[i][j] == firstLet)
                {
                    foundFirstLet = true;
                    candidates.add(new int[]{i,j});
                }
            }
        }
        if(foundFirstLet = false)
            return false;
        
        // [2] now recurse 
        int depth = 0;
        return backtrack(board, word, depth, candidates);
    }
    
    public boolean backtrack(char[][] board, String word, int depth, ArrayList<int[]> candidates)
    {
        // [1] Check if already at maximum possile depth allowed ( assuming obtained fully positive string )
        if(depth == word.length())
            return false; // passed our boundary now!
        
        // [2] Check current candidates set now and FLAG them to not be visited again!
        ArrayList<int[]> nextLevel = new ArrayList<int[]>();
        for(int i = 0; i < candidates.size(); ++i)
        {
            int[] curCand = candidates.get(i);
            int x = curCand[0];
            int y = curCand[1];
            char myLet = board[x][y];
            System.out.printf("At (%d,%d) let = %s depth = %d\n", x,y,myLet, depth);
            if(word.charAt(depth) == myLet && depth == word.length() - 1)
                return true;
            
            if(word.charAt(depth) == myLet)
            {
                board[x][y] = Character.MIN_VALUE;
          
                // [3] Perform bounds check on other candidates and add them to new list

                // West
                if((x-1) >= 0 && board[x-1][y] != Character.MIN_VALUE)
                    nextLevel.add(new int[]{x-1,y});

                // East
                 if((x+1) < board.length && board[x+1][y] != Character.MIN_VALUE)
                    nextLevel.add(new int[]{x+1,y});

                // North
                if( (y-1) >= 0 && board[x][y-1] != Character.MIN_VALUE)
                    nextLevel.add(new int[]{x,y-1});

                // South
                 if((y+1) < board[0].length && board[x][y+1] != Character.MIN_VALUE)
                    nextLevel.add(new int[]{x,y+1});
            }
        }
        
        return backtrack(board, word, ++depth, nextLevel); // short eval here going on! OMG ( helper || foundEval )
    }
}












