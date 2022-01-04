'''
/*
Length equals at most k here
Partition the array here

IDEAS
- highly akin to a sliding window from cur index going forwards too
- Goal is to maximize, as we have subproblems as well
- remember that we can traverse both leftwards and rightwards here


COMPLEXITY for POLY time
Do not worry about 5*10^11 issue : test cases valid
Let N := len(arr)
Let K := subarray lengths ( from 1 to K )
TIME = O(NK)
SPACE = O(NK) | O(N^2K)
    Best SPACE = O(N) instead. Can we do subproblems too?
    Distance within the array inputs are knowns ahead of time
    Even more ideal is O(K), as only O(K) subproblems must be maintained as we travere. Consider dequeue or list dstructure? to avoid swp operations as well?
Returning a summation here
Can use prefix sums too

Cool thing about sliding window : going left at a future elem
was just going right at a previous elem
E.g. {15,7,9} to the right === {15,7,9} to the left
      ----->                    <-----

MAXHEAP
No need to return the array : just solve with sliding window DEQUEUE?
However -> deletion is logarithmic, and it is NOT just a max removal.
But the sliding window sizes vary as well, and hence, confusion.
Try array list then

TEST CASE : 
(A) [1,15,7,19,20,100,101,101] k = 3
    => [15,15,20,20,20,100,101,101]
    => [1,19,19,19,101,101,101,101] = greedy-choice strategy via maximal elem
    => [15,15,19,19,101,101,101,101] = true solution
        =====       -----------    
    * counter example shows that greedy-choice strategy of max element does not work in all cases
    * must exhaust combinations
(B)      
(C)
(D)


'''


'''
        https://www.youtube.com/watch?v=_6FbM085TR8
        list = ['a','b',',c']
        # print(type(list))
        # print(type(list[0]))
        dq = deque([1,2,3])
        print(dq)
        dq.append(11)
        print(dq)
        dq.appendleft(0)
        print(dq)
        dq.popleft()
        dq.pop()
        print(dq)
        dq[1] = 100
        print(dq)   # index notation to update index values!
        # append -> 1 item as extend -> iterable
        lst = [20,21,22]
        tup = (31,32,33,34)
        dq.extend(lst)
        dq.extendleft(tup) # append in a REVERSE order!
        print(dq)
        
        dq2 = deque([1,2,3,4,5])
        dq2.insert(2,[11,22]) # insertion @ index
        print(dq2)
        
        # Can treat deque as a circular buffer AND rotate it
        dq2.rotate(2)
        print(dq2)
        
        dq2.reverse()
        print(dq2) 
        
        dq.clear()
        print(dq)
        
        # Can limit size of an ADT
        dq3 = deque([], 5)
        dq3.extend([1,2,3,4,5])
        print(dq3)
        dq3.append(6)
        print(dq3)  # no exception, but, initElem was removed as a side op
        dq3.append(7)
        print(dq3)
        print(dq3.maxlen)
        
        
        # %%timeit
        # dq3.append(10)
        # %%timeit
        maxSum = 0;
        # ArrayList<Integer> kSubproblems = new ArrayList<Integer>(); // we must initialize it though
        '''

import sys
import array
import numpy


'''
Python - dequeue
List-like container
Fast appends and fast pops on either end
Lists := mutable sequences

https://www.youtube.com/watch?v=_6FbM085TR8

'''
from collections import deque

class Solution:
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        maxSum = 0

        
        return maxSum;  
