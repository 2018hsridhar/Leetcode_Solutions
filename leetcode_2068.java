/*
2068. Check Whether Two Strings are Almost Equivalent
URL = https://leetcode.com/problems/check-whether-two-strings-are-almost-equivalent/
*/
class Solution {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        int n = word1.length();
        for(int i = 0; i < n; ++i){
            int idx1 = (int)(word1.charAt(i) - 'a');
            int idx2 = (int)(word2.charAt(i) - 'a');
            freq1[idx1]++;
            freq2[idx2]++;
        }
        for(int i = 0; i < 26; ++i)
        {
            int diff = (int)Math.abs(freq2[i] - freq1[i]);
            if(diff > 3) {
                return false;
            }
        }
        return true;
    }
}
