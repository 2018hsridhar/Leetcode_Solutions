# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

'''
URL = https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
1644. Lowest Common Ancestor of a Binary Tree II

Idea : Top-down : we interested in only two values : p and q.
At each node, check if we have <p>, have <q>, have both, or none : 4 states
If a node has both & first hit -> this is the LCA.
Avoid allocating lists or checking node space too.

Complexity
Let N := #-nodes in binary tree
Let H := height of btree : log_2(N) balanced and (N) skewed
Time = O(N)
Space = O(1) ( EXP ) O(H) ( IMP ) 

Time taken = 22 minutes
'''
class stateHavePQ:

    haveP = False
    haveQ = False
    myNode = TreeNode(0)

    def __init__(self, haveP, haveQ, myNode):
        self.haveP = haveP
        self.haveQ = haveQ
        self.myNode = myNode

def lowestCommonAncestorInternal(root: 'TreeNode', p: int, q: int) -> stateHavePQ:
    myHavePQState = stateHavePQ(False,False,root) # is always current node
    if(root.left is None and root.right is None):
        if(root.val == p):
            myHavePQState.haveP = True
        elif(root.val == q):
            myHavePQState.haveQ = True
        return myHavePQState
    else:
        # LST has LCA
        # Possibly both here -> but check
        if(root.val == p):
            myHavePQState.haveP = True
        elif(root.val == q):
            myHavePQState.haveQ = True
        if(root.left is not None):
            myHavePQStateLST = lowestCommonAncestorInternal(root.left,p,q)
            if(myHavePQStateLST.haveP and myHavePQStateLST.haveQ):
                return myHavePQStateLST
            myHavePQState.haveP |= myHavePQStateLST.haveP
            myHavePQState.haveQ |= myHavePQStateLST.haveQ
        # RST has LCA
        if(root.right is not None):
            myHavePQStateRST = lowestCommonAncestorInternal(root.right,p,q)
            if(myHavePQStateRST.haveP and myHavePQStateRST.haveQ):
                return myHavePQStateRST
            myHavePQState.haveP |= myHavePQStateRST.haveP
            myHavePQState.haveQ |= myHavePQStateRST.haveQ
    return myHavePQState

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if(root is None):
            return TreeNode(0)
        rootHavePQState = lowestCommonAncestorInternal(root,p.val,q.val)
        lca = None
        if(rootHavePQState.haveP and rootHavePQState.haveQ):
            lca = rootHavePQState.myNode
        return lca
