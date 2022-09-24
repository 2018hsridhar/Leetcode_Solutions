/*
URL = https://leetcode.com/problems/find-the-difference-of-two-arrays/
2215. Find the Difference of Two Arrays

*/
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
        for(int x : nums1) {
            if(!mp.containsKey(x)){
                mp.put(x,1);
            }
        }
        for(int x : nums2) {
            if(!mp.containsKey(x)){
                mp.put(x,2);
            } else {
                if(mp.get(x) == 1) {
                    mp.put(x,mp.get(x) + 2);
                }
            }
        }
        List<Integer> listOne = new ArrayList<Integer>();
        List<Integer> listTwo = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> e : mp.entrySet()){
            if(e.getValue() == 1){
                listOne.add(e.getKey());
            }
            else if(e.getValue() == 2){
                listTwo.add(e.getKey());
            }
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        results.add(listOne);
        results.add(listTwo);
        return results;
    }
}
