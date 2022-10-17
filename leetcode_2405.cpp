/*
2405. Optimal Partition of String
URL = https://leetcode.com/problems/optimal-partition-of-string/
*/
class Solution {
public:
    int partitionString(string s) {
        int numParts = 1; // default 1
        std::unordered_set<char> visited;
        for(int i = 0; i < s.size(); ++i){
            if(visited.find(s.at(i)) == visited.end()){
                visited.insert(s.at(i));
            } else {
                numParts++;
                visited.clear();
                visited.insert(s.at(i));
            }
        }
        return numParts;
    }
};
