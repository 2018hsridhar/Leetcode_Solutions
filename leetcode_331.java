/*

Assume that the provided input format is always correct
You can NOT reconstruct the tree ( e.g. leverage use of TreeNode objects ) 

IDEAS : 
- Uses a stack here ( related topics ) 
- Maybe DFS/BFS too ( as this is a binary tree ) 


COMPLEXITY
Let N := #-nodes in the tree / #-elements in the preorder string
TIME = O()
SPACE = O() ( EXP ) O() ( IMP ) 

When we come back to an element, we have to know if we recurse left or if we recurse right.
We have a range of integers [0,100], so perhaps use parity / signage ( may trip up with 0-element though )
Can maintain a set too, but can we avoid additional auxillary space as well?
We did some conversion thing here, and tried to peek at two elements. Do we need to use a bucket as well?
We must terminate at a leaf case or a single node case too!
Pre-order : root->left->right

Some reallly bad inputs are def going to mess you up here as well. Take close note of this

TEST CASES
(A) "9,3,4,#,#,1,#,#"
    FALSE
(B) "9,3,4,#,#,1,#,#,2,#,#"
    TRUE ( *** not passing this case *** ) 
(C)
(D)
(E)
(F)

*/
// Your earlier iff conditions are massively wrong. Tripped you up later. Exert caution!
// You messed up case where the next character ... is actually a double to triple digit integer. Take close note of this
// '92' != '9'
public class Solution {
    public boolean isValidSerialization(String preorder) {
        boolean status = true;
        if(preorder == null)
        {
            status = false;
        } 
        else if ( preorder.equals("#"))
        {
            status = true;
        }
        else
        {
            Stack<String> tester = new Stack<String>();
            String firstChar = preorder.charAt(0) + "";
            tester.push(firstChar); // always push the root!
            String[] tokens = preorder.split(",");
            int n = tokens.length;
            for(int i = 1; i < n; i++) // could be i = 2, right? not sure! 
            {
                String ithToken = tokens[i];
                // 2nd null character case handling here : removal from stack
                if(ithToken.equals("#"))
                {
                    // Ordering of the conditional blocks of logic!
                    if(!tester.peek().equals("#"))
                    {
                        tester.push("#");
                    }
                    else
                    {
                        if(tester.size() <= 1 && i < n)
                        {
                            return false;
                        }
                        String peekElem = tester.peek();
                        while(tester.size() > 1 && peekElem.equals("#"))
                        {
                            tester.pop();
                            tester.pop();
                            // This push can mess up our while loop here as well too!
                            // Quick optimization here
                            if(tester.size() != 0 && i < n)
                            {
                                peekElem = tester.peek();
                            }
                        }
                        tester.push("#");
                    }
                }
                 else            // it's a digit value! 
                 {
                    tester.push(ithToken);    
                 }
            }
            // While stack is not empty case handling as well!
            // Should not happen if the tree is valid though, and the algo is correct
            // We are not emptying the stack as expected!
            if(tester.size() == 1 && tester.peek().equals("#"))
                return true;
            status = tester.empty();
        }
        return status; 
    }
}
