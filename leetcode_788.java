/*

788. Rotated Digits
Based on problem #246. Strobogrammatic Number
URL = https://leetcode.com/problems/rotated-digits/

*/
class Solution 
{
    HashMap<Character,Character> hm;
    public HashMap<Character,Character> buildStrobogrammaticHashMap()
    {
        HashMap<Character,Character> hm = new HashMap<Character,Character>();
        hm.put('0','0');
        hm.put('1','1');
        hm.put('2','5');
        hm.put('3','X');
        hm.put('4','X');
        hm.put('5','2');
        hm.put('6','9');
        hm.put('7','X');
        hm.put('8','8');
        hm.put('9','6');
        return hm;
    }
    
    public boolean isStrobrogrammaticNumber(int n)
    {
        StringBuilder sb = new StringBuilder("");
        String num = String.valueOf(n);
        char[] c_arr = num.toCharArray();
        for(char c : c_arr)
            sb.append(hm.get(c));
        String flipped = sb.toString();
        if(flipped.indexOf('X') != -1)
               return false;
        return (!flipped.equals(num));
    }
    
    public int rotatedDigits(int n) 
    {
        int numGood = 0;
        hm = buildStrobogrammaticHashMap();
        for(int i = 1; i <= n; ++i)
            if(isStrobrogrammaticNumber(i))
                ++numGood;
        return numGood;
    }
}

