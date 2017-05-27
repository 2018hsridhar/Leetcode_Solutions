// https://leetcode.com/problems/container-with-most-water/

public class Solution 
{
    // I can assume a mostly equal spacing, in this situation ... I don't have a weird ( here's 2 free spaces ) case
    // unlike the "trapping rain water" case
    // hmm ... advance only one of these pointers ... based on the guy, of min height? 
    public int maxArea(int[] height)
    {
        // example input :: {1,2,3,4,5,5,4,3,2,1}
        int result = Integer.MIN_VALUE;
        int ptr1 = 0;
        int ptr2 = height.length - 1; 
        while(ptr1 <= ptr2)
        {
            int newArea = Math.min(height[ptr2],height[ptr1]) * (ptr2 - ptr1);
            result = Math.max(result,newArea);
            if(height[ptr1] <= height[ptr2])
            {
                ptr1++;
            }
            else
            {
                ptr2--;
            }
        }
        return result; 
    }
}
