/*

URL = https://leetcode.com/problems/3sum-closest/
16. 3Sum Closest

Complexity
Let N := len(nums)
Time = O(NlogN) + O(N^2)
Space = 


TEST BENCH
(A)[-4,-1,1,2] target = 1
    2
(B) [0,0,0] target = {0,1}

No empty/null case : array always well-formed input
Numbers in range of [-10e3, 10e3]
target also valid : in range of [-10e4, 10e4]
No need to worry about overflows ( e.g. INT_MIN, INT_MAX ) cases

*/
class Solution 
{
    public int threeSumClosest(int[] nums, int target) 
    {
        Arrays.sort(nums);
        int n = nums.length;
        int minDistAway = Integer.MAX_VALUE;
        int closestSum = Integer.MAX_VALUE;
        for(int i = 0; i < n-2; ++i) // 
        {
            int h = i+1;
            int t = n-1;
            int subSum = target - nums[i];
            while(h < t)
            {
                int val = nums[h] + nums[t];
                int curDist = (int)Math.abs(val + nums[i] - target);
                if(val == subSum)
                {
                    closestSum = target;
                    return target;
                }
                else if ( val > subSum)
                {
                    --t;
                }
                else
                {
                    ++h;
                }
                if(curDist < minDistAway)
                {
                    minDistAway = Math.min(minDistAway, curDist);
                    closestSum = val + nums[i];
                }
            }
        }
        return closestSum;
    }
}
