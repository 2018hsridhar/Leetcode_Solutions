

/*

1232. Check If It Is a Straight Line
URL = https://leetcode.com/problems/check-if-it-is-a-straight-line/

Is a cross-product problem again
Check cross product of each consecutive point, compared to earlier point, equals zero
Is also highly inducative in nature : a[i] compared to a[i+1]

Ideal [T,S] = O(N), O(1) complexity : one-pass linear scan desirable
Cross-product operation time should be zero too!

2D grid : z-azis values will naturally zero out for us too!

*/

class Solution
{
    public boolean checkStraightLine(int[][] coordinates) 
    {
        if(coordinates.length == 2)
            return true;
        
        // Now check the cross-products as we inductively iterate over theh remainder of coordinates
        for(int i = 2; i < coordinates.length; i++)
        {
            int[] point_1 = coordinates[i-2];
            int[] point_2 = coordinates[i-1];
            int[] point_3 = coordinates[i];
            int cross_prod = computeCrossProduct(point_1, point_2, point_3);
            if(cross_prod != 0)
                return false;
        }
        return true;
    }
    
    public int computeCrossProduct(int[] a, int[] b, int[] c)
    {
        int cross_prod_p1 = ( b[0] - a[0] ) * (c[1] - a[1]);
        int cross_prod_p2 = ( b[1] - a[1]) * ( c[0] - a[0]);
        int cross_prod = cross_prod_p1 - cross_prod_p2;
        return cross_prod;
    }
    
}
