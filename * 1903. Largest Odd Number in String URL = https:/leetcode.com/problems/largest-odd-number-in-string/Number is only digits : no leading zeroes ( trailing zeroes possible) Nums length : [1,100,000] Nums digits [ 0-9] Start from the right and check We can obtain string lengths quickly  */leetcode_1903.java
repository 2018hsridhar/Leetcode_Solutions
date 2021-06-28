/*
1903. Largest Odd Number in String
URL = https://leetcode.com/problems/largest-odd-number-in-string/

Number is only digits : no leading zeroes ( trailing zeroes possible)
Nums length : [1,100,000]
Nums digits [ 0-9]
Start from the right and check
We can obtain string lengths quickly

*/

class Solution 
{
    public String largestOddNumber(String num) 
    {
        int finalIdx = num.length() - 1;
        for(int i = num.length() - 1; i >= 0; i--)
        {
            if(Character.getNumericValue(num.charAt(i)) % 2 == 1)
            {
                finalIdx = i;
                break;
            }
            else
                --finalIdx;
        }
        return num.substring(0,finalIdx +1);
        
    }
}
