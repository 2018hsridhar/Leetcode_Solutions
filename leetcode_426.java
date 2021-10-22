// Definition for a Node.
/*
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

/*
Must be an "in-place" modification/conversion


426. Convert Binary Search Tree to Sorted Doubly Linked List
URL = https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

( Ideal ) Complexity
Let N := #-nodes in the BST
Let H := height of the BST ( balanced = log(N), unbalanced = N )
Time = O(N)
Space = O(H) ( implicit ) O(1) ( explicit ) 


left_ptr = predecessor ptr
right_ptr = successor ptr

It is a "circular" doubly linked list as well : NOT the circularity property
Left ptr -> predecessor
right ptr -> successor
Ret ptr to smallest element ( all the way to the left of the tree, with exception if left is NULL ... then go rightwards ) 

Successor -> the next greatest element
Predeccessor -> the next least element

Consider passing in the parent node, from the caller, unto the child node in the callee ( and directionality as well )
if(node.val == parent.left.val ) : node is a predecessor of the parent / parent is a successor of the node
if(node.val == parent.right.val ) : node is a successor of the parent / parent is a predecessor of the node


Strategies : Recursion, Depth-First Search, Tree Traversal
Bottom-up OR top-down strategy ( leaf nodes seem easier to work with )? 

TEST BENCH

****** BASE CASES ******
(A) [1]
    [1]
(B) [1,null,2]
    [1,2]
(C) [1,0,null]
    [0,1]
(D) [1,0,2]
    [0,1,2]
    
****** COMPLEX CASES ******
^ (E) [4,2,5,1,3] { the provided } 
(F) [1,null,2,null,3,null,4,null,5]
(G) [5,4,null,3,null,2,null,1,null]
(H) [2,0,4,null,1,3,null]
(I) [10,9,11,8,null,null,12]
(J) [6,2,10,0,4,null,1,3,null]
(K) [6,2,10,0,4,9,11,null,1,3,null,8,null,null,12] { composition of (i) and (j) here :-) }

*/

class Solution 
{
    public Node treeToDoublyList(Node root) 
    {
        Node res = root;
        if(root == null)
            return res;
        Node dll_leftmost = getLeftmost(root);
        Node dll_rightmost = getRightmost(root);
        setRelationships(root);
        // System.out.printf("Leftmost = %d \t rightmost = %d\n", dll_leftmost.val, dll_rightmost.val);
        
        dll_leftmost.left = dll_rightmost;
        dll_rightmost.right = dll_leftmost;
        res = dll_leftmost;
        return res;
    }
    
    // You are setting up some of these pointers early on when the nodes may not be leaf nodes ( they're being set by a parent )
    // You may not run into that, but still must make your checks as you go!
    
    private void setRelationships(Node root)
    {
        if(root == null)
        {
            return;
        }
        if(root.left == null && root.right == null)
        {
            return;
        }
        else
        {
            // System.out.printf("Root val = %d\n", root.val);
            Node rightsubtree_leftmost = getLeftmost(root.right);
            Node leftsubtree_rightmost = getRightmost(root.left);
            setRelationships(root.left);
            setRelationships(root.right);
            /*
            if(leftsubtree_rightmost != null)
            {
                System.out.printf("left subtree val = %d\n", leftsubtree_rightmost.val);
            }
            if(rightsubtree_leftmost != null)
            {
                System.out.printf("right subtree val = %d\n", rightsubtree_leftmost.val);
            }
            */
            if(leftsubtree_rightmost != null)
            {
                root.left = leftsubtree_rightmost;
                leftsubtree_rightmost.right = root;
            }
            if(rightsubtree_leftmost != null)
            {
                root.right = rightsubtree_leftmost;
                rightsubtree_leftmost.left = root;
            }
            return;
        }
    }
    
    private Node getLeftmost(Node root)
    {
        if(root == null)
        {
            return null;
        }
        Node leftmost = root;
        while(leftmost.left != null)
        {
            leftmost = leftmost.left;
        }
        return leftmost;
    }
    
    private Node getRightmost(Node root)
    {
        if(root == null)
        {
            return null;
        }
        Node rightmost = root;
        while(rightmost.right != null)
        {
            rightmost = rightmost.right;
        }
        return rightmost;        
    }
}
