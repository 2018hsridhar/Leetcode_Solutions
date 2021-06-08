/*
246. Strobogrammatic Number
https://leetcode.com/problems/strobogrammatic-number/
*/

class Solution {
    public boolean isStrobogrammatic(String num) 
    {
        HashMap<Character,Character> hm = new HashMap<Character,Character>();
        hm.put('0','0');
        hm.put('1','1');
        hm.put('2','X');
        hm.put('3','X');
        hm.put('4','X');
        hm.put('5','X');
        hm.put('6','9');
        hm.put('7','X');
        hm.put('8','8');
        hm.put('9','6');
        StringBuilder sb = new StringBuilder("");
        char[] c_arr = num.toCharArray();
        for(char c : c_arr)
        {
            sb.append(hm.get(c));
        }
        String flipped = sb.reverse().toString();
        if(flipped.indexOf('X') != -1)
               return false;
        return flipped.equals(num);
    }
}
