// https://leetcode.com/problems/contains-duplicate/#/description

public class Solution {
    public boolean containsDuplicate(int[] nums) 
    {
        boolean status = false;
        if(nums.length < 2)
        {
            status = false;
        }
        else
        {
            Arrays.sort(nums);
            for(int i = 0; i < nums.length - 1; i++)
            {
                if(nums[i] == nums[i+1])
                {
                    status = true;
                    return status;
                }
            }
            
        }
        return status;
    }
}
