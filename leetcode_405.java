/*

URL = https://leetcode.com/problems/convert-a-number-to-hexadecimal/
405. Convert a Number to Hexadecimal

In a typical base conversation ( base_x to base_y ), I could attempt to convert to base_10 and then divide by base_y. But must support SIGNED, NEGATIVE values too -> so it is not as good
Hex trick : 4 binary digits = 1 hex digit too
Try to apply the (1111)_2 bitmask trick here and offset this mask too ( 1111 << i ) type style. 
Set  ( i -= 4 each time though ) 
Use ampersand to truly obtain values ( everything else will zero out fa sure ) 

Iterate from MSB unto the LSB in "num"
Yes - make sure to offset to MSB here this time

Better idea : use C/C++ for embedded systems/bitwise manipulations due to better unsigned or user-defined datatype ranges

Edge cases : 
(a) [1-9] = basic
(b) [10-16] = verify the HEX conversion to letters quicker
(c) -1,-10 = verify handling of 2's complement
(d) Integer.MAX_VALUE and Integer.MIN_VALUE
(e)
(f)
(g)


1111 != 0b1111 ( in this case, equal to 15 ) 
Issue : <int> mask may not work - consider <long> or <unsigned int>?

// Start at i = 28 ( only 8 masks to check out here : 8 x 4 = 32 ) 
// I think it avoids bad overflow too?
// Expect final mask to be a negative value, but not overflow ( due to trailing zeroes :-) )
// Ideally, mask would be unsigned data types ( NOT signed datatypes ) cuz limitations
Mask is good, but must offset resultant too

URL = https://www.vojtechruzicka.com/bit-manipulation-java-bitwise-bit-shift-operations/
/ Trick : se unsigned right shift ( not signed right shift )

Take note of the preservation of signage in some of these operations : the MSB is not always what you think it be!

*/

class Solution 
{
    public String toHex(int num) 
    {
        char[] hexDigs = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if(0 <= num && num <= 9) return num + ""; // quick base case
        StringBuilder sb = new StringBuilder("");
        boolean startAppend = false;        
        int i = 28;
        while(i >= 0)
        {
            int mask = ( 0b1111 << i );
            int resultant = num & mask; // use int of cast to a char or 4-byte long character be better; also unsigned data typs preferrable ( signage can wreck a mask )
            resultant >>>= i;
            // ENSURE to avoid leading zeroes ( extraneous output in this case)
            if(resultant > 0)
                startAppend = true;
            if(startAppend)
                sb.append(hexDigs[resultant]);
            i -= 4;
        }
        
        return sb.toString();
    }
}
