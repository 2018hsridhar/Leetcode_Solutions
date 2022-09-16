/*
299. Bulls and Cows
URL = https://leetcode.com/problems/bulls-and-cows/

TEST CASES
(A) "1123","0000" -> "0A0B"
(B) "1123","1123" -> "4A0B"
(C) "1123","1132" -> "2A2B"
(D) "7654","4567" -> "0A4B"
(E) "4","0" -> "0A0B"
(F) "4","4" -> "1A0B"
(G) "011777","777110" -> "0A6B"
(G) "0111777","7771110" -> "1A6B"

COMPLEXITY
Let N := len(secret)/len(guess)
TIME = O(N) + O(NlgN) + O(N) = O(NLgN)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 


*/
class Solution {
    
    // Seperate class encapsulating our comparator
    // Don't primitives/characters alreayd have own comparators anyways?
    // public class charComparator implements Comparator<char a, char b>
    // {
//         
    // }
    
    public String getHint(String secret, String guess) 
    {
        int numBulls = 0;
        int numCows = 0;
        int n = secret.length();
        List<Character> unmatchedSecret = new ArrayList<Character>();
        List<Character> unmatchedGuess = new ArrayList<Character>();
        for(int i = 0; i < n; ++i)
        {
            if(secret.charAt(i) == guess.charAt(i))
            {
                numBulls++;
            } else {
                unmatchedSecret.add(secret.charAt(i));
                unmatchedGuess.add(guess.charAt(i));
            }
        }
        
        // These lengths will match up too!
        Collections.sort(unmatchedGuess);
        Collections.sort(unmatchedSecret);
        
        int lPtr = 0;
        int rPtr = 0;
        int un = n - numBulls;
        
        while(lPtr < un && rPtr < un)
        {
            if(unmatchedGuess.get(lPtr) < unmatchedSecret.get(rPtr)) {
                lPtr++;
            } else if(unmatchedGuess.get(lPtr) > unmatchedSecret.get(rPtr)) {
                rPtr++;
            } else {
                numCows++;
                lPtr++;
                rPtr++;
            }
        }
        
        // Fin step : concatenate and create intended results.
        StringBuilder sb = new StringBuilder("");
        sb.append(numBulls);
        sb.append("A");
        sb.append(numCows);
        sb.append("B");
        return sb.toString();
    }
}
