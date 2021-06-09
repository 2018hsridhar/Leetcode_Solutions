
/*

1859. Sorting the Sentence
https://leetcode.com/problems/sorting-the-sentence/

In the regexes for extra spaces, notice the escape character - '\' - used here
Regexes are quantifiers!
To denote extra characters, use '+' too
I think '\\s' is the single whitespace character. \\s+ is >= 1 whitespace
"\\s+" is safer than "\\s" - since "\\s+" matches the entirety of the whispace string ( not just single ones each time )
-> thus, more efficient too!

Are regexes greedy algos in the hiding too? Seems like a perfect interview question too
\s - matches whitespace. 
\S - matches non-whitespace

Uppercase metacharacter = inverse of lowercase metacharacters ( e.g. \w vs \W, \d vs \D )


https://stackoverflow.com/questions/15625629/regex-expressions-in-java-s-vs-s
https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html

Substring search can devolve into a two pointer problem - e.g. s.substring(beginIndex, endIndex);

Invoke the String object method .join() here

*/

class Solution 
{
    public String sortSentence(String s) 
    {
        String regex = "\\s+";
        String[] tokens = s.split(regex);
        String[] sorted = new String[tokens.length];
        for(int i = 0; i < tokens.length; ++i)
        {
            String token = tokens[i];
            int index = (int)(token.charAt(token.length() - 1) - '0');
            String subWord = token.substring(0,token.length() - 1);
            sorted[--index] = subWord;
            
        }
        
        // First arg = character sequence
        // Second arg = an Iterable ( remember C++ : Objects extending this Interface provider their .iterator() methods too ) 
        String sort = String.join(" ", sorted).trim();
        return sort;
        
        // String firstSplit = s.replaceAll("\\s+","");
        // String secondSplit = s.replaceAll("\\s","");
        // System.out.println(firstSplit);
        // System.out.println(secondSplit);
        // System.out.println(firstSplit.equals(secondSplit));
        
        
    }
}
