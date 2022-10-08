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
URL = https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
1305. All Elements in Two Binary Search Trees

Complexity
Let M := size(root1) and N := size(root2) and P = max(M,N)
Time = O(P)
Space = O(P) ( explicit ) O(1) implicit


Cases
(A) [1],[2] => PASS 
(B) [], [2]
(C) [1],[]
(D) 
(E)
(F)
(G)
(H)

*/

class Solution {
    
    class Data {
        public TreeNode node;
        public boolean exp;
        
        public Data() {
            node = null;
            exp = false;
        }
        
        // this = self-referential point to an objec
        // Useful for setting an object's data, according to parameterized signatures or other functions.
        public Data(TreeNode node, boolean exp){
            this.node = node;
            this.exp = exp;
        }
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) 
    {
        List<Integer> sortedList = new LinkedList<Integer>();
        Stack<Data> stkOne = new Stack<Data>();
        Stack<Data> stkTwo = new Stack<Data>();
        if(root1 != null)
            stkOne.push(new Data(root1,true));
        if(root2 != null)
            stkTwo.push(new Data(root2,true));
        // Loops expressions themselves can capture conditional evaluations
        // Very similar code across the board too!
        // Can we modularize? 
        while(!stkOne.isEmpty() && !stkTwo.isEmpty()){
            Data topOne = stkOne.peek();
            Data topTwo = stkTwo.peek();
            // System.out.printf("topOne = %d \t topTwo = %d\n", topOne.node.val, topTwo.node.val);
            // System.out.printf("expOne = %s \t expTwo = %s\n", topOne.exp, topTwo.val);
            // When neither is expandable
            if(topOne.exp == false && topTwo.exp == false){
                if(topOne.node.val < topTwo.node.val) {
                    sortedList.add(topOne.node.val);
                    stkOne.pop();
                } else {
                    sortedList.add(topTwo.node.val);
                    stkTwo.pop();                    
                }
            }
            // When only tree one is expandable
            else if(topOne.exp == true && topTwo.exp == false){
                stkOne.pop();
                if(topOne.node.right != null){
                    stkOne.push(new Data(topOne.node.right,true));
                }
                stkOne.push(new Data(topOne.node,false));
                if(topOne.node.left != null){
                    stkOne.push(new Data(topOne.node.left,true));
                }
            }
            // When only tree two is expandable
            else if(topOne.exp == false && topTwo.exp == true){
                stkTwo.pop();
                if(topTwo.node.right != null){
                    stkTwo.push(new Data(topTwo.node.right,true));
                }
                stkTwo.push(new Data(topTwo.node,false));
                if(topTwo.node.left != null){
                    stkTwo.push(new Data(topTwo.node.left,true));
                }
            }
            // When both trees expandable
            // Always the node with greater value goes under expansion here
            else {
                if(topOne.node.val > topTwo.node.val) {
                    stkOne.pop();
                    if(topOne.node.right != null){
                        stkOne.push(new Data(topOne.node.right,true));
                    }
                    stkOne.push(new Data(topOne.node,false));
                    if(topOne.node.left != null){
                        stkOne.push(new Data(topOne.node.left,true));
                    }   
                } else {
                    stkTwo.pop();
                    if(topTwo.node.right != null){
                        stkTwo.push(new Data(topTwo.node.right,true));
                    }
                    stkTwo.push(new Data(topTwo.node,false));
                    if(topTwo.node.left != null){
                        stkTwo.push(new Data(topTwo.node.left,true));
                    }   
                }
            }
        }
        while(!stkOne.isEmpty()){
            Data topOne = stkOne.pop();
            if(topOne.exp == false)
                sortedList.add(topOne.node.val);
            else {
                if(topOne.node.right != null)
                    stkOne.add(new Data(topOne.node.right,true));
                stkOne.add(new Data(topOne.node,false));
                if(topOne.node.left != null)
                    stkOne.add(new Data(topOne.node.left,true));
            }
        }
        while(!stkTwo.isEmpty()){
            Data topTwo = stkTwo.pop();
            if(topTwo.exp == false)
                sortedList.add(topTwo.node.val);
            else {
                if(topTwo.node.right != null)
                    stkTwo.add(new Data(topTwo.node.right,true));
                stkTwo.add(new Data(topTwo.node,false));
                if(topTwo.node.left != null)
                    stkTwo.add(new Data(topTwo.node.left,true));
            }
                
        }
        return sortedList;
    }
}
