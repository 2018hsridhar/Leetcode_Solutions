/*
2000. Reverse Prefix of Word
URL = https://leetcode.com/problems/reverse-prefix-of-word/

*/
class Solution {
    public String reversePrefix(String word, char ch) {
        int i = 0;
        int lIdx = 0;
        int rIdx = 0;
        while(i < word.length()) {
            if(word.charAt(i) == ch) {
                rIdx = i + 1;
                break;
            }
            ++i;
        }
        StringBuilder sb = new StringBuilder("");
        sb.append(word.substring(lIdx,rIdx));
        sb.reverse();
        if(rIdx != word.length()) // careful on null case checks : IDK lib behavior
            sb.append(word.substring(rIdx));
        return sb.toString();
    }
}
