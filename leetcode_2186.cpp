/*
Get the number of characters shared by both, and subtract totalNumChars - 2 * #-shared chars
URL = https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/
2186. Minimum Number of Steps to Make Two Strings Anagram II

Let N := len(S)
Time = O(N)
Space = O(1) ( EXP & IMP ) 
4 mins to solve :-)
*/
class Solution {
public:
    int minSteps(string s, string t) {
        map<char,int> freqMapS;
        for(const char& c : s){
            if(freqMapS.find(c) == freqMapS.end()){
                freqMapS[c] = 0;
            }
            freqMapS[c]++;
        }
        int numShared = 0;
        for(const char& c : t) {
            if(freqMapS.find(c) != freqMapS.end()){
                if(freqMapS[c] > 0){
                    freqMapS[c]--;
                    numShared += 1;
                }
            }
        }
        return s.size() + t.size() - ( numShared * 2 );
    }
};
