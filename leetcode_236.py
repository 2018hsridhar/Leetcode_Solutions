# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
'''
URL = https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
236. Lowest Common Ancestor of a Binary Tree

Finished quickly since another problem was LCA in nature.
'''

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
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        return getLCA(root,p.val,q.val)
