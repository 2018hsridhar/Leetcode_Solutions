/*
THOUGHT PROCESS : 
1. Utilize copy constructor for deep-copying of initial array ( score ) 
2. Utilize built-in librry functions to sort deep-copy
- assume sort outputs monotonically increasing sequence of original elements ( non-decreasing order ) 
Array values guaranteed uniqueness : no need to worry about duplicate scores
Scores are reasonably bounded from [0,1,000,000] : no data overflow with "signed int "
n is reasonably bounded from [1,10,000] - no worry about O(Nlogn) performance
Computational complexity : 
Time = O(NlogN) + O(N) + O(N) = O(NlogN) [ Initial library sort function + 2 for loops about the scores array ]
Space = O(N) + O(N) = O(N) [ copied array + hashmap construction] 
Edge cases : 
(1) Singletons - {1},{10}
(2) 2-len arrays  {1,2},{10,9}
(3) 3-len arrays - {1,2,3},{3,2,1},{4,6,5} - 3 different sequence orders too!
(4) Large len arrays - {1,2,3,4,8,7,6,9,10}
No repeated values cases to test here - will not break
*/

class Solution 
{
    public String[] findRelativeRanks(int[] score) 
    {
        String[] result = new String[score.length];
        int[] scoreCopy = Arrays.copyOf(score, score.length);
        Arrays.sort(scoreCopy);
        HashMap<Integer,Integer> scoreIndex = new HashMap<Integer,Integer>();
        for(int i = 0; i < scoreCopy.length; ++i)
            scoreIndex.put(scoreCopy[i], i);
        
        // Sorting helps with order  e.g. [3,4,8,9,10] - (len-idx = ranking too ) - [0,1,2,3,4]
        // score = [10,3,8,9,4]
        String[] medals = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        for(int i = 0; i < score.length; ++i)
        {
            int curScore = score[i];
            int rank = (score.length - scoreIndex.get(curScore));
            if(rank > 3)
                result[i] = String.valueOf(rank); // preferrable to (rank + "" ) : more proper and uses library support!
            else
                result[i] = medals[rank - 1]; // faster than a switch-case when dealing with int indices! 
        }

        
        return result;
    }
}
