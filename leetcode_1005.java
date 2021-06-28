/*

We like parity / categorization into two -> cuz it maps unto the range [0,1] :-)

1005. Maximize Sum Of Array After K Negations
URL = https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/

The same index can be chosen multiple times : exert caution
We want to eliminate negative numbers as much sa possible for sure
If operations remaining, and working with only positive numbers :
- if even number ops : just keep at that same index
- if odd number of ops : apply once to current index and call it a day
- boils down to a [0,1] toggle [ even-odd parity ]

Can have singleton array, but arr len bounded by 10^5
Values will prove reasonabel here

[-8,3,-5,-3,-5,-2]
[ -8, -5, -5, -3, -2, 3] => you can not just sweep here : negative all values except for (-2 ) : leave that as is!
[2,3,3,5,5,8] = sort ( by the absolute value ) 
Potential two pointer approach? Split iteration of positive and negative values here!


6

Wrong, as all operations must be applied ( not just 5 ops )
Yes, we can negative all here, but it will not help
Problem space is easier to deal with when dealing with parity of negatives here too!

[ -8, -5, -5, -3, -2, 3] and k = 6
[ -8, -5, -5, -3, -2, -1, 3] and k = 6
[ -8, -5, -5, -3, 3] and k = 6
[ -8, -5, -5, -3, 4] and k = 5

[-3,4,-5,-5,-8] -> if sort by absolute value : (-+) ordering if abs values equal each other, right?
*/

class Solution 
{
    public int largestSumAfterKNegations(int[] nums, int k) 
    {
        int sum = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; ++i)
        {
            if(nums[i] < 0 && k > 0)
            {
                sum += Math.abs(nums[i]);
                --k;
            }
            else if ( nums[i] < 0 && k == 0)
                sum += nums[i];
            else
            {
                if(k % 2 == 1)
                {
                    sum += (nums[i] * -1);
                    k = 0; 
                }
                else
                {
                    sum += nums[i];
                    k = 0;
                }
                
            }
        }
        return sum;
        
    }
}
