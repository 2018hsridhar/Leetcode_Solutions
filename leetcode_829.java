/*

URL = https://leetcode.com/problems/consecutive-numbers-sum/
829. Consecutive Numbers Sum

COMPLEXITY
Let N := the number provided
TIME = O(K)
    -> even though the decrement grows ... it is a linear sequence, in terms of longest sum length
    -> and in worst case [1], K = N here
SPACE = O(1) ( explicit ) O(1) ( implicit ) 

TEST CASES
(A) n = 1
    =>1
(B) n = 2
    =>1
(C) n = 3
    =>2
(B) n = 5
    =>2
(C) n = 4
    => 0
(C) n = 9
(D) n = 14
(E) n = 15
    => 4
(F) n = 10e3 = 1,000
(G) n = 10e6 = 1,000,000
(H) n = 10e8 = 100,000,000
(I) n = 10e9 = 1,000,000,000

Test power in series : we note how they get stopped as well too
10e9 possible integer elmeents in a contiguous array?
Yep their outputs do included the default case of \sum{i=1}{1} here as well!
Yep no negative integers here as well :-)

Strategies : Recursion, DP, Iteration, Combinations
(a) Just check the modulus != 0 here
(b) Leverage lang's library


Note use of the "double" data type here as well 

*/
class Solution 
{
    public int consecutiveNumbersSum(int n) 
    {
        int numWays = 0;
        // double dividend = n;
        int dividend = n;
        int curEl = 1;
        int divisor = 1;
        int quotient = 1;
        // Not really an optimization to scope out the loop local variables here :-P
        while(dividend >= 0)
        {
            dividend -= curEl;
            if(dividend < 0)
            {
                break;
            }
            // double divisor = curEl;
            // double quotient = dividend / divisor;
            
            divisor = curEl;
            quotient = dividend / divisor;
            // System.out.printf("Dividend = %s \t Divisor = %s \t Quotient = %s\n", dividend, divisor, quotient);
            // boolean isInt = (quotient == (int) quotient);
            boolean isInt = (dividend % divisor == 0);
            if(isInt == true)
            {
                ++numWays;
            }
            curEl += 1;
        }
        return numWays;
    }
}
