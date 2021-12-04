/*
2016. Maximum Difference Between Increasing Elements

The input size here is up to 1e3 ( 1000 ) 
Therefore, a quadratic algo can work well ( up to 100K is good ), and will not deteriorate.
A cubic can also work well.

Now remember from binomial coefficients : 2^30 is doable, 2^40 is testing it. 
And 2^n = O(n!).

10 digits ( 1 billino ) vs 13 digits ( 1 trilion ) for 2^n, and n = 30/40
Thus, we avoid non-poly time here!
We need n <= 25 for EXP time algos honestly

No data overflow : difference of two positive numbers is in range [-10^9,10^9]
n >= 2 : no edge cases

*/

int maximumDifference(int* nums, int numsSize)
{
    int maxDiff = -1;
    int i = 0;
    while(i < numsSize)
    {
        int j = i + 1;
        int* first = (nums + i);
        int firstEl = *first;
        while(j < numsSize)
        {
            int* second = (nums + j);
            int secondEl = *second;
            if(firstEl < secondEl)
            {
                int diff = abs(secondEl - firstEl); // Auto included <stdlib.h> in C ( maybe even <math.h> for fabs()) 
                if(diff > maxDiff)
                {
                    maxDiff = diff;
                }
            }
            ++j; // expect candidates to make their bugs here with while loops
        }
        ++i;
    }
    return maxDiff;
}
