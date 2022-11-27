# Cases:
# [2,3,2,1,1] k = 2 => PASS
# [2,3,6,1,1,1,1,1,2,3,4] k = 2 => PASS
# [2,3,1,2,3,4,1,1,2,3,4] k = 2 => PASS
# URL = https://leetcode.com/problems/time-needed-to-buy-tickets/description/
# 2073. Time Needed to Buy Tickets
# 
class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
       # rear gets a decr by (-1) of the kth element here
        timeNeeded = 0
        ticketsAtK = tickets[k]
        for i in range(0,len(tickets),1):
            if(i < k):
                timeNeeded += tickets[i] if tickets[i] <= ticketsAtK else ticketsAtK
            elif(i > k):
                timeNeeded += tickets[i] if tickets[i] < ticketsAtK else ( ticketsAtK - 1)
            else:
                timeNeeded += ticketsAtK
        return timeNeeded
