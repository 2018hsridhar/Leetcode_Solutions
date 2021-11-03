/*

URL = https://leetcode.com/problems/count-numbers-with-unique-digits/
357. Count Numbers with Unique Digits

The leading 'zero' is a special case of handling here
Luckily, n is reasonably bounded in closed interval [0,8]

TEST BENCH
(A) n = 0
(B) n = 1
(C) n = 2
(D) n = 8
(E) n = 3
    739
    
// non-zero leading : 9 * (9*8*7...)
// You can select for zero only afterwards

*/
class Solution 
{
    public int countNumbersWithUniqueDigits(int n) 
    {
        if(n == 0) return 1;
        if(n == 1) return 10;
        int[] numUniques = new int[n+1];
        numUniques[0] = 1;
        numUniques[1] = 10;
        for(int i = 2; i <= n; ++i)
        {
            int nonZeroStartCount = 1;
            nonZeroStartCount *= 9;
            int numAvail = 9;
            // Must decr by 1 index here at lesat
            for(int j = 2; j <= i; ++j) // should be 648 here
            {
                nonZeroStartCount *= numAvail;
                numAvail--;
            }
            numUniques[i] = numUniques[i-1] + nonZeroStartCount;
        }
        
        return numUniques[n];
    }
}
