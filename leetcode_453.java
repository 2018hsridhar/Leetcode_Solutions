//453. Minimum Moves to Equal Array Elements

/*

Current Ideas : 

Base case : 1 or 2 number elements
Single el case : 0 number of moves needed total
Two el case : the difference is needed?

Based on two-el case : do we utilize differences for our solution?
E.g. [1,100] = > 99 moves needed or [0,1] => 1 move needed

Frequency impacts, BUT, only in certain cases
E.g.. NUM_MOVES([1, 100]) = NUM_MOVES([1,1,1,100]) = NUM_MOVES([1,1,........, 100]) = 99

But introduce a new unique element, and now the num moves changes substantially
E.g. NUM_MOVES([1,2,100]) = [100,101,100] + [101,101,101] = 100 moves total
But say, [1,2,2,100] = [100,101,101,100] = [101,101, 102, 101] = [102,102,102,102] = 99 + 2 = 101 moves total

Or say, [1,98,100] = [98,98,197] = [197,197,197] = 97 + 99 = 196 moves total? How derived though? Possiblility is that 196 = (98-1)+(100-1). Account based on the min element?




Suppose elements are sorted : thsi will make it easier to process, from the min element to the max element

Idea 1 : does the frequency impact this?

*/

class Solution {
    public int minMoves(int[] nums) {
        
    }
}
