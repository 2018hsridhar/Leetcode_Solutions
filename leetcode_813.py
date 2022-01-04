'''

URL = https://leetcode.com/problems/largest-sum-of-averages/
813. Largest Sum of Averages

TEST CASES :
(A) [9,1,2,3,9], k = 1
    => 24/5
(B) [9,10,11] k = 3
    1 : 10
    2 : 20.5
    3 : 30 ( this is best choice here ) 
    Seems like the more we partition, the better as well
(C) [1,1,1,1,1]
3
(D)

[9,1,2,3,9] k = 3
    |<= partition
[9,1] + [2,3,9] k = 2

Combinatorial -> hence is DP
K partitions possible only AT MOST : you can partition less as well
Prefix sums seem useful here
sum of averages across each subarray : averages use sums anyways

State : index as far into nums, and int k ( #-remaining partitions )
ID base case of k = 1 as well

Len in range [1,100] 
Numbers reasonably ranged too and k within closed bounds
Two pointers with subarray problems
Any use of Kadane's/other approaches too?

DP COMPLEXITY
Let N := len(nums)
Let K := #-partitions
TIME = O(NK)
SPACE = O(N) or O(NK) or O(N^2) . Can we optimize for O(N) space?

Answers within 10 pow -6 a-okay ( handle FPE with division )
Partitions must cover entire set ( includes integers )


9,1,2,3 => other elems < 9 : no higher average
9,9,9,9 => other elems = 9 : same avg
9,10,11 => other elems > 9 : greater average
    {9,10,11} -> avg = 10
    
Rows -> partitions
Cols -> where remaining numbers start from

Luckily numbers strictly positive ( but should extend to neg cases too, or data overflow exceptions ) 

https://www.youtube.com/watch?v=nlP5kF1_efE

'''

import sys
import array as arr
# import allowed ONLY at module level
from array import *
import numpy

class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        
        largestAvg = 0
        # print(numpy.zeros((5, 5)))
        # [1] Initialize memory footprint
        largestAvg = 0.0;
        n = len(nums)
        DP = [[0 for i in range(n)] for j in range(k+1)] # validate if correct syntax ( alawys element first in list range syntax, then the for loop )
        # Why is this type object treated as an int ( and thus not subscriptable/iterable)?
        prefixSum = [0 for i in range(n)]
        for i in range(0,n,1):
            prefixSum[i] = nums[i]
            if i >= 1:
                prefixSum[i] += prefixSum[i-1]
        
        # [2] Initialize DP base case
        for numParts in range(1,k+1,1):
            DP[numParts][n-1] = nums[n-1] # 1 len array : avg = elem standalone
        
        # [3] The POLY-time DP algo
        # We may have to do some creative bounds checking here ( for validity ) 
        # Your struggle is the invalidation steps here as well! 
        # Other gotcha : going backwards, then forwards, in nested iteration. Could be exam question for Big-O reasoning LOL
        # Failing to account for partition case with single element as well here
        # Riskking possibility of precision errors here too!
        for numParts in range(1,k+1,1): 
            for i in range(n-2,-1,-1):
                ithMaxPartition = -1*float('inf')   # Is not sys.float : it is float
                for j in range(i,n,1):
                    prefixPartSum = prefixSum[j]
                    if(i > 0):
                        prefixPartSum = prefixSum[j] - prefixSum[i-1]
                    numElems = j - i + 1
                    prefixPartAvg = prefixPartSum / numElems
                    curPartitionSum = prefixPartAvg
                    if numParts - 1 > 0 and j+1 < n :
                        curPartitionSum = prefixPartAvg + DP[numParts - 1][j+1]
                    elif j+1 == n and numParts - 1 == 0:
                        curPartitinoSum = prefixPartAvg
                    else :
                        curPartitionSum = -1*float('inf') # quick invalidation
                    ithMaxPartition = max(ithMaxPartition, curPartitionSum)
                DP[numParts][i] = ithMaxPartition; 

        largestAvg = DP[k][0]
        return largestAvg;

      
'''
        Learning of arrays
        Container holding multiple vals, same type
        var = array(typecode, [elements])

        var = array(typecode, [elems])
        Have C-types and non-C types
#         arr = array('i', [-1,2,2,3,4,5])
#         arr.remove(2)   # first value ( must re-run )
#         print(arr)
#         print(arr.buffer_info()) # shows size of buffer ( OS address,size )
#         print(arr[2])        
#         for i in arr:       # Simply value-iteration
#             print(i)
#         for pnt in range(1,4):    # Index-based iteration
#             print(pnt, arr[pnt])
#         arr.reverse()
#         print(arr)
#         arr.append(10)
#         print(arr)
#         arr.remove(2)
#         print(arr)
#         arr.reverse()
#         print(arr[3])
#         print(arr.index(4))
        
#         A = array("i",[])
        
        Range statements default start at 0
        Arrays not fixed-size : they are dynamic ( add/remove )
        
        # x = int(input("Enter size of array"))
       # print('enter %$d elements'%x)
        # print(x)
        # x = 3
        # for i in range(x):
            # n = int(input())
        arr = array('i', [1,2,2,3,3])
        x = len(arr)
        i = 0
        while i < x - 1:
            j = i+1
            while j < x :
                if arr[i] == arr[j]:
                    del arr[j]
                    x += -1 # array one less
                j += 1
            i += 1
        print(arr)
        
        return 0        
'''
