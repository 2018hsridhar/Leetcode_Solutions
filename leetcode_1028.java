/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 /*
 Edge Cases
 (A) "3-3--30--30---3" => PASS
 (B) "3-30--31---32----33-----35-60--90---180--270" PASS
 (C)
 (D)
 (E)
 
 URL = https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/description/

 */
class Solution {
    public TreeNode recoverFromPreorder(String traversal)  {
        int n = traversal.length();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        int curDashSize = 0;
        int lDashPtr = 0;
        int rDashPtr = 0 ;
        int lDigPtr = 0;
        int rDigPtr= 0;
        int lPtr = 0;
        int rPtr = 0;
        boolean firstCase = true;
        boolean hitDigit = false; // always start at a digit ( else invalid )        
        boolean hitDash = false; // always start at a digit ( else invalid )        
        int curDashCount = 0;
        while(rPtr < n){
            if(rPtr == n-1 || Character.isDigit(traversal.charAt(rPtr)) && !Character.isDigit(traversal.charAt(rPtr+1))){
                String curDash = traversal.substring(lPtr,lPtr+curDashCount);
                String intStr = traversal.substring(lPtr + curDashCount, rPtr+1);
                // System.out.printf("CurDash = %s \t intStr = %s\n", curDash, intStr);
                int curDigit = Integer.parseInt(intStr);
                if(curDashCount > curDashSize){
                    TreeNode newLeftChild = new TreeNode(curDigit);
                    TreeNode topNode = stk.peek();
                    topNode.left = newLeftChild;
                    stk.push(newLeftChild);
                } else if ( curDashCount == curDashSize){
                    TreeNode newRightChild = new TreeNode(curDigit);
                    if(stk.size() == 0){
                        stk.push(newRightChild);    
                    } else {
                        stk.pop();
                        TreeNode topNode = stk.peek();
                        topNode.right = newRightChild;
                        stk.push(newRightChild);
                    }
                } else {
                    while(stk.size() != curDashCount){
                        stk.pop();
                    }
                    TreeNode topNode = stk.peek();
                    TreeNode newRightChild = new TreeNode(curDigit);
                    topNode.right = newRightChild;
                    stk.push(newRightChild);
                }
                curDashSize = curDashCount; // in all cases!
                curDashCount = 0;
                hitDash = false;
            } else {
                if(!Character.isDigit(traversal.charAt(rPtr))){
                    if(!hitDash){
                        hitDash = true;
                        lPtr = rPtr;
                    }
                    curDashCount++;
                } 
            }
        ++rPtr;
    }
    while(stk.size() > 1){
        stk.pop();
    }
    TreeNode myNewRoot = stk.peek();
    return myNewRoot;
    }
}
