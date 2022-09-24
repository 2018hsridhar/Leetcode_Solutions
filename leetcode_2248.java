/*
URL = https://leetcode.com/problems/intersection-of-multiple-arrays/
2248. Intersection of Multiple Arrays

*/
class Solution {
    public List<Integer> intersection(int[][] nums) {
        Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
        int numArrs = nums.length;
        for(int i = 0; i < numArrs; ++i){
            int[] arr = nums[i];
            for(int j = 0; j < arr.length; ++j){
                int val = arr[j];
                if(!mp.containsKey(val)) {
                    mp.put(val,1);
                } else {
                    if(i > mp.get(val) - 1) { // index offset into array :-)
                        mp.put(val,mp.get(val) + 1);
                    }
                }
            }
        }
        
        // Iterate over hashmap now
        List<Integer> res = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> entry : mp.entrySet()) {
            if(entry.getValue() == numArrs) {
                res.add(entry.getKey());
            }
        }
        Collections.sort(res);
        return res;
    }
}
