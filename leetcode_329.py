'''
Ideas
1. Flood-fill/DFS/BFS this thing and try each path possible
    -> can we cache the paths explored as well ?
    -> check if a path has alreay been explored or not too!
    
COMPLEXITY
Let M,N := dimensions of the grid
DP Performance
TIME = O(MN)
SPACE = O(MN)

TEST CASES
(A)
(B)
(C)
(D)

We can always expect each path to terminate
Due to the strict '>' inequality here, visited sets need not be kept. We never visit nodes <= current node anyways ( not considered in the backtracking ) 


'''
class Solution:
    
      # Return lengths calculated here
    def dfs(self, DP: List[List[int]], matrix: List[List[int]], i: int, j: int) -> int:
        if(DP[i][j] != -1):
            return DP[i][j] # top-down memoization ( typical in graph problems TBH )
        m = len(matrix)
        n = len(matrix[0])
        dirs = [[-1,0],[0,-1],[1,0],[0,1]]
        maxLen = 1
        for el in dirs:             # PYTHON range-based loop
            childI = i + el[0]
            childJ = j + el[1]
            if(0 <= childI < m and 0 <= childJ < n):
                if(matrix[childI][childJ] > matrix[i][j]):
                    maxLen = max(maxLen, 1 + Solution.dfs(self,DP,matrix,childI, childJ))
        DP[i][j] = maxLen
        return DP[i][j]
        
    # Caution with PY recursive call stack
    # ACcepted. YAY!
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        m = len(matrix)
        n = len(matrix[0])
        longestPath = 1
        DP = [[-1 for j in range(n)] for i in range(m)]
        # visited = [[-1 for j in range(n)] for i in range(m)] # -1 = visited : 1 = visted
        print(DP)
        for i in range(m):
            for j in range(n):
                subProblemPathLen = Solution.dfs(self,DP, matrix, i, j)  # is a  class method here!
                longestPath = max(longestPath, subProblemPathLen)
        return longestPath

