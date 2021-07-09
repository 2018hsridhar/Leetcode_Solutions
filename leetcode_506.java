/*
506. Relative Ranks

THOUGHT PROCESS : 
1. Utilize copy constructor for deep-copying of initial array ( score ) 
2. Utilize built-in librry functions to sort deep-copy
- assume sort outputs monotonically increasing sequence of original elements ( non-decreasing order ) 

Array values guaranteed uniqueness : no need to worry about duplicate scores
Scores are reasonably bounded from [0,1,000,000] : no data overflow with "signed int "
n is reasonably bounded from [1,10,000] - no worry about O(Nlogn) performance

Computational complexity : 
Time = O(NlogN) + O(N) = O(NlogN)
Space = O(N) + O(N) = O(N) [ copied array + hashmap ] 

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
        // base cases : n = 1, n = 2 ( n >= 1 guaranteed ) 
        String[] result = new String[score.length];
        if(score.length == 1)
            result[0] = "Gold Medal";
        else if ( score.length == 2)
        {
            if(score[0] > score[1])
            {
                result[0] = "Gold Medal";
                result[1] = "Silver Medal";
            }
            else
            {
                result[0] = "Silver Medal";
                result[1] = "Gold Medal";
            }
        }
        
        // Non-base case
        int[] scoreCopy = Arrays.copyOf(score, score.length);
        Arrays.sort(scoreCopy);
        HashMap<Integer,Integer> scoreIndex = new HashMap<Integer,Integer>();
        for(int i = 0; i < scoreCopy.length; ++i)
            scoreIndex.put(scoreCopy[i], i);
        
        // Sorting helps with order  e.g. [3,4,8,9,10] - (len-idx = ranking too ) - [0,1,2,3,4]
        // score = [10,3,8,9,4]
        for(int i = 0; i < score.length; ++i)
        {
            int curScore = score[i];
            int rank = (score.length - scoreIndex.get(curScore));
            if(rank > 3)
            {
                result[i] = rank + "";   
            }
            else if ( rank == 3)
            {
                result[i] = "Bronze Medal";                
            }
            else if ( rank == 2)
            {
                result[i] = "Silver Medal";
            }
            else if ( rank == 1)
            {
                result[i] = "Gold Medal";
            }
                
        }
        
        
        return result;
    }
}
