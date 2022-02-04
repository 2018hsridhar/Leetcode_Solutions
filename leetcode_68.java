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
(A)  ["enough","to","explain","to"]
20
    Fully perfect string here
    => PASS
(B) ["enough","to","explain","tooth"]
20
    => PASS
(C) ["enough","to","explain","abcdefghijklmnopqrst","tooth"]
20
    => Div by 0 ( cuz one word only in "abcd...rst" )
    => PASS
(D) ["enough","to","explain","abcdefghijklmnopqrst","abcdefghijklmnopqr", "abcdefghijklmnopqrst"]
20
    => knew this case would not work as epected ... resolve!
    => PASS
(E) ["This", "is", "an"] 20
    => PASS
(F) ["a","b","c","d"]
1
=> PASS
(G) ["abc","defghi","jk","lm","nop","qr","stuvwxyz"]
10
    => PASS
(H)


Strategies : Counting, Enueration, StringBuilder logic

  len = 15 characters : subtract 3 whitespaces too => 2 chars remaining
  We can not subtract the next word : exert caution here
  "Science  is  what we", 
    ^                 ^
    L                 R
    
Work off assumption : one space gap @ minimum between each word
Goal : can we chop down repeated code in ANy of these code paths as well?

Key gotcha : the rightmost never has spaces added to it! Take stock of this fact!

*/
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) 
    {
        List<String> justified = new ArrayList<String>();
        int w = words.length;
        int l = 0;
        int r = 0;
        int remLen = maxWidth;   // reset counter mechanism here as well
        int numWhiteSpaces = 0;
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
                int numWordsInClause = (r - l + 1);
                StringBuilder  clause = new StringBuilder ("");
                if(numWordsInClause == 1)
                {
                    clause.append(words[l]);
                    remLen = maxWidth;
                    remLen -= words[l].length();
                    for(int k = 0; k < remLen; ++k)
                        clause.append(space);
                }
                else
                {
                    int numGaps = numWordsInClause - 1;
                    int gapSize = numWhiteSpaces / numGaps;
                    int gapAdd = numWhiteSpaces % numGaps;  // keep adding this

                    while(l <= r)
                    {
                        clause.append(words[l]);
                        if(l < r)   // Notice this condition as well!
                        {
                            for(int i = 0; i < gapSize; ++i)
                                clause.append(space);
                            if(gapAdd > 0)
                            {
                                clause.append(space); 
                                gapAdd -= 1;
                            }
                        }
                        ++l;
                    }
                }
                justified.add(clause.toString());
    
                // RESET pointers and counters
                remLen = maxWidth;
                ++r;
                l = r;
                numWhiteSpaces = 0;
            }
            else    // can not append next word to the right of text
            {
                // Given two pointers, close them in and add spaces, based on parity
                numWhiteSpaces += remLen;   // in our case, 4 words => 3 gaps, but 5 whitespaces
                int numWordsInClause = (r - l);
                StringBuilder  clause = new StringBuilder ("");
                if(numWordsInClause == 1)
                {
                    clause.append(words[l]);
                    remLen = maxWidth;
                    remLen -= words[l].length();
                    for(int k = 0; k < remLen; ++k)
                        clause.append(space);
                }
                else
                {
                    int numGaps = numWordsInClause - 1;
                    int gapSize = numWhiteSpaces / numGaps;
                    int gapAdd = numWhiteSpaces % numGaps;  // keep adding this

                    while(l < r)
                    {
                        clause.append(words[l]);
                        if(l < r-1)
                        {
                            for(int i = 0; i < gapSize; ++i)
                                clause.append(space);
                            if(gapAdd > 0)
                            {
                                clause.append(space); 
                                gapAdd -= 1;
                            }
                        }
                        ++l;
                    }
                }
                justified.add(clause.toString());
                
                // Reset counters AND pointers
                l = r;  // start the pointers again : also not : do NOT increment r, as we have to restart
                remLen = maxWidth;
                numWhiteSpaces = 0;
            }
        }
        // Last case -> must fill out fully here, if remLen > 0 ( last sentence ) 
        StringBuilder  clause = new StringBuilder ("");
        int curLen = 0;
        if(l < r)
        {
            while(l < r)
            {
                clause.append(words[l]);
                clause.append(space);
                curLen += words[l].length();
                curLen += 1;
                l += 1;
            }
            while(curLen < maxWidth)
            {
                clause.append(space);
                curLen += 1;
            }
            justified.add(clause.toString());
        }

        
        return justified;
    }
}


