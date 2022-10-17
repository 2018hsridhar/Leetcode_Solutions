/*
// https://stackoverflow.com/questions/1939953/how-to-find-if-a-given-key-exists-in-a-c-stdmap
1817. Finding the Users Active Minutes
URL = https://leetcode.com/problems/finding-the-users-active-minutes/
*/
class Solution {
public:
    vector<int> findingUsersActiveMinutes(vector<vector<int>>& logs, int k) {
        std::vector<int> uams = std::vector<int>(k,0); // 0-init mem already!
        unordered_map<int,set<int>> mp;
        // We can use another public member func map.count()! 
        // If val used, use the iterator; else, use count ( avoid iterator construction ) 
        for(const auto& log : logs){
            int uid = log.at(0);
            int minAct = log.at(1);
            if(mp.count(uid) == 1){
                mp[uid].insert(minAct);
            } else if ( mp.count(uid) != -1 ) { // why eval expr again?
                mp[uid] = std::set<int>();
                mp[uid].insert(minAct);
            }
        }
        // Use at for bounds checking
        for(const auto& [k,v] : mp){
            uams.at(v.size()-1) += 1;
        }
        return uams;
    }
};
