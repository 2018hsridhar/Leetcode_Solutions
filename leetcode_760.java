/*
760. Find Anagram Mappings
URL = https://leetcode.com/problems/find-anagram-mappings/
*/
class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer,Integer> appearance = new HashMap<Integer,Integer>();
        int n = nums1.length;
        for(int i = 0; i < n; ++i) {
            int el = nums2[i];
            int idx = i;
            appearance.put(el,idx);
        }
        int[] res = new int[n];
        for(int ptr1 = 0; ptr1 < n; ++ptr1) {
            int elOne = nums1[ptr1];
            res[ptr1] = appearance.get(elOne);
        }
        return res;
    }
}
