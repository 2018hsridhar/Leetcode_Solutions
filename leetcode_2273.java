/*
2273. Find Resultant Array After Removing Anagrams
URL = https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/

*/
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> results = new ArrayList<String>();
        int curPtr = 0;
        int nextPtr = 1;
        int n = words.length;
        while(curPtr < n){
            if(nextPtr < n) {
                if(areAnagrams(words,curPtr,nextPtr)){
                    nextPtr++;
                } else {
                    results.add(words[curPtr]);
                    curPtr = nextPtr++;
                }
            } else if ( nextPtr == n) {
                results.add(words[curPtr]);
                break;
            }
        }
        return results;
    }
    
    private boolean areAnagrams(String[] words, int curPtr, int nextPtr)
    {
        String curWord = words[curPtr];
        String nextWord = words[nextPtr];
        int[] curFreq = new int[26];
        int[] nextFreq = new int[26];
        for(char c : curWord.toCharArray()){
            curFreq[(int)(c-'a')]++;
        }
        for(char c : nextWord.toCharArray()){
            nextFreq[(int)(c-'a')]++;
        }
        for(int i = 0; i < 26; ++i){
            if(curFreq[i] != nextFreq[i])
                return false;
        }
        return true;
    }
    
}
