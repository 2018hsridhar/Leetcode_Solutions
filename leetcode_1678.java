/*

URL = https://leetcode.com/problems/goal-parser-interpretation/
1678. Goal Parser Interpretation

THOUGHT PROCESSES :: 

1. Most parsers utilize stacks to add/remove symbols
Use hashmaps to store mappings of ( strings => strings )

Alphabet is guaranteed to be in the range ["G", "()", "(al)"] too - 3 alphabet characters maximum for interpretation!

Notice : X[i+1] and X[++i] are not the same thing . Former will not re-assign this newly calculated value - the latter will reassign!
Parsers may need to look ahead of their current index, to determine how to interpret following symbols!

Interpretation of symbols ( or an alphabet ) 

*/

class Solution 
{
    public String interpret(String command) 
    {
        StringBuilder sb = new StringBuilder("");
        char[] c_arr = command.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char c = c_arr[i];
            if(c == 'G')
            {
                sb.append('G');
            }
            else if ( c == '(')
            {
                char next = c_arr[i+1];
                if(next == ')')
                {
                    sb.append('o');
                    ++i;
                }
                else if ( next == 'a')
                {
                    sb.append("al");
                    i += 3;
                }
            }
            
        }
        
        
        return sb.toString();
    }
}
