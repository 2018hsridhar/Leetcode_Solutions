/*
URL = https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/
1863. Sum of All Subset XOR Totals

*/
class Solution {
public:
    // oh it's all subsets!
    int subsetXORSum(vector<int>& nums) {
        int n = nums.size();
        int initIdx = 0;
        int initXorNum = 0;
        int subsetSum = recurse(nums,initIdx, initXorNum);
        return subsetSum;
    }

private:
    int recurse(vector<int>& nums, int idx, int xorNum){
        if(idx == nums.size()){
            return xorNum;
        } else {
            int choseNum = xorNum ^ nums.at(idx);
            int notChoseNum = xorNum;
            return recurse(nums,idx+1,choseNum) + recurse(nums,idx+1,notChoseNum);
        }
    }
};
