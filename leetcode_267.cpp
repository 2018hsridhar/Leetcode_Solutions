/*
URL = https://leetcode.com/problems/palindrome-permutation-ii/
267. Palindrome Permutation II
Strategies : backtracking, freq map, generation type of thing
Exploit the `any order` property as well :-)

*/
class Solution {
public:
    vector<string> generatePalindromes(string s) {
        std::vector<int> freqMap(26);
        vector<string> palins;
        for(char c : s) {
            freqMap[c-'a']++;
        }
        bool hitOdd = false;
        for(int i : freqMap){
            if(i % 2 == 1){
                if(!hitOdd) hitOdd = true;
                else if ( hitOdd )  return {}; // no possible palindroms
            }
        }
        vector<char> lhs;
        vector<char> rhs;
        helper(freqMap,s,lhs,rhs,palins);
        return palins;
    }
    
private:
    void helper(std::vector<int>& freqMap, string s, vector<char>& lhs, vector<char>& rhs, vector<string>& palins){
        int oddIdx = -1;
        bool haveOdd = false;
        bool exhaustMap = true;
        for(int i = 0; i < freqMap.size(); ++i){
            char let = static_cast<char>(i + 'a');
            int freq = freqMap[i];
            if(freq >= 2){
                exhaustMap = false;
                // recurse to the child
                vector<int> childMap(freqMap);
                vector<char> childLhs(lhs);
                vector<char> childRhs(rhs);
                childMap[i] -= 2;
                childLhs.push_back(let);
                childRhs.insert(childRhs.begin(), let);
                helper(childMap, s, childLhs, childRhs, palins);
            } else if ( freq % 2 == 1){
                oddIdx = i;
                haveOdd = true;
            }
        }
        // We stop here and concat now
        if(exhaustMap){
            if(haveOdd) {
                string repChar = std::string(freqMap[oddIdx], oddIdx + 'a'); 
                string newPalin = string(lhs.begin(), lhs.end()) + repChar + string(rhs.begin(), rhs.end());
                palins.push_back(newPalin);
            } else {
                string newPalin = string(lhs.begin(), lhs.end()) + string(rhs.begin(), rhs.end());
                palins.push_back(newPalin);
            }
        }
    }
};
