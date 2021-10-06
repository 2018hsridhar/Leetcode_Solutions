/*
565. Array Nesting
URL = https://leetcode.com/problems/array-nesting/

Seems highly akin to a typical DFS/stack approach as well

Length bounded by [1,1e6]
All numbers are unique : noi duplicates
nums itself is a permutation of [0,nums.length - 1]

Complexity : 
Let N := len(nums)
TIME = O(N)
    * Is guaranteed N elements of iteration as each can be its own isolated set
SPACE = O(1) or O(N)

Edge Case Testing : 
(A) [1,2,3,4,5,0]
    => 6
(B) [5,4,0,3,1,6,2]
    => 4
(C) [0,1,2]
    => 1
(D) [1]
    => 1 ( even if [1] == [0] here, no real change as well too ) 
(E) []
(F) []
(G) []
(H) []


*/

class Solution 
{
    public int arrayNesting(int[] nums) 
    {
        int longestNest = 0;
        if(nums == null || nums.length == 0)
            return 0;
        for(int i = 0; i < nums.length; ++i)
        {
            int curLongest = dfs(nums,i);
            longestNest = Math.max(longestNest, curLongest);
        }
        return longestNest;
    }
    
    public static int dfs(int[] nums, int i)
    {
        int longest = 0;
        // [1] Explore parent
        int next = nums[i];
        if(next == -1)
        {
            return 0;
        }
        else
        {
            nums[i] = -1;
            longest = 1 + dfs(nums, next);
        }
        return longest;
    }
    
}


