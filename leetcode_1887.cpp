/*
1887. Reduction Operations to Make the Array Elements Equal
URL = https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/

(A) [5,4,3,2,1] => 10
(B) [5,3,4,3,3] => 
*/
class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        int numRedOps = 0;
        std::sort(nums.begin(), nums.end());
        int numRunSteps = 0;
        for(int i = 1; i < nums.size(); ++i){
            if(nums.at(i) == nums.at(i-1)){
                numRedOps += numRunSteps;
            }
            else if(nums.at(i) > nums.at(i-1)){
                numRunSteps++;
                numRedOps += numRunSteps;
            }
        }
        return numRedOps;
    }
};
