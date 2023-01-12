# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
URL = https://leetcode.com/problems/split-bst/
776. Split BST

Approach : Two pointers, Recursion, DFS/BFS
Just create two new BSTs and set up an insertion method.
ONly 50 nodes too.

Complexity : 
Let N := #-nodes in the BST
Let H := height of the BST : log_2(N) balanced and (N) skewed 
Time = O(N)
Space = O() ( EXP ) O() ( IMP ) 

Ideally, we could do this algo in-place ( vs insertion ) 
Incorporation of guardian/sentinel nodes?
Pass in two pointers in recursive approach?

30+ mins GAAAH
1 hour ( almost there ) -> focus on pointer directionality reasoning!
At point where I can get out of trap questions, but some still difficult.

'''
class Solution:

    # Too many behaviors possibly expressed in the same function makes reasoninig difficult.
    # Must incorporate Optional (or val ) to pass and handle NONE in pythonic logic
    def internalSplitBST(self, root: Optional[TreeNode], target: int,
        leftTargetParent: Optional[TreeNode], rightTargetParent: Optional[TreeNode], dirFromLeft: str, dirFromRight: str,mySplitNodes:List[Optional[TreeNode]]) -> None:
        if(root is None):
            return
        lst = root.left
        rst = root.right
        if(root is not None):
            root.left = None
            root.right = None
        if(root.val <= target):
            if(leftTargetParent == None):
                mySplitNodes[0] = root
                nextLeftParent = root
                Solution.internalSplitBST(self,lst,target,nextLeftParent,rightTargetParent,"L","L",mySplitNodes)
                Solution.internalSplitBST(self,rst,target,nextLeftParent,rightTargetParent,"R","L",mySplitNodes)
            else:
                nextLeftParent = root
                if(dirFromLeft == 'L'):
                    leftTargetParent.left = root
                elif(dirFromLeft == 'R'):
                    leftTargetParent.right = root
                Solution.internalSplitBST(self,lst,target,nextLeftParent,rightTargetParent,"L","L",mySplitNodes)
                Solution.internalSplitBST(self,rst,target,nextLeftParent,rightTargetParent,"R","L",mySplitNodes)
        else:
            if(rightTargetParent == None):
                mySplitNodes[1] = root
                nextRightParent = root
                Solution.internalSplitBST(self,lst,target,leftTargetParent,nextRightParent,"R","L",mySplitNodes)
                Solution.internalSplitBST(self,rst,target,leftTargetParent,nextRightParent,"R","R",mySplitNodes)                
            else:
                nextRightParent = root
                if(dirFromRight == 'L'):
                    # print("Conn right Target Parent left of val = %d to node = %d" % ( rightTargetParent.val, root.val))
                    rightTargetParent.left = root
                elif(dirFromRight == 'R'):
                    # print("Conn right Target Parent right of val = %d to node = %d" % ( rightTargetParent.val, root.val))
                    rightTargetParent.right = root
                Solution.internalSplitBST(self,lst,target,leftTargetParent,nextRightParent,"R","L",mySplitNodes)
                Solution.internalSplitBST(self,rst,target,leftTargetParent,nextRightParent,"R","R",mySplitNodes)
        return

    # You cannot use Pythonic None like JAVA/C++ None, sadly
    def splitBST(self, root: Optional[TreeNode], target: int) -> List[Optional[TreeNode]]:
        mySplitNodes = [None,None]
        if(root is None):
            return mySplitNodes
        # state info does not initially matter.
        curLeftNode = None
        curRightNode = None
        dirFromLeft = "L"
        dirFromRight = "L" 
        Solution.internalSplitBST(self,root,target, curLeftNode, curRightNode, dirFromLeft,dirFromRight,mySplitNodes)
        return mySplitNodes

