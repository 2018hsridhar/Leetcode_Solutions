'''
1376. Time Needed to Inform All Employees
URL = https://leetcode.com/problems/time-needed-to-inform-all-employees/description/
So which path in the tree : root -> leaf, takes up most time for inform?
We have parent->child rels : create adjList from that?
May have encountered this problem earlier on pramp.io mock interview platform.

Edge Cases :
(A)
(B)
(C)

Complexity: 
Let N := #=nodes in the n-ary tree
Let H := height of the tree : log_2(N) balanced and (N) skewed
Time = O(N)
Space = O(H) ( IMP ) O(1) ( EXP ) 

13.5 mins to solution
'''

def getMaxTimeToLeaf(adjList: dict, informTime: List[int], rootNode: int) -> int:
    myMaxTimeFromRootNode = informTime[rootNode]
    if(rootNode not in adjList):
        return 0
    children = adjList[rootNode]
    for child in children:
        myMaxTimeFromRootNode = max(myMaxTimeFromRootNode, 
            informTime[rootNode] + getMaxTimeToLeaf(adjList,informTime,child))
    return myMaxTimeFromRootNode

def createDirectedAdjacencyList(manager: List[int]) -> dict:
    adjList = dict()
    for index,parent in enumerate(manager):
        if(parent not in adjList):
            adjList[parent] = []
        adjList[parent].append(index)
    return adjList

class Solution:
    def numOfMinutes(self, n: int, headID: int, manager: List[int], informTime: List[int]) -> int:
        adjList = createDirectedAdjacencyList(manager)
        myNumMinutesNeeded = getMaxTimeToLeaf(adjList,informTime, headID)
        return myNumMinutesNeeded
