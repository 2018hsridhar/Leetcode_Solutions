/*
1933. Check if String Is Decomposable Into Value-Equal Substrings
URL = https://leetcode.com/problems/check-if-string-is-decomposable-into-value-equal-substrings/

Cases
(A) "00000000000" PASS
(B) "0000000000" PASS
(C) "000000000" PASS
(D) "0001111122222" PASS
(E) ""00022211111" PASS
(F) "11111000222" PASS
(G)
(H)

*/
class Solution {
    public boolean isDecomposable(String s) {
        boolean isDecomp = true;
        int n = s.length();
        int i = 0;
        int curLen = 1; // the first character!
        boolean hitLen2 = false;
        while(i < n){
            // How to avoid duplicate code
            if((i < n-1) && s.charAt(i) == s.charAt(i+1)){
                curLen++;
            } else {
                if(curLen != 3 && curLen != 2) {
                    if(curLen % 3 == 2) {
                        if(!hitLen2) {
                            hitLen2 = true;
                        } else {
                            isDecomp = false;
                            break;
                        }
                    } else if (curLen % 3 != 0) {
                        isDecomp = false;
                        break;
                    }
                } else if ( curLen == 2 ) {
                    if(hitLen2) {
                        return false;
                    } else {
                        hitLen2 = true;
                    }
                }
                curLen = 1;
            }
            ++i;
        }
        return (isDecomp & hitLen2);
    }
}
