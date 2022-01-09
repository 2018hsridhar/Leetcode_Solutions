/*

1027. Longest Arithmetic Subsequence
https://leetcode.com/problems/longest-arithmetic-subsequence/

Consecutive element differences must all be the same

COMPLEXITY
Let N := #-elements in the array ( up to a thousand max here ) 
Values within range [2,500]
TIME = O(N^2^C) ( POLY ) 
SPACE = O(1) to O(N^2)

Strategies : Recursion, DP
    N^2 potential solution here
    Need a hashmap be stored at each index though? 
    This is the naive idea currently
    Also differences can be going downwards too!
    Commence at the second element only too ( can have length one here, by default ) 
    Subsequence elements problem here : can make choices at each step too
    ( include or NOT include ) 
    
Default mine of two in all cases as well
Is a hashmap too difficult to update over each element as well?
( N^2 possible pairwise differences at maximum, for the start of each subsequence )
How to establish the overlap though?
Yes elems can be duplicates too

nums = [9,4,7,2,10]
idxs = [0,1,2,3,4]
See I have the index ( starter value ) and the difference
Parameterization : ( index, diff ) 
Now all of those pairings do have to be stored, BUT, they are known ahead of time!
E.g. LAS(1) = 
    MAX(1 + phi(2,3), 1 + phi(3,-2), 1 + phi(4,6))
    
PHI(4,1) = 1 + phi(7,3) = 2 + phi(10,3) = 3 <- this is the overlapping ( start the seqs earlier than later ) 
We just have to check those pairwise differences do indeed exist here.
Add a one for existing elem too? hmmm?

    

TEST CASES :
(A) [9,9,9,9,9]
    all zeroing out
    len = 5
    *** buggy : amend it *** 
(B) [20,15,10,5]
    len = 4
(C) [12,20,15,10,5,8,4,0,-4,-8,18,21]
    len = 6 ( competition engendered here with subsequences {12,15,18,21} and {20,15,10,5,0}
(D) [12,20,15,10,5,8,4,0,4,8,18,21]
    len = 5
(E)

*/

class Solution 
{
    public int longestArithSeqLength(int[] nums) 
    {
        // [1] Initialize dictionary array here
        int las = 2;     // default
        List<HashMap<Integer,Integer>> lenLongestWithDiffs = new ArrayList<HashMap<Integer,Integer>>();
        int n = nums.length;
        for(int i = 0; i < n; ++i)
        {
            lenLongestWithDiffs.add(new HashMap<Integer,Integer>());
        }
        
        // [2] Iterate over nums, sovle or diffs, and start your adds
        // LOoks like hashmap updating right -> is memory managed incorrectly here though?
        for(int i = n-1; i >= 0; --i)
        {
            // if(i == 0)
                // System.out.printf(" i = 0 : Second [%d] HM : %d\n", 1, lenLongestWithDiffs.get(1).get(0));
            HashMap<Integer,Integer> ithMap = lenLongestWithDiffs.get(i);
            int firstEl = nums[i];
            for(int j = i + 1; j < n; ++j)
            {
                int secondEl = nums[j];
                int diff = secondEl - firstEl;
                HashMap<Integer,Integer> jthMap = lenLongestWithDiffs.get(j);
                if(jthMap.containsKey(diff))
                {
                    // System.out.printf("(i,j) = (%d,%d) AND jth map key for diff = %d EQUALS %d\n", i, j, diff, jthMap.get(diff));
                    int curLen = 1 + jthMap.get(diff);
                    las = Math.max(las, curLen);
                    if(ithMap.containsKey(diff))
                    {
                        int maxSeqLen = Math.max(curLen, ithMap.get(diff));
                        ithMap.put(diff, maxSeqLen); // same logic if key exists or not -> oh but maximize OFC
                    }
                    else
                    {
                        ithMap.put(diff, curLen); // same logic if key exists or not -> oh but maximize OFC
                        // System.out.printf("Put in curLen = %d for diff = %d\n", curLen, diff);
                        // System.out.printf("Second [%d] HM : %d\n", i, lenLongestWithDiffs.get(1).get(0));
                    }
                }
                else
                {
                    if(!ithMap.containsKey(diff))   // TRICKY BUG INDEED
                    {
                        ithMap.put(diff,2); // default handling if non-existent OFC
                    }
                }
            }
        }
        
        return las;
    }
}
