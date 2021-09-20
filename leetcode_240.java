/*

URL = https://leetcode.com/problems/search-a-2d-matrix-ii/
240. Search a 2D Matrix II

How to apply divide-and-conquer/recursive approach here
How do define the recurrence relationship as well

base case : singleton array/single el matrix -> return if we matched with target or not here
Otherwise, reduce the dimensions s.t. they do not overlap and engender overlapping subproblems

[m,n] guaranteed to fit in-mem as well
Target guaranteed in range of -(10e9),(10e9) : fits in INT data type

Binary search can ACCOMPANY a divide-and-conquer esque algorithm for sure
Either we search two arrays or one array ( hit target value or not ) 


D-&-C need not always be a div by 2 approach : tends to be recursive for sure though
Can try a central element approach as well too ( consider this a root case ) 

At least we can work off the assumption that r1 <= r2 AND c1 <= c2 here :-)

PSEUDO-CODE

// The great news about this approach => can ignore dimensionality to a decent extent!


Use math-cartesian plane notation here

   | 
 2 | 1
----------
   |
 3 | 4
 
 
DAC is recursive => hence, an expoential esque type of runtime as well!
 
DAC(matrix, r1,r2,r3,r4, target):
    if(abs(r2-r1) <= 1 && abs(c2-c1) <= 1 )
        return matrix[0][0] == target
    else if ( abs(r2-r1) <= 1)
    {
        DAC(quad_1, r1,r2, c1, c2/2, target)
        DAC(quad_2, r1,r2, (c2/2) + 1, c2, target)        
    }
    else if ( abs(c2-c1) <= 1)
    {
        DAC(quad_1, r1,(r2/2), c1, c2, target)
        DAC(quad_2, (r2/2) + 1, r2, c1, c2, target
    }
    else
    {
        DAC(quad_1, r1,(r2/2), c1, (c2/2), target)
        DAC(quad_2, r1,(r2/2), (c2/2 + 1), c2, target)
        DAC(quad_3, (r2/2)+1, r2, c1, (c2/2), target)
        DAC(quad_4, (r2/2)+1, r2, (c2/2 + 1), c2, target)
    }
    
COMPLEXITY :
Let M := number of row elements
Let N := number of col elements
TIME = 
SPACE = 

Trivial binary search is : 
time = O(mlog(n)) + O(nlog(m)) : can vary as dim(m) == dim(n) ONLY in some cases too!
space = o(1) ( unless done recursively using implicited call procedure stack ) 

*/
class Solution 
{
    public boolean searchMatrix(int[][] matrix, int target) 
    {
        boolean hitTarget = false;
        return hitTarget;
    }
}
