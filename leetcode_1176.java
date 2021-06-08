/*

1176. Diet Plan Performance
URL = https://leetcode.com/problems/diet-plan-performance/
Typical sliding window problem : utilize a deque approach

Fortunately, "k" is bounded by the closed interval [1,len(calories)]
We work with subsequence sums quite a lot - harkens back to R.A. concepts?


*/

class Solution 
{
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) 
    {
        int points = 0;
        Deque<Integer> days = new LinkedList<Integer>();
        int T = 0;
        for(int i = 0; i < calories.length; ++i)
        {
            int myCal = calories[i];
            days.add(myCal);
            T += myCal;
            
            if(days.size() > k)
            {
                int firstDay = days.removeFirst();
                T -= firstDay;
            }
            if ( days.size() == k)
            {
                if(T < lower )
                    --points;
                else if ( T > upper )
                    ++points;
            }
            
        }
        return points;
    }
}
