/*
2023. Number of Pairs of Strings With Concatenation Equal to Target
https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/
*/
class Solution {
public:
    int numOfPairs(vector<string>& nums, string target) {
        int numPairs = 0;
        int n = nums.size();
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                if(i != j){
                    size_t lenI = nums.at(i).size();
                    size_t lenJ = nums.at(j).size();
                    // Use relational operator : will not compare pointers
                    // The relational operators - ==, != - have been overloaded :-)
                    // Eschew strcmp(s1,s2) since thise works only on c-style strings
                    if(lenI + lenJ == target.size()){
                        if(target.substr(0,lenI) == nums.at(i)){
                            if(target.substr(lenI,lenJ) == nums.at(j)){
                                numPairs++;
                            }
                        }
                    }
                }
            }
        }
        return numPairs;
    }
};
