# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
URL = https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
1123. Lowest Common Ancestor of Deepest Leaves

Approach : Bottom-up, recursion

Complexity
Let N := #-nodes in the bTree
Let H := height of the bTree [ log_2(N) balanced, (N) skewed ] 
TIME = O(N)
SPACE = O(H) ( 1 ) O(H) ( IMP )

Edge Cases : 
(A) [1,2,3,4,5,6,7] => 1
(B) [1,2,3,,4,null,null,7] => 1
(C) [1] => 1
(D) [1,null,2] => 2
(E) [1,2,null] => 2
(F) [1,null,2,null,3,null,4] => 4
(G) [1,null,2,3,4,5,6,7,8] => 2

18.5 mins to solution + 5 min thoughts yesterday

'''
# Class attributes need default val!
class NodeInfo:
    lca = None
    deepestNodeLevel = 0

    def __init__(self,lca:TreeNode,deepestNodeLevel:int):
        self.lca = lca
        self.deepestNodeLevel = deepestNodeLevel

# Add levels as we proceed up the tree.
def getDeepestLCA(root: TreeNode) -> NodeInfo:
    deepestLCAInfo = NodeInfo(root,0)
    if(root.left is None and root.right is None):
        deepestLCAInfo.deepestNodeLevel = 1
        deepestLCAInfo.lca = root
    else:
        if(root.left is not None and root.right is not None):
            leftInfo = getDeepestLCA(root.left)
            rightInfo = getDeepestLCA(root.right)
            deepestLCAInfo.deepestNodeLevel += 1
            if(leftInfo.deepestNodeLevel == rightInfo.deepestNodeLevel):
                deepestLCAInfo.deepestNodeLevel = leftInfo.deepestNodeLevel
                deepestLCAInfo.lca = root
            elif(leftInfo.deepestNodeLevel > rightInfo.deepestNodeLevel):
                deepestLCAInfo = leftInfo
            else:
                deepestLCAInfo = rightInfo
        elif(root.left is not None and root.right is None):
            leftInfo = getDeepestLCA(root.left)
            deepestLCAInfo = leftInfo
            deepestLCAInfo.deepestNodeLevel += 1
        elif(root.right is not None and root.left is None):
            rightInfo = getDeepestLCA(root.right)
            deepestLCAInfo = rightInfo
            deepestLCAInfo.deepestNodeLevel += 1
    return deepestLCAInfo

class Solution:
    def lcaDeepestLeaves(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if(root is None):
            return None
        return getDeepestLCA(root).lca
