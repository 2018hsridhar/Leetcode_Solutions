// https://leetcode.com/problems/sort-characters-by-frequency/

// no public keyword here!
// note :: u need an obj, if your data types differ! else, u can directly manipulate a set of arrays! NICE, right!
class chFreq
{
    public char c;
    public int f;
    
    public chFreq(char _c, int _f)
    {
        c = _c;
        f = _f;
    }
}

public class Solution {
    // so I do get that a PQ and a hashmap will eb useful here!
    // ... but ... how exactly to update the PQ? updating the HM, is rather easy, in comparison!
    public String frequencySort(String s) {
    {
        if(s == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        
        // set up priority queue, for 1. largest frequency then 2. increasing lexicographic ordering
        // char = field 0, frequency = field 1
        PriorityQueue<chFreq> topK = new PriorityQueue<chFreq>(
            new Comparator<chFreq>()
            {
                public int compare(chFreq a, chFreq b)
                {
                    // note :: a lexicographic ordering is always a default ... no need for that else statement!
                    if(a.f > b.f) return -1;
                    if(a.f < b.f) return 1;
                    return 0;
                }
            }
        );
        
        // s1 :: update Hm, of character frequencies
        HashMap<Character,Integer> freqMap = new HashMap<Character,Integer>();
        char[] cArr = s.toCharArray();
        for(int i = 0; i < cArr.length; i++)
        {
           // s1 :: update HM  
           if(!freqMap.containsKey(cArr[i]))
           {
               freqMap.put(cArr[i],1);
           }
           else
           {
               freqMap.put(cArr[i],freqMap.get(cArr[i]) + 1);
           }
        }
        
        // s2 :: update PQ
        // uhh, just iterate over ze keys!
        for(Map.Entry<Character,Integer> entry : freqMap.entrySet())
        {
            char let = entry.getKey();
            int freq = entry.getValue();
            topK.offer(new chFreq(let,freq));
        }
        
        // keep polling ze PQ, and add to the string!
        while(topK.size() > 0)
        {
            chFreq temp = topK.poll();
            for(int i = 0; i < temp.f; i++)
            {
                sb.append(temp.c);
            }
        }
        return sb.toString();
    }
}
}
