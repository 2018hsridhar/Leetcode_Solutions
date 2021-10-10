/*

480. Sliding Window Median

Middle value of an ORDERED integer list
Must handle case of parity - even/odd len - of said list though
    [2,3,4] => 3
    [1,2,3,4] => 2.5

Given an array "nums" and an integer "k"
Sliding window size is known
Direction is strict : left -> right only
Movement is by one position only
Can be within a small epsilon range of actual value ( some double calculations ) 
Numbers and nums length is reasonably, BUT numbers can range from [INT_MIN,INT_MAX]
    => leverage use of double/long for a summation

Complexity : 
Let N := len(nums)
Time = O(nklog(k))
Lower bound ( not strict ) : O(n)
Can we get to O(nlogk)?
Space = O(k)


Edge Case Testing : 
(A)
(B)
(C)
(D)
(E)
(F)

sz      toRemove    toCheck
2       0           2
3       1           1
4       1           2
5       2           1
6       2           2
7       3           1

Just keep subtracting 2 I guess till we are less than 2. 
    7-2-2-2 = 1 = 3 times
    3-2     = 1 = 1 time
    2       = 0 = 0 time
    4-2     = 2 = 1 time
    
*/
class Solution 
{
    public double[] medianSlidingWindow(int[] nums, int k) 
    {
    }
    
    public double[] bruteForceSolution(int[] nums, int k) 
    {}
        // [1] Special case handling : k = 1
        // if(k == 1)
            // return nums; 
        
        int numMedians = (nums.length - k) + 1; // k = 8 : 8 - 8 + 1 = 1 intervals
        double[] medians = new double[numMedians];
        for(int i = 0; i < numMedians; ++i)
        {
            double med = 0;
            PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
            for(int j = 0; j < k; ++j)
                minHeap.add(nums[i+j]);
            // Now subsorted priority queue : remove specific num elems in odd or even case
            // Depending on parity, remove that many specific element from heap
            int sz = minHeap.size();
            while(sz > 2)
            {
                minHeap.poll();
                sz -= 2;
            }
            if(sz == 1)
                med = (double)minHeap.poll();
            else
                med = ((double)minHeap.poll() + (double)minHeap.poll()) / 2;
            medians[i] = (double)med;
            minHeap.clear();
        }
        return medians;
    }
}
