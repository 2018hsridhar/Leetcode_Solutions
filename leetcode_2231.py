'''
URL = https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/
2231. Largest Number After Digit Swaps by Parity
'''
class Solution:
    # num > 0 guarnateed :-)
    def largestInteger(self, num: int) -> int:
        evenList = []
        oddList = []
        initNum = list(str(num))
        while(num > 0):
            rem = num % 10
            if(num % 2 == 1):
                oddList.append(rem)
            else:
                evenList.append(rem)
            num = int(num / 10) # careful here!
        oddList.sort()
        oddList.reverse()
        evenList.sort() # can we concat func calls?
        evenList.reverse()
        ptr = 0
        ePtr = 0
        oPtr = 0
        while(ptr < len(initNum)):
            if(int(initNum[ptr]) % 2 == 0):
                initNum[ptr] = evenList[ePtr]
                ePtr += 1
            else:    
                initNum[ptr] = oddList[oPtr]
                oPtr += 1
            ptr += 1
        ptr = len(initNum) - 1
        res = 0
        power = 0
        while(ptr >= 0):
            delta = (pow(10,power) * int(initNum[ptr]))
            res += delta
            ptr -= 1
            power += 1
        return res
