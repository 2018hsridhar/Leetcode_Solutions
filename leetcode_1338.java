
/*

1338. Reduce Array Size to The Half
https://leetcode.com/problems/reduce-array-size-to-the-half/

THOUGHT PROCESSES 

[A] Sorting this array is doable - probably has negligible impact though
[B] We need to generate a frequency count too
-> then proceed to sort this too
We can grab the total number of elements, and check if we removed at least half of them in the process!

*/

public class Pair
{
    int val;
    Integer freq;
    public Pair(){}
    public Pair(int val, int freq) { this.val = val; this.freq = freq; }
    public String toString() { return "Pair = (" + this.val + "," + this.freq + ")"; }
}

class Solution {
    public int minSetSize(int[] arr) 
    {
        int minSize = 0;
        int n = arr.length;
        int half = n / 2;
        int count = 0;
        if(arr.length == 1)
            return 1;
        HashMap<Integer,Integer> freqs = new HashMap<Integer,Integer>();
        for(int i = 0; i < arr.length; ++i)
        {
            int val = arr[i];
            if(freqs.containsKey(val))
            {
                freqs.put(val, freqs.get(val) + 1);   
            }
            else
            {
                freqs.put(val, 1);
            }
        }
        
        // Convert hashmap to pair object : add to a list, and then go sort that instead! 
        // Extra space, but so be it
        LinkedList<Pair> pairs = new LinkedList<Pair>();
        for(Map.Entry<Integer,Integer> e : freqs.entrySet())
        {
            // System.out.printf("< K,V > = < %d,%d >\n", e.getKey(), e.getValue());
            Pair p = new Pair(e.getKey(), e.getValue());
            pairs.addLast(p);
        }
        
        Collections.sort(pairs, new Comparator<Pair> () {
           
            @Override
            public int compare(Pair p1, Pair p2) 
            {
                return (p2.freq.compareTo(p1.freq));
            }
        });
        
        for(Pair p : pairs )
        {
            // System.out.printf(" < K,V > = < %d, %d >\n", p.val, p.freq );
            n -= p.freq;
            ++count;
            if(n <= half )
                return count;
        }
        
        
        // Sort hashmap by values
//         Collections.sort(freqs, new Comparator< Map.Entry<Integer, Integer>>() {
           
//             @Override
//             public int compare(Map.Entry<Integer,Integer> e1, Map.Entry<Integer,Integer> e2)
//             {
//                 Integer val_1 = e1.getValue();
//                 Integer val_2 = e2.getValue();
//                 return val_1.compareTo(val_2);
//             }
//         });
        
        return minSize;
    }
}
