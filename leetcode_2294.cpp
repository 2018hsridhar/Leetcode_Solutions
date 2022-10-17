/*
2294. Partition Array Such That Maximum Difference Is K
URL = https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/
*/
class Solution {
public:
    int partitionArray(vector<int>& nums, int k) {
        int numParts = 0;
        std::sort(nums.begin(), nums.end());
        int lPtr = 0;
        int rPtr = 1;
        int n = nums.size();
        while(rPtr < n){
            if(nums.at(rPtr) - nums.at(lPtr) <= k){
                rPtr++;
            } else {
                numParts++;
                lPtr = rPtr;
                rPtr += 1;
            }
        }
        numParts++; // ending here
        return numParts;
    }
};
