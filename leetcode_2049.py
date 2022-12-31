'''
URL = https://leetcode.com/problems/count-nodes-with-the-highest-score/
2049. Count Nodes With the Highest Score

Approach : Top-Down/Bottom-up, Recursion, Math && Combinatorics

Complexity : 
Let N := #-nodes in the binary tree
Let H := height of the tree ( log_2(N) balanced, (N) skewed ) 
Time = O(N)
Space = O(H) ( IMP ) O(N) ( EXP ) 
    - store size of the trees too.
    - need support dstructs to encapsulate further information.

Time taken = 18.5 minutes.
'''

def createAdjList(parents:List[int]) -> dict:
    adjList = dict()
    for i in range(len(parents)):
        adjList[i] = []
    for child in range(len(parents)):
        parent = parents[child]
        if(parent != -1):
            adjList[parent].append(child)
    return adjList

# Start recursive call at node 0 too.
def solveSubTreeSizes(adjList:dict, curNode:int, nodeSizeDict: dict) -> int:
    myChildren = adjList[curNode]
    currentTreeSize = 1
    if(len(myChildren) > 0):
        for child in myChildren:
            childSubTreeSize = solveSubTreeSizes(adjList, child, nodeSizeDict)
            currentTreeSize += childSubTreeSize
    nodeSizeDict[curNode] = currentTreeSize
    return currentTreeSize

def countHighestScoreNodesInternal(adjList: dict, parents: List[int], nodeSizeDict: dict) -> int:
    # print(nodeSizeDict)
    numHighestScoringNodes = 0
    currentHighestScore = 0
    totalNumberNodes = nodeSizeDict[0] # 0 guaranteed to be root node too!
    for curNode in range(len(parents)):
        curNodeScore = 1 # Is a product
        nodeParent = parents[curNode]
        if(nodeParent != -1):
            sizeTopTree = totalNumberNodes - nodeSizeDict[curNode]
            curNodeScore *= sizeTopTree
        for child in adjList[curNode]:
            childScore = nodeSizeDict[child]
            curNodeScore *= childScore
        # print("For node = %d \t curNodeScore = %d\n" % ( curNode, curNodeScore))
        if(curNodeScore > currentHighestScore):
            currentHighestScore = curNodeScore
            numHighestScoringNodes = 1
        elif(curNodeScore == currentHighestScore):
            numHighestScoringNodes += 1
    return numHighestScoringNodes

class Solution:
    # 0 := root of your tree.
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        adjList = createAdjList(parents)
        nodeSizeDict = {}
        solveSubTreeSizes(adjList, 0, nodeSizeDict)
        return countHighestScoreNodesInternal(adjList, parents,nodeSizeDict)
    
