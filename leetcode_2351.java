/*
2351. First Letter to Appear Twice
URL = https://leetcode.com/problems/first-letter-to-appear-twice/

Letter whose SECOND occurence is first
Extension of frequency counting here.

*/
class Solution {
    public char repeatedCharacter(String s) {
        int[] freqCount = new int[26]; // 0-init memory
        char[] c_arr = s.toCharArray();
        char res = 'a'; // default
        for(char c : c_arr){
            int idx = (int)(c - 'a');
            if(freqCount[idx] == 1)
            {
                res = c;
                break;
            }
            freqCount[idx]++;
        }
        return res;
    }
}
