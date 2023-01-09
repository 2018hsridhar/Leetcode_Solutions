'''
URL = https://leetcode.com/problems/most-profitable-path-in-a-tree/
2467. Most Profitable Path in a Tree

Alice and Bob both move in lockstep : leverage this aspect
Alice's income matters -> @ this point
(A) the max from Bob's path
(B) Used the max from the other paths.

Approach : Recursion, Two pointers.

Complexity: 
Let N := #-nodes in the btree ( pow(10,5)) 
Let H := height of the tree : N worst case
Time = O(N)
Space = O(1) ( EXP ) O(H) ( IMP ) 

'''
class TreeNode:
    id = 0
    amount = 0
    children = dict()
    parent = -1

    def __init__(self,id,amount,children,parent):
        self.id = id
        self.amount = amount
        self.children = children
        self.parent = parent

class Solution:

    def createTreeNodes(self, edges:List[List[int]], amount: List[int], treeNodes: dict) -> None:
        for edge in edges:
            srcId = edge[0]
            dstId = edge[1]
            srcAmount = amount[srcId]
            dstAmount = amount[dstId]
            if(srcId not in treeNodes):
              toAddNode = TreeNode(srcId,srcAmount,dict(),-1)
              treeNodes[srcId] = toAddNode  
            if(dstId not in treeNodes):
              toAddNode = TreeNode(dstId,dstAmount,dict(),-1)
              treeNodes[dstId] = toAddNode  
            parentNode = treeNodes[srcId]
            childNode = treeNodes[dstId]
            parentNode.children[dstId] = childNode
            childNode.parent = srcId

    def getPathBob(self,bob:int,treeNodes: dict) -> List[TreeNode]:
        bobPath = []
        while(bob != -1):
            bobCurNode = treeNodes[bob]
            bobPath.append(bobCurNode)
            bob = bobCurNode.parent
        return bobPath

    # Gate opened : no price required, nor cash -> set amount to 0
    # Gate shared : halve that amount
    # Alice and bob always in diff nodes :-)
    def mostProfitablePath(self, edges: List[List[int]], bob: int, amount: List[int]) -> int:
        treeNodes = dict()
        Solution.createTreeNodes(self,edges, amount, treeNodes)
        # Does start from reverse / botom
        bobPath = Solution.getPathBob(self,bob,treeNodes)
        # [1] Refactor into own method
        haveHalfAdjust = (len(bobPath) % 2 == 1)
        bobPathHalfLen = int(len(bobPath) / 2)
        if(len(bobPath) % 2 == 1):
            bobPathHalfLen = int((len(bobPath) - 1)/ 2) # 3-1 = 2 / 2 = 1 ( 1 node affected only ) 
        indexPtr = 0
        while(indexPtr < bobPathHalfLen):
            bobPath[indexPtr].amount = 0
            indexPtr += 1
        if(haveHalfAdjust):
            bobPath[indexPtr].amount = int(bobPath[indexPtr].amount/2) # all ints even :-)
        maxAliceNetIncome = 0

        return maxAliceNetIncome




