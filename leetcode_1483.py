'''
1483. Kth Ancestor of a Tree Node ( HARD!!! ) 
URL = https://leetcode.com/problems/kth-ancestor-of-a-tree-node/

log2(50000) = 15.609640474437 ~= 16 steps maximium worst case
Approach : TD/BU, Tree, Binary Search

Complexity
Let N := #-nodes
Let Q := #-queries
Let D := max_depth of k to traverse ( say, = N ) 
Time = O(Q*log(D))
Space = O(N*log(D)) ( EXP ) O(1) ( IMP ) 

Parnet->child rels do not tell left,right info.
60 minutes spent ( and not even on meat of the problem ) .
'''

class Node:
    val = 0
    binaryAncestors = dict()
    parentIndex = 0
    children = []

    def __init__(self, val: int, binaryAncestors: dict, children: List['Node'], parent: int):
        self.binaryAncestors = binaryAncestors
        self.val = val
        self.children = children
        self.parent = parent

# self. prefix needed due to class Identiifer.
class TreeAncestor:
    adjList = dict()
    treeIdToNode = dict()

    def fillInBinaryAncestorPointers(self, curNode:int) -> None:
        # [1] Work on parent and fill upwards
        myNode = self.treeIdToNode[curNode]
        parentIdx = myNode.parent
        if(parentIdx != -1):
            myCurBinAnc = self.treeIdToNode[parentIdx]
            binAncStep = 1
            if(binAncStep in myCurBinAnc.binaryAncestors):
                nextbinAnc = myCurBinAnc.binaryAncestors[binAncStep]
                myNode.binaryAncestors[binAncStep * 2] = nextbinAnc
                myCurBinAnc = nextbinAnc
                binAncStep *= 2
        # [2] Recurse down and fill out other node ancestor information.
        myHood = myNode.children
        for child in myHood:
            self.fillInBinaryAncestorPointers(child.val)

    # n -> number of nodes -> is a given too : can populate a HM if needed with this
    def __init__(self, n: int, parent: List[int]):
        # [1] Create map : treeIntVal -> treeNode
        for index in range(n):
            toInsertNode = Node(index,dict(),[],-1) # -1 = no parent
            self.treeIdToNode[index] = toInsertNode
            # self.adjList[toInsertNode] = []
        for index, pVal in enumerate(parent):
            if(pVal != -1):
                childNode = self.treeIdToNode[index]
                parentNode = self.treeIdToNode[pVal]
                parentNode.children.append(childNode)
                childNode.parentIndex = pVal
                childNode.binaryAncestors[1] = parentNode
        # Recurse down and fill pointers up as we go :-)
        startNode = 0
        self.fillInBinaryAncestorPointers(startNode)

    # Root always 0th node
    def printKAncestorTree(self, curNodeId: int):
        startNode = self.treeIdToNode[0]
        children = startNode.children
        print("At node = %d, my binary ancestors =" % (startNode.val))
        print(startNode.binaryAncestors)
        for child in children:
            self.printKAncestorTree(child.val)

    def getKthAncestor(self, node: int, k: int) -> int:
        self.printKAncestorTree(node)
        return 0
        

# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)
