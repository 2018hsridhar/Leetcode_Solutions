"""
# Definition for a Node.
class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None
"""

'''
URL = https://leetcode.com/problems/change-the-root-of-a-binary-tree/
1666. Change the Root of a Binary Tree

Complexity
Let N := #-nodes in the binary tree
Let H := height of the binary tree/length of path from leaf -> root

TIME = O(H)
SPACE = O(1) ( EXP ) O(1) ( IMP ) 

All node unique : utilize hashmap here?
SLL problem in the hiding.
Leaf node guaranteed no left and no right.
Reset node parent pointers after reroot operation too!

28 mins
exert caution here
'''

class Solution:

    # DLL trips folks due to pointers resetting.
    def flipBinaryTreeInternal(self,root:'Node',leaf:'Node') -> None:
        curNode = leaf
        while(curNode.parent is not None):
            parentNode = curNode.parent
            lst = curNode.left
            rst = curNode.right
            # [1] Safely destroy pointers on the path
            curNode.parent = None
            curNode.left = None
            curNode.right = None
            if(parentNode.left == curNode):
                parentNode.left = None
            elif ( parentNode.right == curNode):
                parentNode.right = None
            # [2] Assign pointers
            if(lst != None):
                curNode.right = lst
            else: # Debbuged sans prints ( follow logic closely )
                curNode.right = rst
            curNode.left = parentNode
            curNode = parentNode
        
    # [2] Fix the tree pointers
    def fixParentPointersFromNode(self,root:'Node') -> None:
        curNode = root
        while(curNode is not None):
            if(curNode.left is None and curNode.right is None):
                return
            # Take note if any code can yield a TLE ( time limit exception ) . Ask the question strongly too!
            else:
                if(curNode.left is not None and curNode.left.parent != curNode):
                        curNode.left.parent = curNode
                        curNode = curNode.left
                elif(curNode.right is not None and curNode.right.parent != curNode):
                        curNode.right.parent = curNode
                        curNode = curNode.right
                else:
                    return
            
    def flipBinaryTree(self, root: 'Node', leaf: 'Node') -> 'Node':
        rootFlippedBTree = leaf
        Solution.flipBinaryTreeInternal(self,root,leaf)
        Solution.fixParentPointersFromNode(self,leaf)
        return rootFlippedBTree
