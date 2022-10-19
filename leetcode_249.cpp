/*
249. Group Shifted Strings
URL = https://leetcode.com/problems/group-shifted-strings/

Test Cases
(A) ["a"] => ["a"]
(B) ["a","a","a","a"] => [["a","a","a","a"]]
(C) ["a","b","c","d"] => [["a"],["b"],["c"],["d"]]
(D) ["abc","bcd","cde","def"] => all same group
(E) ["abc","ccd","cdf","dez"] => all diff groups

*/
class Solution {
public:
    vector<vector<string>> groupStrings(vector<string>& strings) {
        map<string,vector<string>> myGroups;
        for(auto& s : strings ){
            string key = getKey(s);
            if(myGroups.find(key) == myGroups.end()){
                myGroups[key] = std::vector<string>();
            }
            // printf("Key = %s\n", key.c_str());
            myGroups[key].push_back(s);
        }
        // [2] Convert hashmap to vector of results
        vector<vector<string>> groups;
        for(const auto& [k,v] : myGroups){
            groups.push_back(vector<string>(v));
        }
        return groups;
    }
    
    // Start each input as if it were at 'a'
    // Get a distance from this too!
// Note : this does compile :-O
private:
private:std::string getKey(std::string input)
    {
        // 'a' - 'z' = 97-122
        // You have to do a subtraction
        // but we need to do the `wrap around` logic too.
        int distToA = input.at(0) - 'a';
        vector<char> newChars;
        for(int i = 0; i < input.size(); ++i){
            char c = input.at(i);
            int newAsciiIdx = ((c - 'a') - (distToA) + 26 ) % 26;
            char shiftC = newAsciiIdx + 'a';
            newChars.push_back(shiftC);
        }
        return string(newChars.begin(), newChars.end());
    }
    
};
