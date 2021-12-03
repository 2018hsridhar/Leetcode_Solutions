/*

URL = https://leetcode.com/problems/find-the-middle-index-in-array/
1991. Find the Middle Index in Array

Relative ordering of original inputs must be preserved : do NOT mutate it
Return the leftmost case ( or -1, if iteration be done )
Check return type of function - is it a pointer or a value?

IDEAL ASYMPTOTIC COMPLEXITY
Let N := #-elems in the list
TIME = O(N)
SPACE = O(1)

CONSTRAINTS ANALYSIS
(a) Num elements <= 100. POLY-TIME works well
    When n = 100K, O(N^2) quad time takes 10 seconds. So we're good
    
In algo, compute the RHS summation first. Then start decrement as we progress here
We have no empty array cases as well

TEST CASES :
(A) [1,-1,0,1,-1,0,1,-1,0,1,-1]
    Valid middle Indices = 2,5,8
    Return {2} here
    Test alternating case
(B) [2,5]
    Return {-1}
(C) [1]
    Return 0
(D) [-1,-2,-3,6,0,0,0,0]
    Return 4
(E) [0,0,0,0,-1,-2,-3,6]
    Return 0

*/

int findMiddleIndex(int* nums, int numsSize)
{
    int leftMostIdx = -1;
    int* leftSum = (int*)malloc(sizeof(int) * 1);
    int* rightSum = (int*)malloc(sizeof(int) * 1);
    *leftSum = 0;
    *rightSum = 0;
    // Pass 1 : O(N) : do not count the 0-index case here!
    for(int i = 1; i < numsSize; ++i)
    {
        *rightSum += *(nums+i);
    }
    if(*leftSum == *rightSum)
    {
        return 0;
    }
    // Pass 2 : O(N)
    // Offset by index val = 1 here ( we handled 0 case earlier ) 
    for(int mid = 1; mid < numsSize; ++mid) // prefix eval here for <mid>
    {
        *rightSum -= *(nums+mid);
        *leftSum += *(nums + (mid - 1));
        if(*leftSum == *rightSum)
        {
            return mid;
        }
    }
    return leftMostIdx;
}
