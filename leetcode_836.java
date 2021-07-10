/*

836. Rectangle Overlap
URL = https://leetcode.com/problems/rectangle-overlap/

Rectangles remain axis-aligned in 2D Euclidean space
Coordinates represented as int[] arrays
Tell if two rectangles overlap 
- If they touch at the corner/edges : does not count as an overlap

Rectangles can be anywhere in cartesian plane ( not limited to first quadrant ) 
Rectangles are valid rectangles too : non-zero area ( thus, not a point nor a line ) 

What is easier to solve
- (a) when they do overlap
- (b) when they do not overlap
As in mathematics, the complement may prove easier to work with

Suppose we have R1 centered about somewhere
Let R2 be in either of the 4 cardinal directions present : { ->, ^, !, -< }

Obtain width and height information of rectangles too
Upper right corner always guaranted to be >= lower left corner : thus, no need for aboslute value in these differences too!

Ideal performance : [T,S] = [O(1), O(1)] : seems like constant time operations for sure

Edge cases to test out : 
[1] Same rectangles - [0,0,2,2], [0,0,2,2]
[2] Non intersecting - [0,0,2,2], [4,4,5,5]
[3] Point intersection - [0,0,2,2], [2,2,4,4]
[4] Line intersections - [0,0,2,2],[2,0,4,4]
[5] Partial intersection - [0,0,2,2], [1,1,3,3]
[6] Perhaps degenerate cases, but they be absent here ( points/lines ) 


We have four cases of overlaps
(1) 4 corners overlap - 4 unique point sets with {R1,R2] intersection
(2) 4 line segments from 2 edges overlappig exactly - esay to determine via "=" operator
(3) the pure non-overlapping case

Really, 12 cases to check out. Draw them out geometrically and tell, from rectangle coordinate information

*/



class Solution 
{
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) 
    {
        // [1] Obtain width and height data
        int rec1_w = rec1[2] - rec1[0];
        int rec2_w = rec2[2] - rec2[0];
        
        int rec1_h = rec1[3] - rec1[1];
        int rec2_h = rec2[3] - rec2[1];
        
        // [2] Obtain 4 coordinates information
        int rec1_left = rec1[0];
        int rec1_right = rec1_left + rec1_w;
        int rec1_bottom = rec1[1];
        int rec1_top = rec1_bottom + rec1_h;
        
        int rec2_left = rec2[0];
        int rec2_right = rec2_left + rec2_w;
        int rec2_bottom = rec2[1];
        int rec2_top = rec2_bottom + rec2_h;
        
        // [3] Perform boundary testing - { E,N,W,S } - edges and no overlap
        if(rec2_left >= rec1_right)
            return false;
        if(rec2_bottom >= rec1_top ) 
            return false;
        if(rec2_right <= rec1_left )
            return false;
        if(rec2_top <= rec1_bottom)
            return false;
        
        // [4] perform test for point overlap of {R1,R2}
        
        // [1] Check rec1(right,top) = rec2(left,bottom)
        if(rec1_right == rec2_left && rec1_top == rec2_bottom)
            return true;
        
        // [2] Check rec1(left,top) = rec2(right,bottom)
        if(rec1_left == rec2_right && rec1_top == rec2_bottom)
            return true;
        
        // [3] Check rec1(left,bottom) = rec2(right,top)
        if(rec1_left == rec2_right && rec1_bottom == rec2_top)
            return true;
        
        // [4] Check rec1(right,bottom) = rec2(left,top)
        if(rec1_right == rec2_left && rec1_bottom == rec2_top)
            return true;
        
        
        
        return true;
    }
}
