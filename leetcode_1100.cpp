/*
7 mins time to implement
1100. Find K-Length Substrings With No Repeated Characters
URL = https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
*/
class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        if(k > s.size())
            return 0;
        int freqMap[26] = {0};
        int numNoRep = 0;
        int lPtr, rPtr = 0;
        while(rPtr < k){
            int idx = s.at(rPtr) - 'a';
            freqMap[idx]++; ++rPtr;
        }
        while(rPtr <= s.size()){
            // Check exisitng window
            bool haveWord = true;
            for(const auto i : freqMap){
                if(i > 1) {
                   haveWord = false; 
                    break;
                }
            }
            if(haveWord)
                numNoRep++;
            if(rPtr == s.size()) // early escape COND
                break;
            // Update the window
            freqMap[s.at(lPtr++)-'a']--;
            freqMap[s.at(rPtr++)-'a']++;
        }
        return numNoRep;
    }
};
