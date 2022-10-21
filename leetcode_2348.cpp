/*
URL = https://leetcode.com/problems/number-of-zero-filled-subarrays/submissions/
2348. Number of Zero-Filled Subarrays

*/
class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        long long numZeroFill = 0;
        long long curZeroLen = 0;
        long long delta = 0;
        for(int i = 0; i < nums.size(); ++i){
            if(nums.at(i) == 0){
                curZeroLen++;
            } else {
                delta = curZeroLen*(curZeroLen+1)/2;
                numZeroFill += delta;
                curZeroLen = 0;
            }
        }
        numZeroFill += curZeroLen*(curZeroLen+1)/2;
        return numZeroFill;
    }
};
