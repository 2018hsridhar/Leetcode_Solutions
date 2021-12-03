/*

URL = https://leetcode.com/problems/1-bit-and-2-bit-characters/
717. 1-bit and 2-bit Characters

CLARIFICATIONS
n = 1000 : O(N^3) perform can work
Bits guaranteed to be [0,1] and can be duplicates as well
The input array is binary, and ALWAYS ends in a zero
-> can <00> or <01> represent bits as well? Is this a trick?
-> are they trying to trick you by the array dimensions as well?
-> <1> is always a starter here ( NEVER at end of array ) 

ASYMPTOTIC COMPLEXITY
Let N := #-bits
TIME = O(N) ( better = O(1) const time, but we showed its impossibility here ) 
SPACE = O(1)

TEST CASES 
(A) bits = [1,1,1,0,1,1,1,0,0]
    TRUE
(B) bits = [1,0]
    FALSE
             --- --- --- - - - -
(C)  bits = [1,0,1,1,1,0,0,0,0,0]
    TRUE { no repeated zeroes }
(D) bits = [0]
    TRUE
(E) bits = [1]
    TRUE
(F) bits = [_,_,_,_,1,0]
(G) bits = [_,_,_,_,0,0]    
    Either a <1> preceded said zero or not here, honestly. Does it simplify to this?
(H) [1,1,1,0,1,1,1,1,0]
    TRUE
    perhaps parity matters too?
    
Do a greedy approach
Correct ptr arithmetic

*/

bool isOneBitCharacter(int* bits, int bitsSize)
{
    bool res = true;
    int i = 0;
    if(bitsSize == 1)
    {
        res = (*bits == 0);   // check if operands FULLY evaluate before the equality == expression
        return true;
    }
    while(i < (bitsSize-1))
    {
        int* firstElemAddr = (bits + i);
        // hey we can go to memory addrs outside array range, and dereference an addr -> value there as well
        if(*firstElemAddr == 1)
        {
            i += 2;
        }
        else
        {
            i += 1;
        }
    }
    if(i == bitsSize - 1)
    {
        res = true;
    }
    else
    {
        res = false;
    }
    return res;
}



