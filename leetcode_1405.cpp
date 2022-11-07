/*
30 mins spent on the problem here.

Cases
(A) 7,7,7 => PASS
(B) 100,99,98 => PASS ( no TLE too ) 
(C) 100,87,65 => PASS
(D) 100,10,12 => PASS
(E)
(F)
(G)


*/
class Solution {
public:
    // Oh : we are given {a,b,c} -> you create said string!
    string longestDiverseString(int a, int b, int c) {
        vector<char> longestStr;
        vector<vector<int>> charFreqs = {{'a',a},{'b',b},{'c',c}};
        std::sort(charFreqs.begin(), charFreqs.end(), [](vector<int> first, vector<int> second) -> bool {
            if(first.at(1) < second.at(1)){
                return false;
            } else {
                return true;
            }
        });
        char longestChar = charFreqs.at(0).at(0);
        int pushedDouble = 0;
        for(int a = 0; a < charFreqs.at(0).at(1); ++a){
            longestStr.push_back(charFreqs.at(0).at(0));
            pushedDouble++;
            if(pushedDouble == 2){
                if(charFreqs.at(1).at(1) > 0){
                    longestStr.push_back(charFreqs.at(1).at(0));
                    charFreqs.at(1).at(1) -= 1;
                } else if ( charFreqs.at(2).at(1) > 0){
                    longestStr.push_back(charFreqs.at(2).at(0));
                    charFreqs.at(2).at(1) -= 1;
                } else {
                    break; // we done here!
                }
                pushedDouble = 0;
            }
        }      
        // now go over the other two characters under consideration.
        // Make sure to inspect their frequencies too as we go!
        pushRemaining(longestChar, longestStr, charFreqs, 1);
        pushRemaining(longestChar, longestStr, charFreqs, 2);
        return string(longestStr.begin(), longestStr.end());
    }

private:
    void pushRemaining(char longestChar, vector<char>& longestStr,vector<vector<int>>& charFreqs, int idx){
        char curChar = charFreqs.at(idx).at(0);
        int freq = charFreqs.at(idx).at(1);
        // check behind
        // you will always have space due to those 2 nice propertyies we have set here too :-)
        for(int a = 0; a < longestStr.size(); ++a){
            if(longestStr.at(a) == longestChar && freq > 0){
                longestStr.insert(longestStr.begin() + a, curChar);
                ++a;
                --freq;
            }
        }
    }
};
