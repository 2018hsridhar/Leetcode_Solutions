'''
URL = https://leetcode.com/problems/path-sum-iv/
666. Path Sum IV

Approach : String parsing, HashMaps, Recursion

Complexity
Let N := #-nodes in the bTree
Let H := height of the bTree : log_2(N) balanced and (N) skewed 
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP )

32 mins
Caught bug
'''
class Solution:
    def pathSum(self, nums: List[int]) -> int:
        sumOfAllRootToLeafPaths = [0]
        nodeUnitDigMap = dict()
        nodePathSumMap = dict()
        # [1] Precomputation step.
        for num in nums:
            numStr = str(num)
            depth = int(numStr[0])
            position = int(numStr[1])
            value = int(numStr[2])
            key = numStr[0:2]
            if(key not in nodeUnitDigMap):
                nodeUnitDigMap[key] = value
                # print('Insert key into map = %s\n' % ( key ))
            if(key not in nodePathSumMap):
                nodePathSumMap[key] = 0
        # [2] Exec algo meat
        rootNodeKey = "11"
        rootRunSum = nodeUnitDigMap[rootNodeKey]
        Solution.fillInNodePathSumMap(self,rootNodeKey,nodeUnitDigMap,nodePathSumMap,sumOfAllRootToLeafPaths, rootRunSum)
        return sumOfAllRootToLeafPaths[0]

    # Why are locally scoped vars in a func same names as that of this signature? Confusing in codesearch.
    def fillInNodePathSumMap(self,parentNodeKey:str,nodeUnitDigMap:dict,nodePathSumMap:dict,sumOfAllRootToLeafPaths:List[int], curRunSum:int) -> None:
        depth = int(parentNodeKey[0])
        position = int(parentNodeKey[1])
        leftChildKey = str(depth +1) + str(position * 2)
        rightChildKey = str(depth +1) + str((position * 2) - 1)
        # [1] Work of parent
        nodePathSumMap[parentNodeKey] = curRunSum
        if(leftChildKey not in nodePathSumMap and rightChildKey not in nodePathSumMap):
            sumOfAllRootToLeafPaths[0] += nodePathSumMap[parentNodeKey]
            # print("Added sum = %d" % (nodePathSumMap[parentNodeKey]))
        # [2] Work of children
        else:
            if(leftChildKey in nodePathSumMap):
                leftSum = curRunSum + nodeUnitDigMap[leftChildKey]
                Solution.fillInNodePathSumMap(self,leftChildKey,nodeUnitDigMap,nodePathSumMap,sumOfAllRootToLeafPaths,leftSum)
            if(rightChildKey in nodePathSumMap):
                rightSum = curRunSum + nodeUnitDigMap[rightChildKey]
                Solution.fillInNodePathSumMap(self,rightChildKey,nodeUnitDigMap,nodePathSumMap,sumOfAllRootToLeafPaths,rightSum)



