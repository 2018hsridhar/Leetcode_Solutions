'''
URL = https://leetcode.com/problems/sort-even-and-odd-indices-independently/description/
2164. Sort Even and Odd Indices Independently

'''
class Solution:
    def sortEvenOdd(self, nums: List[int]) -> List[int]:
        valuesInOddIndicesList = []
        valuesInEvenIndicesList = []
        for i in range(len(nums)):
            if(i % 2 == 0):
                valuesInEvenIndicesList.append(nums[i])
            else:
                valuesInOddIndicesList.append(nums[i])
        valuesInEvenIndicesList.sort()
        # https://www.freecodecamp.org/news/python-sort-list-how-to-order-by-descending-or-ascending/
        valuesInOddIndicesList.sort(reverse=True)
        evenOddSortedList = []
        for i in range(len(nums)):
            nextEl = 0
            if(i % 2 == 0):
                nextEl = valuesInEvenIndicesList[int(i/2)]
            elif(i % 2 == 1):
                nextEl = valuesInOddIndicesList[int((i-1)/2)]
            evenOddSortedList.append(nextEl)

        return evenOddSortedList
