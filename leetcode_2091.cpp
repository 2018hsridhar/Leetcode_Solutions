/*
2091. Removing Minimum and Maximum From Array
URL = https://leetcode.com/problems/removing-minimum-and-maximum-from-array/

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 

12 mins to solution
*/
class Solution {
public:
    int minimumDeletions(vector<int>& nums) {
        if(nums.size() == 1){
            return 1;
        }
        int minDel = std::numeric_limits<int>::max();
        int min = std::numeric_limits<int>::max();
        int max = std::numeric_limits<int>::min();
        int minPos = -1;
        int maxPos = -1;
        for(int i = 0; i < nums.size(); ++i){
            if(nums.at(i) < min){
                min = std::min(min, nums.at(i));
                minPos = i;
            }
            if(nums.at(i) > max){
                max = std::max(max, nums.at(i));
                maxPos = i;
            }
        }
        // 3 types of deletions : 
        // del(left,right), del(left,left), del(right,right)
        // order minPos and maxPos too
        int leftPos= minPos;
        int rightPos = maxPos;
        if(maxPos < minPos){ // flip them
            leftPos = maxPos;
            rightPos = minPos;
        }
        // unique , but handle the singleton case ( single el ) 
        int n = nums.size();
        int delLR = (leftPos + 1) + (n-rightPos);
        int delLL = (rightPos + 1);
        int delRR = (n-leftPos);
        minDel = std::min(delLR, std::min(delLL, delRR));
        return minDel;
    }
};
