class Solution {
    public boolean backspaceCompare(String S, String T) 
    {
        boolean status = true; // assume this
        Stack<Character> stk = new Stack<Character>();
        char[] s_arr = S.toCharArray();
        char[] t_arr = T.toCharArray();
        
        // process the first string <S>
        for(int i = 0; i < s_arr.length; ++i)
        {
            char myC = s_arr[i];
            if(myC == '#')
            {
                // if empty, backspace is an EFFECTIVE NO-OP
                if(!stk.isEmpty())
                    stk.pop(); // get rid of this char, effectively
            }
            else
                stk.push(myC);
        }
        
        // process the second string <T>
        // process backwards too
        // do not end just cuz stack is empty ( consider all backspace charcters too)
        int backPop = 0;
        for(int i = t_arr.length - 1; i >= 0; --i)
        {
            char myC = t_arr[i];
            if(myC == '#')
            {
                // we can just skip our current character anyways. a (-1) incr
                // nope :: because it turns out those backspaces, actually accumulate, HARI!
                backPop += 1;
            }
            else
            {
                if (backPop > 0)
                {
                    backPop -= 1; // use one up.
                }
                // now backspace here
                else
                {
                    if(stk.isEmpty())
                        return false; // clearly, we lack characters
                    char stkC = stk.pop();
                    if(stkC != myC)
                        return false; // mismatched character
                }
            }
        }
        return stk.isEmpty(); // if we have an extra character, on the left. 
        
    }
}

//https://leetcode.com/problems/backspace-string-compare/description/





