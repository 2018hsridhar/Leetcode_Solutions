'''
1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
URL = https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
5 mins
'''
class Solution:
    def numOfSubarrays(self, arr: List[int], k: int, threshold: int) -> int:
        numberSubArraysMeetingThreshold = 0
        # no runtime integer overflow ( 10&9 @ max ) 
        # do proper casting though ( arithmetic exceptions ) 
        lPtr = 0
        rPtr = 0
        contiguousSubArraySum = 0.0
        while(rPtr < len(arr)):
            contiguousSubArraySum += arr[rPtr]
            if(rPtr - lPtr == k):
                contiguousSubArraySum -= arr[lPtr]
                lPtr += 1
            if(rPtr - lPtr == k-1):
                myAverage = contiguousSubArraySum / k # yo python lacks static typing :-)
                if(myAverage >= threshold):
                    numberSubArraysMeetingThreshold += 1
            rPtr += 1
        return numberSubArraysMeetingThreshold
