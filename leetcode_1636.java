
/*

URL = https://leetcode.com/problems/sort-array-by-increasing-frequency/submissions/
1636. Sort Array by Increasing Frequency


// Has to be Comparable<Typename>
// If just Comparable - then compareTo(Object o ) gets expected
// Yeah it be like this

*/

public class freqPair implements Comparable<freqPair>
{
    public int val;
    public int freq;
    
    public freqPair(){};
    public freqPair(int val, int freq)
    {
        this.val = val;
        this.freq = freq;
    }
    
    @Override
    public int compareTo(freqPair other)
    {
        if(this.freq < other.freq)
            return -1;
        else if ( this.freq > other.freq ) 
            return 1;
        else
        {
            if(this.val < other.val)
                return 1;
            else if ( this.val > other.val ) 
                return -1;
            return 0;
        }
    }
    
}

class Solution {
    
    public int[] frequencySort(int[] nums) 
    {
        int n = nums.length;
        int[] results = new int[n];
        // Quickly obtain frequencies of each value
        HashMap<Integer,Integer> freqs = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; ++i)
        {
            int num = nums[i];
            if(freqs.containsKey(num))
                freqs.put(num,freqs.get(num) + 1);
            else
                freqs.put(num,1);
        }
        
        // Sort frequencies, via comparator
        // Do not sort hashmap directly though - lest is prove painful
        // Key-set iterator technique remains easiest iteration technique for java's hashmaps! Compared to EntrySet, especially!
        // Think of hashmaps - only keySet, or EntrySet - and both are sets ( with defined iteators )
        ArrayList<freqPair> pairings = new ArrayList<freqPair>();
        Iterator<Integer> keySet = freqs.keySet().iterator();    
        while(keySet.hasNext())
        {
            Integer myKey = keySet.next();
            Integer val = freqs.get(myKey);
            freqPair newPairing = new freqPair(myKey, val);
            pairings.add(newPairing);
        }
        
        Collections.sort(pairings);
        int wIdx = 0;
        for(freqPair e : pairings)
            for(int i = 0; i < e.freq; ++i)
                results[wIdx++] = e.val;
        
        return results;
    }
}
