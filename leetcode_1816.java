
/*

1816. Truncate Sentence
https://leetcode.com/problems/truncate-sentence/

Arrays help to parse tokens of a string with alacrity.

Regexes themselves are strings, and remain passable as function arguements!
Void methods tend to act on the private fields of an object itself ( s.g. sb.trimToSize()) 
sb.trimToSize() does not remove trailing whitespaces - only the string method .trim() will remove trailing whitespaces and leading whitespaces.

*/


class Solution 
{
    public String truncateSentence(String s, int k) 
    {
        String regex = "\\s+";
        String[] tokens = s.split(regex);
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < k; ++i)
        {
            String token = tokens[i];
            sb.append(token);
            sb.append(" ");
        }
        sb.trimToSize();
        return sb.toString().trim();
    }
}
