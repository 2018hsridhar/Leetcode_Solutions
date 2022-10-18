/*
1685. Sum of Absolute Differences in a Sorted Array
URL = https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
*/
class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        size_t n = nums.size();
        vector<int> absSum = vector<int>(n,0);
        // reserve not working : why is this?
        // absSum.reserve(n); // reserve to prealloc mem: fine since final size known!
        int vecSum = getSum(nums);
        // lPtr
        int count = n-1;
        for(int lPtr = 0; lPtr < n; ++lPtr){
            absSum.at(lPtr) = vecSum - (count*nums.at(lPtr));
            count--;
            vecSum -= nums.at(lPtr);
        }
        // rPtr
        // avoid bad double counting too!
        count = n-1;
        vecSum = getSum(nums);
        for(int rPtr = n-1; rPtr >= 0; --rPtr){
            absSum.at(rPtr) += (count * nums.at(rPtr)) - vecSum;
            count--;
            vecSum -= nums.at(rPtr);
        }
        return absSum;
    }
    
private:
    // Maybe have a std::sum(begin,end) func too?
    int getSum(vector<int>& nums){
        int sum = 0;
        for(const int x : nums){ // trivially-copyable : no need for ref heres
            sum += x;
        }
        return sum;
    }
};
