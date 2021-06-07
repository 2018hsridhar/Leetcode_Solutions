/*
https://leetcode.com/problems/single-row-keyboard/
1165. Single-Row Keyboard

*/

class Solution {
    public int calculateTime(String keyboard, String word) 
    {
        int time = 0;
        
        int index_first = 0;
        int index_second = keyboard.indexOf(word.charAt(0));
        time = Math.abs(index_second - index_first);
        for(int i = 1; i < word.length(); ++i)
        {
            char myC = word.charAt(i);
            index_first = index_second;
            index_second = keyboard.indexOf(myC);
            time += Math.abs(index_second - index_first);
        }
        return time;
    }
}
