// https://leetcode.com/problems/top-k-frequent-elements/

// oh shit ... remember the bucket sort algo!
// ... what happens, in the case of duplicates ... I guess you return those too ... it really shouldn't matter, naa!

public class Solution {
    // so I do get that a PQ and a hashmap will eb useful here!
    // ... but ... how exactly to update the PQ? updating the HM, is rather easy, in comparison!
    public List<Integer> topKFrequent(int[] nums, int k)
    {
        if(nums == null)
        {
            return null;
        }
        LinkedList<Integer> result = new LinkedList<Integer>();    
        // ... now I see where lambda exprs for java would be nice ... direct array manipulation !
        // ... remember ... choose what you want to be @ top of queue ( it is a minheap )
        // note 1 :: it is far tougher to code directly ATOP arrays, versus coding on an object ... but it is a good exercise + learn the lambda-esque new Comparator pattern in java
        PriorityQueue<Integer[]> topK = new PriorityQueue<Integer[]>(k,
            new Comparator<Integer[]>()
            {
                public int compare(Integer[] a, Integer[] b)
                {
                    if(a[1] < b[1])
                    {
                        return -1;
                    }
                    if ( a[1] > b[1])
                    {
                        return 1;
                    }
                    return 0;
                }
            }
        );
        
        HashMap<Integer,Integer> freqMap = new HashMap<Integer,Integer>();
        // seperate teh Hm update, and the PQ update ... it's easier that way!
        
        for(int i = 0; i < nums.length; i++)
        {
           // s1 :: update HM  
           if(!freqMap.containsKey(nums[i]))
           {
               freqMap.put(nums[i],1);
           }
           else
           {
               freqMap.put(nums[i],freqMap.get(nums[i]) + 1);
           }
        }
        
        // s2 :: update PQ
        // uhh, just iterate over ze keys!
        for(Map.Entry<Integer,Integer> entry : freqMap.entrySet())
        {
            int num = entry.getKey();
            int freq = entry.getValue();
            Integer[] newPair = {num,freq};
            if(topK.size() < k)
            {
                topK.offer(newPair);
            }
            else
            {
                Integer[] minMaxPair = topK.peek(); // bug 1 :: should be peek, not poll(). poll() only when u offer()
                if(freq > minMaxPair[1] || (freq == minMaxPair[1] && num > minMaxPair[0]))
                {
                   topK.poll();
                   topK.offer(newPair);
                }
            }
        }
        
        // keep polling ze PQ
        while(topK.size() > 0)
        {
            Integer[] temp = topK.poll();
            result.add(temp[0]);
        }
        return result;
    }
}
