/*
2418. Sort the People
URL = https://leetcode.com/problems/sort-the-people/
*/
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        String[] results = new String[n];
        Map<Integer,String> inverse = new HashMap<Integer,String>();
        for(int i = 0; i < n; ++i){
            inverse.put(heights[i],names[i]);
        }
        Arrays.sort(heights);
        // new Comparator<Integer>() {
           // public int compare(Integer i, Integer j){
               // return -1*i.compareTo(j);
           // } 
        // });
        for(int j = 0; j < n; ++j){
            results[j] = inverse.get(heights[n-1-j]);
        }
        return results;
    }
}
