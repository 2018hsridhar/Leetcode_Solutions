/*
1128. Number of Equivalent Domino Pairs
URL = https://leetcode.com/problems/number-of-equivalent-domino-pairs/

Thought process : 
1. Summation of dominoe values - [1,12] here : but can have multiple sums too, so take note of that
-> Gets problematic with middle summations though


2. Let us generation sums maps

2 => [1,1]
3 => [1,2], [2,1] 
4 => [1,3], [3,1], [2,2]
5 => [1,4],[4,1],[2,3],[4,3]
6 => [1,5],[5,1],[2,4][4,2],[3,3] 

Idea #2 : sort and compare to previous elements ( after a global sort operation )?

A lot of dominos are possible here
Domino values are reasonable too : [1,9] only

Computational complexity :

Pairwise comparisons : O(N^2) due to N(N-1)/2 unique number of domino pairings here
Space Complexity, with pairwise comparison approach = O(1) though ... may run into a RLE error

Edge case testing  :

Use an matrix instead of a hashmap approach, and add indices as we go? Is a 9x9 only!


*/

class Solution
{
    public int numEquivDominoPairs(int[][] dominoes) 
    {
        int numPairs = 0;
        int[][] matrix = new int[10][10];
        for(int i = 0; i < matrix.length; ++i)
            for(int j = 0; j < matrix[0].length; ++j)
                matrix[i][j] = 0;
        
        for(int i = 0; i < dominoes.length; ++i)
        {
            int[] domino = dominoes[i];
            int x = domino[0];
            int y = domino[1];
            ++matrix[x][y];
        }
        
        // Iterate over upper triangle only
        for(int i = 0; i < 10; ++i)
        {
            for(int j = i; j < 10; ++j)
            {
                int dominoCount = matrix[i][j];
                if(i != j)
                    dominoCount += matrix[j][i];
                numPairs += (dominoCount*(dominoCount - 1) / 2); // n choose 2 type of thing now!
            }
        }
        return numPairs;
        
   
