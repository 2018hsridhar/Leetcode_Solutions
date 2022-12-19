'''
URL = https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
1481. Least Number of Unique Integers after K Removals

Complexity
Let N := len(list)
Time = O(NlgN)
Space = O(N) ( EXP ) O(1) ( IMP ) 

15:30 minutes
Something pythoning happening with <k> if used in some type of iteration earlier?
'''
class Solution:
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        myLeastNumUniqueInts = 0
        freqCountOfEls = {}
        for el in arr:
            if(el not in freqCountOfEls):
                freqCountOfEls[el] = 0
            freqCountOfEls[el] += 1
        records = []
        # DId k change here? If so ... woahh!
        for el,v in freqCountOfEls.items():
            records.append([el,v])
        # https://stackoverflow.com/questions/3766633/how-to-sort-with-lambda-in-python
        records.sort(key=lambda x: x[1])
        # print(records)
        ptr = 0
        while(ptr < len(records)):
            if(k == 0):
                break
            if(records[ptr][1] > 0):
                records[ptr][1] -= 1
                k -= 1
            if(records[ptr][1] == 0):
                ptr += 1
        myLeastNumUniqueInts = len(records) - ptr
        return myLeastNumUniqueInts
