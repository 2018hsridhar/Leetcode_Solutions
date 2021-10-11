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

Am I asking to look at price history preceding me here?
A local minima and a local maximum need to be established in comparison to a baseline too!



*/

/*
    Have a set of days corresponding to their prices of given stocks
    Can hold @ most one share of the stock at any time
    Can buy it then sell it on the same day ( but this nets a profit of zero anyways ) 
    Return max profit possible
    
    URL = https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    122. Best Time to Buy and Sell Stock II
    Minima = 0 across the board
    
    Assume prices fits in-mem, and is a reasonable array
    Stock prices are also never negative : [0,1e5] here

    BUDP Complexity : 
    Let N := len(prices)
    Time = O(N^2)
    Space = O(N)

    Edge Case Testing : 
    (A) [3,6,1,4]
        => 6
    (B) [5,4,3,2,1] [a_1,a_2,...] in strictly desc order
        => 0
    (C) [1,1] : [a,a,...]
        => 0
    (D)
    (E)
    
*/

class Solution 
{
    public int maxProfit(int[] prices) 
    {
        int maxProfit = 0;
        int n = prices.length;
        int[] maxProfDays = new int[n];
        maxProfDays[n-1] = 0;
        for(int i = n-2; i >= 0; --i)
        {
            int daysBestProfit = 0;
            int buyPrice = prices[i];
            // [2] Account for no buy on given day i => return (i+1) result here then
            for(int j = i+1; j < n; ++j)
            {
                int sellPrice = prices[j];
                int netProfit = sellPrice - buyPrice;
                if(netProfit > 0)
                {
                    int futureDaysProf = 0;
                    if(j+1 < n)
                         futureDaysProf = maxProfDays[j+1];
                    if((j + 1) == n)
                        futureDaysProf = 0;
                    daysBestProfit = Math.max(daysBestProfit, netProfit + futureDaysProf);
                }
            }
            maxProfDays[i] = Math.max(daysBestProfit, maxProfDays[i+1]);
        }
        
        
        maxProfit = maxProfDays[0];
        return maxProfit;
    }
}
