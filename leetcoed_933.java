/*

URL = https://leetcode.com/problems/number-of-recent-calls/
Problem Statement = 933 : Number of Recent Calls

THOUGHT PROCESSES : 


    Every call to ping is guaranteed to use strictkly larger values of <t> than preceding calls
    We can store a queue, which removes values below the inclusive time range of [t-3000, t]
    Additinoally, we may not need to make a node class - just store the integers of times under consideration here
    Also - need not have to store the time interval - can calculate each time anyways
    Handle case where t < 3000 ( leads to a negative time interval ) 
    
*/

class RecentCounter {

    int numRecReqs;
    Queue<Integer> pings;
    
    public RecentCounter() 
    {
        numRecReqs = 0;
        pings = new LinkedList<Integer>();
    }
    
    
    public int ping(int t) 
    {
        // always add most current entry to queue anyways
        pings.add(t);
        int curNumReqs = 0;
        int tLow = t - 3000;
        
        /*
         * Remove unneeded elements in the beginning ( peek -> then remove )
         * Avoid overflow of the container - reduce memory consumption
         */
        while(pings.size() > 0)
        {
            int nextCandidate = pings.peek();
            if(nextCandidate < tLow)
                pings.remove();
            else
                break;
        }
        
        /*
         * Cannot perform iteration and removal of first node in a queue
         * Will entail a "ConcurrentModificationException" error message then!
         * Need an iterator in place of peek-poll operation, as we desire queue to remain intact
         * The perk of an iterator() - it can leave a data structure still intact! we don't need to rely on size methods too! OH
         * No need for while(x.size() >= y ) coding - just ask if you keep having a next element instead!
         */
        
        Iterator<Integer> itr = pings.iterator();
        while(itr.hasNext())
        {
            int nextCandidate = itr.next();
            if(tLow <= nextCandidate & nextCandidate <= t)
                curNumReqs += 1;
            else
                break;
        }
        numRecReqs = curNumReqs; 
        return curNumReqs;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
