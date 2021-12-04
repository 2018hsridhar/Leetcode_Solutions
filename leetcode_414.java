/*
COMPLEXITY

TIME = O(3N) = O(N)
SPACE = O(1)

Use boolean scoping variables here

*/
class Solution {
    public int thirdMax(int[] nums) 
    {
        // HANDLE your base cases
        if(nums.length == 1)
            return nums[0]; // just single case
        else if ( nums.length == 2)
        {
            if(nums[0] < nums[1])
                return nums[1];
            else
                return nums[0];
        }
        
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;     
        boolean foundFirst = false;
        boolean foundSecond = false;
        boolean foundThird = false;
        
        // [1] Find first max
        for(int i = 0; i < nums.length; ++i)
        {
            int val = nums[i];
            if(val > firstMax)
            {
                firstMax = val;
                foundFirst = true;
            }
        }
        // [2] Find second max
        for(int i = 0; i < nums.length; ++i)
        {
            int val = nums[i];
            if(val < firstMax && val > secondMax)
            {
                secondMax = val;
                foundSecond = true;
            }
        }
        // [2] Find third max
        for(int i = 0; i < nums.length; ++i)
        {
            int val = nums[i];
            if(val >= thirdMax && val < secondMax)
            {
                thirdMax = val;
                foundThird = true;
            }
        }
        
        if(!foundThird)
        {
            return Math.max(firstMax,secondMax);
        }
        else
        {
            return thirdMax;
        }
    }
}
