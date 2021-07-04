
/*

477. Total Hamming Distance
URL = https://leetcode.com/problems/total-hamming-distance/

THOUGHT PROCESSES : 
Neato property of the summation of a bounded arithmetic progression of natural numbers from {1,...,n}
(a) Represents the maximal number of edges |E| = |V^2-1| in a complete graph K_n ( assuming undirected graph with no self-loops/repeating edges too )
(b) Represnts the number of all unique pairs in a given collection

Also - hashmaps need O(1) access, O(1) removal, O(1) hashing : thus underlying dstructur must be a static array or a dynamic array

Time-space complexity : 
Time = O(N^2) where N := number of elements/cardinality of nums array
Space = O(1)

Edge cases to test out 
Normal [4,14,2]
Maximal volume :
High numbers : [1000000000,999999]
Zeros case : [0,0,0,0,0,0,0]
(n-1:1) partition case : [0,0,0,0,1]
power-2 series : [1,2,4,8,16,32,64] -> it'll break and equal 0 :-)

*/


class Solution 
{
    public int totalHammingDistance(int[] nums)
    {
        int totalHamDist = 0;

        // The first method of brute-force all unique pairs comparison runs into a TLE - Time Limit Exception
        for(int i = 0; i < nums.length; ++i)
        {
            for(int j = i+1; j < nums.length; ++j) // offset j to (i+1) to start element comparisons there
            {
                int resultant = nums[i] ^ nums[j]; // Denote as "resultant" here, when performing bitwise binary operations
                totalHamDist += getNumberOneBits(resultant);
            }
        }
        
        // Code from the second method : 
        
        return totalHamDist;
    }
    
    // If a method has no method body ( missing but has a valid datatype return NOT equal to void ) - declare abstract isntead
    // Use MSB,LSB - Most Significant Bit, Least Significant Bit - notation when working with binary values
    public int getNumberOneBits(int num)
    {
        int oneBitCount = 0;
        while(num > 0)
        {
            int lsb = num & 1;
            if(lsb == 1) ++oneBitCount;
            num = (num >> 1);
        }
        return oneBitCount;
    }
}
