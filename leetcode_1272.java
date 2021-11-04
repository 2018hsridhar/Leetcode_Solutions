/*

Note : your input list is sorted anyways, so the output list will still be sorted here

COMPLEXITY : 
Let N := len(intervals)
Time = O(N)
Space = O(1) ( explicit & implicit ) ( not account for space of return list as well :-) ) 

TEST BENCH : 
*** provided *** 
(A) [[0,2],[3,4],[5,7]]
    [1,6]
(B) [[0,5]]
    [2,3]
(C) [[-8,2],[3,4],[5,7]]
    [1,6]
(D) [[-8,8]]
    [1,6]
(E) [[-1000,-100],[-99,-10],[-8,8],[9,100],[101,1000]]
    [1,6]


*** failing case 
[[0,100]]
[0,50]

(F) [[0,100]],[0,50]
(G) [[0,100]],[50,100]

*/
class Solution 
{
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) 
    {
        List<List<Integer>> newSet = new ArrayList<List<Integer>>();
        int n = intervals.length;
        for(int i = 0; i < n; ++i)
        {
            int[] curr = intervals[i];
            List<Integer> transformedInterval = new ArrayList<Integer>();
            if(hasOverlap(curr,toBeRemoved))
            {
                // handle four types of cases here
                // first two are specific nestings as well
                // Yes we do need our nested case here!
                // toBeRemoved is purely greater : don't even try here 
                if(toBeRemoved[0] <= curr[0] && curr[1] <= toBeRemoved[1])
                {
                    continue;
                }
                // Nested within purely : but not 2x partitionable ( not at the interior ... ahh I think I see ? ) 
                // to be remove is less ( like in example 2 here ) 
                else if ( curr[0] < toBeRemoved[0] && toBeRemoved[1] < curr[1] )
                {
                    List<Integer> partition_one = new ArrayList<Integer>();
                    List<Integer> partition_two = new ArrayList<Integer>();

                    partition_one.add(curr[0]);
                    partition_one.add(toBeRemoved[0]);
                    partition_two.add(toBeRemoved[1]);
                    partition_two.add(curr[1]);
                    
                    newSet.add(partition_one);
                    newSet.add(partition_two);
                }
                else
                {
                    if (curr[0] < toBeRemoved[0])
                    {
                        transformedInterval.add(curr[0]);
                        transformedInterval.add(toBeRemoved[0]);
                    }
                    else    // curr[1] > toBeRemoved[1]
                    {
                        transformedInterval.add(toBeRemoved[1]);
                        transformedInterval.add(curr[1]);
                    }
                    newSet.add(transformedInterval);
                }
            }
            else
            {
                transformedInterval.add(curr[0]);
                transformedInterval.add(curr[1]);
                newSet.add(transformedInterval);
            }
        }
        return newSet;
    }
    
    // Remember : equality strictness matters as well : [2,3] and [3,4] possess NO overlap, but [2,3] and [2,4] do possess an overlap
    
    // may need a merge, but that is following the determination of the intersection as well
    // as usual, the nested case is always easiest to handle
    
    // [3,6] and [6,9] should return false here : change inequality strictness
    // inequality strictness changes with OPEN sets VS. closed sets ( take close note ) 
    // not [3,6] and [5,9] though
    // may or may not need intersection code as well
    private boolean hasOverlap(int[] i1, int[] i2)
    {
        // Special case of consecutive STEP-BY-STEP intervals
        if(i1[0] <= i2[0] && i2[0] < i1[1])
            return true;
        if(i2[0] <= i1[0] && i1[0] < i2[1])
            return true;
        return false;
    }
    
}
