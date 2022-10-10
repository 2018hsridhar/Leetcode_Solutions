/*
1940. Longest Common Subsequence Between Sorted Arrays
URL = https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/

Given at least 2 arrays. 

*/
class Solution {
    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        List<Integer> results = new LinkedList<Integer>();
        int numArrays = arrays.length;
        // [1] Set up our hashmap
        Map<Integer,Integer> elFreq = new HashMap<Integer,Integer>();
        for(int i = 0; i < numArrays; ++i){
            int[] array = arrays[i];
            for(int j = 0; j < array.length; ++j) {
                int el = arrays[i][j];
                if(!elFreq.containsKey(el)){
                    elFreq.put(el,0);
                } 
                elFreq.put(el,elFreq.get(el) + 1);
            }
        }
        // [2] Check the hashmap
        int[] arr_one = arrays[0];
        for(int i= 0; i < arr_one.length; ++i) {
            if(elFreq.containsKey(arr_one[i]) && elFreq.get(arr_one[i]) == numArrays){
                results.add(arr_one[i]);
            }
        }
        return results;
    }
}
