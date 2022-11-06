/*
1404. Number of Steps to Reduce a Number in Binary Representation to One
URL = https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/description/
Cases
(A) "1" => PASS
(B) "10" => PASS
(C) "10000" => PASS
(D) "11" =>  PASS
(E) "11111" => PASS
(F) "1010101" => PASS
(G) "1110011000001010001111000100011101110" => PASS

Let N := len(input)
TIME = O(N^2)
SPACE = O(N) ( EXP ) O(1) (IMP ) 

8 mins
*/
class Solution {
public:
    int numSteps(string s) {
        int mySteps = 0;
        vector<int> curNums;
        // Append, but in reverse to simply our operations too!
        for(int i = s.size() - 1; i >= 0; --i){
            char c= s.at(i);
            int nextDig = static_cast<int>(c - '0');
            curNums.push_back(nextDig);
        }
        while(curNums.size() > 1){
            if(curNums.at(0) == 0){
                curNums.erase(curNums.begin());
            } else if ( curNums.at(0) == 1){
                // add a 1 : get carry over if needed
                int carry = 1;
                for(int i = 0; i < curNums.size(); ++i){
                    int nextEl = curNums.at(i) + carry;
                    carry = (nextEl == 2) ? 1 : 0;
                    nextEl = nextEl % 2;
                    curNums.at(i) = nextEl; // write back
                }
                if(carry == 1){
                    curNums.push_back(1);
                }
            }
            mySteps++;
        }
        return mySteps;
    }
};
