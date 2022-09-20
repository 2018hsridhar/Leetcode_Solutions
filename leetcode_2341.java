/*
2341. Maximum Number of Pairs in Array
URL = https://leetcode.com/problems/maximum-number-of-pairs-in-array/

*/
class Solution {
    public int[] numberOfPairs(int[] nums) {
        Map<Integer,Integer> freqMap = new HashMap<Integer,Integer>();
        for(int el : nums) {
            if(freqMap.containsKey(el)) {
                freqMap.put(el,freqMap.get(el) + 1);
            } else {
                freqMap.put(el,1);
            }
        }
        
        int numRem = 0;
        int numPairs = 0;
        for(Map.Entry<Integer,Integer> entry : freqMap.entrySet()) {
            int occurence = entry.getValue();
            if(occurence % 2 == 0) { 
                numPairs += ( occurence / 2 );
            } else {
                numPairs += ( occurence / 2 );
                numRem += 1;
            }
        }
        return new int[]{numPairs, numRem};
    }
}
