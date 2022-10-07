/*
Lenth of binary string guaranteed to fit in [1,100]

Edge Cases
(A) "0000000000" => false
(B) "111111" => true
(C) "101010" => false
(D) "111000" => false
(E) "1111000" => true
(F) "001110" => true
(G) "111011001000" => false
(H)

URL = https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/
1869. Longer Contiguous Segments of Ones than Zeros


*/
class Solution {
    public boolean checkZeroOnes(String s) {
        int lenLongSegOne = 0;
        int lenLongSegZero = 0;
        int n = s.length();
        // Ensure to eval the first or the last character too
        // Must track the current character as well.
        // Can we avoid storing more variables and leverage conditionalal logic expressions?
        int lenSeg = 1;
        if(s.charAt(0) == '1') {
            lenLongSegOne = 1;
        } else if(s.charAt(0) == '0') {
            lenLongSegZero = 1;
        }
        for(int i = 1; i < n; ++i){
            lenSeg = (s.charAt(i) == s.charAt(i-1)) ? lenSeg + 1 : 1;
            // you already captured a longest length earlier
            // restart again
            if(s.charAt(i) == '0') 
                lenLongSegZero = Math.max(lenLongSegZero, lenSeg);
            else                    
                lenLongSegOne = Math.max(lenLongSegOne, lenSeg);
        }
        return (lenLongSegOne > lenLongSegZero);
    }
}
