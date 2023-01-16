# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
655. Print Binary Tree
URL = https://leetcode.com/problems/print-binary-tree/

Complexity
Let N := #-nodes in the btree
Let H := height of the btree : log_2(N) balanced and (N) skewed
Time = O(N)
Space = O(H) ( IMP ) O(1) ( EXP ) 

15 mins

'''
class Solution:

    def getHeight(self,root: TreeNode) -> int:
        if(root.left is None and root.right is None):
            return 0
        else:
            myHeight = 0
            if(root.left is not None):
                myHeight = max(myHeight,1+Solution.getHeight(self,root.left))
            if(root.right is not None):
                myHeight = max(myHeight,1+Solution.getHeight(self,root.right))
            return myHeight

    # Populates children, originate from a parent node
    # Assumes root node is in place.
    def populateFormattedLayoutMatrix(self,height:int, r:int, c:int, root:TreeNode, matrix:List[List[str]]) -> None:
        if(root.left is not None):
            leftChildVal = str(root.left.val)
            leftChildCol = c - pow(2,height-r-1)
            matrix[r+1][leftChildCol] = leftChildVal
            Solution.populateFormattedLayoutMatrix(self,height,r+1,leftChildCol,root.left,matrix)
        if(root.right is not None):
            rightChildVal = str(root.right.val)
            rightChildCol = c + pow(2,height-r-1)
            matrix[r+1][rightChildCol] = rightChildVal
            Solution.populateFormattedLayoutMatrix(self,height,r+1,rightChildCol,root.right,matrix)

    # Lacking definitions for types.
    def printTree(self, root: Optional[TreeNode]) -> List[List[str]]:
        if(root is None):
            return []
        height = Solution.getHeight(self,root)
        m = height + 1
        n = pow(2,height+1) - 1
        matrix = [["" for j in range(n)] for i in range(m)]
        # Populate root node.
        rootCol = int((n-1)/2)
        matrix[0][rootCol] = str(root.val)
        startRow = 0
        startCol = rootCol
        Solution.populateFormattedLayoutMatrix(self,height,startRow, startCol, root,matrix)
        return matrix




