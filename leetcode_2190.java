// Return the target with max count : NOT the target itself here!
/*
2190. Most Frequent Number Following Key In an Array
URL = https://leetcode.com/problems/most-frequent-number-following-key-in-an-array/

Time = O(N)
Space = O(N) ( Explicit ) O(1) ( Implicit ) 
*/
class Solution {
    public int mostFrequent(int[] nums, int key) {
        Map<Integer,Integer> targetCount = new HashMap<Integer,Integer>();
        int maxCount = 0;
        boolean maxTargetSet = false;
        int maxTarget = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; ++i){
            int target = nums[i];
            int preceding = nums[i-1];
            if(preceding == key) {
                if(targetCount.containsKey(target)) {
                    targetCount.put(target,targetCount.get(target) + 1);
                    if(targetCount.get(target) > maxCount) {
                        maxCount = targetCount.get(target);
                        maxTarget = target;
                    }
                } else {
                    targetCount.put(target,1);
                    maxCount = Math.max(maxCount,1);
                    if(!maxTargetSet) {
                        maxTarget = target;
                        maxTargetSet= true;
                    }
                }
            }
        }
        return maxTarget;
    }
}
