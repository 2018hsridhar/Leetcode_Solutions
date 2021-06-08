/*
1652. Defuse the Bomb
https://leetcode.com/problems/defuse-the-bomb/

Boils down to weird modulo tricks to wrap around arrays
If negative number - add the modulo base, and perform operation again ( because of how your programming language decides to return ) 


*/


class Solution {
    public int[] decrypt(int[] code, int k) 
    {
        int n = code.length;
        int[] result = new int[n];
        for(int i = 0; i < n; ++i)
            result[i] = code[i];
        
        for(int i = 0; i < n; ++i)
        {
            if(k == 0)
                result[i] = 0;
            else if ( k < 0 )
            {
                int posK = -1 * k;
                int sum = 0;
                for(int j = 0; j < posK; ++j)
                {
                    int newIdx = ((i-1-j)+n) % n;
                    sum += code[newIdx];
                }
                result[i] = sum;
            }
            else
            {
                int sum = 0;
                for(int j = 0; j < k; ++j)
                {
                    sum += code[(i+1+j) % n];
                }
                result[i] = sum; 
            }
        }
        return result;
    }
}
