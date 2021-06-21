/*
 *
 * URL = https://leetcode.com/problems/sign-of-the-product-of-an-array/
 *
 * It is a trick : do you really have to calculate the product
 * Seems more mathematical in nature too!
 * Avoid overflow : use BigInteger class, or make all nums (-1,1) only
 * Even better : based this off properties we know!
 * 1822. Sign of the Product of an Array
 */

class Solution 
{
    public int arraySign(int[] nums) 
    {
        int countPos = 0;
        int countNeg = 0;
        for(int i : nums)
        {
            if(i == 0)
                return 0;
            else if ( i < 0)
                ++countNeg;
            else
                ++countPos;
        }
        if((countPos != 0 && countNeg == 0) || ( countPos != 0 && countNeg % 2 == 0))
            return 1;
        return -1;
    }
}
