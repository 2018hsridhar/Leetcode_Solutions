/*
388. Longest Absolute File Path

// --> = the \t tab character
new-line character / tab character
Each file and dir in a FS has a unique absolute path
name.extension === file formatting.
Length of the LONGEST absolute path TO A FILE ( not to a director ) in this FS ( or ret 0 if no files too )
No file/dir has length 0 too.
Hence, must termination with the extension of a '.' character / it contians a '.' character in the end too. 

Definietely seems like a stack/recursive problem in nature.

TEST CASES
(A) "dir\n\tsubdir1\n\tsubdir2\n\tsubdir3\n\t\ttransition\n\t\tturpitude"
    = 0 
    all directories here
(B) "file.ext"
    = 8 
(C) "dir\n\tfile.ext\n\tfile.ext2\n\tfile.ext23"
    -> 14
(D) "file.ext\nfile.ext2\nfile.ext23"
    -> 10 ( got 11 )
(E) "dir\n\tfilefilefilefile.ext\n\tdir2\n\t\tfilefile.ext"
    -> 24
    
COMPLEXITY
TIME = ???
SPACE = ???

Do we even use a regex in this case?
\n\t+? type of approach?

Well `\n` is a single regex expression for the listing here.
the '\t' prefix needs to be counted multiple times as well.
We always know the lengths of tokens to parse.

*/


class Solution {
    public int lengthLongestPath(String input) 
    {
        int lenLongPath = 0;
        Stack<String> stk = new Stack<String>();
        String seperator = "\\";
        String delim = "\n";
        String[] tokens = input.split(delim);
        if(tokenIsFile(tokens[0]))
        {
            lenLongPath = tokens[0].length();
        }
        int curPathLen = tokens[0].length(); // reprsents current length of filesystem
        // remember in each step to append a `/` before adding more tokens too.
        stk.push(tokens[0]); // always push the first token too ( also cur level now equals 1 here - offset by a `minus 1` here too )
        // for each token pushed, execute the "isFile" method.
        // need a way to track leveling based on the number of tabs too!
        for(int i = 1; i < tokens.length; ++i) {
            String curToken = tokens[i];
            int countTabs = getNumTabs(curToken);
            // System.out.printf("Count tabs for token = [%s] = %d\n", curToken, countTabs);
            String tokenSubStr = curToken.substring(countTabs);
            // System.out.printf("Token substr = [%s]\n", tokenSubStr);
            if(countTabs == stk.size())
            {
                stk.push(tokenSubStr);
                curPathLen += 1 + tokenSubStr.length(); // here seperator gets mocked
                // System.out.printf("Token substr = [%s]\n", tokenSubStr);
                if(tokenIsFile(tokenSubStr)) {
                    // System.out.printf("here\n");
                    lenLongPath = Math.max(lenLongPath, curPathLen);
                }
            } else if (countTabs < stk.size()) { 
                // Can we do countTabs = stk.size() - 1 too? Seems a stronger invariant!
                int diff = (stk.size() - countTabs);
                for(int a = 0; a < diff - 1; ++a)
                    curPathLen -= (stk.pop().length() + 1);
                String topMost = stk.pop();
                curPathLen -= topMost.length();
                stk.push(tokenSubStr);
                curPathLen += tokenSubStr.length();
                if(stk.size() == 0)
                    curPathLen += 1; // for mock seperator
                if(tokenIsFile(tokenSubStr)) {
                    lenLongPath = Math.max(lenLongPath, curPathLen);
                }
            }
        }
        return lenLongPath;
    }
    
    private boolean tokenIsFile(String token)
    {
        char[] c_arr = token.toCharArray();
        for(char c : c_arr)
        {
            if(c == '.')
                return true;
        }
        return false;
    }
    
    // Easy to test modularized methods!
    private int getNumTabs(String token)
    {
        int i = 0;
        char[] c_arr = token.toCharArray();
        int n = token.length();
        int countTabs = 0;
        while(i < n)
        {
            if(c_arr[i] == '\t') // Know it's not `\` + `t` : it is just `\t` here!
            {
                countTabs++;
                i += 1;
            }
            else
                break;
        }
        return countTabs;
    }
    
    
    
}



