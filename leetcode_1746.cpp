/*
1746. Maximum Subarray Sum After One Operation
URL = https://leetcode.com/problems/maximum-subarray-sum-after-one-operation/

Complexity
Let N := len(nums)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int maxSumAfterOperation(vector<int>& nums) {
        if(nums.size() == 1)
            return static_cast<int>(pow(nums.at(0),2));
        int myMaxAfterOp = INT_MIN;
        int n = nums.size();
        int leftRunSum = 0;
        int rightRunSum = 0;
        vector<int> leftSums(n,0);
        vector<int> rightSums(n,0);
        // get max left run sums
        // recall Kadane's : you can actually go (-) too!
        for(int i = 0; i < n; ++i){
            int nextLeftRunSum = leftRunSum + nums.at(i);
            if(nextLeftRunSum >= nums.at(i)){
                leftRunSum = nextLeftRunSum;
            } else {
                leftRunSum = nums.at(i);
            }
            leftSums.at(i) = leftRunSum;     
            // printf("i = [%d] \t leftSums = %d\n", i, leftSums.at(i));
        }
        // get max right run sums
        for(int i = n-1; i >= 0; --i){
            int nextRightRunSum = rightRunSum + nums.at(i);
            if(nextRightRunSum >= nums.at(i)){
                rightRunSum = nextRightRunSum;
            } else {
                rightRunSum = nums.at(i);
            }
            rightSums.at(i) = rightRunSum;            
        }
        // get max sums DP style
        for(int i = 0; i < n; ++i){
            int curEl = static_cast<int>(pow(nums.at(i),2));
            if(i == 0){
               myMaxAfterOp = max(myMaxAfterOp, (curEl,curEl + rightSums.at(i+1))); 
            } else if ( i == n-1 ) {
                myMaxAfterOp = max(myMaxAfterOp, (curEl,curEl + leftSums.at(i-1)));
            } else {
                vector<int> combos = {  curEl,
                                        curEl + rightSums.at(i+1), 
                                        curEl + leftSums.at(i-1),
                                        curEl + leftSums.at(i-1) + rightSums.at(i+1)
                                      };
                // printf("sums = [%d,%d]\n", leftSums.at(i-1),rightSums.at(i+1));
                // printf("curMax = %d\n", *std::max_element(combos.begin(), combos.end()));
                myMaxAfterOp = max(myMaxAfterOp,*std::max_element(combos.begin(), combos.end()));
            }
        }
        return myMaxAfterOp;
    }
};
