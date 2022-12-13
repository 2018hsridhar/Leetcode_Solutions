'''
2483. Minimum Penalty for a Shop
URL = https://leetcode.com/problems/minimum-penalty-for-a-shop/

Complexity
Let N := len(customers)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Hours : 0 -> n here
0-indexed string here

Test Cases
(A) "YYYYY"
(B) "NNNNN"
(C) "YYYNNN"
(D) "NNNYYY"
(E) "YNYNYN"
(F) "NYNYNYNNYYYNN"
(G) "Y"
(H) "N"

12 mins
Tags :: Greedy, optimization problem, linear scan, single ptr
'''
class Solution:
    def bestClosingTime(self, customers: str) -> int:
        n = len(customers)
        numOpenToRight = 0
        for letter in customers:
            if(letter == 'Y'):
                numOpenToRight += 1
        numCloseToLeft = 0
        earliestHourWithMinPenalty = -1
        myMaxPenalty = float("inf")
        # You close the shop at this specific hour
        for hour in range(0, len(customers) + 1, 1):
            myCurrentHourPenalty = 0
            if(hour - 1 >= 0):
                if(customers[hour - 1] == 'Y'):
                    numOpenToRight -= 1
                elif(customers[hour - 1] == 'N'):
                    numCloseToLeft += 1
            myCurrentHourPenalty = numOpenToRight + numCloseToLeft
            if(myCurrentHourPenalty < myMaxPenalty):
                myMaxPenalty = myCurrentHourPenalty
                earliestHourWithMinPenalty = hour
        return earliestHourWithMinPenalty
