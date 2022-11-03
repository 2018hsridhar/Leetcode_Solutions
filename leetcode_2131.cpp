/*
URL = https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
2131. Longest Palindrome by Concatenating Two Letter Words

Complexity :
Let N := len(words)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

Cases :
(A) ["cc","ll","cc"] => 6
(B) ["cc","cc","cc","cc","cc"] => 10
(C) ["ab","bc","cc","de","fg"] => 2
(D)
(E)

10 mins to solution :-)
*/

class Solution {
public:
    int longestPalindrome(vector<string>& words) {
        int globalLongestLen = 0;
        map<string,int> mp;
        for(const std::string& word : words){
            if(mp.find(word) == mp.end())
                mp[word] = 0;
            mp.at(word)++; // References mutateable!
        }
        // [2] Go over this map : update freqs too
        bool usedSameTokenInMiddle = false;
        for(const auto& [word,wordFreq] : mp){ // avoid extraneous copy ops
            if(word.at(0) == word.at(1)){
               if(wordFreq % 2 == 1 && !usedSameTokenInMiddle){
                   globalLongestLen += 2;
                   usedSameTokenInMiddle = true;
               }
                globalLongestLen += ( 2 * (2 * (wordFreq / 2)));
            } else if (word.at(0) != word.at(1)){
                std::string revWord(word);
                std::reverse(revWord.begin(), revWord.end());
                if(mp.find(revWord) != mp.end()){
                    int sharedOccFreq = std::min(mp.at(word),mp.at(revWord));
                    globalLongestLen += ((sharedOccFreq*2) * 2);
                    mp.at(word) -= sharedOccFreq;
                    mp.at(revWord) -= sharedOccFreq;
                }
            }
        }
        return globalLongestLen;
    }
};
