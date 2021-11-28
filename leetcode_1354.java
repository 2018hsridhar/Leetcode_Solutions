/*
1354. Construct Target Array With Multiple Sums
URL = https://leetcode.com/problems/construct-target-array-with-multiple-sums/

COMPLEXITY
Let N := len(target)
Let S := initial max sum
Time = O(N + Slog(N))
Space = O(N) [ heap is always this much space ]

TEST CASES
(A) [1,100]
    TRUE
(B) [1,100000]
    TRUE
(C) [1,10000000]
    TRUE
(D) [1,50000000]
    TRUE
(E) [1,75000000]
    TRUE, but TLE here

Ideas : Combinations Testing, Recursion, Mathematical Equation, Greedy ( we need to hit that minima ), Summations Storage,
Maximal Testing
So a greedy esque strategy, or an equations test, is actually what led to the idea of a max heap here.

Is right, but ran into a TLE here :-)
target values can get very high [ 10,000,000 ] here :-O
Fast enough for [1,10^7] but NOT [1,10^8]
How to expedite though? Solution is indeed correct.

Without some mathematical background, you may not get past the TLE here ... take close note of this

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
        // O(N)
        for(int i = 0; i < n; ++i)
        {
            // *(target+(i)*sizeOf(int)) address dereferencing requires the nest to fully eval here
            int elem = target[i];
            initSum += elem;
            pq.offer(elem);
        }
        // For some reason, nested if-elseIf-else conditional logic be more desireable
        // O(S)
        while(initSum > finalSum)
        {
            int maxEl = pq.poll();
            int remainderSum = initSum - maxEl;
            int difference = maxEl - remainderSum;
            if(difference < 1)
            {
                return false;
            }
            else
            {
               // O(logN)
                pq.add(difference);
                initSum = initSum - remainderSum;
            }
        }
        // so initSum <= finalSum
        isPossible = (initSum == finalSum);
        return isPossible;
    }
}
