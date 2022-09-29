/*
1065. Index Pairs of a String
URL = https://leetcode.com/problems/index-pairs-of-a-string/
*/
class Solution {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> pairs = new ArrayList<int[]>();
        for(String word : words){
            int lPtr = 0;
            while(text.indexOf(word,lPtr) != -1){
                int lIndex = text.indexOf(word,lPtr);
                pairs.add(new int[]{lIndex,lIndex + word.length() - 1});
                lPtr = lIndex + 1;
            }
        }
        Collections.sort(pairs, new Comparator<int[]>(){
            public int compare(int[] pair_one, int[] pair_two){
                if(pair_one[0] < pair_two[0])
                    return -1;
                else if ( pair_one[0] > pair_two[0])
                    return 1;
                else {
                    if(pair_one[1] < pair_two[1])
                        return -1;
                    else if ( pair_one[1] > pair_two[1])
                        return 1;
                    return 0;
                }
            }
        });
        int[][] results = new int[pairs.size()][2];
        for(int i = 0; i < results.length; ++i){
            results[i][0] = pairs.get(i)[0];
            results[i][1] = pairs.get(i)[1];
        }
        return results;
    }
}
