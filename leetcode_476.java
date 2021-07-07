/*

476. Number Complement
URL = https://leetcode.com/problems/number-complement/

Thought process : 
- Know the base of the number your are working with ( is it base-10, base-2, base-16 ( hex ), or another base )
- Know the log_base(x) formula
- Know we have a depth / max number of digits to represent a number in a given base too

Find out if this be a 1's-complement or a 2's-complement.
- Guaranteed fit in a 32-bit signed integer : range = [-(2^31),(2^31)-1] : one of these reduces as it must be the zero value accordingly, and we use power of 2 series trick and the leading "0" for the signing too.
- Find deriving unsigned range easier : deriving signed range is a bit trickier ( for the unsigned subrange too ) : use offset trick here!


32-bit signed integers : start 
Can assume no leading zero bit in integer's binary representation ( for now! ) in the end output


*/

// Dattype is an signed int : not an unsigned int
// Does not store up to 4, 294, 967, 295 : stores up till a different value
// Expect a 0 at most significant bit location too!


// Safely assume you work with only positive number here too ( thus, at minimum, one leading zero will exist in the solution )

class Solution 
{
    public int findComplement(int num) 
    {
        if(num == 0) return 1;
        else if ( num == 1 ) return 0;
        int i = 31; // max shift to ever go up to is (n-1) in an unsigned integer ( try this way )
        boolean oneFlag = false;
        int complement = 0;
        while(i >= 1)
        {
            int leftDig = ((num>>i) & 1);
            int rightDig = ((num>>(i-1)) & 1);
            // System.out.printf("(leftDig, rightDig ) = (%d,%d)", leftDig, rightDig);
            // System.out.printf("At bit positions (%d,%d), digits = (%d,%d)\n", i, i-1, leftDig, rightDig);
            if ( rightDig == 1)
            {
                oneFlag = true;
                i = i-1;
                break;
            }
            --i;
        }
        
        // at ith digit = 1 : proceed with complement operations
        // Aim to toggle in-place ( no auxillary memory allocated here )
        if(oneFlag)
        {
            while(i >= 0)
            {
                int pow2 = (1 << i);
                num ^= pow2;
                --i;
            }
        }
        return num;
        
    }
}
