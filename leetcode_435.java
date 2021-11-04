/*

IDEAS
- as usual, lexicograpically sort the array it its own modularized method

COMPLEXITY
Let N := #-intervals ( length )
Let K := #-non overlapping intervals
Time = O(NlgN) + O(N)
Space = O(1) ( explicit & implicit ) 

TEST BENCH
(A) [[1,10],[1,9],[1,8],[1,7],[1,6]]
    -> 4 : need to remove te four top nesting intervals
(B) [[2,7],[3,6],[4,5]]
    2
* (C) [[1,10],[2,11],[3,12],[4,13],[11,13]]
    3
(D) [[1,8],[3,10],[5,9],[2,3],[3,5],[5,7]]
    3
(E) [[1,10],[2,11],[10,11]]
1

*/
class Solution 
{
    public int eraseOverlapIntervals(int[][] intervals) 
    {
        if(intervals == null || intervals.length <= 1)
            return 0;

        int numIntervalsRemoved = 0;
        execLexicographicSort(intervals);
        numIntervalsRemoved = solveForNumOverlaps(intervals);
        return numIntervalsRemoved;
    }
    
    private int solveForNumOverlaps(int[][] intervals)
    {
        int numToRemove = 0;
        int[] underCons = new int[2];
        underCons[0] = intervals[0][0];
        underCons[1] = intervals[0][1];
        
        int n = intervals.length;
        for(int i = 1; i < n; ++i)
        {
           int[] curr = intervals[i];
           if(hasOverlap(underCons, curr))
           {
               if(curr[1] < underCons[1])
               {
                    underCons[0] = curr[0];     
                    underCons[1] = curr[1];     
               }
               ++numToRemove;
           }
           else
           {
            underCons[0] = curr[0];     
            underCons[1] = curr[1];     
           }
        }
        return numToRemove;
    }
    
    // note : we do not merge in this code :-).
    // Remember : equality strictness matters as well : [2,3] and [3,4] possess NO overlap, but [2,3] and [2,4] do possess an overlap
    private boolean hasOverlap(int[] i1, int[] i2)
    {
        // Special case of consecutive STEP-BY-STEP intervals
        if(i1[0] < i2[1] && i1[1] == i2[0])
            return false;
        if(i2[0] < i1[1] && i2[1] == i1[0])
            return false;
        
        
        if(i1[0] <= i2[0] && i2[0] <= i1[1])
            return true;
        if(i2[0] <= i1[0] && i1[0] <= i2[1])
            return true;
        return false;
    }
    
    
    private void execLexicographicSort(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]> (){
            public int compare(int[] i1, int[] i2)
            {
                if(i1[0] < i2[0])
                    return -1;
                else if ( i1[0] > i2[0])
                    return 1;
                else
                {
                    if(i1[1] < i2[1])
                        return -1;
                    else if(i1[1] > i2[1])
                        return 1;
                    else
                        return 0;
                }
            }
        });
    }
    
}
