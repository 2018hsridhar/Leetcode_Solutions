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
/*
1665. Minimum Initial Energy to Finish Tasks
URL = https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/

TEST CASES :
PASSES GIVENS and these four ... ok

(A) [[1,2],[3,5],[4,8],[15,17],[2,23],[20,30]]
    => 46
(B) [[1,2],[2,4],[4,6],[4,8]]
    => 12
(C) [[5,9],[20,20],[90,100]]
    => 115
(D) [[2,15],[4,15],[9,15]]
    => 21
(E) [[1,2],[3,5],[4,8],[6,78],[9,99],[1,1],[2,254],[3,46],[4,79],[1,10],[990,1000],[15,17],[2,23],[20,30]]
    => PASS
(F) [[1,2],[3,5],[4,8],[6,78],[5,55],[1,12],[56,678],[23,234],[345,898],[9,99],[1,1],[2,254],[3,46],[4,79],[1,10],[990,1000],[15,17],[2,23],[20,30]]
    => PASS
(E)
(F)
(G)

GOT IT!

[actual_i, minimum_i]

*/
class Solution {
    public int minimumEffort(int[][] tasks) 
    {
        if(tasks == null || tasks.length == 0)
            return 0;
        Arrays.sort(tasks, new Comparator<int[]>(){
            public int compare(int[] taskOne, int[] taskTwo)
            {
                if(taskOne[1] < taskTwo[1])
                    return -1;
                else if ( taskOne[1] > taskTwo[1])
                    return 1;
                else
                {
                    if(taskOne[0] > taskTwo[0])
                        return 1;
                    else if ( taskOne[0] < taskTwo[0])
                        return -1;
                    return 0;
                }
            }
        });
        // Last pointer
        int n = tasks.length;
        int j = n-1;
        int i = j-1;
        int minE = tasks[j][1];
        int[] taskTuple = new int[]{tasks[j][0], tasks[j][1]};
        // for(int i = 0; i < n; ++i)
            // System.out.printf("[%d,%d], ", tasks[i][0], tasks[i][1]);
        while(i >= 0)
        {
            int curDiff = taskTuple[1] - taskTuple[0];
            int[] frontTask = tasks[i];
            if(curDiff < frontTask[1])
            {
                // Change in activation energy AND energy used
                // But energy used is alwasy some summation here : not difficult
                int newEnergyUse = taskTuple[0] + frontTask[0];
                int frontDiff = frontTask[1] - frontTask[0];
                int diffFromFrontToCur = taskTuple[1] - frontDiff;
                int newActivationEnergy = diffFromFrontToCur + frontTask[1];    // initialy biased here, unless we do better
                
                int diffFromTupleToFront = frontTask[1] - curDiff;
                if(diffFromTupleToFront + taskTuple[1] < newActivationEnergy)
                    newActivationEnergy = diffFromTupleToFront + taskTuple[1];
                
                taskTuple[0] = newEnergyUse;
                taskTuple[1] = newActivationEnergy;
            }                
            else
            {
                // No change in activation energy, but change in energy used
                taskTuple[0] += frontTask[0];
            }
            --i;
        }
        minE = taskTuple[1];
        return minE;
    }
}
