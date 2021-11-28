/*

1982. Find Array Given Subset Sums
URL = https://leetcode.com/problems/find-array-given-subset-sums/

CLARIFICATION
1. Can not make any set with n = 0 : a terminating case
2. No null inputs or empty inputs as well
3.


COMPLEXITY

TEST CASES
(A) n = 4, sums = [-3,-2,-1,0,0,1,2,3]
    [1,2,-1,-2]
(B) n = 5, sums = [-3,-2,-1,0,0,1,2,3]
    [1,2,-1,-1, -1]
(C) n = 3, sums = [-6,-5,-4,-3,-2,-1]
    [-3,-2,-1]
(D) n = 3, sums = [-6,-5,-4]
    [-6, -5, -4]
(E) n = 3, sums = [1,2,3,4,5,6]
    [1,2,3]
( *** EASIEST TEST CASE *** ) 
(F) n = 3, sums = [1,2,3,4,5,6]
    [1,2,3]
(G)
(H)
(I)

They need to figure out if the constraints of thsi problem make a recursive approachable feasible 
W.R.T. memory and time as well
The addition of the sums should NOT cause SIGNED INTEGER data overflow though
Luckily, the hashmap comparison is limited as well ( 2^15 max subset sums here ) 
-> potential issue : hashmap itself may be emptied out, but we may have remainder elements as well
-> Indeed true that subsets is the similar question here :-)
-> note : must return final subset : we still need the subset sums -> 2 objects in the backtracking?

PSEUDOCODE : 


    // [0] PRECOMPUTATION STEPS
    
    // [1] The backtracking
    backtrack(int n, int[] sums)
    
    
    // Why return a boolean? Perhaps an object could be more sensical too? 
    // We do create those child lists . Just convert ArrayList or HM to Array Later on
    // Null object comparison test ( objects are pointers : NOT data ) 
    // Break point : either meet <n> or meet end of iteration with <j> here
    private ArrayList<Integer> backtrack(int[] sums, int n, int idx, List<Integer> parentSubset, List<Integer> parentSubsetSums)
    {
        if(n == 0)
        {
            return null;
        }
        
        boolean foundValidSubset = false;
        int j = i + 1;
        while(j < n)
        {
            ArrayList<Integer> childList = backtrack(sums, n - 1, j, __, __);
            if(childRes != null)
            {
                foundValidSubset = true;
                return childList; 
            }
        }
        return null;
    }
    
    // [2] The results array
    // OBSERVE that the seperation of functinos further assists in call stack debugging too.
    public int[] recoverArray(int n, int[] sums)
    {
        if(n == 0 || sums.length == 0)
            return new int[]{};
        List<Integer> parentSubset = new ArrayList<Integer>();
        List<Integer> parentSubsetSums = new ArrayList<Integer>();
        List<Integer> validSet = backtrack(sums, n, 0, parentSubset, parentSubsetSums);
        if(validSet == null)
            return new int[]{};
        int sz = validSet.size();
        int[] recover = new int[sz];
        for(int i = 0; i < validSet.size(); ++i)
        {
            recover[i] = validSet.get(i);
        }
        return recover;
    }    
    

*/

class Solution 
{
    public int[] recoverArray(int n, int[] sums)
    {
        int[] recover = new int[1];
        return recover;
    }
}



