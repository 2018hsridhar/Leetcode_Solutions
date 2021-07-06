/*
31. Next Permutation
URL = https://leetcode.com/problems/next-permutation/

THOUGHT PROCESSES : 

If nums.length == 1 : just return itself
Method is "in-place" with constant extra memory : type=void
Not all elements are guaranteed uniqueness ( 1,1,5) case -> breaks many permutations

Ideal computational complexity : [T,S] = [O(N), O(1)]

Edge case testing : 
(1) Normal case = (1,2,3)
(2) [1,1,5] - non unique els case
(3) Singleton case - {1}
(4) Large volume - say, n := 100 

Efficient in-place swap operations generally need two pointers to be kept track of here
We do not exactly know the min or max of the permutation 
E.g. we can have a case such as (1,1,1) or (4,5,6) for a permutation of length = 3
It need not have to be equal to (1,2,3)


*/
class Solution 
{
    public void nextPermutation(int[] nums) 
    {
        
    }
}
