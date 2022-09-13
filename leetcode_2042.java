/*

URL = https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence/
2042. Check if Numbers Are Ascending in a Sentence

We can attempt the following
(1) Attempt to parse a token -> or ignore
(2) check the starting character of a token
(3) Check against a matcher, based on length of a token

Test Cases
(A) "1 one 2 two 3 three 4 four"
    true
(B) "1 one 1 two"
    false
(C) "4 one 3 two 2 three 1 four"
    false
(D) "50 true 65 true 71 false 98 false"
    true


*/
class Solution {
    public boolean areNumbersAscending(String s) {
        int minNum = Integer.MIN_VALUE; // values all positive nums less than 100, no leading 0!
        boolean areNumAsc = true;
        String delimeter = "\\s+";
        String[] tokens = s.split(delimeter);
        for(int i = 0; i < tokens.length; ++i)
        {
            String token = tokens[i];
            if(token.length() > 2)
                continue;
            else {
                if(token.length() == 2) {
                    if(isDigit(token.charAt(0)) && isDigit(token.charAt(1)))
                    {
                        int cur = Integer.parseInt(token); // usefulness of Integer object class
                        if(cur > minNum) {
                            minNum = cur;
                        } else {
                            areNumAsc = false;
                            break;
                        }
                    }
                } else if ( token.length() == 1) {
                    if(isDigit(token.charAt(0))) {
                        int cur = Integer.parseInt(token);
                        if(cur > minNum) {
                            minNum = cur;
                        } else {
                            areNumAsc = false;
                            break;
                        }
                    }
                }
            }
        }
        return areNumAsc;
    }
    
    private boolean isDigit(char ch)
    {
        return ('0' <= ch && ch <= '9');
    }
}
