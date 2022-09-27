/*
URL = https://leetcode.com/problems/capitalize-the-title/
2129. Capitalize the Title

AntiPattern : we do not always need to store the return values of functions.
To expedite
1. Avoid heap allocation of objects
2. Leverage library methods
3. Do not always store the return values of functions.
*/
class Solution {
    public String capitalizeTitle(String title) {
        String[] words = title.split("\\s+");
        String[] replace = new String[words.length];
        for(int i = 0; i < words.length; ++i)
            replace[i] = convert(words[i]);
        // Let us invoke Java's <String> library instead
        // We can `prealloc` mem too!
        return String.join(" ", replace);
    }
    
    private String convert(String input)
    {
        StringBuilder capitalized = new StringBuilder("");
        if(input.length() <= 2)
        {
            for(int i = 0; i < input.length(); ++i) {
                capitalized.append(toLowerCase(input.charAt(i)));
            }
        } else if ( input.length() > 2) {
            capitalized.append(toUpperCase(input.charAt(0)));            
            for(int i = 1; i < input.length(); ++i) {
                capitalized.append(toLowerCase(input.charAt(i)));
            }
        }
        return capitalized.toString();
    }
    
    private char toUpperCase(char input){
        if('A' <= input && input <= 'Z'){
            return input;    
        } else if ( 'a' <= input && input <= 'z'){
            int upperIdx = (int)(input - 'a');
            char lower = (char)(upperIdx + 'A');
            return lower;
        } else {
            // Not the need for a return with `else`, but `else` capturing all other conditions NOT evaluated as of yet
            // The `else-if` is a single condition specific case.
            return 'a'; 
        }
    }
    
    private char toLowerCase(char input) {
        if('a' <= input && input <= 'z'){
            return input;    
        } else if ( 'A' <= input && input <= 'Z'){
            int lowerIdx = (int)(input - 'A');
            char upper = (char)(lowerIdx + 'a');
            return upper;
        } else {
            return 'a';
        }
    }
    
}
