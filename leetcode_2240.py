'''
2240. Number of Ways to Buy Pens and Pencils
URL = https://leetcode.com/problems/number-of-ways-to-buy-pens-and-pencils/

Complexity
C = cost1, D = cost2, T = total
Time = O(T/C)
Space = O(1) ( EXP & IMP ) 

Easy arithmetic problem.
The 1000th problem solved ( an easy one at it too )! WOOOH!

Cases
(A) 100, 1,1
(B) 100,20,40
(C) 1000000,20,20
(D)
(E)

'''
class Solution:
    def waysToBuyPensPencils(self, total: int, cost1: int, cost2: int) -> int:
        numberWaysToBuy = 0
        numberItem1Bought = 0
        # Do we need to break out of a loop expression?
        while(True):
            remainderToBuyItem2 = total - (numberItem1Bought * cost1)
            if(remainderToBuyItem2 < 0): # overbuying here
                break
            numberItem2Purchaseable = int(remainderToBuyItem2 / cost2)
            numberCurrentPossiblePurchases = (numberItem2Purchaseable+1)
            numberWaysToBuy += numberCurrentPossiblePurchases
            numberItem1Bought += 1
        return numberWaysToBuy
