/*

69. Sqrt(x)
URL = https://leetcode.com/problems/sqrtx/

THOUGHT PROCESS : 
The only one of doing this quickly - without the actual square root functino - is via binary saearch
Use a distance heuristic , as we do not have exactness, for values 'x' where ( x * x <= target ) 
Even if distance heuristic is lower, for a greater value, ignore greater values . E.g. 

h(3,8) = |9-8| = 1, but 9 > 8, so no
h(2,8) = |4 - 8| = 4, but 2 < 8, so yes
h(1,8) = |1 - 8| = 7; now sure, 1 < 8, but ( 7 > 4 )  in above situation
distiance heuristic measure will work, as bSearch convergences when the pointers equal one another eventually

Target search space : [1,x]. We know sqrt is bounded within this range  ( especially for x = 1, sqrt(x) = 1 = x ).

Edge cases : 
x = 1
x = 2
x = 4
x = 8
x = 16
x = 2^(31-1) : ouch large integer case -> will not work well here! Exert caution!
How to avoid doing 2^(31-1) * 2^(31-1) issue?
(a) Use bigger data type, such as double or long ( handles 64-bit case ) 

(b) Limit state space search from 1 to some number which when squares, do not break 2^(31-1). Can we find this out too? Actually yes - power of 2 series trick
sqrt(2^(31)-1) = 46,340 ( so can do this ) 

Time Complexity = binary search = O(LogX)
Space Complexity = O(1) : need only local function variables to store memory

*/

class Solution 
{
    public int mySqrt(int x) 
    {
        double low = 1;
        double high = x;
        double heuristic = Integer.MAX_VALUE;
        int sqrt = bSearchForSqrt(x,low,high, heuristic);
        return sqrt;
    }
    
    // Iterativev binary Search can support state space for heuristic approach
    // Recursive binary search will not support is as well though :-( 
    // The benefits of iterative search : support for keeping track of more state in a global scope ( vs. function scope limitations ) 
    //2147483647 = 2^31 -1 = possible fro TLE/failng problem
    // Wrong answer for 4 here. Woah!
    // Data types really will trip you up here!
    
    public int bSearchForSqrt(int x, double low, double high, double heuristic)
    {
        double myH = heuristic;
        double curBes = 0;
        while(low <= high)
        {
            double mid = (int)low + (int)((high - low)/2);
            double square = mid * mid;
            if(square == x)
            {
                return (int)mid;    
            }
            else if(square > x)
            {
                high = mid - 1;
            }
            else // square < x here
            {
                int manDistToTarget = (int)Math.abs(x - square);
                if(manDistToTarget < heuristic)
                {
                    heuristic = manDistToTarget;
                    curBes = mid;
                    low = mid + 1;
                }
            }
        }
        // Assuming low = high, but never returned actual square - return curBes
        return (int)curBes;
        
    }
    
}
