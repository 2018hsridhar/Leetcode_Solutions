/*
Let N := len(input)

2062. Count Vowel Substrings of a String
URL = https://leetcode.com/problems/count-vowel-substrings-of-a-string/

TIME = O(N^2)
SPACE = O(1) ( EXP & IMP ) 
*/
class Solution {
    public int countVowelSubstrings(String word) {
        int cvs = 0;
        Map<Character,Integer> vowelFreqs = new HashMap<Character,Integer>();
        int lPtr = 0;
        int rPtr = 0;
        int n = word.length();
        // We never change our leftPtr too!
        // Let us deep copy for later on!
        while(rPtr < n) {
            char c = word.charAt(rPtr);
            if(isVowel(c)){
                if(!vowelFreqs.containsKey(c)){
                    vowelFreqs.put(c,0);
                }
                vowelFreqs.put(c,vowelFreqs.get(c) + 1);
                if(allVowelsIn(vowelFreqs)){
                    rPtr++;
                    while(rPtr < n){
                        if(isVowel(word.charAt(rPtr))){
                            vowelFreqs.put(word.charAt(rPtr), vowelFreqs.get(word.charAt(rPtr)) + 1);
                            rPtr++;
                        } else {
                            break;
                        }
                    }
                    // slide window now
                    rPtr--;
                    // System.out.printf("range = [%d,%d]\n", lPtr,rPtr);
                    cvs += countVowelsInWindow(word, vowelFreqs, lPtr,rPtr);
                    lPtr = rPtr+1;
                    rPtr = rPtr+1;
                } else {
                    rPtr++;
                }
            } else {
                // We found a consonant ; clear out now!
                // reset sliding windows here as if you induct and start
                // the string anew again
                // clear vowel Frequences map
                vowelFreqs.clear();
                lPtr = rPtr;
                lPtr++;
                rPtr++;
            }
        }
        return cvs;
    }
    
    // To avoid TLE, make sure to incorporate well-bolstered break/return conditions
    // in your for/while loop expressions too.!
    private int countVowelsInWindow(String orig, Map<Character,Integer> mp, int lPtr, int rPtr)
    {
        int csv = 0;
        // The map truly changes only when lPtr increments
        // Map "temporarily" changes when rPtr decrements, but restores yet again :-)
        while(lPtr < rPtr){
            int rCopy = rPtr;
            while(rCopy > lPtr){
                char toDel = orig.charAt(rCopy);
                mp.put(toDel,mp.get(toDel) - 1);
                csv++;
                if(mp.get(toDel) < 1)
                    break;
                rCopy -= 1;
            }
            // [2] RESTORE THE WINDOW
            while(rCopy <= rPtr){
                char toRestore = orig.charAt(rCopy);
                mp.put(toRestore, mp.get(toRestore) + 1);
                rCopy++;
            }
            // [3] TAKE AWAY SOME OF THE WINDOW NOW
            char toRemove = orig.charAt(lPtr);
            ++lPtr;
            mp.put(toRemove, mp.get(toRemove) - 1);
            if(mp.get(toRemove) < 1) {
                return csv;
            }
        }
        return csv;
    }
    
    
    private boolean isVowel(char c){
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
                
    // We lack a good data structure to constrain our set too.
    private boolean allVowelsIn(Map<Character,Integer> mp){
        char[] keys = new char[]{'a','e','i','o','u'};
        for(char vowel : keys) {
            if(!mp.containsKey(vowel)){
                return false;
            } else if(mp.containsKey(vowel) && mp.get(vowel) < 1) {
                return false;
            }
        }
        return true;
    }
                
    
}
