/*
URL = https://leetcode.com/problems/capitalize-the-title/
2129. Capitalize the Title

AntiPattern : we do not always need to store the return values of functions.

*/
class Solution {
    public String capitalizeTitle(String title) {
        StringBuilder capitalized = new StringBuilder("");
        String[] words = title.split("\\s+");
        for(String el : words)
        {
            capitalized.append(convert(el));
            capitalized.append("\s");
        }
        return capitalized.toString().trim();
    }
    
    private StringBuilder convert(String input)
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
        return capitalized;
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
