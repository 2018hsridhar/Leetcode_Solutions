/*
URL = https://leetcode.com/problems/check-if-a-string-can-break-another-string/description/
1433. Check If a String Can Break Another String
Complexity
Let N := len(S1)
Time = O(N) Space = O(1) ( EXP & IMP ) 

*/
class Solution {
public:
    bool checkIfCanBreak(string s1, string s2) {
        bool stat = true;
        std::sort(s1.begin(), s1.end());
        std::sort(s2.begin(), s2.end());
        size_t n = s1.size(); // assume size same
        if(s1.size() != s2.size()){
            return false;
        }
        // remember : it can go both ways
        // greater heck
        bool s1Great = true;
        bool s1Less = true;
        for(int i = 0; i < n; ++i) {
            if(s1[i] < s2[i]){
                s1Great = false;
                break;
            }
        }
        for(int i = 0; i < n; ++i) {
            if(s1[i] > s2[i]){
                s1Less = false;
                break;
            }
        }
        stat = (s1Great || s1Less);
        return stat;
    }
};
