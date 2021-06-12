/*
258. Add Digits
https://leetcode.com/problems/add-digits/

The digits add is trivial -> based on typical digits iterations anyways
The convergence may be a bit trickier -> we have to keep checking the current num
*/

class Solution 
{
    public int addDigits(int num) 
    {
        while(num >= 10)
            num = sumDigits(num);
        return num;
    }
    
    public int sumDigits(int n)
    {
        int sum = 0;
        while(n >= 10)
        {
            sum += n % 10;
            n /= 10;
        }
        sum += n;
        return sum;
    }
}
