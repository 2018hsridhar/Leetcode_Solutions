/*
URL = https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
1347. Minimum Number of Steps to Make Two Strings Anagram

Lengths of inputs same and [[:lower:]] only.

Complexity
Let N := len(S)
TIME = O(N)
SPACE = O(1) ( IMP ) O(N) ( EXP ) 
*/
class Solution {
public:
    int minSteps(string s, string t) {
        int n = s.size();
        int minSteps = n; // initially this ; we decr it
        std::sort(s.begin(), s.end());
        std::sort(t.begin(), t.end());
        int sPtr = 0;
        int tPtr = 0;
        // practice -> acceiprt
        // leetcode -> cdeeelot
        // You want to convert t -> s : remember that
        // You want to remove character sfrom t, if possible
        // keep doing the subtractions from the map
        map<char,int> freqMap;
        for(int i = 0; i < s.size(); ++i){
            char c = s.at(i);
            if(freqMap.find(c) == freqMap.end())
                freqMap[c] = 0;
            freqMap[c]++;
        }
        
        for(int i = 0; i < t.size(); ++i){
            char c = t.at(i);
            if(freqMap.find(c) != freqMap.end() && freqMap[c] > 0) {
                    freqMap[c]--;
            } else {
                minSteps--;
            }
        }
        
        return n-minSteps;
    }
    
        
};
