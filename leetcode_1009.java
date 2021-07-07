/*
1009. Complement of Base 10 Integer

When asked questions on the complement of 

*/

class Solution 
{
    public int bitwiseComplement(int n) 
    {
        int num = n;
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
