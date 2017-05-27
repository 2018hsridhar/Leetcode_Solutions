//https://leetcode.com/problems/array-partition-i/#/description

class Solution {
public:
    int arrayPairSum(vector<int>& nums) 
    {
        int sum = 0;
        if(nums.empty()) // i presume already checks for EMPTY or NULL vector, right?
            return 0;
        std::sort(nums.begin(), nums.end());
        // how to do an iterateo to process every OTHER element? UNSURE!
        for(int i = 0; i < nums.size(); i += 2)
        {
            sum += nums[i];
        }
        
        return sum;

    }
};
