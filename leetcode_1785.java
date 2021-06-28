/*

THOUGHT PROCESS : 
1. We already know our limit denotes a range of [-limit,limit]
2. Keep subtracing or adding max in said range, until value decreases to less than that ( from goal ) 
3. Can sum up nums already and compute that sum

Number of operations to equal this value, based off some range provided and a sum known?
Ops bounded by [0,x] where x := some marked boundary

Runtime ideal : [T,S] = [O(N), O(1)] where T := One-Pass Linear Scan of elements ( or [-limit,limit] range testing )

Array length never zeroes out : singleton case permitted! { 1 } or {-1}
Limit bounded by [1,1,000,000]
Goal bounded by [1,1,000,000,000] 

Issue : may encounter TLE case!

*/


class Solution 
{
    public int minElements(int[] nums, int limit, int goal) 
    {
        int sum = 0;
        int ops = 0;
        for(int i : nums) sum += i;
        int diff = 0;
        if(goal > sum ) diff = Math.abs(goal - sum);
        else if ( goal < sum ) diff = Math.abs(sum - goal);
        else if ( goal == sum ) return 0; //premature computation
        // System.out.printf("sum = %d\t Limit = %d\t Goal = %d\t diff = %d\n", sum, limit, goal, diff);
        while(diff > 0)
        {
            if(diff < limit) 
                limit = diff;
            diff -= limit;
            ++ops;
        }
        
        return ops;
    }
}
