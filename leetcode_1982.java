/*

1982. Find Array Given Subset Sums
URL = https://leetcode.com/problems/find-array-given-subset-sums/

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
(F)
(G)
(H)
(I)

They need to figure out if the constraints of thsi problem make a recursive approachable feasible 
W.R.T. memory and time as well
The addition of the sums should NOT cause SIGNED INTEGER data overflow though
Luckily, the hashmap comparison is limited as well ( 2^15 max subset sums here ) 
-> potential issue : hashmap itself may be emptied out, but we may have remainder elements as well


PSEUDOCODE : 


    // [0] PRECOMPUTATION STEPS
    
    // [1] The backtracking
    backtrack(int n, int[] sums)
    
    
    // Why return a boolean? Perhaps an object could be more sensical too? 
    // We do create those child lists . Just convert ArrayList or HM to Array Later on
    // Null object comparison test ( objects are pointers : NOT data ) 
    public ArrayList<Integer> backtrack(int[] sums, int n, int idx, List<Integer> ArrayList)
    {
        boolean foundValidSubset = false;
        int j = i + 1;
        while(j < n)
        {
            boolean childRes = backtrack(sums, n, j, childList);
            if(childRes != null)
            {
                foundValidSubset = true;
                return childList; 
            }
        }
        return null;
    }
    // [2] The results array
    
    
    

*/

class Solution 
{
    public int[] recoverArray(int n, int[] sums)
    {
        int[] recover = new int[1];
        return recover;
    }
}



