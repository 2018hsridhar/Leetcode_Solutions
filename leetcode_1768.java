
/*

1768. Merge Strings Alternately
URL = https://leetcode.com/problems/merge-strings-alternately/

A typical merge algorithm
- Merge while both indices less than their bounds : one is guarnteed to be <= the other
- Continue running merge for both ways : only one ends anyways ( either i, or j ) 
- Utilize postfix operator a[i++]; saves us a line of code in a while loop ( NOTE : prefix operator would fail for sure )
-> postfix is especially useful in first while loop with two conditions here
*/

class Solution 
{
    public String mergeAlternately(String word1, String word2) 
    {
        int i = 0;
        int j = 0;
        char[] c_arr_1 = word1.toCharArray();
        char[] c_arr_2 = word2.toCharArray();
        StringBuilder sb = new StringBuilder("");
        while(i < c_arr_1.length && j < c_arr_2.length)
        {
            sb.append(c_arr_1[i++]);
            sb.append(c_arr_2[j++]);
        }
        while(i < c_arr_1.length)
            sb.append(c_arr_1[i++]);
        while(j < c_arr_2.length)
            sb.append(c_arr_2[j++]);
        return sb.toString();
    }
}
