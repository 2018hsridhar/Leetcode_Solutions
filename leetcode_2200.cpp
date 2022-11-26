/*
URL = https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/description/
2200. Find All K-Distant Indices in an Array

*/
class Solution {
public:
    vector<int> findKDistantIndices(vector<int>& nums, int key, int k) {
        set<int> hitIndices;
        int n = nums.size();
        for(int j = 0; j < n; ++j){
            if(nums.at(j) == key){
                for(int i = j - k; i <= j + k; ++i){
                    if(0 <= i && i < n){
                        hitIndices.insert(i);
                    }
                }
            }
        }
        vector<int> indices;
        for(int x : hitIndices){
            indices.push_back(x);
        }
        std::sort(indices.begin(), indices.end());
        return indices;
    }
};
