/*

URL = https://leetcode.com/problems/number-of-digit-one/
233. Number of Digit One

COMPLEXITY
Let N := our number
Let D := #-digits in the number
TIME = O(2*N*D)
SPACE = O(D)

Ideas
- Incorporate DP ( can we solve the problem for fewer than <n> digits as well ) 
- Use for loop for the most significant digit
- We can try some enumerative tricks as well
- Let us assume that we do NOT encounter integer data overflow


TEST CASES
(A) n = 0
    => 0
(B) n = [1,9]
    => 1
(C) n = 10
    => 2
(D) n = 99
    => 20
(E) n = 999
    => 301
(F) n = 9999
    => 4000

Careful note : this is the TOTAL number of digit <1>, appearing in ALL non-negative integers <= ( not < ) the value of n here

*/

class Solution 
{
    public int countDigitOne(int n) 
    {
        // [1] Do some base case handling here, just to account for array length issues
        if(n == 0) 
        {
            return 0;
        }
        else if (n <= 9) 
        {
            return 1;
        }
        int d = (int)Math.floor(Math.log10(n))+1;
        int[] counts10Pows = new int[d+1];  // len = 6, for say, n = 10,000 ( d = 5 = length of radix ) 
        // for less than 1 ( if equal to 0 -> just return 0 then ) 
        counts10Pows[d] = 0; 
        counts10Pows[d-1] = 1; 
        // 10^0 = 1 : not a good combo here!
        System.out.printf("d = [%d]\n", d);
        int exp = 2;
        for(int i = d-2; i >= 0; --i)
        {
            int res = 0;
            int pow10 = (int)Math.pow(10,exp);
            int nines = pow10 - 1; // the all "9"s digits
            while(nines >= 10)  // single digit case no longer reduceable, but values known for sure here
            {
                int moduland = (int)Math.pow(10,exp - 1);   // [99%10 = 9, 9 % 1 = 9 ]
                nines = nines % 
            }
            
            
            
            
            counts10Pows[wIdx] = res;
            wIdx--;
        }
        
        
        // [2] Now actually iterate over <n> and perform computations
        
        
        
        
        int digCount = 0;
        return digCount;
    }
}
