/*
1358. Number of Substrings Containing All Three Characters
URL = https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
Sliding window technique

Complexity
Let N := len(s)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 
Used switch-case in place of object instantiation.

Cases
(A) "abcabcabcabcabcabcabcabc" -> PASS
(B) "aaaaaaab"
(C) "ccaaaaaaaaab"
(D) "abccbacbaabcaabbccccbaba"
(E)

*/
class Solution {
public:
    int numberOfSubstrings(string s) {
        int numMatchingStrs = 0;
        size_t len = s.size();
        int countA = 0;
        int countB = 0;
        int countC = 0;
        size_t lPtr = 0;
        size_t rPtr = 0;
        while(rPtr < len){
            switch(s[rPtr]){
                case 'a':
                    countA++;
                    break;
                case 'b':
                    countB++;
                    break;
                case 'c':
                    countC++;
                    break;
                default:
                    break;
            }
            while(countA >= 1 && countB >= 1 && countC >= 1){
                numMatchingStrs += (len - rPtr);    
                switch(s[lPtr]){
                    case 'a':
                        countA--;
                        break;
                    case 'b':
                        countB--;
                        break;
                    case 'c':
                        countC--;
                        break;
                    default:
                        break;
                }
                ++lPtr;
            }
            ++rPtr;
        }
        return numMatchingStrs;
    }
};
