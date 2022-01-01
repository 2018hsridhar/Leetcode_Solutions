/*
29. Divide Two Integers
URL = https://leetcode.com/problems/divide-two-integers/

Ideas :
- We know that dividend and divisor are within the range of the 32-bit signed integer data type
- must check for possibility of quotient being out of range too -> exert caution ( e.g. INT_MAX/0.5 will break badly ! ) 
- We know logarithms, addition, and subtraction are available
- check signages of values too -> review later
- must truncate and remove the digits following the <.> character 

Naive : repeated subtraction -> BUT not going to be efficient ( possible TLE there ) 
We kno the trick for dividing by 2 ( with the bit manipulation there ). Can we express non power of 2 divisors equivalently?
Notice the loss of information if we bit shift the digit by 1 position, and the MSB = 1

    100010101 ( suppose this were max len )
        <<1 left
    000101010 ( the leading 1 MSB was lost here ) 
    ... but if it were a zero, we can keep going
    ... so inspect that!

3 = 2 + 1
15 = 1 + 2 + 4 + 8
With the '1' here, it is a single subtraction operation!

Decimals := a way to represent fractions

COMPLEXITY : 
Let D := dividend, d := divisor, q := resulting quotient
TIME = O()
SPACE = O()

Hints : Math and bit manipulations
Idea : compare bits of zeroes and 1's to quickly check MSBs and LSBs as well
Remember the signage thing though -> must check at the proper parity too!

Represent divisor fitting in as a polynomial as well
    3*3 = 9 = (2+1)*3 as well : leveerage distributive property across the binary digits
                =3*2 + 3 = (3>>1) + (3>>0)
    
    Another example :
        49 in binary = 0b110001
        7 in binary  = 0b111
        49 = 7*7 = 7*(4+2+1) = 7*4 + 7*2 + 7*1 = (7>>2) + (7>>1) + (7>>0)
        7*8 = (7>>3)
        
        Shift   7>>shift
        0       0b000111
        1       0b001110
        2       0b011100
        3       0b111000
                --------
                0b110001
                
The first place where a 1 is in a place where a zero is, as we can leverage summations well!
32 bitshifts := 32 constant number of operations as well
Count and evaluate sum based on size of offsets
Notice the hidden polynomials underneath 

Example_2 :: 
    10 - ( 3>>2) 10 - 6 = 4     ( + 2 ) 
    4 - (3>>1) = 4 - 3 = 1      ( + 1 ) 
    1 - (3>>0) = 1 - 3 should NOT evaluate
    0b0...001 = 1
    0b0...011 = 3 ( 32-bit signed rep underneath )
                                ( + 0 ) 
                                
    


*/
class Solution 
{
    public int divide(int dividend, int divisor) 
    {
        
        
        
        
        
    }
}
