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
class Solution {
    // Oh yay -> we know size of subtree is n too ( just get size of other subtrees here )
    // You need both the parent and the child -> oh well, we have parent, so hack it.
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) 
    {
        boolean canWin = false;
        // [1] Get the parent
        TreeNode parent = null;
        if(root.val == x) {
            parent = null; // no parent case
        } else {
            parent = getParent(root,x);
        }
        
        TreeNode child = null;
        if(parent == null) {
            child = root;
        }else {
            if(parent.left != null && parent.left.val == x)
                child = parent.left;
            if(parent.right != null && parent.right.val == x) {
                child = parent.right;
            }
        }
        
        // [2] Check optimal adds here
        int sizePMinX = n - getSizeOfTree(child);
        int sizeLst = getSizeOfTree(child.left);
        int sizeRst = getSizeOfTree(child.right);
        
        // System.out.printf("Size dims = [%d,%d,%d]\n", sizePMinX, sizeLst, sizeRst);
        
        // [6] Combinations to check here.
        // y color sstrictly more nodes than x here
        // [1] here represents the already chosen node too!
        if(sizePMinX > 1 + sizeLst + sizeRst) {
            canWin = true;
        } else if ( sizeLst > 1 + sizePMinX + sizeRst ) {
            canWin = true;
        } else if ( sizeRst  > sizePMinX + 1 + sizeLst) {
            canWin = true;
        } else {
            canWin = false;
        }
        
        return canWin;
    }
    
    private TreeNode getParent(TreeNode root, int x)
    {
        if(root == null) {
            return null;
        } else {
            TreeNode lstParent = null;
            TreeNode rstParent = null;
            if(root.left != null) {
                if(root.left.val == x) {
                    return root;
                }
                lstParent = getParent(root.left, x);
                if(lstParent != null) {
                    return lstParent;
                }
            } 
            if ( root.right != null) {
                if(lstParent == null) {
                    if(root.right.val == x) {
                        return root;
                    }
                    rstParent = getParent(root.right, x);
                    if(rstParent != null) {
                        return rstParent;
                    }
                    return null;
                }
            }
        }
        return null;
    }
    
    private int getSizeOfTree(TreeNode root){
        if(root == null) {
            return 0;
        } else {
            int size = 0;
            Stack<TreeNode> stk = new Stack<TreeNode>();
            stk.push(root);
            while(!stk.isEmpty()) {
                TreeNode cur = stk.pop();
                size++;
                if(cur.left != null) 
                    stk.push(cur.left);
                if(cur.right != null) 
                    stk.push(cur.right);
            }
            return size;
        }
    }
}
