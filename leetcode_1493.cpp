/*
URL = https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
1493. Longest Subarray of 1's After Deleting One Element

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int longest = 0;
        std::vector<int> numLeft;
        std::vector<int> numRight;
        int curLeft = 0;
        int curRight = 0;
        int n = nums.size();
        for(int i = 0; i < n; ++i){
            if(nums.at(i) == 0){
                curLeft = 0;
            } else {
                curLeft++;
            }
            numLeft.push_back(curLeft);
        }
        for(int i = n-1; i >= 0; --i) {
            if(nums.at(i) == 0){
                curRight = 0;
            } else {
                curRight++;
            }
            numRight.push_back(curRight);
        }
        std::reverse(numRight.begin(), numRight.end());
        for(int i = 0; i < n; ++i){
            int curSubArrLen = 0;
            if(i == 0){
                curSubArrLen = numRight.at(1);
            } else if ( i == n-1){
                curSubArrLen = numLeft.at(n-2);
            } else {
                curSubArrLen = numLeft.at(i-1) + numRight.at(i+1);
            }
            longest = std::max(longest, curSubArrLen);
        }
        return longest;
    }
};
