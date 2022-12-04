# https://stackoverflow.com/questions/16710112/python-iterate-over-dictionary-sorted-by-key
# 1356. Sort Integers by The Number of 1 Bits
# URL = https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/description/

class Solution:
    def sortByBits(self, arr: List[int]) -> List[int]:
        results = []
        arr.sort() # Leverage arr sort : no need for sorted dict
        mp = dict()
        for el in arr:
            numOne = el.bit_count()
            if numOne not in mp:
                mp[numOne] = []
                mp[numOne].append(el)
            else:
                mp[numOne].append(el)
        for k in sorted(mp):
            results = results + mp[k]
        return results

        
