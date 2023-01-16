# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
Did analogous problem of passing index positions
Level-ord traversal, given that level itself has more than one node
URL = https://leetcode.com/problems/maximum-width-of-binary-tree/
662. Maximum Width of Binary Tree


Approach : Recursion, DFS, Trees, HashMap

Complexity
Let N := #-nodes in bTree
Let H := height of bTree
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
Wen can store a min,max -> if same, indicates one node only
3K nodes at max

L1 -> 1,1
L2 -> 1,2
L3 -> 1,4

Time = 15-ish minutes.
Accepted first try :-)
'''
class Solution:

    def solveLevelMinAndMaxPos(self,root:TreeNode, curLevel:int, curBinTreePosition: int, levelMinPos:dict, levelMaxPos:dict) -> None:
        # [1] Work of parent.
        # Avoid pythonic key error.
        if(curLevel not in levelMinPos):
            levelMinPos[curLevel] = curBinTreePosition
        if(curLevel not in levelMaxPos):
            levelMaxPos[curLevel] = curBinTreePosition
        levelMinPos[curLevel] = min(levelMinPos[curLevel], curBinTreePosition)
        levelMaxPos[curLevel] = max(levelMaxPos[curLevel], curBinTreePosition)
        # [2] Work of children.
        if(root.left is not None):
            Solution.solveLevelMinAndMaxPos(self,root.left,curLevel + 1, (curBinTreePosition*2)-1,levelMinPos,levelMaxPos)
        if(root.right is not None):
            Solution.solveLevelMinAndMaxPos(self,root.right,curLevel + 1, (curBinTreePosition*2),levelMinPos,levelMaxPos)

    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        maxWidth = 0
        levelMinPos = dict()
        levelMaxPos = dict()
        startLevel = 1
        startBinTreePos = 1
        Solution.solveLevelMinAndMaxPos(self, root, startLevel, startBinTreePos, levelMinPos, levelMaxPos)
        for level,maxLevelPos in levelMaxPos.items():
            if(level in levelMinPos):
                minLevelPos = levelMinPos[level]
                curWidth = maxLevelPos - minLevelPos + 1
                if(curWidth > maxWidth):
                    maxWidth = curWidth
        return maxWidth



