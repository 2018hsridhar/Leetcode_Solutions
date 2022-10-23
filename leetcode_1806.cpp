/*
1806. Minimum Number of Operations to Reinitialize a Permutation
URL = https://leetcode.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
*/
class Solution {
public:
    int reinitializePermutation(int n) {
        if(n == 2) return 1; // a base case : can't do well here
        int numOps = 1;
        int initIdx = 1;
        int nextIdx = 2;
        int divNum = (n/2)-1;
        int delta = 0;
        int firstOdd = 1;
        while(nextIdx != initIdx){
            if(nextIdx <= divNum){
                nextIdx *= 2;
                numOps++;
            } else {
                delta = nextIdx - divNum;
                nextIdx = firstOdd + (delta-1) * 2;
                numOps++;
            }
        }
        return numOps;
    }
};
