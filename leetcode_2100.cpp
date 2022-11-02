/*
URL = https://leetcode.com/problems/find-good-days-to-rob-the-bank/
2100. Find Good Days to Rob the Bank

Compleity
Let N := len(security)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    vector<int> goodDaysToRobBank(vector<int>& security, int time) {
        std::vector<int> myGoodDays;
        int n = security.size();
        std::vector<int> lenNonIncr(n,0);
        std::vector<int> lenNonDecr(n,0);
        // Get len nonIncr
        int curLen = 0;
        for(int i = 1; i < n; ++i){
            if(security.at(i) <= security.at(i-1)){
                curLen++;
            } else {
                curLen = 0;
            }
            lenNonIncr.at(i) = curLen;
        }
        // Get len nonDecr
        // Other way : days are after
        curLen = 0;
        for(int i = n-2; i >= 0; --i){
            if(security.at(i) <= security.at(i+1)){
                curLen++;
            } else {
                curLen = 0;
            }
            lenNonDecr.at(i) = curLen;
        }
        // Check both conds now across all security days
        for(int i = 0; i < n; ++i){
            if(lenNonIncr.at(i) >= time && lenNonDecr.at(i) >= time){
                myGoodDays.emplace_back(i); // woah : emplace != emplace_back!
            }
        }
        return myGoodDays;
    }
};
