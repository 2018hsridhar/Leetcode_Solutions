/*
URL = https://leetcode.com/problems/determine-if-two-strings-are-close/description/
1657. Determine if Two Strings Are Close

Let M = len(word1) and N = len(word2)
Time = O(M + N)
Space = O(M+N) ( exp ) O(1) ( imp ) 

Test Cases :
(A)
(B)
(C)

*/
class Solution {
public:
    bool closeStrings(string word1, string word2) {
        bool areClose = true;
        size_t sigma = 26;
        std::vector<int> freqOne(sigma,0);
        std::vector<int> freqTwo(sigma,0);
        for(const char c : word1){
            int idx = static_cast<int>(c - 'a');
            freqOne[idx]++;
        }
        for(const char c : word2) {
            int idx = static_cast<int>(c - 'a');
            freqTwo[idx]++;
        }
        // Ok ; they have to be existing characters ( you cannot swap to a non-existing character )
        // This eases problem too much!
        std::sort(freqOne.begin(), freqOne.end());
        std::sort(freqTwo.begin(), freqTwo.end());
        for(int i = 0; i < 26; ++i){
            if(freqOne.at(i) != freqTwo.at(i)){
                areClose = false;
                break;
            }
        }
        // check that their character sets have a pure intersection
        set<char> wordOneChars;
        for(const char c : word1){
            wordOneChars.insert(c);
        }
        for(const char c : word2){
            if(wordOneChars.find(c) == wordOneChars.end()){
                areClose = false;
                break;
            }
        }

        return areClose;
    }
};
