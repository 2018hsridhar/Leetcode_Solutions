/*
URL = https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/
1637. Widest Vertical Area Between Two Points Containing No Points

No need to check invalidity of data/inputs : assume mostly valid.
Number of points reasonable : 100,000 max only; nlgn not atrocious.

Edge Cases
(A) [[3,3],[3,3],[3,4],[3,7],[3,1]] => 0
(B) [[3,3],[3,3],[4,7],[3,4],[3,7],[4,1]] => 1
(C) [[3,3],[3,3],[6,9],[4,7],[3,4],[3,7],[4,1]] => 2
(D)
(E)

Let N := len(points)
Time = O(NlgN)
Space = Space the sort lib func call needs

*/
class Solution {
    // Goal : can we avoid coding up a seperate class and having to change accessibility modifiers?
    // Can we shield potential code usage too?
    // Can we avoid creating seperate objects?
    
    public int maxWidthOfVerticalArea(int[][] points) {
        int widestVerticalArea = 0;
        Arrays.sort(points, new Comparator<int[]>(){
           public int compare(int[] pointOne, int[] pointTwo){
               if(pointOne[0] < pointTwo[0]){
                   return -1;
               } else if ( pointOne[0] > pointTwo[0]){
                   return 1;
               } else {
                   // more optimal : no need to even sort by <y> / the second entry
                   return 0;
               }
           } 
        });
        for(int i = 0; i < points.length-1; ++i){
            widestVerticalArea = Math.max(widestVerticalArea, points[i+1][0] - points[i][0]);
        }
        return widestVerticalArea;
    }
}
