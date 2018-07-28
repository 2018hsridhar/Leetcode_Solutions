// https://leetcode.com/problems/shuffle-an-array/

// ... so a recursive solution, just isn't accepted @ all ... now that's annoying!
public class Solution {
    int[] original;
    private Random random = null;

    public Solution(int[] nums)
    {
        original = nums;
        random = new Random(System.currentTimeMillis());
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        //shuffle_helper(curCopy.length - 1);
        int[] ans = Arrays.copyOf(original,original.length); // for array copying in java, use this from now on!
        if(ans.length <= 1)
            return ans;
        shuffle_helper(ans,ans.length-1);
        return ans;
    }
    
    private void shuffle_helper(int[] A, int i)
    {
        if(i == 0)
        {
            return;
        }
        else
        {
            shuffle_helper(A,i-1);
            int rand_num = randInt(i);
            swap(A, rand_num,i);
        }
    }
    
    private void swap(int[] A, int i, int j)
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    private int randInt(int v)
    {
        return (int) random.nextInt(v+1);
    }
    
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
