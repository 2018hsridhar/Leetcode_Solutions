/*
URL = https://leetcode.com/problems/count-number-of-nice-subarrays/
1248. Count Number of Nice Subarrays

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(1) ( EXP & IMPL )

Edge Cases
(A) [1,4,2,10,10,10,4,2,1,3]
2
(B) [1,3,5,10,1,2,4,7,6,4,2]
3
(C) [2,4,6,3,1,5,2,4,2,6,8,10,2,3,1,2,4,6]
3

(D)
(E)

*/
class Solution {
public:
    int numberOfSubarrays(vector<int>& nums, int k) {
        int numSubArrs = 0;
        vector<int> curOddBounds;
        int n = nums.size();
        int lPtr = 0;
        int rPtr = 0;
        int rightDelta = 0;
        int leftDelta = 0;
        while(rPtr < n){
            int el = nums.at(rPtr);
            if(el % 2 == 1){
                if(curOddBounds.size() < k){
                    curOddBounds.push_back(rPtr);
                } else if ( curOddBounds.size() == k) {
                    rightDelta = rPtr - curOddBounds.at(k-1);
                    leftDelta = curOddBounds.at(0) - lPtr + 1;
                    // printf("deltas = left = %d \t right = %d\n", leftDelta, rightDelta);
                    numSubArrs += (leftDelta * rightDelta);
                    lPtr = curOddBounds.at(0) + 1;
                    curOddBounds.erase(curOddBounds.begin());
                    curOddBounds.push_back(rPtr);
                }
            } 
            rPtr++; // avoid TLE in iterative loops
        }
        // final case handling OFC ( check correctness though )!
        if(curOddBounds.size() == k){
            rightDelta = rPtr - curOddBounds.at(k-1);
            leftDelta = curOddBounds.at(0) - lPtr + 1;
            // printf("deltas = left = %d \t right = %d\n", leftDelta, rightDelta);
            numSubArrs += (leftDelta * rightDelta);
        }
        return numSubArrs;
    }
};
