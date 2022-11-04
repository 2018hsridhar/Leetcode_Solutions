/*
477. Total Hamming Distance

THOUGHT PROCESSES : 
Neato property of the summation of a bounded arithmetic progression of natural numbers from {1,...,n}
(a) Represents the maximal number of edges |E| = |V^2-1| in a complete graph K_n ( assuming undirected graph with no self-loops/repeating edges too )
(b) Represnts the number of unique pairs in a given collection

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

6 mins to solution :-)
*/


class Solution {
public:
    int totalHammingDistance(vector<int>& nums)
    {
        int totalHamDist = 0;
        // int n = 9;
        // System.out.printf("For num - %d ham dist = %d\n", n, getNumberOneBits(n));
        int n = nums.size();
        vector<int> countZeroAtIndices(32,0);
        vector<int> countOneAtIndices(32,0);        
        for(int i = 0; i < n; ++i)
        {
            // https://www.geeksforgeeks.org/c-bitset-and-its-application/
            bitset<32> bset(nums.at(i));
            for(int i = 0; i < 31; ++i){
                if(bset[i] == 1){
                    countOneAtIndices.at(i)++;
                } else if ( bset[i] == 0){
                    countZeroAtIndices.at(i)++;
                }
            }
        }
        for(int i = 0; i < 31; ++i){
            totalHamDist += countOneAtIndices.at(i) * countZeroAtIndices.at(i);
        }
        return totalHamDist;
    }
};
