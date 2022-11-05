/*
1282. Group the People Given the Group Size They Belong To
URL = https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
7 mins to solution :-)
*/
class Solution {
public:
    vector<vector<int>> groupThePeople(vector<int>& groupSizes) {
        vector<vector<int>> myFinalGroups;
        std::unordered_map<int,vector<int>> groupMembers;
        size_t n = groupSizes.size();
        for(int i = 0; i < n; ++i){
            int curGroupSize = groupSizes.at(i);
            if(groupMembers.find(curGroupSize) == groupMembers.end()){
                groupMembers[curGroupSize] = std::vector<int>();
            }
            groupMembers.at(curGroupSize).push_back(i);
        }
        for(const auto& [k,v] : groupMembers){
            int numMembers = v.size();
            size_t step = 0;
            for(int i = 0; i < numMembers/k; ++i ){
                vector<int> newGroup;
                for(int j = 0; j < k; ++j){
                    newGroup.push_back(v.at(step));
                    ++step;
                }
                myFinalGroups.push_back(newGroup);
            }
        }
        return myFinalGroups;
    }
};
