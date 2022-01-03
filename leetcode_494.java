/*

URL = https://leetcode.com/problems/target-sum/
494. Target Sum

Idea : alternate signage of the computation as we go to get #-total expressions
All integers -> check data overflow too
Even if we added other nums now -> you would add whatever you skipped later, is the key here
Decrease lengths as we proceed

COMPLEXITY
Let n := len(nums)
TIME = O()
SPACE = O()

TEST CASES 
(A) [1,1,1] target = 3
    => 1
(B) [1,1,1,1,1] target = 3
    => 5 ( getting 4 : amend this ) 
(C) 2 [1,1,1,1]
(D) 0 [1,1]
    => expect 2 : getting a 1 here
(E) [6,1,4,3,7,8,9,10,8,7,2,3] target = 6
Could it be done in BUDP fashion?
    we do not know targets yet
    
*/
class Solution 
{
    public int findTargetSumWays(int[] nums, int target) 
    {
        int numWays = 0;
        int index = 0;
        numWays = helper(nums, index, target,target);
        return numWays;
    }
    
    /*
        helper(nums,0,3)
            helper(nums,1,2)
                helper(nums,2,1)
                    helper(nums,3,0)
                        helper(nums,4,-1)
                        helper(nums,4,1)    YES
                    helper(nums,3,2)
                        helper(nums,4,1)    YES
                        helper(nums,4,3)
                helper(nums,2,3)
                        helper(nums,3,2)
                            helper(nums,4,1)    YES
                            helper(nums,4,3)
                        helper(nums,3,4)
                            helper(nums,4,3)   
                            helper(nums,4,5)
            helper(nums,1,4)
    */
    private int helper(int[] nums, int index, int curVal, int target)
    {
        // System.out.printf("Index = [%d] \t target = [%d]\n", index, target);
        int numWays = 0;
        int n = nums.length;
        if(index == n - 1)
        {
            // if(target == -1*nums[index] + curVal)  // getting too huge now : readjust
            //     numWays += 1;
            // if ( target == nums[index] + curVal)
            //     numWays += 1;
            if(0 == -1*nums[index] + curVal)  // getting too huge now : readjust
                numWays += 1;
            if ( 0 == nums[index] + curVal)
                numWays += 1;

        }
        else if ( index < n - 1)
        {
            int nextIndex = index + 1;
            int higherNum = curVal + nums[index];
            int lowerNum = curVal - nums[index];
            numWays += helper(nums,nextIndex, higherNum, target);
            numWays += helper(nums,nextIndex, lowerNum, target);
        }
        return numWays;
    }
    
}
