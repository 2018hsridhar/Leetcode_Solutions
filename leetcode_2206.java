/*
2206. Divide Array Into Equal Pairs
URL = https://leetcode.com/problems/divide-array-into-equal-pairs/

*/
class Solution {
    public boolean divideArray(int[] nums) {
        Map<Integer,Integer> freqMap = new HashMap<Integer,Integer>();
        for(int el : nums) {
            if(!freqMap.containsKey(el)){
                freqMap.put(el,1);
            } else {
                freqMap.put(el,freqMap.get(el) + 1);
            }
        }
        
        boolean status = true;
        for(Map.Entry<Integer,Integer> entry : freqMap.entrySet()) {
            if(entry.getValue() % 2 == 1) {
                status = false;
                break;
            }
        }
        
        return status;
    }
}
