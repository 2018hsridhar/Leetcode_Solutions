/*
2180. Count Integers With Even Digit Sum
URL = https://leetcode.com/problems/count-integers-with-even-digit-sum/

*/
class Solution {
    public int countEven(int num) 
    {
        int countEven = 0;
        for(int i = 1; i <= num; ++i)
        {
            if(isEvenDigitSum(i))
                countEven++;
        }
        return countEven;
    }
    
    public boolean isEvenDigitSum(int n)
    {
        int sum = 0;
        while(n >= 10){
            sum += (n % 10);
            n /= 10;
        }
        sum += n;
        return (sum % 2 == 0);
    }
    
    
}
