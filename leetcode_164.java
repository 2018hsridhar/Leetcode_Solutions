// Accompanying hyperlinks 

// Radix Sorting : 
// https://www.youtube.com/watch?v=nu4gDuFabIM
// https://www.youtube.com/watch?v=XiuSW_mEn7g&t=81s
// https://www.youtube.com/watch?v=OKd534EWcdk
// https://www.youtube.com/watch?v=XcF1l2pq9ZI


// Counting Sort : 
// https://www.youtube.com/watch?v=OKd534EWcdk

/*

164. Maximum Gap
URL = https://leetcode.com/problems/maximum-gap/

THOUGHT PROCESS : 
Let us test our knowledge of the radix sort algorithm
Things I will ask you in a sort algorithm : 
1. What are your keys used here?
2. How to handle case of shared keys? Is your sort algorithm stable or unstable?
3. What subroutine are you using?
4. Can we establish the depth of a sorting algorithm ( e.g. Radix / Merge ) . If recursive, note time and space complexity of each step
5. Is the sorting algorithm in-place or uses external memory?
6. Can we establish a type of convergence?
7. Is it a comparitive ( e.g. merge/quick ) or a non-comparative sort algorithm?
8. Talk about sorting algorithms in terms of keys ( not elements ) : sometimes, we compute some info from each element.
9. Is a lexicographic sort : works across the dimensions of tuples of data ( x1,x2,...,xn ). Sort from the broadest dimension unto the narrowest dimension. Max number of digits can be considered a "dimension" for our data! Most familiar lexicographic orderings are 2D cartesian planes or dictionary orderings.
10. Want to see if interviee understands either "bucketsort" or "countsort"


EDGE CASES : 
Singleton array case -> return 0


DATA TYPE LIMITS : 
Length of array reasonably guranteed from 1,2,....100,000
Elements of array reasonably guaranteed from 1,2,...,1,000,000,000




Edge cases : 
*/

class Solution 
{
    public int maximumGap(int[] nums) 
    {
        int maxGap = Integer.MIN_VALUE;
        
        return maxGap;    
    }
}
