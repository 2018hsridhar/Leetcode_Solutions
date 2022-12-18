'''
URL = https://leetcode.com/problems/count-unhappy-friends/
1583. Count Unhappy Friends

Complexity
Let P := #-pairs
Let N := #-nodes
Let X := len(preferences)
Time = O(P) + P(N^2) + O(N^2) = O(P) + O(N^2)
Space = O(N^2) ( EXP ) O(1) ( IMP ) 

Time taken ~ 31 minutes
Took a bit longer than expected
'''
class Solution:
    def unhappyFriends(self, n: int, preferences: List[List[int]], pairs: List[List[int]]) -> int:
        # [1] Create hashmap of pairs
        myPairings = {}
        for pair in pairs:
            src = pair[0]
            dst = pair[1]
            myPairings[src] = dst
            myPairings[dst] = src
        # [2] Use ptr approach : collect the nodes preferred over existing myPairings
        myMorePreferredNodes = {}
        for node in range(0,n,1):
            nodePreference = preferences[node]
            prefPtr = 0
            nodesMoreDesiredThanCurNode = set()
            while(prefPtr < len(nodePreference)):
                if(nodePreference[prefPtr] == myPairings[node]):
                    break
                else:
                    nodesMoreDesiredThanCurNode.add(nodePreference[prefPtr])
                prefPtr += 1
            myMorePreferredNodes[node] = nodesMoreDesiredThanCurNode
        # print(myMorePreferredNodes)
        # [3] Avoid double counting : check set val > node val itself
        nodesMeetingCritera = set()
        for node in range(0,n,1):
            moreDesiredNodes = myMorePreferredNodes[node]
            for el in moreDesiredNodes:
                if(el > node):
                    if(node in myMorePreferredNodes[el]):
                        nodesMeetingCritera.add(node)
                        nodesMeetingCritera.add(el)

        numberUnhappyFriends = len(nodesMeetingCritera)
        return numberUnhappyFriends
