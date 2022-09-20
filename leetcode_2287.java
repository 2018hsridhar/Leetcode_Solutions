/*
You do frequency counting here
You obtain a global minima across all your elements too.
URL = https://leetcode.com/problems/rearrange-characters-to-make-target-string/
2287. Rearrange Characters to Make Target String

*/
class Solution {
    // Ignore null inputs safely + all lower case too.
    public int rearrangeCharacters(String s, String target) {
        int[] sMap = getFreqMap(s);
        int[] tMap = getFreqMap(target);
        int numCopies = Integer.MAX_VALUE; // set to this
        for(int i = 0; i < tMap.length; ++i) {
            
            int tFreq = tMap[i];
            int sFreq = sMap[i];
            if(tFreq == 0){
                continue; // letter non-existent in target
            } else {
                // numCopies = Math.min(numCopies, sFreq);
                if(sFreq < tFreq) {
                    numCopies = 0;
                } else  {
                    int bound = ( sFreq / tFreq ); // ignore remainder
                    numCopies = Math.min(numCopies, bound);
                }
            }
        }
        return numCopies;
    }
    
    public int[] getFreqMap(String x)
    {
        char[] c_arr = x.toCharArray();
        int[] freqMap = new int[26];
        for(char c : x.toCharArray()) {
            int digit = (int)(c-'a');
            freqMap[digit]++;
        }
        return freqMap;
    }
    
}
