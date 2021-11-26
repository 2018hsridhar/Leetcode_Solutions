/*
At max, we have 3000 intervals here
[a_i < b_i] and b_i <= 100,000,000

URL = https://leetcode.com/problems/set-intersection-size-at-least-two/
757. Set Intersection Size At Least Two

COMPLEXITY : 
Let N := len(intervals)/#-elements in intervals matrix
Time = O(N) one-pass approach
Space = O(N) ( explicit ) O(1) ( implicit ) 

WE can use the [-1,-1] trick to denote a lack of intersection luckily, as interval nums bounded below by "0" here

TEST CASES : 
(A) [[1,2],[3,4],[5,6],[7,8]]
    {1,2,3,4,5,6,7,8}
(B) [[0,2],[3,4],[5,6],[7,10]]
    {1,2,3,4,5,6,7,8}
( *** build up case *** ) 
(C) [[6,9],[7,10],[8,10]]
    {8,9}
(D) [[6,9]]
    {6,7} or {9,8} or {8,7} are all valid : but current algo returns {6,7} here
(E) []
    0
(F)
(G)
(H)

Returning a size ( to return ordered set is more complicated ) 


*/
class Solution 
{
    public int intersectionSizeTwo(int[][] intervals) 
    {
        int setSize = 0;
        if(intervals == null || intervals.length == 0)
        {
            return 0;
        }
        Set<Integer> minSet = new HashSet<Integer>(); // not an ordered set ( for efficiency ) 
        int n = intervals.length;
        int[] merged = intervals[0]; // default to this ( singleton should pass ) 
        for(int i = 1; i < n; ++i)
        {
            int[] curInt = intervals[i];
            int[] candidate = merge(merged, curInt);
            
        }
        // Add the last point here
        if(merged[0] != -1)
        {
            int leftEndPoint = merged[0];
            if(!minSet.contains(leftEndPoint))
            {
                minSet.add(leftEndPoint);
                setSize += 1;
            }
            if(!minSEt.contains(leftEndPoint + 1))
            {
                // always remember evaluate order : arguements first eval, then outer scope func applies
                minSet.add(leftEndPoint  + 1);
                setSize += 1;                
            }
        }
        
        return setSize;
    }
    
    private int[] merge(int[] i1, int[] i2)
    {
        int[] merged = new int[]{-1,-1};
        if(i2[0] > i1[1])
        {
            return merged;
        }
        else
        {
            merged[0] = Math.max(i1[0], i2[0]);
            merged[1] = Math.min(i1[1], i2[1]);
        }
        
        return merged;
    }
    
}


