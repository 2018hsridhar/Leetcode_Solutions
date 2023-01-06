# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

'''
URL = https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
863. All Nodes Distance K in Binary Tree

Complexity
Let N := #-nodes in the btree
Let H := height of the btree

Time = O(N^2)
    Worst case : N nodes in a single tree, and N nodes to traverse down per each node to path.
Space = O(1) ( EXP ) O(H)( IMP ) 

Case Testing : 
(A) [1,2,3] 3 1 => {2}
(B) [1,2,3] 3 0 => {3}
(C) [1,2,3] 3 2 => {1}
(D) [1,null,2,null,3,null,4] 4 2 => {2}
(E) [1,2,3,4,5,6,7] 4 2 => {1,5} 

33 mins

'''
def getNodesAtGivenDistance(root: TreeNode, distance: int, myNodes: List[int]) -> None:
    if(root is None or distance < 0):
        return
    if(distance == 0):
        myNodes.append(root.val)
    if(root.left is not None):
        getNodesAtGivenDistance(root.left,distance-1,myNodes)
    if(root.right is not None):
        getNodesAtGivenDistance(root.right,distance-1,myNodes)

# get path to target ( and their associated distances from target ) -> is a tuple.
def getPathToTarget(root: TreeNode, target: int, pathToTarget: List[TreeNode]) -> bool:
    if(root is None):
        return False
    if(root.val == target):
        pathToTarget.append(root)
        return True
    hasPathToTarget = False
    if(root.left is not None and getPathToTarget(root.left,target,pathToTarget)):
        pathToTarget.append(root)
        hasPathToTarget = True
    if(root.right is not None and getPathToTarget(root.right,target,pathToTarget)):
        pathToTarget.append(root)
        hasPathToTarget = True
    return hasPathToTarget

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        pathToTarget = []
        nodesKAway = []
        getPathToTarget(root,target.val, pathToTarget)
        distFromTarget = 0
        for index in range(len(pathToTarget)):
            parentNode = pathToTarget[index]
            if(index == 0):
                getNodesAtGivenDistance(parentNode,k,nodesKAway)
            elif ( index > 0):
                if ( distFromTarget == k):
                    nodesKAway.append(parentNode.val)
                elif (distFromTarget < k):
                    childNode = pathToTarget[index-1]
                    if(parentNode.left == childNode):
                        getNodesAtGivenDistance(parentNode.right,k - distFromTarget - 1,nodesKAway)
                    else:
                        getNodesAtGivenDistance(parentNode.left,k - distFromTarget - 1,nodesKAway)
            distFromTarget += 1
        return nodesKAway

