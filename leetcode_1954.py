'''
URL = https://leetcode.c1954. Minimum Garden Perimeter to Collect Enough Apples
1954. Minimum Garden Perimeter to Collect Enough Apples

Approach : Binary Search, Mathematics, Heuristics. 
^ Approach is on the right track, at least

Complexity
Time = O(logN)
Space = O(1) ( EXP && IMP ) 

Leverage the expected perimeter as a `maxbound` for your math equation :-)
Hopefully, no data overflow ( int is long underneath in Py3 ) ?

Edge Cases
(A) 3456354
(B) 1
(C) 99
(D) 729
(E)

Algo is not fully correct
Not all edge cases correct here

1 hour spent on problem-solving.
# OMG 305/306 cases passing. You just have one failing case. Go resolve this eventually


'''

# Might be TLE for large inputs : take note of math here
def numberApplesInSquarePlotOfLengthXFirst(squareHalfLen: int) -> int:
    myNumberApples = 0
    yStep = squareHalfLen
    xStep = squareHalfLen
    countApples = squareHalfLen * 2
    counter = 1
    while(yStep >= 0):
        myNumberApples += (countApples * counter)
        yStep -= 1
        counter += 1
        countApples -= 1
    xStep -= 1 # avoid a double count here!
    while(xStep > 0):
        countApples -= 1 # start decr part earlire
        counter -= 1
        myNumberApples += (countApples * counter)
        xStep -= 1
    return myNumberApples

# See the fast math way of doing it :-)
def numberApplesInSquarePlotOfLengthX(squareHalfLen: int) -> int:
    myNumberApples = 0
    yStep = squareHalfLen
    xStep = squareHalfLen
    myNumberApples += squareHalfLen * (squareHalfLen + 1)
    nnSum = squareHalfLen*(squareHalfLen + 1) / 2
    myNumberApples += (squareHalfLen * 2) * ( nnSum )
    return int(myNumberApples)

class Solution:
    def minimumPerimeter(self, neededApples: int) -> int:
        myMinPerimeter = 0
        minDistanceToNeededApples = sys.maxsize
        low = 0
        high = neededApples * 4
        # Also take note to skip squareLengths which are not of even parity!
        # Or change your equation handling, to not worry about this too!
        while(low <= high):
            mid = int((high + low) / 2)
            squareHalfLen = int(mid / 2)
            # You are double counting!!!! Be careful again!
            # Subtract out the 4 corners 4 times ( 0,halfStep)
            myNumApplesInPlot = 4 * numberApplesInSquarePlotOfLengthX(squareHalfLen)
            myNumApplesInPlot -= 4 * int((squareHalfLen*(squareHalfLen+1))/2)
            if(myNumApplesInPlot == neededApples):
                return squareHalfLen * 4 * 2
            elif(myNumApplesInPlot > neededApples): # Heuristic step
                if(myNumApplesInPlot - neededApples < minDistanceToNeededApples):
                    minDistanceToNeededApples = myNumApplesInPlot
                    myMinPerimeter = 4 * squareHalfLen * 2
                high = mid - 1
            else:
                low = mid + 1
        return myMinPerimeter
