/*

Absolute paths may end up with trailing `/` too.
    They always begin with a leading `/`.
    
URL = https://leetcode.com/problems/simplify-path/
71. Simplify Path

Convert : absolute path -> simplified canonical path.
Treat multiple `///` as a single `/`.
Can do a stack -> or can we avoid a stack possibly?
    

TEST CASES
(A) "/../" -> /
(B) "/../../../" -> /
(C) "/home/foo/bar/baz/../../../../" -> /
(D)  "/home////foo/....//bar//baz/../../../../" -> /
(E) "/home/../bar/../baz/../foo" -> foo
(F) "/home/../bar/../baz/../foo/./././." -> /foo
(G) "/./././././" -> '/'
(H) "/./././././....." -> "......"
(I)
(J)

FAILED CASES :
(X) "///TJbrd/owxdG//"

COMPLEXITY
TIME = O()
SPACE = O() ( IMP ) O() ( EXP ) 


*/
class Solution {
    public String simplifyPath(String path) 
    {
        StringBuilder sb = new StringBuilder("");
        Stack<String> curPath = new Stack<String>();
        String delim = "/+";
        // Take note of how the split works here.
        String[] tokens = path.split(delim);
        for(int i = 0; i < tokens.length; ++i ) {
            if(tokens[i].equals(""))
                continue;
            String token = tokens[i];
            if(token.equals(".")) // CWD
            {
                continue; // renders as a NOP
            } else if ( token.equals(".."))  // go up one dir
            {
                if(curPath.size() > 0) {
                    curPath.pop();
                }
                
            } else { // a file or a directory
                curPath.push(token);
            }
        }
        if(curPath.isEmpty())
            sb.insert(0,"/");
        while(!curPath.isEmpty()) {
            sb.insert(0,curPath.pop());
            sb.insert(0,"/");
        }
        String result = sb.toString();
        return result;
    }
}
