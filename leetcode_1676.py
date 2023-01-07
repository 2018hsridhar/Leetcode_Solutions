# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
'''
URL = https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
1676. Lowest Common Ancestor of a Binary Tree IV

Leverage passing up a set for each node in bottom-up manner
Use set size ( no need to check all els if additive op is well configured ) 
Complexity
Let N := #-nodes in the btree
Let X := size of nodes to have
Let H :+ height of btree : log_2(N) balanced and (N) skewed.
Time = O(N)
Space = O(H) ( IMP ) O(X) ( EXP ) 

Time ~ 18 mins
'''
class NodeStats:

    descSet = set()
    myNode = TreeNode(0)

    def __init__(self, descSet, myNode):
        self.descSet = descSet
        self.myNode = myNode


# How to quickly copy sets?
def lcaInternal(root: 'TreeNode', targetDescendantSet: set) -> NodeStats:
    rootNodeStats = NodeStats(set(),root)
    if(root.left is None and root.right is None):
        if(root.val in targetDescendantSet):
            rootNodeStats.descSet.add(root.val)
    else:
        if(root.val in targetDescendantSet):
            rootNodeStats.descSet.add(root.val)
        if(root.left is not None):
            lstStats = lcaInternal(root.left, targetDescendantSet)
            if(len(lstStats.descSet) == len(targetDescendantSet)):
                print("LEFT")
                return lstStats
            rootNodeStats.descSet.update(lstStats.descSet) # ideally, copy op complexity reducible.
        if(root.right is not None):
            rstStats = lcaInternal(root.right, targetDescendantSet)
            if(len(rstStats.descSet) == len(targetDescendantSet)):
                print("RIGHT")
                return rstStats
            rootNodeStats.descSet.update(rstStats.descSet)
    return rootNodeStats

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', nodes: 'List[TreeNode]') -> 'TreeNode':
        descendantSet = set()
        for node in nodes:
            descendantSet.add(node.val) # methods are attributes in PY
        rootNodeStats = lcaInternal(root, descendantSet)
        return rootNodeStats.myNode
