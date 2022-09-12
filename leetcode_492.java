/*

492. Construct the Rectangle

Can we binary search the rectangle area?
We know our bounds here
We also have factorizations -> we can keep trying that.
We can test up to square root
Area= 1,000,000 ( 1 MIL max ) 
    sqrt = 1000
    1000 possibilities only here
    test for modulo too

*/
class Solution {
    public int[] constructRectangle(int area) {
        int maxDiff = area - 1; // pretend as if area where a length in itself ( e..g 4 x 1 ) type of thing
        int[] pair = new int[]{area,1};
        int sqrtBound = (int)Math.sqrt(area);
        for(int L = 1; L <= sqrtBound; L++)
        {
            if(area % L == 0) {
                int W = area / L ;
                if(W <= L )
                {
                    int diff = L - W; // always positive
                    if(diff < maxDiff) // minimize this diff
                    {
                        maxDiff = diff;
                        pair[0] = L;
                        pair[1] = W;
                    }
                }
                else if ( L < W )
                {
                    int diff = W - L;
                    if(diff < maxDiff )
                    {
                        maxDiff = diff;
                        pair[0] = W;
                        pair[1] = L;
                    }
                }
            }
        }
        return pair;
    }
}
