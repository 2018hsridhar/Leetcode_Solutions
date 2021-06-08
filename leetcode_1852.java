/*

URL = https://leetcode.com/problems/distinct-numbers-in-each-subarray/
1852. Distinct Numbers in Each Subarray

Is definetely of the form of a sliding window problem
Still need a hashmap to accompany along the way
Handle case where nums.length == 1 too
Luckily, k is guaranteed to be bounded by the closed interval [1,nums.length] :-). No need to handle k > nums.length case too


Yep - classic sliding window technique here : use Java Deque data structure!

*/

class Solution {
    public int[] distinctNumbers(int[] nums, int k) 
    {
        int[] dflt = {1};
        if(nums.length == 1 ) 
            return dflt;
        
        ArrayList<Integer> distincts = new ArrayList<Integer>();        
        Deque<Integer> sliding = new LinkedList<Integer>();
        HashMap<Integer,Integer> freqs = new HashMap<Integer,Integer>();
        int numDistinct = 0;
        
        for(int i = 0; i < nums.length; ++i)
        {
            int val = nums[i];
            sliding.addLast(val);
            if(freqs.containsKey(val))
                freqs.put(val, freqs.get(val) + 1);
            else
            {
                freqs.put(val, 1);
                ++numDistinct;
            }
            
            if(sliding.size() == k)
                distincts.add(numDistinct);
            else if(sliding.size() > k)
            {
                // REMOVE FRONT ELEMENT
                int inFront = sliding.removeFirst();
                if(freqs.containsKey(inFront) && freqs.get(inFront) == 1)
                {
                    freqs.remove(inFront);
                    --numDistinct;
                }
                else if ( freqs.containsKey(inFront) && freqs.get(inFront) > 1)
                    freqs.put(inFront, freqs.get(inFront) - 1);
                
                distincts.add(numDistinct);
            }
        }
        
        int[] arr = new int[distincts.size()];
        for(int i = 0; i < distincts.size(); ++i)
            arr[i] = distincts.get(i);
        return arr;
        
    }
}
