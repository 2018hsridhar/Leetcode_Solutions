/*
2114. Maximum Number of Words Found in Sentences
URL = https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/

*/
class Solution {
    public int mostWordsFound(String[] sentences) {
        int mostWordsFound = 0;
        int n = sentences.length;
        String delim = "\\s+";
        for(int i = 0; i < n; ++i)
        {
            String sentence = sentences[i];
            String[] tokens = sentence.split(delim);
            int m = tokens.length;
            mostWordsFound = Math.max(mostWordsFound,m);
        }
        return mostWordsFound;
    }
}
