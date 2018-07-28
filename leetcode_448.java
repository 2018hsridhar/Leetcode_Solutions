// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums)
    {
        // n = arr size = 8 :; perfect list = [1,2,3,4,5,6,7,8] .. need to account for proper indexing, of values too!
        // also ... u will never get something like [10,9] .. thsi does not meet ze constraint !
        LinkedList<Integer> res = new LinkedList<Integer>();
         // don't sort ... but flag, via -1. if u already encountered it ... uh, janne de
         for(int i = 0; i < nums.length; i++)
         {
             int index = Math.abs(nums[i]) - 1;
             if(nums[index] < 0)
             {
                 continue;
             }
             else
             {
                 nums[index] *= -1;
             }
         }
         
         
         // anotehr pass .. if not neg ... add ze index
         for(int i = 0; i < nums.length; i++)
         {
            int val = nums[i];
            if(val > 0)
            {
                res.add(i+1);
            }
         }
         
         
        return res;
    }
}
