public class Solution {
    // always assume a valid k
    // ... shit ... it is kth-largest elem! REMEMBER THAT!!
    public int findKthLargest(int[] nums, int k)
    {
        if(nums == null)
        {
            return -1;
        }
        int largest = Integer.MIN_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k);
        // kth largest eleme ... in a k-sized PQ, that is the min elem ... hence, min PQ.
        // array is given , in an unsorted order
        // say, pq = {2,3}, k = 2? ... if new elem is <= pq min ( can peek )  ... uh, we don't return it ( ans lies in the pq, always!)
        // if new elem, is in pq range, or > max ... pop min, add new elem to pq
        
        // 1. init pq
        for(int i = 0; i < k; i++)
        {
            pq.add(nums[i]);
        }
        
        // 2. iterate over rest of ze array
        for(int i = k; i < nums.length; i++)
        {
            int curMin = pq.peek();
            if(nums[i] > curMin)
            {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        largest = pq.poll();
        return largest;
    }
}
