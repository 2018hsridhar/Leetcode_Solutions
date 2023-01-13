"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""
'''
URL = https://leetcode.com/problems/diameter-of-n-ary-tree/
1522. Diameter of N-Ary Tree

Algorithms : DFS, Recursion

Complexity
Let N := #-nodes in the n-ary tree
Let H := height of the n-ary tree : N in the worst case
Time = O(N)
Space = O(H) ( IMP ) O(1) ( EXP ) 

Test Cases
(A) [1] => 0
(B) [1,null,2,null,3] => 2
(C) [1,2,null,3,null] => 2
(D) [1,2,3] => 2
(E)
(F)

15 mins to completion.

'''

class Solution:

    def computeDiameterOfNAryTree(self,root:'Node',diameter:List[int]) -> int:
        curLongestRootToLeafPathLength = 0
        if(len(root.children) == 0):
            return 0
        # 1 or two paths here
        # better : pq of size = 2, brings us down to O(N), versus sort here.
        childPathLens = [] # clear and make member of class desired.
        for child in root.children:
            lenPathChildToLeaf = Solution.computeDiameterOfNAryTree(self,child,diameter)
            childPathLens.append(lenPathChildToLeaf)
        if(len(childPathLens) == 1):
            curLongestRootToLeafPathLength = 1 + childPathLens[0]
            curRootDiameter = curLongestRootToLeafPathLength
            if(curRootDiameter > diameter[0]):
                diameter[0] = curRootDiameter
        else:
            childPathLens.sort(reverse=True) # increases complexity here
            firstLongestChildPath = childPathLens[0]
            secondLongestChildPath = childPathLens[1]
            curRootDiameter = firstLongestChildPath + secondLongestChildPath + 2
            if(curRootDiameter > diameter[0]):
                diameter[0] = curRootDiameter
            curLongestRootToLeafPathLength = firstLongestChildPath + 1 
        return curLongestRootToLeafPathLength

    def diameter(self, root: 'Node') -> int:
        diameterLength = [0]
        Solution.computeDiameterOfNAryTree(self,root,diameterLength)
        return diameterLength[0]



