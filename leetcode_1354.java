/*
1354. Construct Target Array With Multiple Sums
URL = https://leetcode.com/problems/construct-target-array-with-multiple-sums/

COMPLEXITY
Let N := len(target)
Let S := initial max sum
Time = O(Slog(N))
Space = O(N) [ heap is always this much space ]

TEST CASES
(A)
(B)
(C)
(D)
(E)

Ideas : Combinations Testing, Recursion, Mathematical Equation, Greedy ( we need to hit that minima ), Summations Storage,
Maximal Testing
So a greedy esque strategy, or an equations test, is actually what led to the idea of a max heap here.

*/

class Solution 
{
    public boolean isPossible(int[] target) 
    {
        if(target == null || target.length == 0)
        {
            return true; 
        }
        else if(target.length == 1)
        {
            return (target[0] == 1);
        }
        // INVARIANT : target len = 2 here at least
        boolean isPossible = false;
        int n = target.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        int finalSum = n;
        int initSum = 0;
        for(int i = 0; i < n; ++i)
        {
            // *(target+(i)*sizeOf(int)) address dereferencing requires the nest to fully eval here
            int elem = target[i];
            initSum += elem;
            pq.offer(elem);
        }
        while(initSum > finalSum)
        {
            if(initSum < finalSum)
            {
                return false;
            }
            
            
        }
        
        return isPossible;
    }
}
