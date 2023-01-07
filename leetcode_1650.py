"""
# Definition for a Node.
class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None
"""

'''
URL = https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
1650. Lowest Common Ancestor of a Binary Tree III

Approach : Get one path ( of any node ) and intersect with set 

Complexity
Let N := #-nodes in the tree
Let H := height of tree : log_2(N) balanced AND (N) skewed
Time = O(H)
Space = O(H) ( EXP ) O(1) ( IMP ) 
Manipulate structures in place instead ( toggle ) ?
    no bit set readily avail.
Time spent = 18 minutes

'''

def getAncestorsOfNode(root: 'Node') -> set:
    myAncestors = set()
    while(root is not None):
        myAncestors.add(root.val)
        root = root.parent
    return myAncestors

def ancestorOfNodeInAncestorSet(q: 'Node', ancestorsSet: set()) -> 'Node':
    lca = Node(0)
    while(q is not None):
        if(q.val in ancestorsSet):
            lca = q
            break
        else:
            q = q.parent
    return lca

class Solution:

    def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
        ancestorsSetNodeP = getAncestorsOfNode(p)
        # https://stackoverflow.com/questions/52090334/printing-elements-of-a-set-in-python
        lca = ancestorOfNodeInAncestorSet(q,ancestorsSetNodeP)
        return lca 
