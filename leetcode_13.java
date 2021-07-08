/*

13. Roman to Integer
URL = https://leetcode.com/problems/roman-to-integer/

THOUGHT PROCESS : 

Desired computational complexity : 
Time = O(s), s := length of the string
Space = O(d), where d := fixed-number of ( roman -> integer ) symbols

Edge case testing : 
(a) The ones - I, II, III, IV
(b) Singletons - V,X,L,C,D,M,I -> make sure each translates accordingly
(c) Large values - known to be 3999 = MMMCMXCIX [1,3999] will map accordingly unto range [1,3999] from roman => integer 
(d) The null/0 length string
(e) Longest possible string length - 15 of these MMMCCXXXIIIV

No degenerate roman numeral character strings will pop up
Guaranteed roman numeral within range [1,3999] - 
In roman numeral notation, we know that symbols show up in a greedy manner : once M's fully done, we no longer process any more Ms.

May need a bounds check for each prefix value too
In production environment, one could check for any degenerate strings/cases too



*/
class Solution 
{
    public int romanToInt(String s) 
    {
        int res = 0;
        
        // [1] Initialize hashmap keyset and value st : each entry<K,V> pairing
        // Use of objects entails quick comparisons too with <k,V> values ( versus <char,int>)
        HashMap<Character, Integer> hm = new HashMap<Character,Integer>();
        hm.put('I',1);
        hm.put('V',5);
        hm.put('X',10);
        hm.put('L',50);
        hm.put('C',100);
        hm.put('D',500);
        hm.put('M',1000);
        
        // [2] Iterate over character array from MSB to LSB
        int i = 0;
        while(i <= ( s.length() - 2))
        {
            char cur = s.charAt(i);;
            char next = s.charAt(i+1);
            if ( cur == 'I' && (next == 'V' || next == 'X'))
            {
                res += (hm.get(next) - 1);
                i += 2;    
            }
            else if ( cur == 'X' && (next == 'L' || next == 'C'))
            {
                res += (hm.get(next) - 10);
                i += 2; 
            }
            else if ( cur == 'C' && (next == 'D' || next == 'M'))
            {
                res += (hm.get(next) - 100);
                i += 2;
            }
            else
            {
                res += hm.get(cur);
                ++i;
            }
        }
        
        // [3] Do not just add the final value : just make sure you do not perform a lookahead here
        if(i == s.length() - 1)
            res += hm.get(s.charAt(i));
        
        return res;
    }
}
