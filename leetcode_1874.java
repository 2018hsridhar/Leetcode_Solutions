/*

URL = https://leetcode.com/problems/minimize-product-sum-of-two-arrays/discuss/1316700/Straightforward-Java-Solution-%3A-O(N)-time-O(1)-space-after-O(NlogN)-sorts-greedy
1874. Minimize Product Sum of Two Arrays

THOUGHT PROCESS :

Knowns and datatye concerns : 
len(A) = len(B)
len(A) >= 1 at least and reasonable length [ 1,100,000 ]
Numbers reasonably valued from 1-100 :: do not worry about 32-bit Integer Overflow issues I presume, when outputting miProd
Some minimum product sum will be guaranteed here too

COMPUTATIONAL COMPLEXITY : 
T = O(NLOGN) + O(N) = O(NLogN) = { time(sort) + time(solve_product)}
S = O(1) { need local function variables only } 

Edge case testing : 


*/
class Solution
{
    
    public int minProductSum(int[] nums1, int[] nums2) 
    {
        // In production environment, check for null or degenerate array cases ( e.g. 0-length arrays ) 
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int minProdSum = 0;
        for(int i = 0; i < nums1.length; ++i)
            minProdSum += (nums1[i] * nums2[nums2.length - 1 - i]);
        return minProdSum;
    }
}
