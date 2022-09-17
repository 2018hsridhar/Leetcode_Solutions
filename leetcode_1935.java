/*
1935. Maximum Number of Words You Can Type
URL = https://leetcode.com/problems/maximum-number-of-words-you-can-type/

*/
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        int canBeTyped = 0;
        // [1] Create broken index characters
        int[] broken = new int[26];
        for(char c : brokenLetters.toCharArray()) // non-applicability of expression type ( iterable vs string )
        {
            int idx = (int)(c - 'a');
            if(broken[idx] == 0) {
                broken[idx] = 1;
            }
        }
        // Iterate over text broken by the space delimeter ( single space ) 
        String delim = "\\s+";
        String[] tokens = text.split(delim);
        for(int i = 0; i < tokens.length; ++i) {
            boolean canTypeThisWord = true;
            char[] c_arr = tokens[i].toCharArray();
            for(int j = 0; j < c_arr.length; ++j) {
                int myIdx = (int)(c_arr[j] - 'a');
                if(broken[myIdx] == 1)
                {
                    canTypeThisWord = false;
                    break;
                }
            }
            if(canTypeThisWord)
                canBeTyped++;
        }
        
        return canBeTyped;
    }
}
