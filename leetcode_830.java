/*
Your 2 pointer manipulations here at the key gotchas
830. Positions of Large Groups
URL = https://leetcode.com/problems/positions-of-large-groups/

*/
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        char[] c_arr = s.toCharArray();
        int lPtr = 0;
        int rPtr = 1;
        int n = c_arr.length;
        int curGroupSize = 1 ; // single letter case
        while(rPtr < n) {
            if(c_arr[lPtr] == c_arr[rPtr]) {
                curGroupSize++;
                rPtr++;
            } else {
                curGroupSize = ( rPtr - lPtr );
                if(curGroupSize >= 3) {
                    List<Integer> newRange = new ArrayList<Integer>();
                    newRange.add(lPtr);
                    newRange.add(rPtr - 1);
                    results.add(newRange);
                }
                lPtr = rPtr;
                curGroupSize = 1;
                rPtr = lPtr + 1;
            }
        }
        if(curGroupSize >= 3) {
            List<Integer> newRange = new ArrayList<Integer>();
            newRange.add(lPtr);
            newRange.add(rPtr - 1);
            results.add(newRange);
        }
        return results;
    }
}
