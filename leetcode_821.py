'''
821. Shortest Distance to a Character
URL = https://leetcode.com/problems/shortest-distance-to-a-character/
'''
import sys

class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        n = len(s)
        dists = [sys.maxsize for i in range(n)] # initialize accordingly too
        #print(type(dists))
        distCLeft = sys.maxsize
        distCRight = sys.maxsize    # int obj not callabel
        
        # loop from left
        hitC = False
        for i in range(0,n,1):
            if s[i] != c and hitC:
                distCLeft += 1
            elif s[i] == c:
                hitC = True
                distCLeft = 0
            dists[i] = distCLeft
        
        # loop from right
        hitC = False
        for i in range(n-1,-1,-1):
            if s[i] != c and hitC:
                distCRight += 1
            elif s[i] == c:
                hitC = True
                distCRight = 0
            dists[i] = min(dists[i],distCRight)
            
        return dists
        
