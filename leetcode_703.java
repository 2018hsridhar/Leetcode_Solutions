/*

703. Kth Largest Element in a Stream
URL = https://leetcode.com/problems/kth-largest-element-in-a-stream/

*/


class KthLargest {

    PriorityQueue<Integer> pq;
    int[] nums;
    int k;

    public KthLargest(int k, int[] nums) 
    {
        // public PriorityQueue(int initialCapacity)
        // Notice : priority queue / size of other data structures is not like arrays - may be unbounded
        // Must manually auto-adjust each time
        pq = new PriorityQueue<Integer>(k);
        this.nums = nums;
        this.k = k;
        
        // Fill up priority queue as much as possible
        // Assume PQ utilizes the default sort method for Integers too!
        for(int i : nums)
        {
            pq.add(i);
            if(pq.size() > k)
            {
                pq.remove();
            }
        }
                
        // Iterator<Integer> itr = pq.iterator();
        // while(itr.hasNext())
            // System.out.printf("%d\n", itr.next());
        
    }
    
    // Guaranteed <k> elements at least during search for the <kth> element
    public int add(int val) 
    {
        int result = -1;
        // Return kth-largest value, BUT, only after additino too!
        pq.add(val);
        if(pq.size() > k)
        {
            pq.remove();
        }
        result = pq.peek();
        return result;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
