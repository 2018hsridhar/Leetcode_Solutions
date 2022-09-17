/*


(A) "alice and  bob are -hypen hypen- hy-phen hype-n playing!wer !werewr playing!"
    -> 7
(B) "ali bob-! char-lie!"
-> 2
(C) "tur,pitude! debau.chery!"
(D)
(E)

*/

class Solution {
    public int countValidWords(String sentence) {
        int numValid = 0;
        String delim = "\\s+";
        String[] tokens = sentence.split(delim);
        for(int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            if(token.equals(""))
                continue;
            if(isValidWord(token.toCharArray())) { // EXPECT end of expression `;` errors
                numValid++;
            }
        }
        return numValid;
    }
    
    // Jeez so many break statements : how to expand as a MACRO?
    private boolean isValidWord(char[] c_arr)
    {
        boolean isValidWord = true;
        int n = c_arr.length;
        boolean hitPunct = false;
        boolean hitHypen = false;
        for(int i = 0; i < n; ++i)
        {
            char c = c_arr[i];
            if(isLetter(c)) {
                continue;
            } else {
                if(c == '-') {
                    if(hitHypen) {
                        isValidWord = false;
                        break;
                    }
                    hitHypen = true;
                    if(i == 0 || i == n-1) {
                        isValidWord = false;
                        break;
                    } else { 
                        if(isLetter(c_arr[i-1]) && isLetter(c_arr[i+1])) {
                            continue;
                        } else { 
                            isValidWord = false;
                            break;
                        }
                    }
                    
                } else if ( isPunctuationMark(c)) {
                    if(hitPunct) {
                        isValidWord = false;
                        break;
                    }
                    hitPunct = true;
                    if(i != n-1) {
                        isValidWord = false;
                        break;
                    }
                } else { 
                    isValidWord = false;
                    break;
                }
            }
        }
        return isValidWord;
    }
    
    private boolean isPunctuationMark(char c)
    {
        return (
            c == '!' ||
            c == '.' ||
            c == ','
        );
    }
    
    private boolean isLetter(char c)
    {
        return ('a' <= c && c <= 'z');
    }
}
