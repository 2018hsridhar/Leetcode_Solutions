/*
https://en.wikipedia.org/wiki/Triangle_inequality

URL = https://leetcode.com/problems/largest-triangle-area/
812. Largest Triangle Area

Points are not zero-indexed here
Lengths guaranteed within range [3,10^4] too
Nums lengths can be duplicated ( think equilateral triangle ) 
Guaranteed at least 3 points 


Area of triangle = (0.5*b*h) or other formulas ( s(s-a)(s-b)(s-c) ) type of deal
Utilize the Triangle inequality [ https://en.wikipedia.org/wiki/Triangle_inequality ] 

Edge case testing
(1) Normal case - [1,2,2]
(2) Equal lengths case - [6,6,6]
(3) Broken case - [1,1,2]
(4) Large volume case - [1,3,2,5,7,9,204,12,345,12,3]
(5) Small-large variation - [1,2,3,100,101,102]
(6) If possible, an incredibly large array of random data

*/

class Solution 
{
    public int largestPerimeter(int[] nums) 
    {
        int largestPerim = 0;
        Arrays.sort(nums);
        for(int i = nums.length - 1; i >= 2; --i )
        {
            // Run triangle check
            int len_1 = nums[i];
            int len_2 = nums[i-1];
            int len_3 = nums[i-2];
            if((len_1 + len_2 > len_3) && (len_2 + len_3 > len_1) && ( len_3 + len_1 > len_2))
            {
                largestPerim = len_1 + len_2 + len_3;
                break;
            }
        }
        
        return largestPerim;
    }
}
