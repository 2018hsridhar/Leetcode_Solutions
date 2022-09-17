/*
Guaranteed that each word is unique
1408. String Matching in an Array
URL = https://leetcode.com/problems/string-matching-in-an-array/

*/
class Solution {
    public List<String> stringMatching(String[] words) {
        Set<String> matched = new HashSet<String>();
        List<String> matching = new ArrayList<String>();
        for(int i = 0; i < words.length; ++i)
        {
            String firstEl = words[i];
            for(int j = i+1; j < words.length; ++j)
            {
                String secondEl = words[j];
                // Seems like you have to check both ways : not just one way only
                // Seems like a set is more useful in this context -> convert to list later.
                if(firstEl.contains(secondEl) && !matched.contains(firstEl)) {
                    matched.add(secondEl);
                }
                if(secondEl.contains(firstEl) && !matched.contains(secondEl)) {
                    matched.add(firstEl);
                }
            }
        }
        for(String x : matched)
            matching.add(x);
        return matching;
    }
}
