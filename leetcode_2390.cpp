/*
URL = https://leetcode.com/problems/removing-stars-from-a-string/
2390. Removing Stars From a String

This is straight up a stack problem; be greedy here
Complexity
Let N := len(s)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    string removeStars(string s) {
        int n = s.size();
        std::stack<char> stk;
        for(int i = 0; i < n; ++i){
            if(s[i] == '*'){
                if(stk.size() > 0){
                    stk.pop();
                }
            } else {
                stk.push(s[i]);
            }
        }
        vector<char> resChars;
        while(stk.size() > 0){
            resChars.push_back(stk.top());
            stk.pop();
        }
        std::string result(resChars.rbegin(), resChars.rend());
        return result;
    }
};
