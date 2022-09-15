/*

2011. Final Value of Variable After Performing Operations
URL = https://leetcode.com/problems/final-value-of-variable-after-performing-operations/

Initial value of X is set to 0 here.
++X,X++ -> incr val
--X,X-- -> decr val
Can we takea  count of incr and decs here?
And from there, apply the diff
Versus doing the ops itself

*/
class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int finalVal = 0;
        int countIncr = 0;
        int countDecr = 0;
        for(String op : operations)
        {
            if(op.equals("++X") || op.equals("X++"))
                countIncr++;
            else if ( op.equals("--X") || op.equals("X--"))
                countDecr++;
        }
        finalVal = finalVal + countIncr - countDecr;
        return finalVal;
    }
}
