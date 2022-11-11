/*
URL = https://leetcode.com/problems/letter-tile-possibilities/
1079. Letter Tile Possibilities

Complexity
Let N := len(tiles)
SPACE = O(N) ( IMP ) O(1) ( EXP ) 
TIME = O(Sigma^n) where Sigma = size(alphabet)

Cases
(A) AAA => 3
(B) A => 1
(C) ABC => 15

Time = 8 mins
*/
class Solution {
public:
    int numTilePossibilities(string tiles) {
        vector<int> charFreq(26,0);
        for(char c : tiles){
            int charIdx = static_cast<int>(c-'A'); // this is the trip up ( A over a ) : all caps
            charFreq.at(charIdx)++;
        }
        int numPoss = helper(charFreq);
        return numPoss;
    }

private:
    int helper(vector<int> charFreq){
        int numKids = 0;
        for(int i = 0; i < 26; ++i){
            if(charFreq.at(i) > 0){
                vector<int> childCharFreq(charFreq);
                childCharFreq.at(i)--;
                numKids += helper(childCharFreq);
                numKids += 1;
            }
        }
        return numKids;
    }
};
