/*
2340. Minimum Adjacent Swaps to Make a Valid Array
URL = https://leetcode.com/problems/minimum-adjacent-swaps-to-make-a-valid-array/

Greedy : rightmost max & leftmost min

Cases
(A) [5,5,5,5,5] => PASS
(B) [1,2,3,4,5] => PASS
(C) [5,4,3,2,1] => PASS
(D) [3,2,1,6,5,4] => PASS
(E) [3,2,6,5,4,1] => PASS
(F) [3,2,1,6,5,4,6,1] => PASS
(G)
(H)

10 mins

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 

Can we eschew executing swaps
Ask if pos(max) < pos(min) or if pos(min) > pos(max)
    as this means 1 less than expected
*/
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int maxRightMost = INT_MIN;
        int posMaxRightMost = -1;
        int minLeftMost = INT_MAX;
        int posMinLeftMost = -1;
        int n = nums.size();
        for(int i = 0; i < nums.size(); ++i){
            if(nums.at(i) >= maxRightMost){
                maxRightMost = nums.at(i);
                posMaxRightMost = i;
            }
        }
        for(int i = nums.size() - 1; i >= 0; --i){
            if(nums.at(i) <= minLeftMost){
                minLeftMost = nums.at(i);
                posMinLeftMost = i;
            }
        }
        int minSwapsNeeded = (n - 1 - posMaxRightMost) + (posMinLeftMost);
        if(posMinLeftMost > posMaxRightMost){
            minSwapsNeeded--; // take away one
        }
        return minSwapsNeeded;
    }
};
