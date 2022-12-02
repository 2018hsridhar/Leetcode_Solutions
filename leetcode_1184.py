# https://leetcode.com/problems/distance-between-bus-stops/
# 1184. Distance Between Bus Stops
# N = len(distnace)
# Time = O(N)
# Space = O(1) (EXP ) O(1) ( IMP ) 
class Solution:
    def distanceBetweenBusStops(self, distance: List[int], start: int, destination: int) -> int:
        # https://appdividend.com/2022/10/13/python-max-int/#:~:text=There%20is%20no%20concept%20of%20max%20int%20in,interpreter%E2%80%99s%20word%20size%20in%20Python%203%2C%20use%20sys.maxsize.
        minDistance = float('inf') # or sys.maxsize()
        n = len(distance)
        # dist to Right
        rPtr = start
        rightDist = 0
        while(rPtr != destination):
            rightDist += distance[rPtr]
            rPtr = (rPtr + 1 ) % n
        # dist to Left
        lPtr = start
        leftDist = 0
        # Check modulo error
        while(lPtr != destination):
            lPtr = (n + lPtr - 1 ) % n
            leftDist += distance[lPtr]
        minDistance = min(rightDist, leftDist)
        return minDistance
