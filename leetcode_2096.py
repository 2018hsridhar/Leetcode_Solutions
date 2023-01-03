# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
It is a LCA problem, in the hiding
    with a modiifcation made for obtaining our directionality strings.
    Will work if a dst node is still a root node, at least.
2096. Step-By-Step Directions From a Binary Tree Node to Another
URL = https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/

Complexity
Let N := #-nodes in the tree
Let H := height of the binary tree.
TIME = O(N)
SPACE = O(N) ( EXP ) O(H) ( IMP ) 

It is TLE, but passing at least.
ID the inefficiency happening somewhere

Time taken ~ 30 minutes.

'''
# Guaranteed to exist
# Check empty list concatenation here! Carefuly!
# An inefficiency may be arising here with the `extent` method
# Can we avoid this to obtaining the path?

def getPathToNode(root: TreeNode, value:int, pathToNode: List[TreeNode]) -> bool:
    if(root.val == value):
        pathToNode.append(root)
        return True
    else:
        status = False
        if(root.left is not None):
            pathOnLeftSide = getPathToNode(root.left,value, pathToNode)
            if(pathOnLeftSide):
                pathToNode.append(root)
                status = True
        if(root.right is not None):
            pathOnRightSide = getPathToNode(root.right,value, pathToNode)
            if(pathOnRightSide):
                pathToNode.append(root)
                status = True
        return status

def getLCA(root: TreeNode, startValue: int, destValue: int) -> TreeNode:
    pathToStart = []
    getPathToNode(root,startValue,pathToStart)
    pathToStart.reverse()
    pathToDest = []
    getPathToNode(root,destValue,pathToDest)
    pathToDest.reverse()
    startPtr = 0
    destPtr = 0
    # Vals unique
    while(startPtr < len(pathToStart) and destPtr < len(pathToDest)):
        sNode = pathToStart[startPtr]
        dNode = pathToDest[destPtr]
        if(sNode.val == dNode.val):
            startPtr += 1
            destPtr += 1
        else:
            break
    # Go one pointer less now : at the end.
    startPtr -= 1
    lcaNode = pathToStart[startPtr]
    return lcaNode

class Solution:
    def getDirections(self, root: Optional[TreeNode], startValue: int, destValue: int) -> str:
        if(root is None):
            return ""
        myDirections = ""
        lcaNode = getLCA(root, startValue, destValue)
        lcaPathToStart = []
        getPathToNode(lcaNode,startValue,lcaPathToStart)
        lcaPathToStart.reverse()
        lcaPathToDest = []
        getPathToNode(lcaNode,destValue,lcaPathToDest)
        lcaPathToDest.reverse()
        for i in range(len(lcaPathToStart) - 1):
            myDirections += 'U'
        for i in range(len(lcaPathToDest) - 1):
            parent = lcaPathToDest[i] 
            child = lcaPathToDest[i+1]
            if(parent.left == child):
                myDirections += 'L'
            else:
                myDirections += 'R'
        return myDirections

'''
def getPathToNode(root: TreeNode, value:int, List[TreeNode]) -> None:
    pathToNode = []
    if(root.val == value):
        pathToNode.append(root)
        return pathToNode
    else:
        if(root.left is not None):
            pathLeftSide = getPathToNode(root.left,value)
            if(len(pathLeftSide) > 0):
                pathToNode.extend(pathLeftSide)
                pathToNode.append(root)
        if(root.right is not None):
            pathRightSide = getPathToNode(root.right,value)
            if(len(pathRightSide) > 0):
                pathToNode.extend(pathRightSide)
                pathToNode.append(root)
    return pathToNode
'''

