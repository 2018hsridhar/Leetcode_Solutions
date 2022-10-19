/*
763. Partition Labels
URL = https://leetcode.com/problems/partition-labels/

Let N := len(s)
Time = O(N)
Space = O(1) ( EXP & IMP ) 
*/
class Solution {
public:
    vector<int> partitionLabels(string s) {
        vector<int> sizeParts;
        map<char,int> leftBound;
        map<char,int> rightBound;
        for(int i = 0; i < s.size(); ++i){
            char c = s.at(i);
            auto leftItr = leftBound.find(c);
            if(leftItr == leftBound.end()){
                leftBound[c] = i; 
                rightBound[c] = i; // either create OR update
            } else {
                rightBound[c] = i; // either create OR update
            }
        }
        pair<int,int> curInt = 
            make_pair(leftBound[s.at(0)], rightBound[s.at(0)]);
        for(int i = 1; i < s.size(); ++i){
            char c = s.at(i);
            if(leftBound.find(c) != leftBound.end()){
                int lBound = leftBound[c];
                int rBound = rightBound[c];
                if(lBound <= curInt.second){
                    curInt.first = min(curInt.first, lBound);
                    curInt.second = max(curInt.second, rBound);
                } else {
                    sizeParts.push_back(curInt.second - curInt.first + 1);
                    curInt.first = lBound;
                    curInt.second = rBound;
                }
            }
        }
        // Final interval case
        sizeParts.push_back(curInt.second - curInt.first + 1);
        return sizeParts;
    }
};
