/*
2150. Find All Lonely Numbers in the Array
URL= https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/
*/
class Solution {
public:
    vector<int> findLonely(vector<int>& nums) {
        if(nums.size() <= 1) return nums;
        std::vector<int> lonely;
        std::sort(nums.begin(), nums.end());
        for(int i = 0; i < nums.size(); ++i){
            if(i == 0){
                if(nums.at(0) +2 <= nums.at(1)) {
                    lonely.push_back(nums.at(0));
                }
            } else if ( i == nums.size() - 1){
                if(nums.at(nums.size() - 1) >= nums.at(nums.size() - 2) + 2) {
                    lonely.push_back(nums.at(nums.size() - 1));
                }
            } else {
                int leftNum = nums.at(i-1);
                int rightNum = nums.at(i+1);
                int curNum = nums.at(i);
                if(leftNum == curNum || curNum == rightNum){
                    continue;
                } else if ( leftNum != curNum - 1 && curNum != rightNum - 1){
                    lonely.push_back(curNum);
                }
            }
        }
        return lonely;
    }
};
