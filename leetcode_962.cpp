/*
962. Maximum Width Ramp
URL = https://leetcode.com/problems/maximum-width-ramp/

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

Cases
(A) [1,2,3,4,5] => PASS
(B) [5,4,3,2,1] => PASS
(C) [5,5,5,5,5] => PASS
(D) [100,99,1,5,23,56,78,34,23,45,768,905,56] => PASS
(E)
(F)
(G)

25 mins

*/
class Solution {
public:
    int maxWidthRamp(vector<int>& nums) {
        int maxWidth = 0;
        int n = nums.size();
        map<int,int> leftMostPos;
        map<int,int> rightMostPos;
        for(int i = 0; i < n; ++i){
            int el = nums.at(i);
            if(leftMostPos.find(el) == leftMostPos.end()){
                leftMostPos[el] = i;
            }
        }
        for(int i = n-1; i >= 0; --i){
            int el = nums.at(i);
            if(rightMostPos.find(el) == rightMostPos.end()){
                rightMostPos[el] = i;
            }
        }
        vector<int> copyNums(nums);
        std::sort(copyNums.begin(), copyNums.end());
        int i = n-1;
        int idxRightEl = -1;
        int idxLeftEl = -1;
        while(i >= 0){
            if(i > 0 && copyNums.at(i) == copyNums.at(i-1)){
                --i;
            } else {
                int el = copyNums.at(i);
                idxRightEl = std::max(idxRightEl, rightMostPos.at(el));
                idxLeftEl = leftMostPos.at(el);
                // printf("Indices = [%d,%d]\n", idxLeftEl, idxRightEl);
                if(idxLeftEl <= idxRightEl){ // said cond must be met
                    maxWidth = std::max(maxWidth, idxRightEl - idxLeftEl);
                }
                --i;
            }
        }
        return maxWidth;
    }
};
