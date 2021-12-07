/*
Use the right bit shift nad the ampersand technique here
Is a counting problem, based on the bit values of (a,b,c) here as well
A toggle of 2 when we need to go from two 1's to two 0's

COMPLEXITY = O(1) Time & Space 

TEST CASES : 
(A) Value of INT_MAX is +2147483647.

(B)
(C)


*/

int minFlips(int a, int b, int c)
{
    int flips = 0;
    for(int i = 0; i < 32; ++i) // 31 positions ( 0 index : need a 0 shift, for a NOP basically ) 
    {
        int a_lsb = (a>>i)&1;
        int b_lsb = (b>>i)&1;
        int c_lsb = (c>>i)&1;
        
        // Test for four combinatinos here
        if(a_lsb == 0 && b_lsb == 0 && c_lsb == 1)
        {
            flips += 1; // Ok so if both = 0, and res = 1 : toggle 1 at least
        }
        else if ( a_lsb == 1 && b_lsb == 0 && c_lsb == 0)
        {
            flips += 1;
        }
        else if ( a_lsb == 0 && b_lsb == 1 && c_lsb == 0)
        {
            flips += 1;
        }
        else if ( a_lsb == 1 && b_lsb == 1 && c_lsb == 0)
        {
            flips += 2;
        }
        
    }
    return flips;
}
