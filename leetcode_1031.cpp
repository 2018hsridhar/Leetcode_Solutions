/*
1031. Maximum Sum of Two Non-Overlapping Subarrays
URL = https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
*/
class Solution {
public:
    int maxSumTwoNoOverlap(vector<int>& nums, int firstLen, int secondLen) {
        int myMaxSum = max(
            getMaxSum(nums,firstLen,secondLen),
            getMaxSum(nums,secondLen,firstLen)
        );
        return myMaxSum;       
    }
    
private:
    int getMaxSum(vector<int>& nums, int firstLen, int secondLen){
        // DIR 1 : firstLen . secondLen
        int myMaxSum = 0;
        int n = nums.size();
        vector<int> DP = vector<int>(n,0);
        int secondArg = 0;
        int count = 0;
        for(int j = n-1; j >= 0; --j){
            if(count < secondLen) {
                secondArg += nums.at(j);
                count++;                
            }
            else {
                secondArg += nums.at(j);
                secondArg -= nums.at(j+secondLen);                
            }
            DP[j] = (j == n-1) ? secondArg : max(DP[j+1], secondArg);
        }
        count = 0;
        int curProblem = 0;
        int curProblemSum = 0;
        for(int i = 0; i < n; ++i){
            if(count < firstLen) {
                curProblem += nums.at(i);
                count++;
            } else {
                curProblem += nums.at(i);
                curProblem -= nums.at(i-firstLen);
            }
            if(i +1 < n){
                curProblemSum = curProblem + DP[i+1];
            }
            myMaxSum = max(myMaxSum, curProblemSum);
        }
        return myMaxSum;
    }
    
    
    
    
};
