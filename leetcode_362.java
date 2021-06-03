/*

362. Design Hit Counter
URL = https://leetcode.com/problems/design-hit-counter/

THOUGHT PROCESSES : 

Several hits can happen at same timestamp - store their frequencies, or double count them too
Need to store number of hits in past 300 seconds ( t - 300 ) 
Idea : Utilize hashmap? 
Chronological order - but - can have multiple hits such as hit(2), hit(2), hit(2) 
Does design scale if (hits/second) increases to a large amount?
Other idea - utilize a queue and iterator? Wasn't this done earlier?
Stream of data -> entails queue approach
*/


class HitCounter {

    /** Initialize your data structure here. */
    Queue<Integer> hits;
    
    public HitCounter() 
    {
        hits = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) 
    {
        hits.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) 
    {
        int numHits = 0;
        int tLow = timestamp - 300 + 1;
        // Iterate and remove from head of our queue
        while(!hits.isEmpty())
        {
            int top = hits.peek();
            if(top < tLow)
                hits.remove();
            else
                break;
        }
        
        // Then iterate over queue - but do not remove any elements
        Iterator<Integer> itr = hits.iterator();
        while(itr.hasNext())
        {
            int nextCan = itr.next();
            if(tLow <= nextCan && nextCan <= timestamp )
            {
                ++numHits;
            }
            else
                break;
        }
        
        return numHits;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
