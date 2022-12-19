'''
Try simulation
Math based problem
Fancy division involved?

1518. Water Bottles
URL = https://leetcode.com/problems/water-bottles/

'''
class Solution:
    def numWaterBottles(self, numBottles: int, numExchange: int) -> int:
        # It is a question of knowing how to manipulate state across iterations, and maintaining programmatic counters.
        numMaxBottlesConsumable = 0
        curNumberEmptyBottles = 0
        curNumberFullBottles = numBottles # Starting state
        while(curNumberFullBottles > 0): # while we can consume!
            bottlesCurrentlyConsumed = curNumberFullBottles # greedy : always drink
            numMaxBottlesConsumable += bottlesCurrentlyConsumed
            curNumberEmptyBottles += bottlesCurrentlyConsumed
            numNextFull = int(curNumberEmptyBottles / numExchange)
            numNextEmpty = int(curNumberEmptyBottles % numExchange) # if it could not be exchanged
            curNumberFullBottles = numNextFull
            curNumberEmptyBottles = numNextEmpty
        return numMaxBottlesConsumable
