/*

THOUGHT PROCESSES : 

Trick : Approach is a Greedy Algorithm
- Iterative-based solution
- Finds local optima from candidates in each iteration : ignores global optima
- Solve subproblems later
- Fastest amongst optimization methods
- Do we obtain a global optimum [ Kruskal/Prim ] or a local optimum? Hey - Krushkal/Prim are greedy!
- Ask if we take into account history from preceding array, or not?

2 transactions : store two indices or two scalars?
- one pair for buy time
- other pair for sell time

Base case : array length = 1/2 ( no null/0-length arrays exist here ) 

IDEAL performance : [T,S] = [O(n), O(1)], where n = number of elements. Linear scan the solution
We know prices are bounded by the closed interval [ 0, 10^4 ]


EDGE cases : 
[7,1,5,3,6,4]
[7,1,5,3,1,6,4]
[7,1,5,3,6,2,10] - 8 + 3 + 4 = 15 ( not just 10-1 = 9 )
[7,8,4,5,2,3] - also all local maximas = 1 + 1 + 1 = 3
[7,1,5,3,2,1,0] - continuous decreasing subsequence 
[7,1,5,3,4,5,6] - continuous increasing subsequence : a min and max can be established here?



-> How to distinguish here after processing [7,1,5]?
-> Processing subarray [3,1,6,4] 



[7,1,5,6,4]
[7,1,4,5,6,4]
[7,1,4,5,6,10]

(10-1) = 9
(10-5) + (4-1) = 5 + 3 = 8
Seems from [min,max], we are bounded actually!
Notice the "sell before you buy" requirement!

In each case, we purchase at <1> UNTIL we hit a local maxima!
Because we want to find the date we can do up to the best




*/


class Solution {
    public int maxProfit(int[] prices) {
        
    }
}
