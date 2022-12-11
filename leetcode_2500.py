'''
URL = https://leetcode.com/problems/delete-greatest-value-in-each-row/
2500. Delete Greatest Value in Each Row

Complexity
Let m,n within 50 here ( grid dims )
Time = O(MN)
Space = O(1) ( EXP ) O(1) ( IMP )
'''
class Solution:
    def deleteGreatestValue(self, grid: List[List[int]]) -> int:
        answer = 0
        # Each row itself is a list of lists ( in py ) -> can we sort them too?
        m = len(grid)
        n = len(grid[0])
        for row in grid:
            row.sort()
        for col in range(0,n,1):
            colMax = 0
            for row in grid:
                colMax = max(colMax,row[col])
            answer += colMax
        return answer
