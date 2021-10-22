/*

URL = https://leetcode.com/problems/sliding-window-median/
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

CURRENT COMPLEXITY
Let N := len(nums)
Time = O(nklog(k))
Lower bound ( not strict ) : O(n)

OPTIMAL COMPLEXITY
Can we get to O(nlogk)?
Space = O(k)


Edge Case Testing : 
(A) nums = [1,3,-1,-3,5,3,6,7], k = 1
(B) nums = [1,3,-1,-3,5,3,6,7], k = 2
(C) nums = [1,4,2,3], k = 4
    Bound here as well
(D) [2147483647,2147483647]
2
    Oh yay int overflow ... use longs now
(E) [15,12,9,6,3]
3
(F) [3,6,9,12,15]
3
(G) [3,6,9,12,15,18]
4
(H) [18,15,12,9,6,3]
4
(I) [2147483647,2147483647,-2147483643,-1]
4

* (J) [0,4,6,0,3,2,6,9,4,1]
3
* (K) [1,3,-1,-3,5,3,6,7]
3 
* (L) [6,5,9,5,4,9,1,7,5,5]
4


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
        // [1] Special case handling : k = 1, k = 2 here
        int n = nums.length;
        int numMedians = (nums.length - k) + 1; // k = 8 : 8 - 8 + 1 = 1 intervals
        double[] medians = new double[numMedians];
        
        for(int i = 0; i < numMedians; ++i)
        {
                if(k == 2)
                {
                    double temp = (double)nums[i] + (double)nums[i+1];
                    medians[i] = temp / 2.0; // special case where median = avg here
                }
                else if ( k == 1 )
                {
                    medians[i] = nums[i];
                }
        }
        if(k == 1 || k == 2)
        {
            return medians;
        }
        
        // Now when k = 3 here
        
        
        PriorityQueue<Double> maxLeft = new PriorityQueue<Double>(new Comparator<Double>() {
            public int compare(Double left, Double right)
            {
                return -1*left.compareTo(right);
            }
        });
        PriorityQueue<Double> minRight = new PriorityQueue<Double>();
        int heapSz = k / 2;
        if(k % 2 == 1)
        {
            heapSz = (k/2) + 1;
        }
        for(int i = 0; i < k; ++i) // {0,1,2} for k = 3, {0,1,2,3} for k = 4
        {
            double val = nums[i];
            maxLeft.add(val);
            minRight.add(val);
            if(maxLeft.size() > heapSz)
            {
                maxLeft.poll();
            }
            if(minRight.size() > heapSz)
            {
                minRight.poll();
            }
        }
            
        
        int wIdx = 0;
        for(int i = k; i < n; ++i) // If k = 3 : do we start @ index 0, 1 or 2?
        {
            // Print out heap states @ start of each
            double median = 0;
            median = (maxLeft.peek() + minRight.peek()) / 2.0;
            medians[wIdx] = median;
            wIdx++;
            double toRemove = (double)nums[i - k];
            double toAdd = (double)nums[i];

            // The number we remove and the number we add has a major affect here
            // Try some bounding arguements as well ... special case if the number we remove is a median though
            
            // Wrote median after computing it -> now case analysis here
            // The bug is probably here in the removal stages
            if(toRemove == median)  // this can be buggy as well too! median NOT guaranteed to be in both sets here as well!
            {
                if(maxLeft.peek() <= toAdd && toAdd <= minRight.peek())
                {
                    maxLeft.remove(toRemove);
                    minRight.remove(toRemove);
                    maxLeft.add(toAdd);
                    minRight.add(toAdd);
                }
                else if ( toAdd < maxLeft.peek())
                {
                    maxLeft.remove(toRemove);
                    maxLeft.add(toAdd);
                    if(k % 2 == 1)
                    {
                        minRight.remove(toRemove);
                        minRight.add(maxLeft.peek());
                    }
                }
                else
                {
                    minRight.remove(toRemove);
                    minRight.add(toAdd);
                    if(k % 2 == 1)
                    {
                        maxLeft.remove(toRemove);
                        maxLeft.add(minRight.peek());
                    }
                }
            } 
            else if ( toRemove < median)
            {
                maxLeft.remove(toRemove);
                if(toAdd <= maxLeft.peek())
                {
                    maxLeft.add(toAdd);
                }
                else
                {
                    if(k % 2 == 1)
                    {
                        minRight.poll();
                        minRight.add(toAdd);
                        maxLeft.add(minRight.peek());
                        
                    }
                    else
                    {
                        minRight.add(toAdd);
                        maxLeft.add(minRight.poll());
                    }
                }
            }
            else
            {
                minRight.remove(toRemove);
                if(toAdd >= minRight.peek())
                {
                    minRight.add(toAdd);
                }
                else
                {
                    if(k % 2 == 1)
                    {
                        maxLeft.poll();
                        maxLeft.add(toAdd);
                        minRight.add(maxLeft.peek());
                    }
                    else
                    {
                        maxLeft.add(toAdd);
                        minRight.add(maxLeft.poll());
                    }
                }
            }
            
        }
        
        // Now at the final point : no more elems to add -> just to check here
        // Ideally, the median is already discovered ... not much logic here as well :-)
        double median = (maxLeft.peek() + minRight.peek()) / 2.0;
        medians[wIdx] = median;
        return medians;
    }
}
