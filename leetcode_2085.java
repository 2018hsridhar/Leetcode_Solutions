/*
2085. Count Common Words With One Occurrence
https://leetcode.com/problems/count-common-words-with-one-occurrence/
*/
class Solution {
    public int countWords(String[] words1, String[] words2) {
        int countOfWordOnceEach = 0;
        Map<String,Integer> wordFreqMap = new HashMap<String,Integer>();
        // Iterate over set one
        for(String el : words1){
            if(!wordFreqMap.containsKey(el)){
                wordFreqMap.put(el,0);
            }
            wordFreqMap.put(el,wordFreqMap.get(el) + 1);
        }
        // Iterate over set two
        for(String el : words2) {
            if(wordFreqMap.containsKey(el) && wordFreqMap.get(el) <= 1){
                wordFreqMap.put(el,wordFreqMap.get(el) - 1);
            }
        }
        // Iteate over hashmap
        // Ternary expressions are for variable assignments only!
        // Variable = (condition) ? EXPR1 : EXPR2
        for(Map.Entry<String,Integer> entry : wordFreqMap.entrySet()){
            countOfWordOnceEach = (entry.getValue() == 0 ) ? countOfWordOnceEach + 1 : countOfWordOnceEach;
        }
        return countOfWordOnceEach;
    }
}
