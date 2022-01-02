/*

137. Single Number II
URL = https://leetcode.com/problems/single-number-ii/

Test cases we expect to be the same irregardless of values placed
(A) [2,2,3,2]
    => 9
(B) [10,10,11,9,11,10,11]
    => 9
(C) [0,-1,0,-1,0,-1,-99]
    => -99 ( err : getting 29 -> investigate this ) 
(D)
(E)

Any array length = 3*k+1, for k \in N, as well!
So k + 1 numbers possible
valArray or bitset classes

Let N := len(list ) 
Even though we go over the values here, it is linear time complexity ( 32 bit ops * N)

Leverage modulo ( expediteable in CPUs ) and that each elem appears exactly three times too
No modulo result should equal two !

*/

#include <iostream>
#include <bitset>
#include <valarray>

// Remember that bitsets work in a different direction too! Account for that!

class Solution {
public:
    int singleNumber(vector<int>& nums) 
    {
        int num = 0;
        int freqCounts[32] = {0}; // MSB -> LSB
        int size = nums.size();
        for(int i = 0; i < size; ++i)
        {
            int elem = nums[i];
            bitset<32> bitwiseRep(elem);
            // printf("Bitwise rep of elem = [%d] equals \t", elem);
            // cout << bitwiseRep << endl;
            // Format specifier for bitsets?
            for(int j = 0; j < 32; ++j)
            {
                int bitVal = bitwiseRep[j];
                if(bitVal == 1)
                {
                    freqCounts[j] += 1;
                }
            }
        }
        
        // OMG zero-initialization here
        // [2] Modulo 3 frequency counts
        for(int i = 0; i < 32; ++i)
        {
            freqCounts[i] = freqCounts[i] % 3;
        }
        
        // [3] Create new num from a bitset
        bitset<32> newNum(0);
        newNum.reset(); // 32 unsigned long
        
        // OMG toggle bit in a bit set and then convert back as well!
        for(int i = 0; i < 32; ++i)
        {
            if(freqCounts[i] == 1)
            {
                newNum.flip(i);
            }
        }
        num = (int)newNum.to_ulong();
        return num;
    }
};
