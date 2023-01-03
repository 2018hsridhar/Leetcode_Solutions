# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
URL = https://leetcode.com/problems/distribute-coins-in-binary-tree/
979. Distribute Coins in Binary Tree

Complexity
Let N := #-nodes in the binary tree
Let H := height of the binary tree : Log_2(N) balanced, (N) skewed
Time = O(N)
Space = O(H) ( IMP ) O(1) ( EXP ) 

Edge Cases
(A) [0,0,0,5,null,null,0,null,null,4,null,0,0,0]
(B) [0,0,0,4]
(C)
(D)
(E)

44 minutes
A bit longer than expected

'''

class Helper:
    numcoins = 0
    toFillDists = []

    # Not like OOP : leverage built-in __init__ for constructor.
    def __init__(self, numCoins, toFillDists):
        self.numCoins = numCoins
        self.toFillDists = toFillDists

    def print(self):
        print("Num coins = %d \t list = %s" % (self.numcoins, self.toFillDists))

# We will not get weird case of a singleton node with no coinage :-)
def getMinSteps(root: TreeNode, ans: List[int]) -> Helper:
    curNodeInfo = Helper(0,[])
     # Base case of leaf node
    if(root.left is None and root.right is None): # leaf case handling
        if(root.val >= 2):
            curNodeInfo.numCoins = root.val - 1
            ans[0] += curNodeInfo.numCoins # We pass up this many coins
        elif ( root.val == 0):
            curNodeInfo.toFillDists.append(1)
    else: # Operate on each branch.
        if(root.left is not None):
            leftNodeInfo = getMinSteps(root.left, ans)
            curNodeInfo.toFillDists.extend(leftNodeInfo.toFillDists)
            curNodeInfo.numCoins += leftNodeInfo.numCoins
        if(root.right is not None):
            rightNodeInfo = getMinSteps(root.right, ans)
            curNodeInfo.toFillDists.extend(rightNodeInfo.toFillDists)
            curNodeInfo.numCoins += rightNodeInfo.numCoins
        # Meat of algo now
        curNodeInfo.numCoins += root.val
        curNodeInfo.toFillDists.append(0) # serves as a placeholder
        ptr = 0
        # We forgot to remove here :-)
        numElsToRemove = 0
        while(curNodeInfo.numCoins > 0 and ptr < len(curNodeInfo.toFillDists)):
            myDist = curNodeInfo.toFillDists[ptr]
            ans[0] += myDist
            curNodeInfo.numCoins -= 1
            ptr += 1
            numElsToRemove += 1
        for i in range(numElsToRemove):
            curNodeInfo.toFillDists.pop(0)
        # You passed only distances : not whether coins went up too!
        for i in range(len(curNodeInfo.toFillDists)):
            curNodeInfo.toFillDists[i] += 1
        ans[0] += curNodeInfo.numCoins
    # print(ans[0])
    # curNodeInfo.print()
    return curNodeInfo

class Solution:
    def distributeCoins(self, root: Optional[TreeNode]) -> int:
        if(root is None):
            return 0
        myMinSteps = [0]
        myMinStepsHelper = getMinSteps(root,myMinSteps)
        return myMinSteps[0]

