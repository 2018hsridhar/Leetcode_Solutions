'''
URL = https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
1519. Number of Nodes in the Sub-Tree With the Same Label

Approach : Top-down/bottom-up, recursive, hashmap

Edge Cases :
(A)
(B)
(C)

Complexity
Let E : = #-edges = (N-1)
Time = O(N) ( twice : TD, then BU ) 
Space = O(H) 
N := #-nodes in the tree
H := height of the tree ( log_2(N) balanced, (N) skewed 0 )

Time = 22 minutes
Worked on first attempt :-)
'''

def createAdjList(n: int, edges: List[List[int]]) -> dict:
    adjList = dict()
    for i in range(0,n,1):
        adjList[i] = []
    for edge in edges :
        src = edge[0]
        dst = edge[1]
        adjList[src].append(dst)
        adjList[dst].append(src)
    return adjList

def obtainLabelCounts(adjList:dict, curNode:int, myCounts: List[int], visited:set, labels:str) -> dict:
    labelCounts = dict()
    if(curNode not in visited):
        visited.add(curNode)
        theHood = adjList[curNode]
        # [1] First, process the children nodes ; ensure parent added to visited set.
        for childNode in theHood:
            childLabelCount = obtainLabelCounts(adjList, childNode, myCounts, visited,labels)
            for key,val in childLabelCount.items():
                if key not in labelCounts:
                    labelCounts[key] = 0
                labelCounts[key] += val
        # print("Cur node = %d\n" % (curNode))
        # print("Label Counts = " % labelCounts)
        # [2] Then exec label count testing, calcuation of label count, and update of myCounts
        curNodeLabel = labels[curNode]
        curNodeLabelCount = 1
        if(curNodeLabel in labelCounts):
            curNodeLabelCount += labelCounts[curNodeLabel]
        myCounts[curNode] = curNodeLabelCount
        # [3] Update the labelCount HM with the latest frequencies.
        if(curNodeLabel not in labelCounts):
            labelCounts[curNodeLabel] = 0
        labelCounts[curNodeLabel] = curNodeLabelCount
    return labelCounts


# Index of each edge = node = label in the str too!
class Solution:
    def countSubTrees(self, n: int, edges: List[List[int]], labels: str) -> List[int]:
        myCounts = [0 for i in range(n)]
        adjList = createAdjList(n,edges)
        startNode = 0
        visited = set()
        # print(adjList)
        obtainLabelCounts(adjList,startNode,myCounts, visited, labels)
        return myCounts
