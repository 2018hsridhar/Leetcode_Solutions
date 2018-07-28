public class Solution {
    public int titleToNumber(String s)
    {
        // index .. char - 'a' + 1 ... rather useful, naa?
        int pos = 0;
        if( s == null)
        {
            return pos;
        }
        else
        {
            char[] cArr = s.toCharArray();
            int base = 26;
            int pow = 0;
            for(int i = cArr.length - 1; i >= 0; i--)
            {
                int factor = (int)(cArr[i] - 'A' + 1);
                int basePow = (int)Math.pow(base,pow);
                pos += (factor * basePow);
                pow++;
            }
        }
        return pos;
    }
}

// https://leetcode.com/problems/excel-sheet-column-number/

