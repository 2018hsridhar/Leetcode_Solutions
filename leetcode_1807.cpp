/*
16 mins
URL = https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/description/
1807. Evaluate the Bracket Pairs of a String

*/
class Solution {
public:
    string evaluate(string s, vector<vector<string>>& knowledge) {
        vector<char> resultStrChars;
        int n = s.size();
        int lPtr = 0;
        int rPtr = 0;
        std::unordered_map<string,string> keyRepl;
        for(const vector<string>& pairings : knowledge){
            keyRepl[pairings.at(0)] = pairings.at(1);
        }
        bool hitLeft = false;
        while(rPtr < n){
            char c = s.at(rPtr);
            if(c == '('){
                lPtr = rPtr;
                hitLeft = true;
            }else if ( c == ')'){
                std::string myKey = s.substr(lPtr+1,rPtr-lPtr-1);
                if(keyRepl.find(myKey) != keyRepl.end()){
                    // https://stackoverflow.com/questions/51833098/insert-string-into-vector-character-by-character
                    vector<char> other(keyRepl[myKey].begin(), keyRepl[myKey].end());
                    // https://stackoverflow.com/questions/2551775/appending-a-vector-to-a-vector
                    resultStrChars.insert(std::end(resultStrChars), std::begin(other), std::end(other));
                } else {
                    resultStrChars.push_back('?');
                }
                hitLeft = false;
            } else {
                if(!hitLeft){
                    resultStrChars.push_back(c);
                } else {
                    hitLeft = true;
                }
            }
            ++rPtr;
        }
        std::string resultStr(resultStrChars.begin(), resultStrChars.end());
        return resultStr;
    }
};
