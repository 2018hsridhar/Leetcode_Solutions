'''
2363. Merge Similar Items
URL = https://leetcode.com/problems/merge-similar-items/

Complexity
Let M := len(items1) and N := len(items2)
Time = O(M+N)
Space = O(M+N) ( EXP ) O(1) ( IMP )

'''
class Solution:
    def mergeSimilarItems(self, items1: List[List[int]], items2: List[List[int]]) -> List[List[int]]:
        myItems = dict()
        mergedItems = []
        for v,w in items1:
            if(v not in myItems):
                myItems[v] = 0
            myItems[v] += w
        for v,w in items2:
            if(v not in myItems):
                myItems[v] = 0
            myItems[v] += w
        for k in sorted(myItems):
            mergedItems.append([k,myItems[k]])
        return mergedItems
