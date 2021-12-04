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
I am suspecting potential for data overflow here already !
-> exert caution!
-> please read the problem constraints better too!

*/


class Solution 
{
    public int minElements(int[] nums, int limit, int goal) 
    {
        long sum = 0;
        int ops = 0;
        for(int i : nums) 
        {
            sum += i;
        }
        // 100,000,000,000 vs 2,147,483,647
        long diff = 0;
        if(goal > sum ) 
        {
            diff = Math.abs((long)goal - sum);
            ops = (int) (diff / limit);
            if(diff % limit != 0)
                ops++;
            return ops;
        }
        else if ( goal < sum ) diff = Math.abs((long)sum - goal);
        else if ( goal == sum ) return 0; //premature computation
        while(diff > 0)
        {
            if(diff < limit) 
                limit = (int)diff;
            diff -= limit;
            ++ops;
        }
        return ops;
    }
}
