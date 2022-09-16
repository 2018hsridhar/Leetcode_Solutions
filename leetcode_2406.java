/*

2406. Divide Intervals Into Minimum Number of Groups
URL = https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/

Similar to a lot of problems working with intervals.
Greedy min-max optimization-esque problem.

Complexity
Let N := #-intervals
Time = O(NLgN)
Space = O(K), but in worst case, O(K) = O(N), so O(N) ( explicit ) and O(1) ( implicit ) 


So 32/35 test cases pass
Which one failing here?

*/


public class Info
{
    public int left;
    public int right;
    
    public Info() {
        this.left = 0;
        this.right = 0;
    }
    
    public Info(int left, int right)
    {
        this.left = left;
        this.right = right;
    }
    
    
}
class Solution {

    public class InfoComparator implements Comparator<int[]> {
        public int compare(int[] one, int[] two){
            if(one[0] < two[0])
            {
                return -1;
            } else if(one[0] > two[0])
            {
                return 1;
            } else {
                // Flip the other way for the inclusion of nested intervals.
                if(one[1] < two[1])
                {
                    return 1;
                } else if(one[1] > two[1])
                {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public class HeapComparator implements Comparator<int[]> {
        public int compare(int[] one, int[] two){
            // Focus on order of right instead.
            // If we have something less "right" on the x-axis -> have that "pollable" than an interval "MORE" right on the x-axis.
            if(one[1] < two[1])
            {
                return -1;
            } else if(one[1] > two[1])
            {
                return 1;
            } else {
                // Agnostic to order of left here
                if(one[0] < two[0])
                {
                    return 1;
                } else if(one[0] > two[0])
                {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
    
    public int minGroups(int[][] intervals) 
    {
        if(intervals == null || intervals.length == 0)
            return 0;
        int minNumGroups = 1; // at least one group @ min
        // benefit of comparator class : can instantiate multiple comparator objects across
        // different data structures too!
        Arrays.sort(intervals, new InfoComparator());
        PriorityQueue<int[]> maxSlideWind = new PriorityQueue<int[]>( new HeapComparator());
        // `de facto` push the first interval in too!
        maxSlideWind.add(intervals[0]);
        for(int i = 1; i < intervals.length; ++i)
        {
            int[] curInterval = intervals[i];
            if(maxSlideWind.size() == 0) {
                maxSlideWind.add(curInterval);
                minNumGroups = Math.max(minNumGroups, maxSlideWind.size());
            } else {
                int[] minRightInterval = maxSlideWind.peek();
                if(minRightInterval[1] >= curInterval[0])
                {
                    maxSlideWind.add(curInterval);
                }
                else if ( minRightInterval[1] < curInterval[0]) { // 
                    while(!maxSlideWind.isEmpty()) {
                        int[] myMinRightInterval = maxSlideWind.peek();
                        if(myMinRightInterval[1] < curInterval[0])
                        {
                            maxSlideWind.poll();
                        } else {
                            break;
                        }
                    }
                    maxSlideWind.add(curInterval); // ensure this added at some point too!
                }
                int sizeWind = maxSlideWind.size();
                minNumGroups = Math.max(minNumGroups, sizeWind);
            }
        }
        return minNumGroups;
    }
}
