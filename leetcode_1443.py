'''
1443. Minimum Time to Collect All Apples in a Tree
URL = https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

Complexity
Let N := #-nodes in the binary tree
Let H := height of the binary tree : log_2(N) balanced, (N) skewed
Time = O(N)
Space = O(H) ( IMP ) O(1) ( IMP ) 

Time taken = 23 mins
'''

class TreeStats:
    def __init__(self, hasApple, numStepsToApple):
        self.hasApple = hasApple
        self.numStepsToApple = numStepsToApple

def createAdjacentList(n: int, edges:List[List[int]]) -> dict:
    adjList = dict()
    for i in range(n):
        adjList[i] = []
    for edge in edges:
        src = edge[0]
        dst = edge[1]
        adjList[src].append(dst)
        adjList[dst].append(src)
    return adjList

# Class with initializer
# https://docs.python.org/3/tutorial/classes.html
def exploreTree(visited: set, adjList:dict, hasApple: List[bool], curNode: int) -> TreeStats:
    myNumSteps = 0
    curNodeSubtreeHasApples = False
    if(hasApple[curNode] == True):
        curNodeSubtreeHasApples = True
    children = adjList[curNode]
    visited.add(curNode)
    for child in children:
        if(child not in visited):
            childTreeStats = exploreTree(visited, adjList, hasApple, child)
            childTreeHasApple = childTreeStats.hasApple
            childTreeStepsTaken  = childTreeStats.numStepsToApple
            if(childTreeHasApple):
                myNumSteps += childTreeStepsTaken
                myNumSteps += 2
                curNodeSubtreeHasApples = True
    return TreeStats(curNodeSubtreeHasApples, myNumSteps)

class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        myMinTime = 0
        adjList = createAdjacentList(n,edges)
        # print(adjList)
        startNode = 0
        visited = set()
        treeStats = exploreTree(visited, adjList, hasApple, startNode)
        myMinTime = treeStats.numStepsToApple
        return myMinTime
