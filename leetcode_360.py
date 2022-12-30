'''
URL = https://leetcode.com/problems/sort-transformed-array/description/
360. Sort Transformed Array

45 mins to solution 

Complexity
Let N := len(nums)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 

Edge Cases
(A) [1,2,3,4,5] 1 2 3
(B) [-5,-4,-3,-2,1] 1 2 3
(C) [1,2,3,4,5] -1 -2 -3
(D) [-5,-4,-3,-2,-1] -1 -2 -3
(E)
(F)

'''
def solvePolynomial(num: int, a: int, b: int, c: int) -> int:
    return (a * num * num ) + (b * num ) + c

# YOu seem ... close?
def zipperMerge(posNums: List[int], negNums: List[int]) -> List[int]:
    transformedArray = []
    pPtr = 0
    nPtr = 0
    while(pPtr < len(posNums) and nPtr < len(negNums)):
        if(posNums[pPtr] < negNums[nPtr]):
            transformedArray.append(posNums[pPtr])
            pPtr += 1
        else:
            transformedArray.append(negNums[nPtr])
            nPtr += 1
    while(pPtr < len(posNums)):
        transformedArray.append(posNums[pPtr])
        pPtr += 1
    while(nPtr < len(negNums)):
        transformedArray.append(negNums[nPtr])
        nPtr += 1
    return transformedArray

class Solution:

    def sortTransformedArray(self, nums: List[int], a: int, b: int, c: int) -> List[int]:
        n = len(nums)
        lPtr = 0
        rPtr = n-1
        wPtr = n-1
        # The negative flips us ---> iterate and create two lists, based on parity/signage of the input
        negListNums = []
        posListNums = []
        # It is based on the saddle point Hari!
        # saddlePoint = (-b + sqrt(b*b - 4 * a  * c) / (2 * a ))
        # Better idea  1'st derivative from calculus yields the answer :-)
        # What if a = 0 ?
        saddlePoint = (-b/(2 *a) ) if (a != 0) else 0 # Nest your operations here!!
        negNums = []
        for i in range(len(nums)):
            if(nums[i] < saddlePoint):
                negNums.append(nums[i])
                negListNums.append(solvePolynomial(nums[i], a,b,c)) # Technically o(1) here
            else:
                posListNums.append(solvePolynomial(nums[i], a,b,c))
        if(len(negListNums) > 0 and negListNums[0] > negListNums[len(negListNums) - 1]):
            negListNums.reverse()
        if(len(posListNums) > 0 and posListNums[0] > posListNums[len(posListNums) - 1]):
            posListNums.reverse()
        transformedArray = zipperMerge(negListNums,posListNums)
        return transformedArray
