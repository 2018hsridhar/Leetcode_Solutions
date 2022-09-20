/*

URL = https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
255. Verify Preorder Sequence in Binary Search Tree

Test Cases
(A) [1,2,3,4,5] -> true
(B) [5,4,3,2,1] -> true
(C) [1,2,4,3,5] -> true
(D) [5,2,6,4,3,7] -> false
    ^ must be remedied!
    ^ found location of bug.
(E) [5,2,4,6] -> false
(F) [3,2,6,4,5,9,8,1] -> false
(G) [3,2,6,4,5,9,8,10] -> true
(H) [4,2,1,3,8,5,6,10,16] -> true
(I) 


Wait - do I need even more range information here too ( and not juts two pieces of data )
Oh crud!

*/
class Solution {
    
    class Info {
        public int val;
        public int min;
        public int max;
        
        public Info() {
            this.val = 0;
            this.min = 0;
            this.max = 0;
        }
        
        public Info(int val, int min, int max){
            this.val = val;
            this.min = min;
            this.max = max;
        }
            
    }
    
    // All node values are unique : use strict inequality measures
    public boolean verifyPreorder(int[] preorder) {
        boolean status = true;
        Stack<Info> stk = new Stack<Info>();
        // Note : Integer and other classes expose useful CONSTANTS.
        Info firstEl = new Info(preorder[0], Integer.MIN_VALUE, Integer.MAX_VALUE);
        stk.push(firstEl);
        int n = preorder.length;
        int i = 1; // reads into preorder here
        while(!stk.isEmpty() && i < n){
            Info curInfo = stk.peek();
            int curVal = curInfo.val;
            int curMin = curInfo.min;
            int curMax = curInfo.max;
            
            int nextVal = preorder[i];
            if(nextVal < curInfo.val) {
                if(curMin < nextVal && nextVal < curMax) {
                    Info leftInfo = new Info(nextVal, curMin, curVal);
                    stk.push(leftInfo);
                } else {
                    status = false;
                    break;
                }
            // clearly nextVal will not work as a left input
            } else if ( nextVal > curInfo.val) { 
                // Let us elimiate any left nodes here.
                boolean pushedRightNode = false;
                while(!stk.isEmpty()){
                    Info topRoot = stk.pop(); // technically speaking, you could elim this as you still do your range checks too
                    // System.out.printf("Top root = [%d]\n", topRoot.val);
                    int topVal = topRoot.val;
                    int topMin = topRoot.min;
                    int topMax = topRoot.max;
                    if(topMin < nextVal && nextVal < topMax) {
                        Info rightInfo = new Info(nextVal, topVal, topMax);
                        // System.out.printf("Range = [%d,%d]\n", topVal, topMax);
                        stk.push(rightInfo);
                        pushedRightNode = true;
                        break;
                    } 
                }
                if(!pushedRightNode) {
                    status = false;
                    break;
                }
            }
            i += 1;
        }
        return status;
    }
}
