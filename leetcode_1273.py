'''
URL = https://leetcode.com/problems/delete-tree-nodes/
1273. Delete Tree Nodes

Approach : Recursion, DFS

Complexity
Let N := #-nodes in the tree
Let H := height of the tree : log_2(N) balanced and (N) skewed
Time = O(N)
Space = O(1) ( EXP ) O(H) ( IMP ) 

Edge Cases
(A) 1 [-1][1] => 1
(B) 1 [-1][0] => 0
(C) 3 [-1,0,0][1,2,3] => 3
(D) 3 [-1,0,0][2,-1,-1] => 0
(E) 3 [-1,0,1][0,-1,1] => 0

23 minutes
But you tested and debugged better :-), so kudos !

'''
def createAdjacencyList(nodes:int, parent: List[int]) -> dict:
    adjList = dict()
    for i in range(0,nodes,1):
        adjList[i] = []
    for index, value in enumerate(parent):
        if(value != -1):
            adjList[value].append(index)
    return adjList

class NodeInfo:
    # Set attributes and reduce duplicate code via constructors.
    sizeOfTree = 0
    sumOfTree = 0

    # Since __init__ made, default construct no longer invocable
    def __init__(self,sizeOfTree,sumOfTree):
        self.sizeOfTree = sizeOfTree
        self.sumOfTree = sumOfTree

    def toString(self) -> str:
        return "treeSize = " + str(self.sizeOfTree) + "sumOfTree = " + str(self.sumOfTree)

# Function annotation forces return of value.
# Note : we may proceed all the way up -> how to `delete` a pseudo tree?
# If you delete at a higher level -> you technically deleted a lower level ( think of subset relations ) 
def solveNumberNodes(nodes: int, curNode: int, adjList:dict, value: List[int], ans: List[int]) -> NodeInfo:
    children = adjList[curNode]
    curNodeSum = value[curNode]
    curNodeSizeTree = 1
    for child in children:
        childInfo = solveNumberNodes(nodes, child, adjList, value,ans)
        curNodeSum += childInfo.sumOfTree
        curNodeSizeTree += childInfo.sizeOfTree
    if(curNodeSum == 0):
        ans[0] += curNodeSizeTree
        curNodeSizeTree = 0
        # no need for explicit curNodeSum = 0 here
    myNodeInfo = NodeInfo(curNodeSizeTree,curNodeSum)  # Parameters ordering
    # print(myNodeInfo.toString())
    # manipulate myNodeInfo when curNodeSum = 0 too!
    return myNodeInfo

class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        numNodesToDeleteWrapper = [0]
        rootNode = 0
        adjList = createAdjacencyList(nodes, parent)
        solveNumberNodes(nodes,rootNode,adjList,value,numNodesToDeleteWrapper)
        numNodesToDelete = numNodesToDeleteWrapper[0]
        numNodesRemaining = nodes - numNodesToDelete
        return numNodesRemaining
