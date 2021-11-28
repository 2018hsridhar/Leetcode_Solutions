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
(G) n = 10 [1,2,3,4,5,6,7,8,9,10,11,12,...,55] ( natural numbers sum here gets yee far ) 
    [1,2,3,4,5,6,7,8,9,10]
(H) ( *** failing *** ) 
3
[0,1,2,3,4,5,6,7]

(I) 5
[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
-> works, but is kinda slow AF atm. Needs expediting as well too!

Leverage binary here as well, for pow set generation
LPT : do not confuse <n> for subset size here as well too!

They need to figure out if the constraints of thsi problem make a recursive approachable feasible 
W.R.T. memory and time as well
The addition of the sums should NOT cause SIGNED INTEGER data overflow though
Luckily, the hashmap comparison is limited as well ( 2^15 max subset sums here ) 
-> potential issue : hashmap itself may be emptied out, but we may have remainder elements as well
-> Indeed true that subsets is the similar question here :-)
-> note : must return final subset : we still need the subset sums -> 2 objects in the backtracking?
-> wait a second ... is 0 a default in all cases anyways?

PSEUDOCODE : 


    // [0] PRECOMPUTATION STEPS
    
    // [1] The backtracking
    backtrack(int n, int[] sums)
    
    


*/

class Solution 
{
        
    // [2] The results array
    // OBSERVE that the seperation of functinos further assists in call stack debugging too.
    public int[] recoverArray(int n, int[] sums)
    {
        Arrays.sort(sums); // pre processing step here
        if(n == 0 || sums.length == 0)
            return new int[]{};
        // Initialize these sets as empty anyways!
        List<Integer> parentSubset = new ArrayList<Integer>();
        List<Integer> parentSubsetSums = new ArrayList<Integer>();
        parentSubsetSums.add(0); /// defacto null set sum = 0 for []
        HashMap<Integer,Integer> sumsFreqs = new HashMap<Integer,Integer>();
        for(int i = 0; i < sums.length; ++i)
        {
            if(!sumsFreqs.containsKey(sums[i]))
                sumsFreqs.put(sums[i], 1);
            else
                sumsFreqs.put(sums[i], sumsFreqs.get(sums[i]) + 1);
        }

        // Failure to start valid sets from earlier as well too!
        List<Integer> validSet = null;
        validSet = backtrack(sums, n, 0, parentSubset, parentSubsetSums, sumsFreqs);
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
    
    // Why return a boolean? Perhaps an object could be more sensical too? 
    // We do create those child lists . Just convert ArrayList or HM to Array Later on
    // Null object comparison test ( objects are pointers : NOT data ) 
    // Break point : either meet <n> or meet end of iteration with <j> here
    // Add performed only in the backtrack : 
    private List<Integer> backtrack(int[] sums, int n, int idx, List<Integer> parentSubset, List<Integer> parentSubsetSums, HashMap<Integer,Integer> sumsFreqs)
    {
        // System.out.printf("Invoking the backtracking on n = %d for idx = [%d]\n", n, idx);
        if(n == 0)
        {
            // System.out.printf("hit n < 0 case\n");
            if(listIsPowSetBase(parentSubsetSums, sumsFreqs))
            {
                return parentSubset;                
            }
            else
            {
                return null;
            }
        }
        if(listIsPowSetBase(parentSubsetSums, sumsFreqs))
        {
            // System.out.printf("Hit terminating condition\n");
            return parentSubset;
        }
        boolean foundValidSubset = false;
        int j = idx;
        while(j < sums.length)
        {
            int curEl = sums[j];
            // System.out.printf("j = %d\n", j);
            
            // note how we still utilize the "new" keyword for heap-alloc objs, but leverage copy constructor syntax as well
            List<Integer> childSubset = new ArrayList<Integer>(parentSubset);
            childSubset.add(curEl);
           // System.out.printf("Here 2\n");

            List<Integer> childSubsetSums = new ArrayList<Integer>(parentSubsetSums); 
            int sz = childSubsetSums.size(); // and this is why we do not ask size () : esp if adding dynamically to end
            for(int i = 0; i < sz; ++i)
            {
                 childSubsetSums.add(curEl + childSubsetSums.get(i));
            }
            List<Integer> childList = backtrack(sums, n - 1, j + 1, childSubset, childSubsetSums, sumsFreqs);
            if(childList != null)
            {
                foundValidSubset = true;
                return childList; 
            }
            ++j;
        }
        return null;
    }
    
    private boolean listIsPowSetBase(List<Integer> subsetSums, HashMap<Integer,Integer> powSet)
    {
        HashMap<Integer,Integer> copy = new HashMap<Integer,Integer>(powSet);
        for(int i = 0; i < subsetSums.size(); ++i)
        {
            int el = subsetSums.get(i);
            if(!copy.containsKey(el))
            {
                return false;
            }
            else if ( copy.get(el) == 1)
            {
                copy.remove(el);
            }
            else
            {
                copy.put(el, copy.get(el) - 1);
            }
        }
        return (copy.size() == 0);
    }

}



