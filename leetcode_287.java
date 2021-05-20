// mathematical-based solution works, BUT ONLY in the case of all the numbers being unique ( except for one number ). 
// does not work if all the numbers can be the same, or a duplicate can appear more than twice
// Unlikely to solve in the near future


class Solution {
   
  
  public int findDuplicate(int[] nums) 
    {
        int dupl = -1;
        // int n = nums.length - 1;
        
        if(nums.length == 2)
            return 1; // duplicate already known here
        
        int len = nums.length;
        int sum = len * ( len + 1 ) / 2;
        // System.out.printf("n = [%d] \t sum = [%d] \t len = [%d]\n", n, sum, len);
        
        for(int i = 0; i < len; ++i)
        {
            sum -= nums[i];
        }
        // use remainder technique here
        dupl = len - sum;

        return dupl;
    }
}

        
// for(int i = 0; i < nums.length; ++i )
// {
//     for(int j = i+1; j < nums.length; ++j)
//     {
//         if(nums[i] == nums[j])
//         {
//             dupl = nums[i];
//             return dupl;
//         }
//     }
// }
