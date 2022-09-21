/*
URL = https://leetcode.com/problems/count-the-number-of-consistent-strings/
1684. Count the Number of Consistent Strings

*/
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> whitelist = new HashSet<Character>();
        for(char el : allowed.toCharArray()) {
            if(!whitelist.contains(el)) {
                whitelist.add(el);
            }
        }
        
        int count = 0;
        int n = words.length;
        for(int i = 0; i < n; ++i) {
            String word = words[i];
            boolean meetsCrit = true;
            for(int j = 0; j < word.length(); ++j) {
                if(!whitelist.contains(word.charAt(j))){
                    meetsCrit = false;
                    break;
                }
            }
            if(meetsCrit) {
                count++;
            }
        }
        return count;
    }
}
