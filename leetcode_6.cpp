/*
6. Zigzag Conversion
URL = https://leetcode.com/problems/zigzag-conversion/

Let N := len(S)
Time = O(N)
Space = O(N)( EXP ) O(1) ( IMP ) 

Constraints : 
all [a-z]
1K numRows
1K s len

Cases :
(A)
(B)
(C)

16 mins
*/
class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows == 1)
            return s;   
        int n = s.size();
        int step = 1;
        int seed = 1;
        std::unordered_map<int,vector<char>> charOnRows;
        for(int a = 1; a <= numRows; ++a){
            charOnRows[a] = vector<char>();
        }
        charOnRows[1].push_back(s[0]); // default value 
        for(int i = 1; i < n; ++i){
            int nextVal = seed + step;
            charOnRows[nextVal].push_back(s[i]);
            if(nextVal == numRows){
                step = -1;                
            } else if ( nextVal == 1){
                step = 1;
            }
            seed = nextVal;
        }
        vector<char> finalChars;
        for(int i = 1; i <= numRows; ++i){
            finalChars.insert(std::end(finalChars), charOnRows[i].begin(), charOnRows[i].end());
        }
        std::string result(finalChars.begin(), finalChars.end());
        return result;
    }
};
