'''

Naive approach -> 2 pass, 2*N space solution using increasing seq and decreasing seqs ( harken back to sunset views )

845. Longest Mountain in Array
URL = https://leetcode.com/problems/longest-mountain-in-array/

POLYNOMIAL - O(N) TIME, O(1) SPACE ( call stack && explicit ) 

TEST CASES :
(A) [2,1,4,7,3,2,5]
    => 5
(B) [2,2,2]
    => 0 ( no mountain ) 
(C) [2,1,2,3,2,1,2,3,2,2,2,3,4,5,4,4,3,2,3,2,1,0,0]
    => 5 ( is correct :-O )
(D) [1,2,3,4,5]
    => 0
(E) [5,4,3,2,1]
    => 0
(F) [1,2,1,2,3,2,1] ( *** overlap too *** ) 
    => 5
(G) [0,1,2,3,4,5,4,3,2,1,0] <==== focus here
    => getting 10 : expected 11
    1 off err for sure
    

Subarr could start @ beginning too
See if duplicates can be cut off as well too!
Handle the edge cases ( literally ) 

'''


class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        n = len(arr)
        i = 0
        longestMount = 0
        curLen = 1  # This was bug
        encountIncrPart = False
        inDecrPart = False
        while(i < n):
            if i < n-1:
                if arr[i] <= arr[i+1] and inDecrPart == True:
                    inDecrPart = False
                    curLen = 1          # Perform a reset here
                if arr[i] < arr[i+1]:
                    curLen += 1
                    encountIncrPart = True
                elif arr[i] > arr[i+1]:
                    inDecrPart = True
                    curLen += 1
                elif arr[i] == arr[i+1]:
                    encountIncrPart = False
                    inDecrPart = False
                    curLen = 1
            if inDecrPart and encountIncrPart:
                longestMount = max(longestMount, curLen)
            i += 1
        return longestMount
