/*

THOUGHT PROCESS :
URL = https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
762. Prime Number of Set Bits in Binary Representation


Inclusive : <= VERSUS exclusive : < 

Desired computational complexity : 
Time = O(Nsqrt(N)), where N := range cardinality ( right - left + 1 ) 
Space= O(1) 

Edge case testing
(1) Range = singleton elements ( left=right=1, left=right=10 )
(2) Range = all primes {2,3}
(3) Large volume - [left = 1, right = 1,000,000 ]

In this case, 1 is not prime

*/


class Solution 
{
    public int countPrimeSetBits(int left, int right) 
    {
        int setPrimes = 0;
        int x = 127;
        for(int i = left; i <= right; ++i)
        {
            int countBits = countNumSetBits(i);
            if(isPrime(countBits)) ++setPrimes;
        }
        return setPrimes;
    }
    
    // In this case, 1 => false, but 2,3 => true
    public boolean isPrime(int x)
    {
        if(x == 1) return false;
        if(x <= 3) return true;
        int upperLim = (int)Math.sqrt(x);
        for(int i = 2; i <= upperLim; ++i)
            if(x % i == 0) return false;
        return true;
    }
    
    
    // Working with unsigned integer here
    // BUT values in range never go past (2^31)-1
    // count LSB here ( rightmost bit )
    public int countNumSetBits(int x)
    {
        int numSetBits = 0;
        while(x > 0)
        {
            int lsb = (x & 0b1);
            if(lsb == 1) ++numSetBits;
            x >>>= 1; // Divide by 2 for next value ( is arithmetic shift operation )
        }
        return numSetBits;
    }
    
}
