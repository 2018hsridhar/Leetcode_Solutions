/*
1780. Check if Number is a Sum of Powers of Three
URL = https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/
*/
class Solution {
public:
    bool checkPowersOfThree(int n) {
        bool stat = true;
        if(n == 1 || n == 3)
            return true;
        else if(n == 0 || n == 2)
            return false;
        else {
            while(n >= 3){
                if(n % 3 == 0){
                    n /= 3;
                } else {
                    if((n-1)%3 == 0){
                        n -= 1;
                        n /= 3;
                    } else {
                        stat = false;
                        break;
                    }
                }
            }
            if(n == 2) {
                stat = false;
            } else if ( n == 1 || n == 3) { 
                stat = true;
            }
        }
        return stat;
    }
};
