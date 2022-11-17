/*
It's a brute force problem at its core
Just get the moduland and indexing correctly
Should be classified as EASY
1508. Range Sum of Sorted Subarray Sums


*/
class Solution {
public:
    int rangeSum(vector<int>& nums, int n, int left, int right) {
        int mySum = 0;
        std::vector<int> runSums;
        for(int i = 0; i < n; ++i){
            int curSum = 0;
            for(int j = i; j < n; ++j){
                curSum += nums.at(j);
                runSums.push_back(curSum);
            }
        }
        int moduland = static_cast<int>(pow(10,9)+7);
        std::sort(runSums.begin(), runSums.end());
        for(int a = left-1; a <= right-1; ++a){
            mySum += runSums.at(a);
            mySum %= moduland;
        }
        return mySum;
    }
};
