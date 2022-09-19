
/*
Your algorithm is right but you run into a TLE
There may be a faster way for checking if target is contained? Not sure though

*/

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


class FindElements {
    
    TreeNode goodRoot;
    HashSet<Integer> elems;
    
    // For many constructors, we initialze by a single member OR by multiple members
    // TreeNOdes are a canon example!
    // The return type of a constructor is the name of the class itself
    // In fact, it deviates from typical syntax, as we lack a `return type` name too!
    public FindElements(TreeNode root) {
        if(root == null) {
            goodRoot = null;
        }
        goodRoot = new TreeNode(0);
        elems = new HashSet<Integer>();
        FindElementsHelper(goodRoot,root,elems);
    }
    
    // Can we do this explicitly instead?
    // [1] Make an initial deep copy : challenge yourself to do this explicitly too
    // [2] Change copy to new values.
    private void FindElementsHelper(TreeNode goodRoot, TreeNode root, HashSet<Integer> elems) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        Stack<TreeNode> goodStk = new Stack<TreeNode>();
        goodStk.push(goodRoot);
        stk.push(root);
        while(!stk.isEmpty()){
            TreeNode cur = stk.pop();
            TreeNode goodCur = goodStk.pop();
            elems.add(goodCur.val);
            // System.out.printf("Good cur val = [%d]\n", goodCur.val);
            if(cur.left != null) {
                // TreeNode goodLeftNew = new TreeNode(cur.left.val);
                TreeNode goodLeftNew = new TreeNode((2 * goodCur.val) + 1);
                stk.push(cur.left);
                goodStk.push(goodLeftNew);
                goodCur.left = goodLeftNew;
            }
            if(cur.right != null) {
                TreeNode goodRightNew = new TreeNode((2 * goodCur.val) + 2);
                stk.push(cur.right);
                goodStk.push(goodRightNew);
                goodCur.right = goodRightNew;
            }
        }
    }
    
    
    // A bit esaier : fewer conditions to check with the explicit solution ( e.g. return of a recursive function ).
    /*
    public boolean find(int target) {
        boolean status = false;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(goodRoot);
        while(!stk.isEmpty()){
            TreeNode cur = stk.pop();
            if(cur.val == target) {
                status = true;
                break;
            } else {
                if(cur.left != null)
                    stk.push(cur.left);
                if(cur.right != null)
                    stk.push(cur.right);
            }
        }
        return status;
    }
    */
    public boolean find(int target){
        return (elems.contains(target));
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
