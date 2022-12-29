'''
URL = https://leetcode.com/problems/minimum-health-to-beat-game/
2214. Minimum Health to Beat Game

Complexity
Let N := len(damage)
Time = O(N)
Space = O(1) ( EXP && IMP )

Seems like a greedy algo
Can we find the level where the armor has to be worn?
Every other (n-1) level has to be played!

Test Cases
(A) [1,2,3,4,5], 1  => 2+3+4+5
(B) [1,2,3,4,5], 6  => 1+2+3+4
(C) [1,2,3,6,7,8], 5 => 1+2+6+7+8
(D) [9,10,11], 5 => 26

Avoid sorting ( O(nlgn) ) -> or ugh, binary search the answer too?
Time = 15 minutes?

'''
# Not an instance method.
def searchClosestDamage(damage: List[int], armor: int) -> int:
    low = 0
    high = len(damage) - 1
    closestDamage = -1 # Dmaage always >= 0 ~sys.maxsize # get minimum ( via binary complement ) 
    while(low <= high):
        mid = int(low + (high-low)/2)
        if(damage[mid] == armor):
            closestDamage = armor
            break
        elif(damage[mid] > armor):
            high = mid - 1
        else:
            if(damage[mid] > closestDamage):
                closestDamage = damage[mid]
            low= mid + 1
    return closestDamage

class Solution:

    # Easier than binary search -> you subtract armor from the max, or just go 0 out
    def minimumHealth(self, damage: List[int], armor: int) -> int:
        maxEl = max(damage)
        minHealthNeeded = sum(damage) + 1 - maxEl
        minHealthNeeded += (maxEl - armor) if(maxEl > armor) else 0
        return minHealthNeeded




