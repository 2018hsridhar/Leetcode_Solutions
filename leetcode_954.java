/*

Strategy -> execute a sort here, or perform (k,v) insertions as well
Hmm ... perhaps (k,v) pairings might be more helpful?

URL = https://leetcode.com/problems/array-of-doubled-pairs/
954. Array of Doubled Pairs
Inputs guaranteed to be integers
Array len reasonably bounded by [2,30K] = [2,3e5]
Array len guaranteed even property
Integers guaranteed reasonableness too
Array values not guaranteed uniqueness as well here!
Expect a trick with division too

Complexity ( outside of Merge sort of O(NlogN) time step ): 
Let N := len(arr)
Time = O(N)
Space = O(N).

Edge Case Testing : 
(A) [3,1,3,6]   => FALSE
(B) [4,-2,2,-4] => TRUE
(C) [-1,-2,-4,-8] => TRUE
(D) [1,2,4,8] => TRUE
(E) [-1,-2,-4,-8, 1, 2, 4, 8 ] => TRUE
(F) [2,2,2] => FALSE
(G) [2,1,2,1,1,1,2,2]
    => Expected True
    [1,1,1,1,2,2,2,2] is actually true
    yep this was going to trip me up !
    Can try to do a hashmap of frequencies here? If we encounter a duplicate? 
        -> but need to store frequency of each duplicate as well!
 (H) [1,1,2,2,2,2,4,4]      
    f(1) = f(4) = 2, but f(2) = 4
    Need to ensure to account for both directions as well -> decrement those frequencies in your hashtable here if we have a relationship mapping too
    If I could reduce it to [1,2,4], sure that is easier -> but need to account for frequencies as well here!
    
                                                                            

*/

class Solution 
{
    public boolean canReorderDoubled(int[] arr) 
    {
        if(arr == null || arr.length < 2)
            return true;
        boolean canReorder = true;
        
        // [1] Sort thy array input
        Arrays.sort(arr);
        
        // [2] Fill out hashmap
        // Consider allocate a new array, of reduced size, with the duplicates removed as well?
        HashMap<Integer,Integer> intFreqs = new HashMap<Integer,Integer>();
        for(int i = 0; i < arr.length; ++i)
        {
            int val = arr[i];
            if(intFreqs.containsKey(val))
                intFreqs.put(val,intFreqs.get(val) + 1);                
            else
                intFreqs.put(val,1);
        }
            
        // [3] Perform the algo
        int n = arr.length;
        int i = 0;
        while(i < n/2)
        {
            // System.out.printf("i = %d \t n = %d\n", i, n);
            // System.out.printf("2*i = %d \t 2*i+1 = %d\n", 2*i, 2*i + 1, n);
            if(i >= n) break;
            if(arr[2*i] < 0)
            {
                if(2*arr[(2*i) + 1] != arr[2*i]) return false;
            }
            if(arr[2*i] > 0)
            {
                if(2*arr[2*i] != arr[(2*i)+1]) return false;
            }
            ++i;
        }
        return canReorder;
    }
}
