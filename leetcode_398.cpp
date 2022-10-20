/*
398. Random Pick Index
URL = https://leetcode.com/problems/random-pick-index/
*/
class Solution {
private:
    map<int,vector<int>> idxMap;
public:
    Solution(vector<int>& nums) {
        for(int i = 0; i < nums.size(); ++i){
            int el = nums.at(i);
            if(idxMap.find(el) == idxMap.end()){
                idxMap[el] = vector<int>();
            }
            idxMap[el].push_back(i);
        }
    }
    
    int pick(int target) {
        int val = -1;
        auto itr = idxMap.find(target);
        if(itr == idxMap.end())
            return -1;
        int setSize = idxMap[target].size();
        // cout << setSize << endl;
        int randIdx = (std::rand() % setSize);
        val = idxMap[target].at(randIdx);
        return val;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * int param_1 = obj->pick(target);
 */
