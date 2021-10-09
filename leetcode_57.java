/*

57. Insert Interval
URL = https://leetcode.com/problems/insert-interval/

Provided a list of NON-OVERLAPPING intervals
Intervals[i] = [start_i, end_i]
Also sorted in ascending order ( NOT STRICTLY in all cases ) by [start_i]
Given another interval titled 'newInterval'

Operation must preserve existing sorted order
Merge overlapping intervals if needed

Return the intervals after insertion ( need not be an in-place modification ) 

Edge cases 
(A) [[1,3],[6,9]] : [2,5]
    => [[1,5],[6,9]]
(B) [[1,2],[3,5],[6,7],[8,10],[12,16]] : [4,8]
    => [[1,2],[3,10],[12,16]]
(C) [[1,3],[4,5],[6,9]] : [10,11]
    => [[1,3],[4,5],[6,9], [10,11]]
    => insert into end
(D) [[2,3],[4,5],[6,9]] : [0,1]
    => [[0,1],[2,3],[4,5],[6,9]]
    => insert into beginning
(E) [[2,3],[4,5],[10,12]] : [6,8]
    [[2,3],[4,5],[6,8],[10,12]]
    => insert into middle
(F) [[2,3],[4,5],[10,12]] : [1,13]
    [[1,13]]
    => fully overlapped all other intervals contained here :-O
(G) [[2,3],[4,5],[7,9],[10,12]] : [1,5]
    [[1,5],[7,9],[10,12]]


*/


class Solution 
{
    public int[][] insert(int[][] intervals, int[] newInterval) 
    {
        ArrayList<int[]> mergedInts = new ArrayList<int[]>();
        
        
        
        // Convert : ArrayList -> Array
        int n = mergedInts.size();
        int[][] results = new int[n][2];
        for(int i = 0; i < mergedInts.size(); ++i)
        {
            int[] myInterval = mergedInts.get(i);
            results[i][0] = myInterval[0];
            results[i][1] = myInterval[1];
        }
        return results;
    }
}
