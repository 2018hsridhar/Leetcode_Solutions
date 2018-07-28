// https://leetcode.com/problems/word-pattern/

public class Solution {
    public boolean wordPattern(String pattern, String str)
    {
        boolean stat = true;
        if(pattern == null || str == null)
        {
            return false;
        }
        else
        {
            char[] patt_arr = pattern.toCharArray();   
            String[] str_arr = str.split(" ");
            if(patt_arr.length != str_arr.length)
            {
                stat = false;
                return stat;
            }
            HashMap<Character,String> pat_to_str = new HashMap<Character,String>();
            HashMap<String,Character> str_to_pat = new HashMap<String,Character>();
            for(int i = 0; i < patt_arr.length; i++)
            {
                pat_to_str.put(patt_arr[i],str_arr[i]);
                str_to_pat.put(str_arr[i],patt_arr[i]);
            }
            
            // now constrct new strs ... and tell they are the same ( note :: add space, except for last one )
            // better ... construct array, and join ze string !
            StringBuilder const_patt = new StringBuilder("");
            StringBuilder const_str = new StringBuilder("");
            
            for(int i = 0; i < str_arr.length; i++)
            {
                const_patt.append(str_to_pat.get(str_arr[i]));
                const_str.append(pat_to_str.get(patt_arr[i])).append(" ");
            }

            // compare to initial results
            if(!const_str.toString().trim().equals(str) || !const_patt.toString().equals(pattern))
            {
                stat = false;
            }
        }
        return stat;
    }
}
