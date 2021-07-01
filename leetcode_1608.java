
/*

URL = https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
1608. Special Array With X Elements Greater Than or Equal X

THOUGHT PROCESSES : 
Seems like a sort and binary sesarch combination for sure
At each midpoint, we already know how many elements are greater than or equal to us ( as we know array lengths too ) . 
We know <X> is bounded by [1,array_length] here


Preprocessing sort using something like MergeSort - O(NlogN) time-space complexity for sure

Brute force solution entails possibly more than O(N) time, even with O(1) space
Binaary search reduces time complexity down to O(log(N))

Array length guaranteed to be at least 1 : zero elements >= zero is guaranteed to never happen honestly ( worst case be a singleton array = [0])

Misread the problem : it must be an exact count - it is not ( oh, we met a count )
For Example 4, X=3 fails ( we have 4 numbers >= 3 ) ! 

*/


class Solution 
{
    public int specialArray(int[] nums) 
    {
        Arrays.sort(nums);
        
        int maxX = -1;
        // Goal : binary search iterative for loop for efficiency?
        for(int i = 1; i <= nums.length; ++i)
        {
            int idx = (nums.length - 1 - (i - 1));
            
            if(i == nums.length)
            {
                // Handle i = nums.length case here now
                if(nums[0] >= nums.length)
                    maxX = nums.length;
            }
            else
            {
                if(nums[idx] >= i && nums[idx-1] < i)
                    maxX = i; 
            }
        }
        
        if(maxX > 0) 
            return maxX;
        return -1;
    }
}
