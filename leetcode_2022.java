/*
Thought process
It remains an iterative approach here
We fill by rows ... NOT by columns. 
Modulo operation should be helpful here

Complexity
Let m,n be integers here
Time = O(MN)
Space = O(1) ( explicit & implicit ); O(MN) is the resultant space
Check if possible -> if NOT, return empty 2D ( must assert dimensions here as well ) 
Yes data will fit in memory as well

TEST BENCH
(A) original = [1,2,3,4], m = 2, n = 2
    [[1,2],[3,4]]
(B) original = [1,2,3,4], m = 1, n = 4
    [1,2,3,4]
(C) original = [1,2,3,4], m = 1, n = 4
    [1,2,3,4]
(D) original = [1,2,3,4,5,6], m = 2, n = 3 
    [[1,2],[3,4],[5,6]]
(E) original = [1,2,3,4], m = 4, n = 1
        [[1],[2],[3],[4]]

(F)

2022. Convert 1D Array Into 2D Array
URL = https://leetcode.com/problems/convert-1d-array-into-2d-array/

*/


class Solution 
{
    public int[][] construct2DArray(int[] original, int m, int n) 
    {
        int[][] result = new int[m][n];
        if(original == null)
        {
            return null;
        }
        int orig_len = original.length; // remember : a (len*1) = a (len) product matrix here too!
        if(orig_len != (m*n))
        {
            return new int[0][0];
        }
        int newC = 0;
        for(int i = 0; i < orig_len; ++i)
        {
            int newR = i / n;
            // System.out.printf("new (r,c) = (%d,%d)\n", newR, newC);
            result[newR][newC] = original[i];
            newC = (newC + 1) % n;
        }
        return result;
    }
}
