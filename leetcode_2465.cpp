/*
2465. Number of Distinct Averages
URL = https://leetcode.com/problems/number-of-distinct-averages/description/
*/
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        std::set<double> myUniqueAverages;
        std::sort(nums.begin(), nums.end());
        int lPtr = 0;
        int rPtr = nums.size() - 1;
        while(lPtr < rPtr){
            double curAvg = ( static_cast<double>(nums.at(lPtr)) + static_cast<double>(nums.at(rPtr)) ) / 2.0;
            myUniqueAverages.insert(curAvg);
            ++lPtr;
            --rPtr;
        }
        return myUniqueAverages.size();
    }
};
