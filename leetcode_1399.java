/*

URL = https://leetcode.com/problems/count-largest-group/
n is bounded by the closed interval [1,10000] where 10000 = 10^4
1399. Count Largest Group
Bound on array size is 9999 : sum = 9 8 4 = 36

So we need to maintain a count of each grouping
And then the frequency of said maximum too!
We can get the maximum in O(N) time as we do countings
But to obtain a further count - need another O(n) iteration too! Unless another hashmap structure is kept ( but that entails extra storage space :-( )

*/


class Solution 
{
    public int countLargestGroup(int n) 
    {
        int largestGroup = 0;
        int countOfLargestGroup = 0;
        int[] groupCounts = new int[37];
        for(int i = 1; i <=n; ++i)
        {
            int digSum = computeSumOfDigits(i);
            groupCounts[digSum]++;
            largestGroup = Math.max(largestGroup, groupCounts[digSum]);
        }
        for(int i : groupCounts)
            if(largestGroup == i) ++countOfLargestGroup;
        return countOfLargestGroup;
    }
    
    public int computeSumOfDigits(int i)
    {
        int sum = 0;
        while(i >= 10)
        {
            sum += i % 10;
            i /= 10;
        }
        return (sum += i);
    }
    
}
