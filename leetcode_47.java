/*
47. Permutations II


THOUGHT PROCESSES : 

Permutations are a highly recursive problem
Like earlier, pass the following in thy state of the function within the method signature :
- 2 linked lists ( 1 LL for cur perm, 1 LL for global LL )
- nums array
- current length of list
- Visited set of numbers ( to not add as we go in each depth )
- Requires memory in solution

Tree of depth "n" where n := max length of a permutation ( in this case, nums.length )
Max depth of a permutation here is 8
Max nums range : {-10,10}
No array trick here due to the negative integers range :-( )



*/


class Solution 
{
    /*
    
    Note the weirdness here : 
    1. You must return a List<List<Integer>> 
    2. You can not instantiate an abstract class ( a.k.a. do List is abstract; cannot be instantiated unto new List<List<Integer>>() ) 
    
    
    - so you must set List<List<Integer>> to something such as ArrayList<ArrayList<Integer>> or List<ArrayList<Integer>> or ArrayList<List<Integer>>
    3. Instantiation to ArrayList<ArrayList<Integer>> will fail you
         error: incompatible types: ArrayList<ArrayList<Integer>> cannot be converted to List<List<Integer>>
    
    4. List<ArrayList<Integer>> is also invalid : can not instantiate to List at first
    5. In the end, you are left with instnaite to ArrayList<List<Integer>> or LinkedList<List<Integer>>
    6. At least you can make the inside arg a LinkedList, or an ArrayList
    7. Can't instsantiate an abstract class as its abstract methods are never overwritten!!
    
         
    */
    public List<List<Integer>> permuteUnique(int[] nums) 
    {
        // Precompute hashmap
        HashMap<Integer,Integer> freqs = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; ++i)
        {
            if(freqs.containsKey(nums[i]))
                freqs.put(nums[i], freqs.get(nums[i]) + 1);
            else
                freqs.put(nums[i], 1);
        }
        
        // Execute recursive helper with hasmap
        int depth = nums.length;
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> levelList = new ArrayList<Integer>();
        generatePermutations(permutations, levelList, depth, freqs);
        return permutations;

        
    }
    
    // Void method : state is impacted within method signature anyways, to begin with 
    // Multiple deep copying steps needed here though!
    // Notice use of copy constructor/deep copying too!
    public void generatePermutations(List<List<Integer>> permutations, ArrayList<Integer> levelList, int depth, HashMap<Integer,Integer> freqs)
    {
        if(depth == 0)
        {
            permutations.add(levelList);
            return;
        }
        for(int i : freqs.keySet())
        {
            int curElFreq = freqs.get(i);
            if(curElFreq != 0)
            {
                HashMap<Integer, Integer> nextFreqs = new HashMap<Integer, Integer>();
                nextFreqs.putAll(freqs);
                nextFreqs.put(i, --curElFreq );

                ArrayList<Integer> nextLevelList = new ArrayList<Integer>();
                nextLevelList.addAll(levelList);
                nextLevelList.add(i);
                generatePermutations(permutations, nextLevelList, depth - 1, nextFreqs);
            }
        }
        
    }
        
    
    
    
}
