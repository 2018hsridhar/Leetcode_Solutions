# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
URL = https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
742. Closest Leaf in a Binary Tree

Complexity
Let N := #-nodes in the tree
Let H := height of the tree ( log_2(N) balanced and (N) skewed ) 
Time = O(N^2)
Space = O(H) ( IMP ) O(1) ( EXP ) 

N ~ 1K and vals unique
29 mins 
Lot of coding 
Messed up deepest and nearest
'''

# Types follow naming convention.
class TreeInfo:
    leaf = TreeNode(0)
    depth = 0

    def __init__(self,leaf,depth):
        self.leaf = leaf
        self.depth = depth

def getPathToNode(root: TreeNode, k: int, path: List[TreeNode]) -> bool:
    if(root.val == k):
        path.append(root)
        return True
    elif(root.val != k):
        onLeft = False
        onRight = False
        if(root.left is not None):
            onLeft = getPathToNode(root.left,k,path)
        if(root.right is not None): # trick to reducing call stack space.
            onRight = getPathToNode(root.right,k,path)
        status = (onLeft or onRight)
        if(status):
            path.append(root)
        return status

# Could change thismethod to return extra info ( or set up diff method ) 
def getTreeDepthAndNearestLeaf(root: TreeNode) -> TreeInfo:
    if(root is None):
        return TreeInfo(None,0)
    depthLeft = 1000000
    depthRight = 1000000
    if(root.left is None and root.right is None):
        return TreeInfo(root,1)
    else:
        res = TreeInfo(None,0)
        leftTreeInfo = TreeInfo(None,0)
        rightTreeInfo = TreeInfo(None,0)
        if(root.left is not None):
            leftTreeInfo = getTreeDepthAndNearestLeaf(root.left)
            depthLeft = leftTreeInfo.depth
        if(root.right is not None):
            rightTreeInfo = getTreeDepthAndNearestLeaf(root.right)
            depthRight = rightTreeInfo.depth
        if(depthLeft < depthRight):
            res.depth = 1 + depthLeft
            res.leaf = leftTreeInfo.leaf
        else:
            res.depth = 1 + depthRight
            res.leaf = rightTreeInfo.leaf
        return res

class Solution:
    # Get deepelest leaf : not case of overlap
    # Work bottom-up here, and work on subtrees only
    # note : repeated calls for getting height of subtrees.
    def findClosestLeaf(self, root: Optional[TreeNode], k: int) -> int:
        if(root is None):
            return 0
        myClosestNodeVal = -1
        path = []
        getPathToNode(root,k,path)
        # path.reverse() # reverse for top-down
        # for node in path:
            # print(node.val)
        # Oh no ... you need to get the leaf value itself ( not tell which parent ) 
        curNode = path[0]
        curNodeInfo = getTreeDepthAndNearestLeaf(curNode)
        curStepsToLeaf = curNodeInfo.depth
        curDeepestLeaf = curNodeInfo.leaf
        parentStepOffset = 1
        for index in range(1, len(path),1):
            parentNode = path[index]
            childNode = path[index - 1]
            if(parentNode.left is not None and parentNode.left != childNode):
                leftTreeInfo = getTreeDepthAndNearestLeaf(parentNode.left)
                if(leftTreeInfo.depth + 1 + parentStepOffset < curStepsToLeaf):
                    curStepsToLeaf = leftTreeInfo.depth + 1 + parentStepOffset
                    curDeepestLeaf = leftTreeInfo.leaf
            if(parentNode.right is not None and parentNode.right != childNode):
                rightTreeInfo = getTreeDepthAndNearestLeaf(parentNode.right)
                if(rightTreeInfo.depth + 1 + parentStepOffset < curStepsToLeaf):
                    curStepsToLeaf = rightTreeInfo.depth + 1 + parentStepOffset
                    curDeepestLeaf = rightTreeInfo.leaf
        return curDeepestLeaf.val



