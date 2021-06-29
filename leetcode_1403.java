/*
1403. Minimum Subsequence in Non-Increasing Order
https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/

Is the subsequence part meant to dupe you?
Just sort -> that sorted part = thy subsequence anyways

Nums bounded by range [1,100] in the integers
Nums length bounded by [1,500] too

Return a subsequence of the array -> can erase (possibly zero ) els of the array
Answer guaranteed uniqueness AND guaranteed existence

Ideal [T,S] = [O(N), O(1)] one-pass linear scan, constant memory
If sort needed, ideal [T,S] = [O(NlgN), O(1)]

Singleton case - return directly
No need to handle any null inputs too

Weird edge cases : 
[4,4,4,4,4] - answer = [4,4,4] 
[4,4,4,4] - answer = [4,4,4] still

[1,1,1,1,1,1,1,...............1,100] : 1 repeated 0-99 times : still return [100] here
Repeated +100 times : start including 1 in our returns!
[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10]

Subsequence is easy to return - start from RHS pointer anyways!
2 pointers racing from each side of array : seems like an interesting partitioning problem


[1,7,4,7,1,9,4,8,8]
[3,4,8,9,9,10]
No zero-elements here : equality is relatively easy to work with! ( in event array len >= 2 )
The property of sort here guarantees convergen unto a solution, and guarantees a unique convergence too!
Funny edge cases : when LHS_sum >= RHS_sum : must decrement by one leftPointer value, to obtain solution ( the way the while loops were coded up indicate it ) 


*/

class Solution 
{
    public List<Integer> minSubsequence(int[] nums) 
    {
        List<Integer> minSubSeq = new ArrayList<Integer>();
        if(nums.length == 1) 
        {
            minSubSeq.add(nums[0]);
            return minSubSeq;
        }
        

        Arrays.sort(nums);
        int sum = 0;
            for(int i : nums) sum += i;
        
        int lPointer = 0;
        int rPointer = nums.length - 1;
        int LHS_sum = nums[lPointer];
        int RHS_sum = nums[rPointer];
        minSubSeq.add(nums[rPointer]);
        
        ++lPointer;
        --rPointer;
        while(lPointer <= rPointer)
        {
            if(RHS_sum < LHS_sum)
            {
                minSubSeq.add(nums[rPointer]);
                RHS_sum += nums[rPointer--];
            }
            else if ( LHS_sum <= RHS_sum)
                LHS_sum += nums[lPointer++];
        }
        
        // rPointer already adavnced here
        if(LHS_sum >= RHS_sum)
        {
            LHS_sum -= nums[--lPointer];
            minSubSeq.add(nums[rPointer]);
            RHS_sum += nums[rPointer];
        }
        
        return minSubSeq;
    }
}
