/*

1085. Sum of Digits in the Minimum Number
https://leetcode.com/problems/sum-of-digits-in-the-minimum-number/

*/
class Solution {
    public int sumOfDigits(int[] nums) 
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i)
            min = Math.min(min, nums[i]);
        
        int digParity = 0;
        int digSum = 0;
        while(min > 10)
        {
            int dig = min % 10;
            digSum += dig;
            min /= 10;
        }
        digSum += min;
        
        if(digSum % 2 == 0)
            digParity = 1;
        else
            digParity = 0;
        
        return digParity;
    }
}
