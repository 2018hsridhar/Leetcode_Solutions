/*

URL = https://leetcode.com/problems/flip-string-to-monotone-increasing/submissions/
926. Flip String to Monotone Increasing


Edge Cases
(A) "000110" => 1
(B) "000000" => 0
(C) "1111111" -> 1
(D) "000111" -> 0
(E) "0001110000101111110100100010010001101000111000110"
(F)

*/
class Solution {
    // de facto answer is actually 0, in event of a single digit too!
    public int minFlipsMonoIncr(String s) {
        int minFlips = 0;
        int n = s.length();
        int i = n-1;
        // Set our computation state as we iterate over our input tapes.
        int numRightZero = 0;
        int numRightOne = 0;
        if(s.charAt(i) == '0') {
            numRightZero = 1;            
        }
        else if ( s.charAt(i) == '1'){
            numRightOne = 1;
        }
        i--;
        int numFlipsToMon = 0; // always no flips initially too
        while(i >= 0){
            char c = s.charAt(i);
            int zeroCase = 0;
            int oneCase = 0;
            if(c == '0'){
                // everything to right to 0 
                zeroCase += numFlipsToMon;
                oneCase += (1 + numRightZero);
            } else if ( c == '1'){
                // everything to right to 1 now
                zeroCase += (1 + numFlipsToMon);
                oneCase += numRightZero;
                // System.out.printf("One case = %d\n", oneCase);
            }
            minFlips = Math.min(zeroCase,oneCase);
            numFlipsToMon = minFlips;
            // System.out.printf("Fips to Mon = [%d]\n", numFlipsToMon);
            if(c == '0'){
                numRightZero++;
            } else if ( c == '1') {
                numRightOne++;
            }
            --i;
        }
        return minFlips;
    }
}
