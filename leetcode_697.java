/*
Can we try to refactor as we go here?
URL = https://leetcode.com/problems/degree-of-an-array/
697. Degree of an Array

Is there a way to avoid having to recompute a degree each time too?
It should exist since ONLY one element has a max degree as well.
You care only on whether current Deg = maxDegree too

Remember the right ptr will incr again -> so curDeg will update
But what if multiple elements with max degree?
Well one of those is the shrotest, and clearly ONLY the current element trigged it too!

TIME = O(N)
SPACE = O(N) [ EXPLICIT ] O(1) [ IMPLICIT ]
*/
class Solution {
    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        int maxDeg = getArrayDegree(nums);
        int lPtr = 0;
        int rPtr = 0;
        int minLen = n; // assume original array length
        int curDeg = 0;
        Map<Integer,Integer> freqMap = new HashMap<Integer,Integer>();
        while(rPtr < n) {
            int el = nums[rPtr];
            if(!freqMap.containsKey(el)) {
                freqMap.put(el,1);
            } else {
                freqMap.put(el,freqMap.get(el) + 1);
            }
            curDeg = Math.max(curDeg,freqMap.get(el));
            while(curDeg == maxDeg) { // incompatible type and conversions error in EXPRS
                int curLen = (rPtr - lPtr + 1);
                minLen = Math.min(minLen,curLen);
                int leftEl = nums[lPtr];
                freqMap.put(leftEl,freqMap.get(leftEl) - 1);
                if(leftEl == el) {
                    curDeg--;
                }
                lPtr++;
            }
            ++rPtr; // always ensuring this "races-ahead" too to fill computatoin state correctly!
        }
        return minLen;
    }
    
    private int getArrayDegree(int[] nums)
    {
        int degree = 0;
        Map<Integer,Integer> freqMap = new HashMap<Integer,Integer>();
        for(int el : nums) {
            if(!freqMap.containsKey(el)){
                freqMap.put(el,1);
            } else {
                freqMap.put(el,freqMap.get(el) + 1);
            }
            degree = Math.max(degree, freqMap.get(el));
        }
        return degree;
    }
}
