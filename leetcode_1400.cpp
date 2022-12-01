/*
URL = https://leetcode.com/problems/construct-k-palindrome-strings/
1400. Construct K Palindrome Strings

Complexity
Let N := len(s)
TIME = O()
SPACE = O() ( EXP ) O() ( IMP ) 

Greedy algo in the hiding.
Cases
(A) s = "annabelle", k = 6 => TRUE
    a-2,n-2,e-2,l-2,b-1
    8/2 - 4
(B) s = "annabelle", k = 9 => TRUE
(C) s = "annabelle", k = 10 => FALSE
(D) "abcd",2 => FALSE
(E)
(F)
(G)
(H)


Even case -> oh yeah we can just make each of those single chars too!
*/
class Solution {
public:
    bool canConstruct(string s, int k) {
        map<char,int> freqMap;
        for(int i = 0; i < s.size(); ++i){
            if(freqMap.find(s[i]) == freqMap.end()){
                freqMap[s[i]] = 0;
            }
            freqMap[s[i]]++;
        }
        for(const auto& [myC, myF] : freqMap){
            if(myF % 2 == 1){
                if(k > 0){
                    k--;
                    freqMap[myC] -= 1;
                } else {
                    return false;
                }
            }
        }
        int numEven = 0;
        for(const auto& [myC, myF] : freqMap){
            numEven += myF;
        }
        // int numEvenPairs = numEven / 2;
        if(numEven < k){
            return false;
        }
        return true;
    }
};
