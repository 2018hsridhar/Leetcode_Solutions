*

THOUGHT PROCESS : 

1037. Valid Boomerang
URL = https://leetcode.com/problems/valid-boomerang/

This is a pure cross-product problem ( in the hiding )
Check the angle between two lines, and it is proves zero, then the points be collinear!
Cross-products help ascertain collinearity

Recall : 2D graphics => 3D graphics cases

Test cases : 
True case : [[1,1],[2,3],[3,2]]
False case : [[1,1],[2,2],[3,3]]
'
Their other solution : the triangle formed from them has a non-zero area ( s(s-s1)(s-s2)(s-s3)) formula?
*/

class Solution 
{
    public boolean isBoomerang(int[][] points) 
    {
        // Compute vector points for three points - {A,B,C}
        int[] A = points[0];
        int[] B = points[1];
        int[] C = points[2];
        
        // Compute cross-prod quantities
        int cross_prod_p1 = ((B[0] - A[0]) * (C[1]-A[1]));
        int cross_prod_p2 = ((B[1] - A[1]) * (C[0]-A[0]));
        int cross_prod = cross_prod_p1 - cross_prod_p2;
        if(cross_prod != 0)
            return true;
        return false;
    }
}
