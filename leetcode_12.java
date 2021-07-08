/*

URL = https://leetcode.com/problems/integer-to-roman/
12. Integer to Roman
Seems highly akin to the dynamic programming "coin change" problem!


THOUGHT PROCESS :

- Notice that roman numerals do not encompass 0 => restricted to natural numbers only
- Always ask if the representation will be working with the largest possible symbols, left -> right


*/
class Solution 
{
    
    
    
    public String intToRoman(int num) 
    {
        
        // [1] Initialize hashmap keyset and value st : each entry<K,V> pairing
        // Use of objects entails quick comparisons too with <k,V> values ( versus <char,int>)
        // From {5,100}, each entails a special case of an offset ( check preceding and subtract 100/10/1 )
        
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        hm.put(1, "I");
        
        hm.put(4, "IV");
        hm.put(5, "V");
        
        hm.put(9, "IX");
        hm.put(10, "X");

        hm.put(40, "XL");
        hm.put(50, "L");
        
        hm.put(90, "XC");
        hm.put(100, "C");
        
        hm.put(400, "CD");
        hm.put(500, "D");
        
        hm.put(900, "CM");
        hm.put(1000, "M");
        
        // [2] Iterate and construct resultant string
        // The naive idea : iterate the "offset" over hashmap ( e,g, in an array of these integers )
        int[] offsets = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int i = 0;
        int offset = offsets[i];
        StringBuilder sb = new StringBuilder("");
        while(num > 0)
        {
            int diff = num - offset;
            if(diff >= 0)
            {
                sb.append(hm.get(offset));
                num -= offset;
            }
            else
            {
                ++i;
                offset = offsets[i];
            }
        }
        
        return sb.toString();
    }
}
