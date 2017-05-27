//https://leetcode.com/problems/longest-common-prefix/
public class Solution {
   public String longestCommonPrefix(String[] strs) {
      if(strs == null || strs.length < 1)
      {
          return "";
      }
      // longest common prefix ... across all strs .. keep getting the longest common strins, and store this result!
      String lcp = strs[0];
      for(int i = 1; i < strs.length; i++)
      {
          String myStr = strs[i];
          String newPref = getLongestPrefix(lcp,myStr);
          lcp = newPref;
      }
      return lcp;
   }

    // go up to length of the shorter string ... obvious ... !
    public String getLongestPrefix(String accumulator, String input)
    {
       StringBuilder sb = new StringBuilder("");
       int i = 0;
       char[] accum_Arr = accumulator.toCharArray();
       char[] input_Arr = input.toCharArray();
       while(i < accumulator.length() && i < input.length())
       {
          if(accum_Arr[i] != input_Arr[i])
          {
             break;
          }
          else
          {
             sb.append(input_Arr[i]);
             i++;
          }
       }
       return sb.toString();
    }
}
