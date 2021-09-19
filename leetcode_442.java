/*
THOUGHT PROCESS : 
Highly highly akin to the negatie markeing question
Integers strictly positive and within the range of [1,n]
Each element appears exactly ONCE or exactly TWICE
Return those which appear twice

Complexity
Time = O(N)
Space = O(N)
442. Find All Duplicates in an Array

*/
class Solution 
{
    public List<Integer> findDuplicates(int[] nums) 
	{
        List<Integer> results = new LinkedList<Integer>();
        for(int i = 0; i < nums.length; ++i)
		{
			int val = nums[i];
			int idx = val - 1;
			if(val < 0)
				idx = (int)Math.abs(val) - 1;
			if(nums[idx] > 0)
				nums[idx] *= -1;
			else
                results.add((int)Math.abs(val));
		}
		return results;
	}
}
