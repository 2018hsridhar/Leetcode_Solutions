/*

URL = https://leetcode.com/problems/wiggle-sort/
280. Wiggle Sort

TEST BENCH : 
(A) [1]
(B) [1,2]
(C) [1,2,3]
    [3,1,2] -> [1,3,2]
(D) [1,2,3,4]
(E) [1,2,3,4,5]
    [4,3,1,5,2] -> [3,4,1,2,5] : swp as we go?
(F) [1,2,3,4,5,6]
(G) [1,5,2,3,6,2,6,1,7,0,4,2,97,3]
(H) [2,4,1,67,1,3,8,22,34,863,12,98,34]

Input is ALWAYS guaranteed an answer as well

Complexity
Let N := len(input)
Time = O(N^2)
Space = O(1)

*/
class Solution 
{
    public void wiggleSort(int[] nums) 
    {
        // mergeSortSolution(nums);
        noSortSolution(nums);
    }
    
    // T = O(N^2), S = O(1)
    // mins for idxs = {0,2,4,...}
    // maxes for idxs = {1,3,5,...}
    private void noSortSolution(int[] A)
    {
        int n = A.length;
        for(int i = 0; i < n; ++i)
        {
            int el = A[i];
            int srcIdx = i;
            for(int j = i; j < n; ++j)
            {
                if(i % 2 == 0)
                {
                    if(A[j] < el)
                    {
                        el = Math.min(el,A[j]);
                        srcIdx = j;
                    }
                }
                else
                {
                    if(A[j] > el)
                    {
                        el = Math.max(el,A[j]);
                        srcIdx = j;
                    }
                }
            }
            swap(A,i,srcIdx);
        }
        
    }
    
    // T = O(NLogN), S = O(1)
    private void mergeSortSolution(int[] A)
    {
        Arrays.sort(A);
        int n = A.length;
        for(int i = 0; i < n-1; ++i)
        {
            if(i % 2 == 0)
            {
                if(A[i] > A[i+1])
                {
                    swap(A,i,i+1);
                }
            }
            else if ( i % 2 == 1)
            {
                if(A[i] < A[i+1])
                {
                    swap(A,i,i+1);
                }
            }
        }
    }
    
    private void swap(int[] A, int i, int j)
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

// [3,5,6,6,6,8]
// [3,8,5,6,6,6]
