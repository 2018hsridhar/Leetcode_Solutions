'''
1122. Relative Sort Array
URL = https://leetcode.com/problems/relative-sort-array/
'''
class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        results = []
        # https://stackoverflow.com/questions/604802/python-finding-an-element-in-a-list
        # Has to be in the order found in arr2
        freqElTwo = dict()
        for el in arr1:
            stat = el in arr2
            if(el in arr2):
                if(el not in freqElTwo):
                    freqElTwo[el] = 0
                freqElTwo[el] += 1
        for el in arr2:
            while(freqElTwo[el] > 0):
                results.append(el)
                freqElTwo[el] -= 1
        # This part in asc order
        ascEndList = []
        for el in arr1:
            if(el not in arr2):
                ascEndList.append(el)
        ascEndList.sort()
        results = results + ascEndList # Direct list concatenation
        return results
