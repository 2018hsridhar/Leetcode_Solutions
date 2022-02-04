/*
502. IPO
URL= https://leetcode.com/problems/ipo/

Strategies : Maximization => greedy, DP, recursion/backtracking
Fits into RAM : profits/capital at least 0, at most some decent amount
w = some amount = starter capital
-> (ith project, ith capital) => select for well too!

COMPLEXITY
Let N := #-projects, K := #-distinct projects to select for
TIME = O()
SPACE = O() ( CALL STK ) O() ( EXP AUX ) 

* when a project is finished, it's pure profit is added to your total capital
* no capital is ever lost 
* maybe even at a higher capital, we still select for lower capital entry capital projects.
* always expect the provided exampes to be FAR too trivial as well

TEST CASES
(A)
(B)
(C)
(D)
(E)
(F)

*/
class Solution 
{
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) 
    {
        
    }
}
