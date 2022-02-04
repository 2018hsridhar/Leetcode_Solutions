/*
URL = https://leetcode.com/problems/text-justification/
68. Text Justification

Perform a greedy packing here
Pad extra spaces ' ' when needed : exactly <maxWidth> characters
Distribute spaces evenly ( take note of this )
    - # spaces on  line must divide evently
Last line : left-justified : NO extra sapce insertwed in between words?
    ... oh. So one whitespace allowed then. But no more.
    
"Science  is  what we", : notice first two slots = 2 white spaces, next slot = 1 white space only
^ in this example, we have 5 whitespaces, but must fill first two with two additional space characters
we may need to count the gaps here as well too

Constraints
- words will fit into RAM
- word lengths reasonably sied
- words only English letters and Englih symbols

TEST CASES
(A)
(B)
(C)
(D)
(E)

Strategies : Counting, Enueration, StringBuilder logic

  len = 15 characters : subtract 3 whitespaces too => 2 chars remaining
  We can not subtract the next word : exert caution here
  "Science  is  what we", 
    ^                 ^
    L                 R
    
Work off assumption : one space gap @ minimum between each word
Goal : can we chop down repeated code in ANy of these code paths as well?

*/
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) 
    {
        List<String> justified = new ArrayList<String>();
        int w = words.length;
        int l = 0;
        int r = 0;
        int remLen = maxWith;   // reset counter mechanism here as well
        // How to code up for bottom-row case as well here?
        String space = " ";
        while(r < w)
        {
            String word = words[r];
            int curLen = word.length();
            // Careful with this conditional logic and the pointer manipulatinos too
            if ( remLen > curLen)
            {
                remLen -= curLen;
                remLen -= 1;    // default of one white space at least ( careful if final word though )
                ++r;
                numWhiteSpaces += 1;
            }
            else if(remLen == curLen)
            {
                remLen -= curLen;
                
                
                
                
                ++r;
            }
            else    // can not append next word to the right of text
            {
                // Given two pointers, close them in and add spaces, based on parity
                numWhiteSpaces += remLen;   // in our case, 4 words => 3 gaps, but 5 whitespaces
                int numWordsInClause = (r - l);
                int numGaps = numWordsInClause - 1;
                int gapSize = numWhiteSpaces / numGaps;
                int gapAdd = numWhiteSpaces % numGaps;  // keep adding this
                
                StringBuidler clause = new StringBuilder("");
                while(l < r)
                {
                    clause.append(words[l]);
                    for(int i : gapSize)
                        clause.append(space);
                    if(gapAdd > 0)
                    {
                        clause.append(space); 
                        gapAdd -= 1;
                    }
                    ++l;
                }
                
                // Reset counters AND pointers
                l = r;  // start the pointers again
                remLen = maxWidth;
            }
        }
        return justified;
    }
}


