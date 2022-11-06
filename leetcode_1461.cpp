/*
URL = https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
1461. Check If a String Contains All Binary Codes of Size K

COMPLEXITY
Let N := len(S)
TIME = O(S)
SPACE = O(2^K) ( EXP ) O(1) ( IMP ) 

(A) "0011011000000001111001010101010111111000001111111111111110000000000000000001010101010000111110001010001", k = 20 => PASS

13 mins to solution
*/
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        if(k > s.size())
            return false;
        bool status = false;
        int n = s.size();
        int curVal = 0;
        int a = 0;
        std::set<int> visited;
        while(a < k){
            if(s.at(a) == '1'){
                curVal += static_cast<int>(pow(2,k-a-1));
            }
            ++a;
        } 
        visited.insert(curVal);
        while(a < n){
            // take away the left
            char leftMostEl = s.at(a-k);
            if(leftMostEl == '1'){
                curVal -= static_cast<int>(pow(2,k-1));
            }
            // mult by 2 ( denotes << left shifting operation ) 
            curVal *= 2;
            // add by 1 or 0 here
            curVal += static_cast<int>(s.at(a) - '0');
            visited.insert(curVal);
            ++a;
        }
        int numExpEls = pow(2,k);
        status = (numExpEls == visited.size());
        return status;
    }
};
