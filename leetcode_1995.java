/*
URL = https://leetcode.com/problems/count-special-quadruplets/
1995. Count Special Quadruplets

COMPLEXITY
Let N := len(nums)
For very small N, we can check all combinations
TIME = O(N^4)
SPACE = O(1) ( EXP & IMP ) 


*/

int countQuadruplets(int* nums, int numsSize)
{
    int count = 0;
    int i = 0;
    while(i < numsSize)
    {
        int* first = (nums + i);
        int firstElem = *first;
        for(int j = i + 1; j < numsSize; ++j)
        {
            int* second = (nums + j);
            int secondElem = *second;
            int k = j + 1;
            while(k < numsSize)
            {
                int* third = (nums + k);
                int thirdElem = *third;
                for(int m = k + 1; m < numsSize; ++m)
                {
                    int* fourth = (nums + m);
                    int fourthElem = *fourth;
                    if(firstElem + secondElem + thirdElem == fourthElem)
                    {
                        ++count;
                    }
                }
                ++k;
            }
        }
        ++i;
    }
    return count;
}
