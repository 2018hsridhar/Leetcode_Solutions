```
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


Dividing by 10 is akin to a bit shift in zeroes place
E.g. ( 7530/10  = 750 ) &&& ( 750 / 10  )= 75.
Take 7530 : we will go 7530 -> 753 -> 75 -> 7 over each round
And each modulo in the steps yields us 0 -> 3 -> 5 -> 7 ( modulo by 10 ).
Imagine it being like (7500>>1) in base 10
Now can we use (>>1) for counting number of binary digits?? 
We can not really prepend leading zeroes here, sadly!

Modulo 10 = (x && 1 ) too

Edge cases : 
*/

class Solution 
{
    public int maximumGap(int[] nums) 
    {
        int maxGap = Integer.MIN_VALUE;
        if(nums.length == 1) return 0;
        int maxDigit = findMaxNumDigits(nums);
        for(int i = 1; i <= maxDigit; ++i)
            executeCountSort(nums,i);
        for(int i = 0; i < nums.length - 1; ++i)
            maxGap = Math.max(maxGap, nums[i+1]-nums[i]);
        return maxGap;    
    }
    
    public void executeCountSort(int[] nums, int depth)
    {
        // [1] Iterate over array, and solve for each digit representation
        // Convert to own method in production code possibly?
        int[] results = new int[nums.length];
        int divisor = (int)Math.pow(10,depth-1);

        // [2] Allocate auxillary space for prefix sum couting
        // Guaranteed to be an array of length = 10
        // Generate the prefix array count
        int[] psa = new int[10];
        for(int i = 0; i < nums.length; ++i)
        {
            int key = ( nums[i] / divisor ) % 10;
            psa[key] += 1;
        }
        
        // [3] Perform prefix array portion of algorithm
        for(int i = 1; i < psa.length; ++i)
            psa[i] = psa[i] + psa[i-1];
        
        // [3] Iterate over initial range of values ( nums ) backwards and decremnt prefix array counts
        for(int i = nums.length - 1; i >= 0; --i)
        {
            int key = ( nums[i] / divisor) % 10;
            --psa[key];
            results[psa[key]] = nums[i];
        }        
        
        for(int i = 0; i < results.length; ++i)
            nums[i] = results[i];
    }
    
    public int findMaxNumDigits(int[] nums)
    {
        int numDig = Integer.MIN_VALUE;
        for(int el : nums)
            numDig = Math.max(numDig, countNumDigits(el));
        return numDig;        
    }
    
    public int countNumDigits(int x)
    {
        int count = 0;
        while(x >= 10)
        {
            x /= 10;
            ++count;
        }
        count += 1;
        return count;
    }
     
}
```
