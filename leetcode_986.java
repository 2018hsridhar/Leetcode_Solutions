/*

986. Interval List Intersections
URL = https://leetcode.com/problems/interval-list-intersections/

COMPLEXITY
Let M := len(firstList)
Let N := len(secondList)
Let K := number of intersections
Time = O(M+N)
Space = O(1)

TEST BENCH
(A) [], []
( *** provided *** ) ( *** never happens *** ) 
(B) [[0,2],[5,10],[13,23],[24,25]]
    [[1,5],[8,12],[15,24],[25,26]]
(C) [[1,2],[3,4]], [[2,3],[4,5]]
    [[2,2],[3,3],[4,4]]
(D) [], [[2,3],[4,5]]
    []
(E) [[1,2],[3,4]], []
    []
(F) [[1,2],[3,5],[6,7],[8,100]], [[1,3],[4,5]]
    [[1,2],[3,3],[4,5]]

Each list is PAIRWISE DISJOINT ( Take note of that ... no nesting within cases here at least ) 
Note : given [a,b] and [b,c] where a < c but both shared same b -> return [b,b] as intersection ( denotes a point in space ) 
Yes one list may be empty ( or even both ) 

Is a set of real numbers : intersections of closed intervals are alwas closed intervals
Intervals in each lists are always closed as well

Strategies : two pointers, 
Pairwise disjoint property means that pointers can easily be advanced, even if intervals in A are close

*/

class Solution 
{
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) 
    {
        List<int[]> intersects = new ArrayList<int[]>();
        int m = firstList.length;
        int n = secondList.length;
        int p1 = 0;
        int p2 = 0;
        while(p1 < m && p2 < n)
        {
            int[] i1 = firstList[p1];
            int[] i2 = secondList[p2];
            if(hasOverlap(i1,i2))
            {
                int[] intersection = getIntersection(i1,i2);
                intersects.add(intersection);
            }
            if(i1[1] > i2[1])
            {
                ++p2;
            }
            else
            {
                ++p1;
            }
        }
        
        // Convert Arrayist to array
        int k = intersects.size();
        int[][] intersections = new int[k][2];
        for(int i = 0; i < k; ++i)
        {
            int[] interval = intersects.get(i);
            intersections[i][0] = interval[0];
            intersections[i][1] = interval[1];
        }
        return intersections;
    }
    
    // note : we do not merge in this code :-).
    // Remember : equality strictness matters as well : [2,3] and [3,4] possess NO overlap, but [2,3] and [2,4] do possess an overlap
    private boolean hasOverlap(int[] i1, int[] i2)
    {
        // Special case of consecutive STEP-BY-STEP intervals
        if(i1[0] <= i2[0] && i2[0] <= i1[1])
            return true;
        if(i2[0] <= i1[0] && i1[0] <= i2[1])
            return true;
        return false;
    }
    
    // NOT merging : getting intersection -> reverse plz
    // [1,3],[3,5] => [3,3] => is ok
    public int[] getIntersection(int[] i1, int[] i2)
    {
        int[] intersection = new int[2];
        intersection[0] = Math.max(i1[0], i2[0]);
        intersection[1] = Math.min(i1[1], i2[1]);
        return intersection;
    }
}
