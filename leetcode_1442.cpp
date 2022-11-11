/*
Length of 300 only
URL = https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/
1442. Count Triplets That Can Form Two Arrays of Equal XOR

15 mins 

Complexity : TIME = O(N^2), SPACE = O(1) ( EXP/IMP 0 )
Leverage running XORs



*/
class Solution {
public:
    int countTriplets(vector<int>& arr) {
        int numTriplets = 0;
        int n = arr.size();
        for(int i = 0; i < n; ++i){
            int leftXor = 0;
            for(int j = i; j < n - 1; ++j){
                leftXor ^= arr.at(j);
                int rightXor = 0;
                int k = j + 1;
                while(k < n){
                    rightXor ^= arr.at(k);
                    if(leftXor == rightXor){
                        numTriplets++;
                    }
                    ++k;
                }
            }
        }
        return numTriplets;
    }
};
