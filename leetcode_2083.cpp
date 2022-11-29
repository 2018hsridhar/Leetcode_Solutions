/*
URL = https://leetcode.com/problems/substrings-that-begin-and-end-with-the-same-letter/
2083. Substrings That Begin and End With the Same Letter

Complexity
TIME = O(N)
SPACE = O(SIGMA) ( EXP ) O(1) ( IMP ) 
SIGMA = SIZE-ALPHABET
N = LEN(S)

6 mins
*/
class Solution {
public:
    long long numberOfSubstrings(string s) {
        using ll = long long;
        ll sum = 0;
        map<char,int> freqCount;
        for(char c : s){
            if(freqCount.find(c) == freqCount.end()){
                freqCount[c] = 0;
            }
            freqCount[c]++;
        }
        for(const auto& [myChar, myFreq] : freqCount){
            for(int i = 1; i <= myFreq; ++i){
                // printf("delta = %d\ti=%d\n", myFreq - i + 1,i);
                sum += (myFreq - i + 1);
            }
        }
        return sum;
    }
};
