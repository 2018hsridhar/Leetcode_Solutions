/*

2006. Count Number of Pairs With Absolute Difference K
URL = https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/

CONSTRAINTS : 
1. nums length in closed interval [1,200]
2. Values of array within range [1,100]
3. k within range [1,99]. Differences will always exist as well

TIME
Let N := size of the input
TIME = O(N^2)
SPACE = O(1)

TEST CASES : 
(A) [1,2,3,4,5,6,7,8,9,10], k = 5
    5 

Does problem simplify with a sort of elements into a well-ordering?
Returning a value ( not an address ) 

Make a problem difficult : convert for -> while loop expressions
We can pass in instruction pointers ( function pointers ) in that register as well ( IP ) :-O

C-Library   Func    Input Type    
stdlib.h    abs()   int
math.h      fabs()  double
*/

int countKDifference(int* nums, int numsSize , int k)
{
    int kDiffCount = 0;
    int i = 0;
    while(i < numsSize)
    {
        int j = i + 1;
        int firstElem = *(nums + i);
        while(j < numsSize)
        {
            int secondElem = *(nums + j);
            int diff = secondElem - firstElem;
            // int absDiff = fabs(diff);    // this works, but lack of run-time typecasting makes this a bit dangerous
            int absDiff = (int)fabs(diff);
            if(absDiff == k)
            {
                ++kDiffCount;
            }
            ++j;
        }
        ++i;
    }
    
    return kDiffCount;
}
