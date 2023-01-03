# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
URL = https://leetcode.com/problems/smallest-string-starting-from-leaf/
988. Smallest String Starting From Leaf

Complexity : 
N := # nodes ( up to 100K ) ]
H := height of the tree : log_2(N) balanced, (N) skewed.
Time = O(N)
Space = O(H) ( IMP ) O(1) ( EXP ) 

9 mins to a working solution :-) 
'''

def constructStringFromLeafToRoot(root: Optional[TreeNode], ans: List[str], curStrPath: str) -> None:
    convLetter = chr(ord('a') + root.val)
    nextStringPath = curStrPath + convLetter
    if(root.left is None and root.right is None): # leaf case
        finalStr = nextStringPath[::-1]
        if(ans[0] == ""):
            ans[0] = finalStr
        else:
            if(finalStr < ans[0]):
                ans[0] = finalStr
    elif(root.left is not None and root.right is None) : # left child
        constructStringFromLeafToRoot(root.left, ans, nextStringPath)
    elif(root.left is None and root.right is not None): # right child
        constructStringFromLeafToRoot(root.right, ans, nextStringPath)
    else:
        constructStringFromLeafToRoot(root.left, ans, nextStringPath)
        constructStringFromLeafToRoot(root.right, ans, nextStringPath)

class Solution:
    def smallestFromLeaf(self, root: Optional[TreeNode]) -> str:
        if(root is None):
            return ""
        ans = [""] 
        constructStringFromLeafToRoot(root, ans, "")
        lexSmallestStr = ans[0]
        return lexSmallestStr
