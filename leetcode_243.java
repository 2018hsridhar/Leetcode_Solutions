/*

243. Shortest Word Distance
Shortest Word Distance
URL = https://leetcode.com/problems/shortest-word-distance/
Two words of interest SIMPLIFIES to just two pointers : ptr1 and ptr2.

TEST CASES
(A) ["hari","sridhar","haran","temperance","hari","haran"] => 1
(B) ["practice", "makes", perfect", "coding", "makes"] => 1
(C) ["practice", "perfect", "coding", "makes"] => 3
(D)
(E)
(F)

8 mins total to solve here :-)

Complexity
Let N := total number of words in wordsDict
TIME = O(N)
SPACE = O(1) ( EXPLICIT AND IMPLICIT )

Note : words already exist -> return shortest dist
Current shortest distance = both lens filled up : n-1 then
[X,,,Y] -> dist(X,Y) = 3

Iterate left to right -> see where next change occurs too!
We can "inducitvely" argue here too.

*/

class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int ptr1 = -1;
        int ptr2 = -1;
        int n = wordsDict.length;
        int minDist = n - 1;
        for(int i = 0; i < n; ++i)
        {
            String word = wordsDict[i];
            if(word.equals(word1)){
                ptr1 = i;
            } else if ( word.equals(word2)) {
                ptr2 = i;
            }
            boolean bothPtrsSet = (ptr1 != -1 && ptr2 != -1);
            if(bothPtrsSet) {
                int curDist = Math.abs(ptr2 - ptr1);
                minDist = Math.min(curDist, minDist);
            }
            
        }
        return minDist;
    }
}
