import abc 
from abc import ABC, abstractmethod 
"""
This is the interface for the expression tree Node.
You should not remove it, and you can define some classes to implement it.
"""

'''
1628. Design an Expression Tree With Evaluate Function
URL = https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/description/
'''
'''
Expression tree of 100 characters only, and always of odd len, and is always valid input.

Approach : 
2 ptrs/recursion/build the tree?

Cases :
(A) ["3","4","+"]
(B) ["3","4","-"]
(C) ["3","4","*"]
(D) ["2","4","/"]
(E)

27 mins spent here
Complexity
N := #-nodes in the tree
Time = O(N)
Space = O(N) ( EXP ) O(1) (IMP )

'''
# numbers and chars -> string supports this.
# strings can convert to multiple types under the hood :-) !
class Node(ABC):
    # define your fields here
    @abstractmethod
    def __init__(self,val,leftNode,rightNode):
        self.val = val
        self.leftNode = left
        self.rightNode = right

    @abstractmethod
    def evaluate(self) -> int:
        pass

# overriding class
# numbers and chars -> string supports this.
# strings can convert to multiple types under the hood :-) !
class ExprNode(Node):

    def __init__(self,val,leftNode,rightNode):
        self.val = val
        self.leftNode = leftNode
        self.rightNode = rightNode

    # overriding abstract method
    def evaluate(self) -> int:
        if(self.val == "+"):
            return int(ExprNode.evaluate(self.leftNode) + ExprNode.evaluate(self.rightNode))
        elif(self.val == "-"):
            return int(ExprNode.evaluate(self.leftNode) - ExprNode.evaluate(self.rightNode))
        elif(self.val == "*"):
            return int(ExprNode.evaluate(self.leftNode) * ExprNode.evaluate(self.rightNode))
        elif(self.val == "/"):
            return int(ExprNode.evaluate(self.leftNode) / ExprNode.evaluate(self.rightNode))
        else: # Case of being an operand.
            return int(self.val)

"""    
This is the TreeBuilder class.
You can treat it as the driver code that takes the postinfix input
and returns the expression tree represnting it as a Node.
"""

# You have to populate the binary expression tree ( fill in the Node left,right,parent,values )
# postfix notation : operands before operators!!
# post-order traversal : left->right->root?
# Good programmers can use lists as stacks :-). 
class TreeBuilder(object):
    def buildTree(self, postfix: List[str]) -> 'Node':
        root = None
        postFixExpressionStack = [] 
        postFixOperationsSet = {'+','-','*','/'}
        for token in postfix:
            if(token in postFixOperationsSet):
                rightChild = postFixExpressionStack.pop()
                leftChild = postFixExpressionStack.pop()
                postFixExpressionStack.append(ExprNode(token,leftChild,rightChild))
            else:
                postFixExpressionStack.append(ExprNode(int(token),None,None))
        return postFixExpressionStack[0]
		
"""
Your TreeBuilder object will be instantiated and called as such:
obj = TreeBuilder();
expTree = obj.buildTree(postfix);
ans = expTree.evaluate();
"""
