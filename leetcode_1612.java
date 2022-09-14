/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*
1612. Check If Two Expression Trees are Equivalent
URL = https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/
10 minutes to solve here :-)


*/

class Solution {
    public boolean checkEquivalence(Node root1, Node root2) 
    {
        // [1] Get the different <val> possible across nodes are set them up with mock values.
        // Operation is addition : naturally associative and commutative.
        // If the two trees do not share the same set of variables -> we can immediately eliminate and return false.
        Map<Character,Integer> valPairs = new HashMap<Character,Integer>();
        fillInValPairs(root1,valPairs);
        int sumOne = evaluate(root1,valPairs); // this will eval 
        int sumTwo = evaluate(root2,valPairs); // this will not eval well
        if(sumOne == -1 || sumTwo == -1)
            return false;
        return (sumOne == sumTwo);
    }
    
    // return -1 if the evaluate goes wrong too ( e.g. member not in set of valPair's keys )
    private int evaluate(Node root, Map<Character,Integer> valPairs)
    {
        if(root == null)
            return 0;
        if(isLeaf(root)) // 0 child
        {
            if(!valPairs.containsKey(root.val)) {
                return -1;
            } else {
                return valPairs.get(root.val);
            }
        } else { // two children
            int lst = evaluate(root.left, valPairs);
            int rst = evaluate(root.right, valPairs);
            if(lst == -1 || rst == -1)
                return -1;
            return lst + rst;
        } 
            
    }
    
    private boolean isLeaf(Node root)
    {
        return (root != null && (root.left == null && root.right == null));
    }
    
    
    // Explicit method preferred for keeping state :-)
    private void fillInValPairs(Node root, Map<Character,Integer> valPairs)
    {
        int incr = 0;
        if(root == null)
            return;
        Stack<Node> stk = new Stack<Node>();
        stk.push(root);
        while(!stk.isEmpty())
        {
            Node cur = stk.pop();
            if(cur.val != '+'){
                if(!valPairs.containsKey(cur.val))
                {
                    valPairs.put(cur.val, incr);
                    incr += 1;
                }
            }
            if(cur.left != null) stk.push(cur.left);
            if(cur.right != null) stk.push(cur.right);
        }
        return;
    }
}
