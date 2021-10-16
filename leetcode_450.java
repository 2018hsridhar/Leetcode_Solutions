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
Given a root node reference of a BST
Also given a key

Delete the node with the given key in the BST
Return root node reference ( maybe updated )

Need to perform a self-balancing operation, BUT, exploit the property of tree being a BST as well
Is it solveable in O(H) time?
Assume node values are unique here
Assume BST property : given an arbitrary node "X", all nodes left to v have value < X.val, all nodes right to v have value > X.val
A problem where it is easier for the caller parent to assess the properties of the callee function here


Complexity : 
Let N := # nodes in the tree
Let H := height of the tree ( log_2(n) if balanced, n if NOT balanced ) 
Time = O(H) + O(H) [ search + removal ] = O(H)
Space = O(H) ( implicit ), O(1) ( explicit )

Edge Case Testing : 

3 cases with the root
(A) root = [5,3,null,2,4], key = 5
    [3,2,4]
(B) root = [5,null,7,6,8] key = 5
    [6,null,7,null,8]
(C) root = [5,3,6,2,4,null,7], key = 5
            [6,3,7,2,4]
(D) [5,1,7,-1,3,6,8,null,null,null,4], key = 4
(E) [5,1,7,-1,3,6,8,-2,null,null,4], key = -1
(F) [5,1,7,-1,3,6,8,-2,null,2,4] key = 3
     
Failing test cases : 
(G) [5,3,6,2,4,null,7]
    key = 0
*/

class Solution 
{
    private TreeNode searchNode(TreeNode root, int key)
    {
        if(root == null)
            return null;
        else if(root.val == key)
            return root;
        else
        {
            // check the left and right childs of the root
            if(root.left != null && root.left.val == key)
            {
                return root;
            }
            if(root.right != null && root.right.val == key)
            {
                return root;
            }
            if(root.right != null && key > root.val)
                return searchNode(root.right, key);
            if(root.left != null && key < root.val)
                return searchNode(root.left,key);
        }
        return null;
    }
    
    public void deleteNode(TreeNode root, TreeNode toDeleteParent, int key)
    {
        // need a boolean to check if node is on the right or on the left ( temporary storage ) from the parent
        boolean connectOnLeft = false;
        boolean connectOnRight = false;
        TreeNode rootSubTree = root;
        if(toDeleteParent.left != null && toDeleteParent.left.val == key)
        {
            connectOnLeft = true;
            rootSubTree = toDeleteParent.left;
        }
        if(toDeleteParent.right != null && toDeleteParent.right.val == key)
        {
            connectOnRight = true;
            rootSubTree = toDeleteParent.right;
        }
        
        // Now perform checks on the root of this subtree ( is it a leaf or what not)
        if(rootSubTree.left == null && rootSubTree.right == null)
        {
            if(connectOnRight) toDeleteParent.right = null;
            if(connectOnLeft) toDeleteParent.left = null;
        }
        else if(rootSubTree.left == null)
        {
            if(connectOnLeft) toDeleteParent.left = rootSubTree.right;
            if(connectOnRight) toDeleteParent.right = rootSubTree.right;
        }
        else if(rootSubTree.right == null)
        {
            if(connectOnLeft) toDeleteParent.left = rootSubTree.left;
            if(connectOnRight) toDeleteParent.right = rootSubTree.left;
        }
        else
        {
            TreeNode lhs = rootSubTree.left;
            TreeNode rhs = rootSubTree.right;
            TreeNode cur = rhs;
            while(cur.left != null)
                cur = cur.left;
            cur.left = lhs;
            if(connectOnLeft) toDeleteParent.left = rhs;
            if(connectOnRight) toDeleteParent.right = rhs;
        }
    }
    
    public TreeNode deleteNode(TreeNode root, int key) 
    {
        TreeNode searched = searchNode(root,key);
        if(searched == null)
            return root;

        // Try the sentinel node logic instead : mwe already know boudns for the node values here itself!
        if(root.val == key)
        {
            TreeNode sentinel = new TreeNode(Integer.MAX_VALUE);
            sentinel.left = root;
            sentinel.right = null;
            deleteNode(root,sentinel,key);
            return sentinel.left;
        }
        else
            deleteNode(root,searched,key);
        
        return root;
    }
}
