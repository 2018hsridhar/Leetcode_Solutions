/*

URL = https://leetcode.com/problems/interval-list-intersections/
986. Interval List Intersections

Two lists -> two pointers for traveral
Pairwise disjoint and in sorted ordedr helps consideraby!

Iterate both SLL pointers { i, j } . While less then their lengths, keep going
One you are done with one ... you should not have any more remaining intersections anyways ( I think )? 

Easiest conversion : ArrayList append method -> to Array ( size of result is not known, and O(1) insertion time is desirable here ) 
Helps that itervals are pairwise disjoint ( the sorted list has no intersections ) !

Focus on increment of pointers during merge of two sorted lists!
SRP : set aside extra class function to determine if two timeslots intersect!

Test case 1 : one of these lists is an empty list or a null list - > return empty array

What type of order relation defines our intersection ( <=, or < )? Is a gotcha

Time complexity : Desirably [T,S] = [O(N), O(1)] where N := number of elements total in both SLLs

Getting tripped up : dealing with a full merge or a partial merge of intervals?
Can we get tripped up testing or coding for each type of merge?

*/


class Solution 
{
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) 
    {
        
        if(firstList.length == 0 || secondList.length == 0)
            return new int[0][0];
        
        int i = 0;
        int j = 0;
        ArrayList<int[]> mergedIntervals = new ArrayList<int[]>();
        while(i < firstList.length && j < secondList.length)
        {
            int[] ts_one = firstList[i];
            int[] ts_two = secondList[j];
            
            int[] merged = merge(ts_one, ts_two);
            ++i;
            ++j;
        }
        
        // ArrayList -> Array Conversion step
        int[][] results = new int[mergedIntervals.size()][2];
        for(int x = 0; x < mergedIntervals.size(); ++x)
            results[x] = mergedIntervals.get(x);
        return results;
    }
    
    public boolean intervalsIntersect(int[] A, int[] B)
    {
        if(A[1] < B[0])
            return false;
        else if ( B[1] < A[0] )
            return false;
        return true;
    }
    
    public int[] merge(int[] A, int[] B)
    {
        
    }
    
}
