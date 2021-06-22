//453. Minimum Moves to Equal Array Elements

/*

Current Ideas : 

Base case : 1 or 2 number elements
Single el case : 0 number of moves needed total
Two el case : the difference is needed?

Based on two-el case : do we utilize differences for our solution?
E.g. [1,100] = > 99 moves needed or [0,1] => 1 move needed

Frequency impacts, BUT, only in certain cases
E.g.. NM([1, 100]) = NM([1,1,1,100]) = NM([1,1,........, 100]) = 99

But introduce a new unique element, and now the num moves changes substantially
E.g. NM([1,2,100]) = [100,101,100] + [101,101,101] = 100 moves total
But say, [1,2,2,100] = [100,101,101,100] = [101,101, 102, 101] = [102,102,102,102] = 99 + 2 = 101 moves total

Or say, [1,98,100] = [98,98,197] = [197,197,197] = 97 + 99 = 196 moves total? How derived though? Possiblility is that 196 = (98-1)+(100-1). Account based on the min element?




Suppose elements are sorted : thsi will make it easier to process, from the min element to the max element

Idea 1 : does the frequency impact this?
Idea 2 : Suppose we introduce new element, and then updates it's frequency? What impact does this have?
Idea 3 : When solving for sequences with a resultant count, can we set up our inputs to generate a consecutive sequence? E.g. I want inputs that deliver {3,4,5,6} - how to set up?
Idea 4 : 2/3 are your magic testing numbers ( one is even : the other is odd ) 

NM([1,2]) = 1
NM([1,2,2]) = 1 + NM([2,2,3]) = 2
NM([1,2,2,2]) = 1 + NM([2,2,3,3]) = 2 + NM([3,3,3,4]) = 3
NM([1,2,2,2,2]) = 1 + NM([2,2,3,3,3]) = 2 + NM([3,3,3,4,4]) = 3 + NM([4,4,4,4,5]) = 4

NM([1,2,4]) = 3
NM([1,2,2,4]) = 4
NM([1,2,2,2,4]) = 5
NM([1,2,2,2,2,4]) = 6



Notice the increment with each '2' or '3' added here
*/
/*

453. Minimum Moves to Equal Array Elements
https://leetcode.com/problems/minimum-moves-to-equal-array-elements/

THOUGHT PROCESS : 

In such as optimizing number of operations, try a greedy approach : what is the dumbest idea?

Notwithstanding initial merge sort time-space complexity [ O(NlogN), O(N)] pre-processing
Ideal [T,S] = [O(N), O(1)] : traverse all elemenets of the array

Test cases : 
[1,4,2,7,3,9,4,100,62,35]
[1,3]
[1,1,3]
[1,1,1,1,3]

[1,3,3]
[1,3,3,3]
[1,3,5,7]

[1,100]
[1,99,100]

*/


class Solution 
{
    public int minMoves(int[] nums) 
    {
        int numMoves = 0;
        Arrays.sort(nums);
        int initVal = nums[0];
        for(int i = 1; i < nums.length; ++i)
            numMoves += nums[i] - initVal;
        return numMoves;
    }
}
