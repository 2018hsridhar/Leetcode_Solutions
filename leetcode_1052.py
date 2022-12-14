'''
URL = https://leetcode.com/problems/grumpy-bookstore-owner/
1052. Grumpy Bookstore Owner

Complexity
Let N := len(customers)
Time = O(N)
Space = O(1) ( EXP & IMP ) 

Algos : Sliding technique, 2 ptrs, Linear Scan, Optimization

Test Cases
(A)
(B)
(C)
(D)

Time taken = 22 mins.
Debugging : 8 mins
'''
class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], minutes: int) -> int:
        maxCustomersSatisfied = 0
        lPtr = 0
        rPtr = 0
        numSatisfiedToLeft = 0
        numSatisfiedToRight = 0
        for idx,el in enumerate(customers): # double access : index and value[index]
            if(grumpy[idx] == 0):
                numSatisfiedToRight += el
        nonGrumpyWindowSum = 0
        while(rPtr < len(customers)):
            # Apply exclusion of right first @ each step
            # right = everything right of rPtr index.
            numCustomersSatifiedInWindow = 0
            if(grumpy[rPtr] == 0):
                numSatisfiedToRight -= customers[rPtr]
            # if(grumpy[rPtr] == 0):
            nonGrumpyWindowSum += customers[rPtr]
            if(rPtr - lPtr == minutes):                 # First IF cond, then next IF cond.
                # Count left only from this leftPtr now ( starts @ 0 too ) 
                if(grumpy[lPtr] == 0):
                    numSatisfiedToLeft += customers[lPtr]
                nonGrumpyWindowSum -= customers[lPtr] # possible bug here?
                lPtr = lPtr + 1
            if(rPtr - lPtr == minutes - 1):
                numCustomersSatifiedInWindow += nonGrumpyWindowSum
                numCustomersSatifiedInWindow += numSatisfiedToLeft
                numCustomersSatifiedInWindow += numSatisfiedToRight
                maxCustomersSatisfied = max(maxCustomersSatisfied, numCustomersSatifiedInWindow)
            rPtr += 1
        return maxCustomersSatisfied
