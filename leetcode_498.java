/*
Why were you overcomplicating this problem earlier with the two pointers?
Scales to the row case as well as the column case -> parity still holds here
Evens -> go diag up
Odds -> go diag down
We know the [lower_bound,upper_bound] as well too!

URL = https://leetcode.com/problems/diagonal-traverse/
498. Diagonal Traverse

Complexity : 
Let M := number of rows
Let N := number of columns
Time = O(MN) quadratic time -> must visit and print out each element
Space = O(1) constant time

Edge cases
(A)
(B)
(C)
(D)
(E)
(F)
(G)


*/


class Solution 
{
    public int[] findDiagonalOrder(int[][] mat) 
    {
        int m = mat.length;
        int n = mat[0].length;
        int prod = m * n;
        int sum = (m-1) + (n-1);
        int[] traversal = new int[prod];
        int wIdx = 0;
        // Pretend as if we had a larger matrix and let's just bea  bit inefficient 
        // Or leverage complements of matrix elements instead!
        for(int k = 0; k <= sum; ++k)
        {
            if(k % 2 == 0)      // even : go up-right ( NE ) 
            {
                for(int i = k; i >= 0; --i)
                {
                    int j = (k - i);
                    if(isInBounds(i,j, m, n))
                        traversal[wIdx++] = mat[i][j];
                }
            }   
            else                // odd : go left-down ( SW ) 
            {
                for(int j = k; j >= 0; --j)
                {
                    int i = (k - j);
                    if(isInBounds(i,j, m, n))
                        traversal[wIdx++] = mat[i][j];
                }
            }
        }
        return traversal;        
    }
    
    public static boolean isInBounds(int i, int j, int m, int n)
    {
        if((i >= 0 && i < m) && (j >= 0 && j < n))
            return true;
        return false;
    }
}
