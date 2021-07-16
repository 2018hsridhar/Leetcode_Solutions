/*
No error handling neccessary
Dates guaranteed their validity too

URL = https://leetcode.com/problems/reformat-date/
1507. Reformat Date

Edge case Testing
(1)
(2)
(3)
(4)
(5) 

THOUGHT PROCESS : 
1. Will utilize the StringTokenizer class here :-) . Second use case
*/

import java.util.StringTokenizer;

class Solution 
{
    public String reformatDate(String date) 
    {
        
        HashMap<String,String> months = new HashMap<String,String>();
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");
        
        // [1] Iterate and lexically parse out tokens from tokenized date string
        StringBuilder sb = new StringBuilder("");
        String delim = " ";
        StringTokenizer st = new StringTokenizer(date, delim);
        String Day = st.nextToken();
        String Month = st.nextToken();
        String Year = st.nextToken();
        
        // [2] Execute Day and Month conversions
        String DD = Day.substring(0, Day.length() - 2);
        String MM = months.get(Month);
        String YYYY = Year;
        
        
        // [3] Append results to StringBuilder object
        sb.append(YYYY);
        sb.append("-");
        sb.append(MM);
        sb.append("-");
        if(DD.length() == 1)
            sb.append("0");
        sb.append(DD);
        String res = sb.toString();
        return res;
    }
}
