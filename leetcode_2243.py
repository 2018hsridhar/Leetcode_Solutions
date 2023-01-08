'''
URL = https://leetcode.com/problems/calculate-digit-sum-of-a-string/
2243. Calculate Digit Sum of a String
Easier to process as a list of ints instead
'''
class Solution:
    def digitSum(self, s: str, k: int) -> str:
        digList = []
        for letter in s:
            digList.append(int(letter))
        # Strings immutable : leverage a temp copy space / buffer
        while(len(digList) > k):
            tempList = []
            digListPtr = 0
            kGroupSum = 0
            curGroupSize = 0
            while(digListPtr < len(digList)):
                kGroupSum += digList[digListPtr]
                curGroupSize += 1
                if(curGroupSize == k):
                    self.insertValIntoTempList(tempList,kGroupSum)
                    kGroupSum = 0
                    curGroupSize = 0
                digListPtr += 1
            # final run sum again ( based on kCount val too ) 
            if(curGroupSize > 0):
                self.insertValIntoTempList(tempList,kGroupSum)
            digList.clear()
            digList.extend(tempList) # faster than append()
        finalS = ''.join([str(digit) for digit in digList])
        return finalS

    def insertValIntoTempList(self, tempList:List[int],kGroupSum:int) -> None:
        if(kGroupSum == 0):
            tempList.append(0)
            return
        listToRev = []
        while(kGroupSum > 0):
            rem = kGroupSum % 10
            listToRev.append(rem)
            kGroupSum = int(kGroupSum / 10)
        if(kGroupSum > 0):
            listToRev.append(kGroupSum)
        listToRev.reverse()
        for x in listToRev:
            tempList.append(x)


