/*
2185. Counting Words With a Given Prefix
URL = https://leetcode.com/problems/counting-words-with-a-given-prefix/

*/
class Solution {
    public int prefixCount(String[] words, String pref) {
        int prefixCount = 0;
        int lenPref = pref.length(); // say pref = "at" : len = 2 here
        for(String word : words)
        {
            if(word.length() < pref.length()) {
                continue;
            } else {
                if(word.substring(0,lenPref).equals(pref)) {
                    prefixCount++;
                }
            }
        }
        return prefixCount;        
    }
}
