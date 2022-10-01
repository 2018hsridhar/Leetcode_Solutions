/*
1417. Reformat The String
URL = https://leetcode.com/problems/reformat-the-string/
*/
class Solution {
    public String reformat(String s) {
        if(s.length() == 1) 
            return s;
        int countLet = 0;
        int countDig = 0;
        for(char c : s.toCharArray()){
            if(isLetter(c)) countLet++;
            if(isDigit(c)) countDig++;
        }
        if(Math.abs(countLet - countDig) > 1)
            return "";
        int letPtr = (countLet > countDig) ? 0 : 1;
        int digPtr = (countLet > countDig ) ? 1 : 0;
        char[] res = new char[s.length()];
        for(char c : s.toCharArray()){
            if(isLetter(c)) {
                res[letPtr] = c;
                letPtr += 2;
            }
            if(isDigit(c)) {
                res[digPtr] = c;
                digPtr += 2;
            }
        }
        return new String(res);
    }
    
    private boolean isLetter(char c){
        return ('a' <= c && c <= 'z');
    }
    
    private boolean isDigit(char c){
        return ('0' <= c && c <= '9');
    }
}
