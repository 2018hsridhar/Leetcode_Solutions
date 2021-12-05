/*

1665. Minimum Initial Energy to Finish Tasks
URL = https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/

THOUGHT PROCESSES
- we must have an energy at least greater than the minimal activation energy, or >= the maximal activation energy,
so intuition suggests a bias with a summation starting there
- We can take an overall sum of activation energies, OR, of work energies too

So we know this is a greedy problem. That means the following
1. A valid greedy strategy entails the greedy choice property
2. A greedy strategy is POLY time and the problem is expressible as DP/naive recursion
3. The problem features the Optimal Substructure Property

Greedy Strategies
- sort by the actual energies or the minimum energies of each task
- sort by either increasing order or by decreasing order

COMPLEXITY
Let N := len(tasks)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 

TEST CASES
(A) [[1,12],[1,12],[1,12],[1,12],[1,12]]
    16
(B) [[1,9],[1,10],[1,11],[1,12]]
    12
^ notice the distinction here ^ 
(C)
(D)
(E)
(F)


*/
class Solution 
{
    public int minimumEffort(int[][] tasks) 
    {
        
    }
}
