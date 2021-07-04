/*
504. Base 7
URL = https://leetcode.com/problems/base-7/
Time Complexity : O(log_7(n)) : not log_2(n) here
Space Complexity : O(log_7(n)) : resutant StringBuilder object's internal string will be of this length!
*/


class Solution
{
    public String convertToBase7(int num) 
    {
        StringBuilder sb = new StringBuilder("");
        boolean negFlag = false;
        if(num < 0)  
        {
            negFlag = true;
            num = Math.abs(num);
        }
        
        while(num >= 7)
        {
            int modRes = num % 7;
            sb.append(modRes);
            num /= 7;   
        }
        sb.append(num);
        if(negFlag)
            sb.append("-");
        return sb.reverse().toString();
            
    }
}
