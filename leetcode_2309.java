/*
2309. Greatest English Letter in Upper and Lower Case
URL = https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/
*/
class Solution {
    public String greatestLetter(String s) 
    {
        char greatest = '0'; //non letter case
        Set<Character> lower = new HashSet<Character>();
        Set<Character> upper = new HashSet<Character>();
        for(char ch : s.toCharArray()) {
            if(Character.isLowerCase(ch)) {
                if(!lower.contains(ch))
                    lower.add(ch);
                char upperCase = Character.toUpperCase(ch);
                if(upper.contains(upperCase)) {
                    if(greatest == '0' || upperCase > greatest) {
                        greatest = upperCase;
                    }
                }
            }
            if(Character.isUpperCase(ch)) {
                if(!upper.contains(ch))
                    upper.add(ch);
                char lowerCase = Character.toLowerCase(ch);
                if(lower.contains(lowerCase)) {
                    if(greatest == '0' || ch > greatest) {
                        greatest = ch;
                    }
                }
            }
        }
        if(greatest == '0')
            return "";
        return greatest + "";
    }
}
