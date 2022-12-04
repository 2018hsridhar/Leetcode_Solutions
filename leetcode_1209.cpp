/*
1209. Remove All Adjacent Duplicates in String II
URL = https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

Complexity
Let N := len(S)
Time = O(S)
Space = O(S) ( EXP ) O(1) ( IMP ) 

No case where k = 1 fortunatley :-). 
7 mins to solution
*/
class Solution {
public:
    string removeDuplicates(string s, int k) {
        std::stack<pair<char,int>> stk;
        for(char c : s){
            if(stk.empty()){
                stk.push(make_pair(c,1));
            } else {
                pair<char,int>& topFrame = stk.top();
                if(topFrame.first == c){
                    if(topFrame.second + 1 == k){
                        stk.pop();
                    } else {
                        topFrame.second++;
                    }
                } else {
                    stk.push(make_pair(c,1));
                }
            }
        }
        vector<char> finalStrChars;
        while(!stk.empty()){
            for(int a = 0; a < stk.top().second; ++a){
                finalStrChars.push_back(stk.top().first);
            }
            stk.pop();
        }
        std::string result(finalStrChars.rbegin(), finalStrChars.rend());
        return result;
    }
};
