/*
2027. Minimum Moves to Convert String
URL = https://leetcode.com/problems/minimum-moves-to-convert-string/
Can we artifiically bound it?
Can we force a directionality of change in the letters too ( e.g. go left->right or right->left only )?
    * or heck, why not test conversions in both directions too?
    
    
XXX
XXO
XOX
OXO
XOO
OOX
^ Leverage groupings of three here too!
Skip the pointers ahead as well!

TEST CASES
(A) "XOXOXOXOX" -> 2
(B) "XOXXXOXXX" -> '3'

*/
class Solution {
    public int minimumMoves(String s) {
        // Class member functions are also treated sa symbols.
        if(s == null || s.length() == 0){
            return 0;
        }
        int minMoves = 0;
        int n = s.length();
        int ptr = 0;
        while(ptr < n) {
            char val = s.charAt(ptr);
            if(val == 'X') {
                minMoves++;
                ptr = ptr + 3;
            } else {
                ptr = ptr + 1;
            }
        }
        return minMoves;
    }
}
