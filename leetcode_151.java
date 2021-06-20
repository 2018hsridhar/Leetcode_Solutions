/* Always utilize string builders in these cases too - Java library eases our life here
 * Java has no means of reversing arrays quickyl
 * https://leetcode.com/problems/reverse-words-in-a-string/submissions/
 * 151. Reverse Words in a String
*/

class Solution {
    public String reverseWords(String s) 
    {
        // Eliminate redudant spaces during space split
        String[] tokens = s.split("\\s+");
        String[] newSentences = new String[tokens.length];
        for(int i = 0; i < tokens.length; ++i)
        {
            String w = tokens[i];
            StringBuilder sb = new StringBuilder(w);
            newSentences[tokens.length - 1 - i] = sb.toString();
        }
        return String.join(" ", newSentences).trim();
    }
}
