'''
URL = https://leetcode.com/problems/construct-binary-tree-from-string/
536. Construct Binary Tree from String

Algorithms : Two pointers, stack, string tokenization.

Complexity
Let N := #-nodes in the tree
Let H := height of the tree : log_2(N) balanced and (N) skewed
Time = O(N)
Space = O(H) ( EXP ) O(1) ( IMP ) 

https://docs.python.org/3/tutorial/datastructures.html#using-lists-as-stacks
Support negs or multiple digits too!

Case Testing : 
(A) "4"
(B) "4(2)"
(C) "4(2)(3)"
(D) "4(2(3)(4))(3)"
(E) "-4(-2(3)(4))(3)"
(F) "-4(-2(3))(3(4))"

40+ mins GAAAH
Terrible tokenization

'''
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def str2tree(self, s: str) -> Optional[TreeNode]:
        if(len(s) == 0):
            return None
        stack = []
        lPtr = 0
        rPtr = 0
        n = len(s)
        rootNode = None
        # Algo needs a different mechanism for starting and handling initial root node.
        # Parent just tells nesting -> right is guaranteed for new parent->childNode
        # but ... careful with 2(3)(1) handling!
        # Going end to begin, from the parens, is easier than begin -> end TBH.
        # Can do special handling for ')(' case by token len Testing)
        while(rPtr < n):
            if(s[rPtr] == '('):
                token = s[lPtr:rPtr] # slice expr of token preceding parent
                if(len(token) > 0):
                    curNumVal = int(token)
                    newSubTreeRoot = TreeNode(curNumVal)
                    stack.append(newSubTreeRoot)
                rPtr += 1
                lPtr = rPtr
            elif(s[rPtr] == ')'):
                token = s[lPtr:rPtr] 
                if(len(token) > 0):
                    curNumVal = int(token)
                    newSubTreeRoot = TreeNode(curNumVal)
                    stack.append(newSubTreeRoot)
                if(len(stack) >= 2):
                    childNode = stack.pop()
                    parentNode = stack.pop()
                    if(parentNode.left is None and parentNode.right is None):
                        parentNode.left = childNode
                    elif(parentNode.left is not None and parentNode.right is None):
                        parentNode.right = childNode
                    stack.append(parentNode)
                rPtr += 1
                lPtr = rPtr
            else:
                rPtr += 1
                token = s[lPtr:rPtr] # slice expr of token preceding parent
        if(len(token) > 0):
            curNumVal = int(token)
            newSubTreeRoot = TreeNode(curNumVal)
            stack.append(newSubTreeRoot)
        rootNode = stack[0]
        return rootNode
