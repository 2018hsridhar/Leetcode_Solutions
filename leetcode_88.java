//https://leetcode.com/problems/merge-sorted-array/

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) 
    {
        if(nums1.length == 0)
        {
            return;
        }
        else
        {
            int ptr1 = m-1; // m = 2 elems ... idx 1 
            int ptr2 = n-1;
            int wIdx = nums1.length - 1;
            
            while(ptr1 >= 0 || ptr2 >= 0)
            {
                if(ptr1 >= 0 && ptr2 >= 0)
                {
                    if(nums1[ptr1] >= nums2[ptr2])
                    {
                        nums1[wIdx] = nums1[ptr1];
                        ptr1--;    
                    }
                    else
                    {
                        nums1[wIdx] = nums2[ptr2];
                        ptr2--;     
                    }
                }
                else if ( ptr1 >= 0)
                {
                    nums1[wIdx] = nums1[ptr1];
                    ptr1--;
                }
                else
                {
                    nums1[wIdx] = nums2[ptr2];
                    ptr2--;    
                }
                wIdx--;
            }
        }
    }
}

