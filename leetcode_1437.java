

/*
1437. Check If All 1's Are at Least Length K Places Away
https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/

Ideal Time-Space Complexity : [O(N), O(1)]

All ones also means having no "1" values too!

Test cases : 
Nums len == 1 : return true in case of [1], false in case of [0]


[0]
[1]


[0,0,0,0], k = ANY_VALUE ( may fail if we never even find a oneIdx too! ) 
[1,1,1,1], k = 0


[0,1,0,0]

[1,0,0,0,1,0,0,1]
2

[1,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,1]
4

*/

class Solution 
{
    public boolean kLengthApart(int[] nums, int k) 
    {
        int oneIdx = -1;
        for(int i = 0; i < nums.length; ++i)
        {
            if(nums[i] == 1)
            {
                if(oneIdx == -1)
                    oneIdx = i;
                else
                {
                    if((i-k) > oneIdx)
                        oneIdx = i;                        
                    else
                        return false;
                }
            }
        }
        if(oneIdx == -1) // no ones even encountered in problem space
            return true;
        return true;
        
    }
}
