/*
2278. Percentage of Letter in String
URL = https://leetcode.com/problems/percentage-of-letter-in-string/

*/
class Solution {
    public int percentageLetter(String s, char letter) 
    {
        double freq = 0;
        double len = s.length();
        for(int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == letter) {
                freq++;
            }
        }
        double percentage = 100 * (freq / len);
        int res = (int)(Math.floor(percentage));
        return res;
    }
}
