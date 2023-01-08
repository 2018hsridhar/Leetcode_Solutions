'''
1483. Kth Ancestor of a Tree Node
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

Cases
(A) [[7, [-1, 0, 1,2,3,4,5]], [3, 1]]
(D) [[9, [-1, 0, 1,2,3,4,5,6,7]], [3, 1]]
(E)

["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[10000,[-1,0,1,1,1,2,2,6,2,7,0,3,11,10,1,8,15,10,11,9,0,2,2,19,20,0,2,3,16,4,27,9,23,20,24,1,30,27,21,31,20,14,5,15,22,7,17,27,8,47,26,49,24,37,39,29,3,52,44,33,37,7,20,3,27,41,14,39,47,39,14,8,63,39,28,50,55,74,24,31,57,57,56,60,56,40,52,46,85,88,77,70,74,83,40,38,82,43,10,13,97,88,85,87,35,50,57,106,5,32,59,106,7,40,94,7,36,37,48,7,83,31,71,18,83,117,102,10,102,108,32,108,55,80,133,117,123,87,83,1,36,76,140,115,67,143,52,139,56,38,53,95,73,131,105,10,130,116,8,117,128,75,100,149,66,63,73,36,51,18,131,128,104,82,83,149,150,149,60,114,93,89,145,118,32,121,56,159,183,111,12,129,70,164,125,16,51,58,71,188,17,8,199,80,116,37,23,75,154,103,23,184,181,57,170,42,73,42,91,202,85,128,18,210,152,67,82,74,130,158,91,77,212,121,189,81,117,188,10,184,64,95,77,116,13,72,23,78,211,127,215,218,251,39,243,160,94,74,17,8,156,182,236,177,178,87,149,94,157,38,240,65,254,141,194,102,206,237,192,122,76,19,126,17,5,98,37,125,45,201,108,276,211,0,60,104,129,201,165,121,209,238,202,150,100,281,111,11,269,7,2...



#-test cases passed = 7/16
TLE = ___ sadly yes.

20 minutes
ACCEPTED
OMG a hard! 2 hours spent!
'''

class Node:
    val = 0
    binaryAncestors = dict()
    parentIndex = 0
    children = []

    def __init__(self, val: int, binaryAncestors: dict, children: List['Node'], parentIndex: int):
        self.binaryAncestors = binaryAncestors
        self.val = val
        self.children = children
        self.parentIndex = parentIndex

    def baToString(self) -> str:
        result = ""
        for key,val in self.binaryAncestors.items():
            result += "( Key: "
            result += str(key)
            result += ", "
            result += "Val: "
            result += str(val.val)
            result += "), "
        return result

# self. prefix needed due to class Identiifer.
class TreeAncestor:
    adjList = dict()
    treeIdToNode = dict()

    # Debug this method later :-).
    def fillInBinaryAncestorPointers(self, curNode:int) -> None:
        # [1] Work on parent and fill upwards
        myNode = self.treeIdToNode[curNode]
        parentIdx = myNode.parentIndex
        # print("Called at node = %d with parentIndex = %d" % ( curNode, parentIdx))
        if(parentIdx != -1):
            myCurBinAnc = self.treeIdToNode[parentIdx]
            binAncStep = 1 
            # print(myCurBinAnc.baToString())
            while(binAncStep in myCurBinAnc.binaryAncestors):
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
    # def printKAncestorTree(self, curNodeId: int):
    #     startNode = self.treeIdToNode[curNodeId]
    #     children = startNode.children
    #     for child in children:
    #         self.printKAncestorTree(child.val)

    # 1000 -> 0001 now
    # Coudl eb at this string conversion too. There should be a faster way as well!
    # def getNumInBinary(self,x:int) -> str:
    #     binStr = ""
    #     while(x > 0):
    #         if(x % 2 == 1):
    #             binStr += '1'
    #         else:
    #             binStr += '0'
    #         x = int(x/2)
    #     return binStr # this has been reversed too!

    def getKthAncestor(self, node: int, k: int) -> int:
        # self.printKAncestorTree(0)
        # binStr = self.getNumInBinary(k)
        powerOfTwo = 0
        curNode = self.treeIdToNode[node]
        kthAncestor = -1
        # for index in range(len(binStr)):
        #     if(binStr[index] == '1'):
        #         step = int(pow(2,powerOfTwo))
        #         if(step in curNode.binaryAncestors):
        #             curNode = curNode.binaryAncestors[step]
        #         else:
        #             return -1
        #     powerOfTwo += 1
        curK = k
        while(curK > 0):
            rem = curK % 2
            if(rem == 1):
                step = int(pow(2,powerOfTwo))
                if(step in curNode.binaryAncestors):
                    curNode = curNode.binaryAncestors[step]
                else:
                    return -1
            powerOfTwo += 1
            curK = int(curK / 2)
        kthAncestor = curNode.val
        return kthAncestor

# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)
