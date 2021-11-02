/*
This is just the intervals problem in the hiding honestly
Any inductive interval merging problem def. lends itself well to a greedy strategy as well

452. Minimum Number of Arrows to Burst Balloons
URL = https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

Complexity 
Let N := len(baloons)
Let M := number of eventual intervals ( after merge step). Bounded by [1,N] if all or no intervals overlap

Time = O(NlogN) + O(M) = O(NlogN)
Space = O(N)
Most computation occurs in the sort stage

The y-coordinates of the baloons are not needed here
Arrows shootable are unlimited as well
Arrow keeps bursting ANY baloon in its path as well

TEST BENCH :
(A) [1,6],[2,3],[5,7]
    Yes that [2,3],[5,7] have no overlap
    but [1,6] and [5,7] have an overlap
    Can not just do consecutive pair element comparisons then!
(B) [[10,16],[2,8],[1,6],[7,12]]
    2
(C) [[1,7],[1,8],[1,9],[2,4],[2,5],[5,10],[5,20]]
    2
(D) [[1,10],[1,11],[2,4],[5,6],[7,9]]
    3
(E) [[1,2],[2,3],[3,4],[4,5]]
    2
(F) [[1,2],[3,4],[5,6],[7,8]]
    4
    
*/

class Solution 
{
    public int findMinArrowShots(int[][] points) 
    {
        int minNumArrows = 1;
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2)
            {
                if(i1[0] < i2[0])
                    return -1;
                else if (i1[0] > i2[0])
                    return 1;
                return 0;
            }
        });
            
        // This is where they will trip you up ( at the merge stage )
        // Wait ... do we actually have to merge here? The intervals have been sorted already
        // No you must merge
        // See if they can be merged
        // If mergeable - overwrite OR write into a new subintervals code ( new ArrayList ) 
        // Ok ... so first we compare (a1,a2) and then next step compare (a2,a3)
        // Now if (a1,a2) had an overlap -> why not make the next a2 = merged(a1,a2)? 
        // That would do a carry over effect then
        int n = points.length;
        int[] candid = new int[2];  // always first interval anyways
        candid[0] = points[0][0];
        candid[1] = points[0][1];
        for(int i = 1; i < n; i++)
        {
            // System.out.printf("Current set = (%d,%d)\n", candid[0], candid[1]);
            int[] next = points[i];
            if(hasOverlap(candid,next))
            {
                int[] merged = mergeInterval(candid,next);
                candid[0] = merged[0];
                candid[1] = merged[1];
            }
            else
            {
                candid[0] = next[0];
                candid[1] = next[1];                
                minNumArrows++;
            }
        }
         
        return minNumArrows;
    }
    
    public boolean hasOverlap(int[] i1, int[] i2)
    {
        boolean hasOverlap = false;
        if(i1[0] <= i2[0] && i2[0] <= i1[1])
            hasOverlap = true;
        if(i2[0] <= i1[0] && i1[0] <= i2[1])
            hasOverlap = true;
        return hasOverlap;
    }
    
    public int[] mergeInterval(int[] i1, int[] i2)
    {
        int[] merged = new int[2];
        merged[0] = Math.max(i1[0],i2[0]);
        merged[1] = Math.min(i1[1],i2[1]);
        return merged;
    }
    
}
